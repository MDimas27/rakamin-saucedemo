@FirstRelease
Feature: Shopping Cart functionality

  Background: 
    Given user on the Saucedemo login page
    When user input standard_user as username
    And user input secret_sauce as password
    And user click enter
    Then user verify successful login result

  @Positive
  Scenario: Add a product to the cart
    # precondition
    Given I am on the Saucedemo products page
    # steps
    When I add a product with the name "Sauce Labs Backpack" to the cart
    # expected
    Then I should see the product in the cart

  @Positive
  Scenario: Remove a product from the cart
    # precondition
    Given I am on the Saucedemo products page
    # steps
    And I have a product "Sauce Labs Backpack" in my cart
    When I remove the product from the cart
    # expected
    Then I should not see the product in the cart
