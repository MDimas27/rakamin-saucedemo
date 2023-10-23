@FirstRelease
Feature: Checkout functionality

   Background: Login to the application
    Given user on the Saucedemo login page
    When user input standard_user as username
    And user input secret_sauce as password
    And user click enter

  Scenario Outline: Checkout by Filling Out the Form with Valid Information
    When I add a product with the name "Sauce Labs Backpack" to the cart
    When User clicks the cart badge
    And User is on the Cart page
    And User clicks the Checkout button
    And User fills out the checkout form with "<first_name>", "<last_name>", and "<zip_code>"
    And User clicks the Continue button
    Then User should be on the Checkout Overview page
    And User verifies the details in the overview
    And User completes the checkout process
    And User sees the order confirmation message

    Examples: 
      | first_name | last_name | zip_code |
      | John       | Doe       |    12345 |

  Scenario: Cancel Checkout
  	When I add a product with the name "Sauce Labs Backpack" to the cart
    When User clicks the cart badge
    And User is on the Cart page
    And User clicks the Checkout button
    And User decides to cancel the checkout process
    Then User is redirected back to the shopping cart page
