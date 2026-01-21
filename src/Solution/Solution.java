package Solution;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Simple console-based supermarket billing system.
 * Allows user to:
 *   - Add items with name and price
 *   - View current cart
 *   - Finalize purchase and print receipt
 *   - Calculate total with optional tax
 */
public class Solution
{
    // Inner class to represent one item in the shopping cart
    static class CartItem
    {
        String name;
        double price;
        int quantity;

        public CartItem(String name, double price, int quantity)
        {
            this.name = name.trim();
            this.price = price;
            this.quantity = quantity;
        }

        public double getSubtotal()
        {
            return price * quantity;
        }

        @Override
        public String toString()
        {
            return String.format("%-22s %4d × %,8.2f = %,10.2f",
                    name, quantity, price, getSubtotal());
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();

        final double TAX_RATE = 0.15;           // 15% VAT (example - South Africa common rate)
        boolean shopping = true;

        printWelcomeMessage();

        while (shopping)
        {
            displayMenu();
            int choice = getMenuChoice(scanner);

            switch (choice)
            {
                case 1:
                    addItem(scanner, cart);
                    break;

                case 2:
                    viewCart(cart);
                    break;

                case 3:
                    if (cart.isEmpty())
                    {
                        System.out.println("Cart is empty. Cannot generate receipt.");
                    }
                    else
                    {
                        printReceipt(cart, TAX_RATE);
                        shopping = false;
                    }
                    break;

                case 4:
                    System.out.println("Thank you for shopping with us. Goodbye!");
                    shopping = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printWelcomeMessage()
    {
        System.out.println("=====================================");
        System.out.println("   WELCOME TO NAOYUKI SUPERMARKET    ");
        System.out.println("=====================================");
        System.out.println("Date: January 21, 2026");
        System.out.println();
    }

    private static void displayMenu()
    {
        System.out.println("Options:");
        System.out.println("  1. Add item to cart");
        System.out.println("  2. View current cart");
        System.out.println("  3. Checkout & print receipt");
        System.out.println("  4. Exit (discard cart)");
        System.out.print("Enter choice (1-4): ");
    }

    private static int getMenuChoice(Scanner scanner)
    {
        while (true)
        {
            try
            {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (choice >= 1 && choice <= 4)
                {
                    return choice;
                }
                else
                {
                    System.out.print("Please enter a number between 1 and 4: ");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    private static void addItem(Scanner scanner, ArrayList<CartItem> cart)
    {
        System.out.print("Enter item name     : ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty())
        {
            System.out.println("Item name cannot be empty.");
            return;
        }

        double price = getPositiveDouble(scanner, "Enter unit price (R) : ");
        int quantity = getPositiveInteger(scanner, "Enter quantity       : ");

        CartItem item = new CartItem(name, price, quantity);
        cart.add(item);

        System.out.printf("Added: %d × %s @ R%.2f each%n", quantity, name, price);
    }

    private static double getPositiveDouble(Scanner scanner, String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            try
            {
                double value = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                if (value > 0)
                {
                    return value;
                }
                else
                {
                    System.out.println("Price must be greater than zero.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static int getPositiveInteger(Scanner scanner, String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            try
            {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value > 0)
                {
                    return value;
                }
                else
                {
                    System.out.println("Quantity must be at least 1.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid whole number.");
                scanner.nextLine();
            }
        }
    }

    static void viewCart(ArrayList<CartItem> cart)
    {
        if (cart.isEmpty())
        {
            System.out.println("Your cart is currently empty.");
            return;
        }

        System.out.println("CURRENT CART");
        System.out.println("---------------------------------------------");
        double totalBeforeTax = 0;

        for (int i = 0; i < cart.size(); i++)
        {
            CartItem item = cart.get(i);
            System.out.printf("%2d. %s%n", i + 1, item);
            totalBeforeTax += item.getSubtotal();
        }

        System.out.println("---------------------------------------------");
        System.out.printf("Total before tax   : R%,10.2f%n", totalBeforeTax);
        System.out.println();
    }

    static void printReceipt(ArrayList<CartItem> cart, double taxRate)
    {
        double subtotal = 0;
        double tax = 0;
        double grandTotal = 0;

        System.out.println("=========================================");
        System.out.println("         NAOYUKI SUPERMARKET RECEIPT     ");
        System.out.println("=========================================");
        System.out.println("Date: January 21, 2026");
        System.out.println("Cashier: Self-service terminal");
        System.out.println("----------------------------------------");
        System.out.println("Item                        Qty   Price    Amount");
        System.out.println("----------------------------------------");

        for (CartItem item : cart)
        {
            System.out.println(item);
            subtotal += item.getSubtotal();
        }

        tax = subtotal * taxRate;
        grandTotal = subtotal + tax;

        System.out.println("----------------------------------------");
        System.out.printf("SUBTOTAL                   : R%,10.2f%n", subtotal);
        System.out.printf("VAT (%.0f%%)                : R%,10.2f%n", taxRate * 100, tax);
        System.out.println("----------------------------------------");
        System.out.printf("GRAND TOTAL                : R%,10.2f%n", grandTotal);
        System.out.println("=========================================");
        System.out.println("          Thank you! Come again!          ");
        System.out.println("=========================================");
    }
}