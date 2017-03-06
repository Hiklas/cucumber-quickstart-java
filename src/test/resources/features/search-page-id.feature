Feature: Search for a customer using their ID
  As an admin user of the site
  I want to be able to search for users using their ID
  In order to find their earnings information

  Background:
    Given I go to the Search page


  Scenario Outline: As a user I want to search for a record using an ID
    Given I am on the Search page
      And I enter a ID of <ID>
     When I click the Search button
      Then I am able to see Filters by Timeframe

    Examples:
    |ID       |
    |1234567  |


  Scenario Outline: As a user I enter an invalid ID
    Given I am on the Search page
    Given I enter a ID of <ID>
     When I click the Search button
     Then I am shown an Error Message informing me I need to <ERROR>
      And I am unable to see Filters by Timeframe

    Examples:
      |ID       | ERROR                              |
      |wibble   | Enter the ID in the Correct Format |

