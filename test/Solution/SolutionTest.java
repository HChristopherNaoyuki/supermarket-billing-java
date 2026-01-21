package Solution;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * JUnit 4 test class for the supermarket billing system (Solution.java)
 */
public class SolutionTest
{
    // We capture System.out to verify printed receipt and messages
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp()
    {
        // Redirect System.out so we can assert what was printed
        System.setOut(new PrintStream(outputCapture));
    }

    @After
    public void tearDown()
    {
        // Restore original System.out after each test
        System.setOut(originalOut);
        outputCapture.reset();
    }

    @Test
    public void testCartItemSubtotalCalculation()
    {
        Solution.CartItem item = new Solution.CartItem("Milk 2L", 24.99, 3);

        assertEquals(74.97, item.getSubtotal(), 0.001);
        assertEquals("Milk 2L", item.name);
        assertEquals(24.99, item.price, 0.001);
        assertEquals(3, item.quantity);
    }

    @Test
    public void testCartItemToStringFormatting()
    {
        Solution.CartItem item = new Solution.CartItem("Whole Chicken", 89.90, 2);

        String expected = "Whole Chicken           2 ×   89,90 =    179,80";
        String actual = item.toString();

        // We allow small differences in spacing (depending on locale thousand separator)
        assertTrue(actual.contains("Whole Chicken"));
        assertTrue(actual.contains("2"));
        assertTrue(actual.contains("89,90") || actual.contains("89.90"));
        assertTrue(actual.contains("179,80") || actual.contains("179.80"));
    }

    @Test
    public void testEmptyCartViewMessage()
    {
        ArrayList<Solution.CartItem> cart = new ArrayList<>();
        Solution.viewCart(cart);

        String output = outputCapture.toString();

        assertTrue(output.contains("Your cart is currently empty"));
    }

    @Test
    public void testViewCartWithItemsShowsCorrectTotals()
    {
        ArrayList<Solution.CartItem> cart = new ArrayList<>();
        cart.add(new Solution.CartItem("Bread", 18.50, 2));
        cart.add(new Solution.CartItem("Butter 500g", 42.99, 1));
        cart.add(new Solution.CartItem("Eggs 18", 54.90, 3));

        Solution.viewCart(cart);

        String output = outputCapture.toString();

        assertTrue(output.contains("CURRENT CART"));
        assertTrue(output.contains("Bread"));
        assertTrue(output.contains("Butter 500g"));
        assertTrue(output.contains("Eggs 18"));
        assertTrue(output.contains("37,00"));   // 18.50 × 2
        assertTrue(output.contains("42,99"));
        assertTrue(output.contains("164,70"));  // 54.90 × 3
        assertTrue(output.contains("Total before tax"));
    }

    @Test
    public void testReceiptCalculationAndFormattingWith15PercentTax()
    {
        ArrayList<Solution.CartItem> cart = new ArrayList<>();
        cart.add(new Solution.CartItem("Apples 1kg", 29.99, 2));
        cart.add(new Solution.CartItem("Orange Juice 2L", 38.50, 1));

        double taxRate = 0.15;
        Solution.printReceipt(cart, taxRate);

        String receipt = outputCapture.toString();

        // Basic content checks
        assertTrue(receipt.contains("YUKI'S SUPERMARKET RECEIPT"));
        assertTrue(receipt.contains("Apples 1kg"));
        assertTrue(receipt.contains("Orange Juice 2L"));
        assertTrue(receipt.contains("SUBTOTAL"));
        assertTrue(receipt.contains("VAT (15%)"));
        assertTrue(receipt.contains("GRAND TOTAL"));
        assertTrue(receipt.contains("Thank you! Come again!"));

        // Value checks (approximate - locale may affect comma/dot)
        assertTrue(receipt.contains("59,98") || receipt.contains("59.98"));     // 29.99 × 2
        assertTrue(receipt.contains("38,50") || receipt.contains("38.50"));
        assertTrue(receipt.contains("98,48") || receipt.contains("98.48"));     // subtotal
        assertTrue(receipt.contains("14,77") || receipt.contains("14.77"));     // 15% tax
        assertTrue(receipt.contains("113,25") || receipt.contains("113.25"));   // grand total
    }

    @Test
    public void testZeroTaxReceiptStillFormatsCorrectly()
    {
        ArrayList<Solution.CartItem> cart = new ArrayList<>();
        cart.add(new Solution.CartItem("Pasta 500g", 14.99, 5));

        Solution.printReceipt(cart, 0.0);

        String receipt = outputCapture.toString();

        assertTrue(receipt.contains("Pasta 500g"));
        assertTrue(receipt.contains("74,95"));      // 14.99 × 5
        assertTrue(receipt.contains("VAT (0%)") || receipt.contains("VAT"));
        assertTrue(receipt.contains("GRAND TOTAL") && receipt.contains("74,95"));
    }
}