
  @NegativeTests
    Feature: Getting errors

      Background:
        Given I landed on Ecommerce page

        @tag2
        Scenario Outline: Logining with invalid username and password

          When Logged in with username <username> and password <password>
          Then "Incorrect email or password." error is displayed on LandingPage

          Examples:
            | username                | password  |  |
            | oguzntaksar@hotmail.com | Master25% |  |

        @tag2
        Scenario Outline: Validate that product does not exists

          When Logged in with username <username> and password <password>
          And I add product <productName> to cart

          Then <negativeProductName> does not exists in cart


          Examples:
            | username                   | password  | productName | negativeProductName |
            | oguzhantaksar2@hotmail.com | Master25% | ZARA COAT 3 | ZARA COAT 2         |