# Supermarket Billing System - Java Console Application

A simple, console-based supermarket billing application written in Java.  
The program allows users to add items to a cart, view the cart contents,  
calculate totals with tax, and print a formatted receipt.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [How to Contribute](#how-to-contribute)
- [License](#license)
- [Disclaimer](#disclaimer)

## Overview

This project is a lightweight Java console application designed to simulate a  
basic point-of-sale (POS) receipt generator for a supermarket. Users interact  
with a text-based menu to build a shopping cart and generate a professional-  
looking receipt that includes item details, subtotal, VAT, and grand total.  
The application was created as an educational example and can serve as a  
foundation for more advanced billing systems.

## Features

- Menu-driven interface (add item, view cart, checkout, exit)  
- Input validation for prices and quantities (only positive numbers accepted)  
- Support for multiple items with quantity  
- Automatic calculation of subtotal, VAT (configurable rate), and grand total  
- Clean, aligned receipt output with proper formatting  
- Allman-style code formatting and detailed professional comments  

## Technologies

- Java (JDK 8 or higher recommended)  
- JUnit 4 (for unit tests)  
- Standard Java libraries only (no external dependencies)  

## Installation

1. Make sure you have Java Development Kit (JDK) installed.  
   You can check by running:  
   ```
   java -version
   ```

2. Clone the repository:  
   ```bash
   git clone https://github.com/HChristopherNaoyuki/supermarket-billing-java.git
   cd supermarket-billing-java
   ```

3. (Optional) If you want to run the unit tests, make sure JUnit 4 is available  
   in your environment.

## Usage

### Compile and run the application

```bash
# From the project root folder
javac src/Solution/Solution.java
java -cp src Solution.Solution
```

### Example workflow

1. Select option `1` → Add item to cart  
2. Enter item name, unit price, and quantity  
3. Repeat for more items  
4. Select option `2` → View current cart  
5. When finished, select option `3` → Checkout & print receipt  
6. The program displays a formatted receipt and exits  

### Example receipt output

```
=========================================
YUKI'S SUPERMARKET RECEIPT     
=========================================
Cashier: Self-service terminal
----------------------------------------
Item                        Qty   Price    Amount
----------------------------------------
Apples 1kg                 2 ×    29.99 =     59.98
Orange Juice 2L            1 ×    38.50 =     38.50
----------------------------------------
SUBTOTAL                   : R     98.48
VAT (15%)                  : R     14.77
----------------------------------------
GRAND TOTAL                : R    113.25
=========================================
          Thank you! Come again!          
=========================================
```

## Project Structure

```
supermarket-billing-java/
├── src/
│   └── Solution/
│       ├── Solution.java       # Main application logic
│       └── SolutionTest.java   # JUnit 4 unit tests
├── README.md
└── (recommended) images/       # Folder for screenshots and visual media
```

**Important note about images**  
No images or screenshots are included directly in this README file.  
All visual media must be placed in a dedicated folder such as `images/`,  
`screenshots/`, or `media/`.

## How to Contribute

1. Fork the repository  
2. Create a new branch (`git checkout -b feature/improvement-name`)  
3. Make your changes  
4. Commit your changes (`git commit -m 'Add some feature'`)  
5. Push to the branch (`git push origin feature/improvement-name`)  
6. Open a Pull Request  

Bug reports, feature suggestions, and documentation improvements are welcome.  
Please open an issue first to discuss major changes.

## License

This project is provided as-is for educational and personal use.  
See the [Disclaimer](#disclaimer) section below.

## Disclaimer

UNDER NO CIRCUMSTANCES SHOULD IMAGES OR EMOJIS BE INCLUDED DIRECTLY IN THE README  
FILE. ALL VISUAL MEDIA, INCLUDING SCREENSHOTS AND IMAGES OF THE APPLICATION,  
MUST BE STORED IN A DEDICATED FOLDER WITHIN THE PROJECT DIRECTORY. THIS FOLDER  
SHOULD BE CLEARLY STRUCTURED AND NAMED ACCORDINGLY TO INDICATE THAT IT CONTAINS  
ALL VISUAL CONTENT RELATED TO THE APPLICATION (FOR EXAMPLE, A FOLDER NAMED  
images, screenshots, OR media).  

I AM NOT LIABLE OR RESPONSIBLE FOR ANY MALFUNCTIONS, DEFECTS, OR ISSUES THAT  
MAY OCCUR AS A RESULT OF COPYING, MODIFYING, OR USING THIS SOFTWARE. IF YOU  
ENCOUNTER ANY PROBLEMS OR ERRORS, PLEASE DO NOT ATTEMPT TO FIX THEM SILENTLY  
OR OUTSIDE THE PROJECT. INSTEAD, KINDLY SUBMIT A PULL REQUEST OR OPEN AN ISSUE  
ON THE CORRESPONDING GITHUB REPOSITORY, SO THAT IT CAN BE ADDRESSED  
APPROPRIATELY BY THE MAINTAINERS OR CONTRIBUTORS.

---

End of Document

---
