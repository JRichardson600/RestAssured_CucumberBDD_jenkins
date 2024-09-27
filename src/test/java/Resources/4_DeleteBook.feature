@regression
Feature: Delete book to the library API

  
      
  @sanity
  Scenario Outline: Delete book to the library API with incorrect data
    Given User requested to hit the application URL for Delete request
    And User will pass the payload with all details required for Delete request
    When the User will hit the specific for Delete request "<endpoint>"
    Then We are going to validate the response body with specific "<status_code>" for Delete request

    Examples: 
      | endpoint                | status_code |
      | /Library/DeleteBook.php |         404 |

  
@smoke_x
  Scenario Outline: Delete book to the library API with incorrect data
    Given User requested to hit the application URL for Delete request
    And User will pass the payload with all details required for Delete request with incorrect data
    When the User will hit the specific for Delete request "<endpoint>" with correct data
    Then We are going to validate the response body with specific "<status_code>" for Delete request with correct data

    Examples: 
      | endpoint                | status_code |
      | /Library/DeleteBook.php |         200 |