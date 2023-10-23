@FirstRelease
Feature: Logout functionality

  @Positive
  Scenario: Logout after successful login
    Given user on the Saucedemo login page
    When user input standard_user as username
    And user input secret_sauce as password
    And user click enter
    And user should be on the inventory page
    When user clicks the logout button
    Then user should be logged out and on the login page
