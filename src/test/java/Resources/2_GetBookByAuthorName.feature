@regression
Feature: Get Book by Author name

  @sanity
  Scenario Outline: Get Book by Author name
    Given User requested to hit the application URL for get request
    When the User will hit the specific for get request "<endpoint>"
    Then We are going to validate the response body with specific "<status_code>" for get request

    #Then User Validates the response body
    Examples: 
      | endpoint             | status_code |
      | /Library/GetBook.php |         200 |

  @smoke
  Scenario Outline: Get Book by Author name with incorrect author name
    Given User requested to hit the application URL for get request with incorrect author name
    When the User will hit the specific for get request "<endpoint>"
    Then We are going to validate the response body with specific "<status_code>" for get request with incorrect author name

    Examples: 
      | endpoint             | status_code |
      | /Library/GetBook.php |         404 |

  @smoke
  Scenario Outline: Get Book by Author name with blank author name
    Given User requested to hit the application URL for get request with blank author name
    When the User will hit the specific for get request "<endpoint>"
    Then We are going to validate the response body with specific "<status_code>" for get request with blank author name

    Examples: 
      | endpoint             | status_code |
      | /Library/GetBook.php |         500 |
