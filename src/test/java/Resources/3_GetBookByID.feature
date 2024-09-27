@regression
Feature: Get Book by ID

  @sanity
  Scenario Outline: Get Book by ID
    Given User requested to hit the application URL for get request by ID
    When the User will hit the specific for get request "<endpoint>" by ID
    Then We are going to validate the response body with specific "<status_code>" for get request by ID

    #Then User Validates the response body
    Examples: 
      | endpoint             | status_code |
      | /Library/GetBook.php |         200 |

      
        @sanity
  Scenario Outline: Get Book by ID with incorrect ID
    Given User requested to hit the application URL for get request by ID with incorrect ID
    When the User will hit the specific for get request "<endpoint>" by ID
    Then We are going to validate the response body with specific "<status_code>" for get request with incorrect ID

    #Then User Validates the response body
    Examples: 
      | endpoint             | status_code |
      | /Library/GetBook.php |         404 |
      
      
    Scenario Outline: Get Book by ID with blank ID
    Given User requested to hit the application URL for get request by ID with blank ID
    When the User will hit the specific for get request "<endpoint>" by ID
    Then We are going to validate the response body with specific "<status_code>" for get request with blank ID

    #Then User Validates the response body
    Examples: 
      | endpoint             | status_code |
      | /Library/GetBook.php |         500 |
      