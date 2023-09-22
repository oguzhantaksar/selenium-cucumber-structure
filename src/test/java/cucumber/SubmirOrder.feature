
  @OrderTests
    Feature: Purchase order from Ecommerce site

      Background:
        Given I landed on Ecommerce page

        @tag2
        Scenario Outline: Positive Test of Submitting the order

          Given Logged in with username <username> and password <password>
          When I add product <productName> to cart
          And Checkout <productName> and submit the order
          Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

          Examples:
            | username                  | password  | productName |
            | oguzhantaksar@hotmail.com | Master25% | ZARA COAT 3 |
            | oguzhan+2@hotmail.com | Master25% | ADIDAS ORIGINAL |





        @tag2
        Scenario Outline:  Validate order from orders page

          Given Logged in with username <username> and password <password>
          When I navigate to orders page
          Then <productName> exists on the orders page

          Examples:
            | username                  | password  | productName |
            | oguzhantaksar@hotmail.com | Master25% | ZARA COAT 3 |
