Feature: View a customer record

  Background: We start on the Search page for now, need to move from here for all tests
    Given I go to the Search page

  Scenario Outline: As a user I want to view a customer
    Given I am on the Search page
      And I enter a name of <NAME>
     When I click the Search button
     Then I can choose to Filter by <date>

   Examples:
    |NAME     |date    |
    |Rincewind|1 Month |


  Scenario Outline: As a user I want to view a customer record 3 month
    Given I am on the Search page
      And I enter a name of <NAME>
     When I click the Search button
    Then I can choose to Filter by <date>

    Examples:
      |NAME     |date    |
      |Rincewind|1 Month |


  Scenario Outline: As a user I want to view a customer record all data
    Given I am on the Search page
      And I enter a name of <NAME>
     When I click the Search button
     Then I can choose to filter by <date>

    Examples:
      |NAME     |date    |
      |Rincewind|All     |

