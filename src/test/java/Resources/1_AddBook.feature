@regression
Feature: Add book to the library API

@sanity_y
  Scenario Outline: Add book to the library API with correct data
    Given User requested to hit the application URL
    And User will pass the payload with all details required
    When the User will hit the specific "<endpoint>"
    Then We are going to validate the response body with specific "<status_code>"
    Then User Validates the response body

    Examples: 
      | endpoint            | status_code |
      | Library/Addbook.php |         200 |

 @smoke    
    Scenario Outline: Add book to the library API with incorrect data
    Given User requested to hit the application URL
    And User will pass the payload with incomplete details required
    When the User will hit the specific "<endpoint>"
    Then We are going to validate the response body with specific "<status_code>"
    Then User Validates the response body with incorrect data

    Examples: 
      | endpoint            | status_code |
      | Library/Addbook.php |         200 |
      
 @smoke    
    Scenario Outline: Add book to the library API with already existing data
    Given User requested to hit the application URL
    And User will pass the payload with existing data
    When the User will hit the specific "<endpoint>"
    Then We are going to validate the response body with specific "<status_code>"
    Then User Validates the response body with existing data

    Examples: 
      | endpoint            | status_code |
      | Library/Addbook.php |         200 |