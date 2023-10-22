@FirstRelease
Feature: Login functionality

  @Positive
  Scenario Outline: Ensure user successfully login
    # precondition
    Given user on the Saucedemo login page
    # steps
    When user input <username> as username
    And user input <password> as password
    And user click enter
    # expected
    Then user verify <status> login result

    Examples: 
      | username      | password     | status     |
      | standard_user | secret_sauce | successful |

  @Negative
  Scenario Outline: Ensure user unsuccessfully login
    # precondition
    Given user on the Saucedemo login page
    # steps
    When user input <username> as username
    And user input <password> as password
    And user click enter
    # expected
    Then user verify <status> login result

    Examples: 
      | username     | password     | status       |
      | user_standar | saus_rahasia | unsuccessful |
