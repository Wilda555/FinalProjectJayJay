Feature: Test Automation Rest API Tag Controller

  @api
  Scenario: Test Get List Data Tag Normal
    Given prepare url for "GET_LIST_TAGS"
    When hit api get list tags
    Then  validation status code api tag is equals 200
    Then  validation response body get list tags