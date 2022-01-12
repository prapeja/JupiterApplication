# JupitorApplication

Test Scripts for this project meet below considerations,

Test Automation Tool: Selenium with Java
Framework Used to design scripts: Maven - Page Object Model with TestNG framework
Integrated to GIT and used to run using Jenkins Command Line Interface

This framework supports multiple browsers execution even on headless mode, but as of now targeted to Google Chrome only.

Below Testcases are automated:

Test case 1:

From the home page go to contact page
Click submit button
Validate errors
Populate mandatory fields
Validate errors are gone
Test case 2:

From the home page go to contact page
Populate mandatory fields
Click submit button
Validate successful submission message
Note: Run this test 5 times to ensure 100% pass rate
Test case 3:

From the home page go to shop page
Click buy button 2 times on “Funny Cow”
Click buy button 1 time on “Fluffy Bunny”
Click the cart menu
Verify the items are in the cart
Test case 4: Advanced

Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
Go to the cart page
Verify the price for each product
Verify that each product’s sub total = product price * quantity
Verify that total = sum(sub totals)
