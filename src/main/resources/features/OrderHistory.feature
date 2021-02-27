Feature: ECommerce Testing

  Scenario: Order T-shirt
    Given User in login page
    When user successfully logged in with "testing.for.qualitest@gmail.com" and "Test@123"
    Then home page should displayed
    Then search and add product to cart
    Then checkout the cart
    Then validate Order in Orders History

  Scenario: Update User Name
    Given User in login page
    When user successfully logged in with "testing.for.qualitest@gmail.com" and "Test@123"
    Then home page should displayed
    Then click on personal information
    Then edit user name "Tester" with password "Test@123"
    Then Save changes
    Then validate changes "Tester"

