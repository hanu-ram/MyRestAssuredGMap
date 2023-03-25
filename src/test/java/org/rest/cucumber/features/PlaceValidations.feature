Feature: Vaidating Place API's
@AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using addPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls the "ADDPLACEAPI" with "post" http request
    Then API call should got success with status code 200
    And In response the "status" contains "OK"
    And verify place_id created matched to "<name>" using "GETPLACEAPI"
    Examples:
      | name          | language | address                          |
      | hanu-paradise | Kanada   | electronic city,Bangalore, India |
      | ram-paradise  | Telugu   | Gachibowli, Hyderabad, India     |
@DeletePlace @Regression
  Scenario: Verify whether the place is deleted
  Given delete payload is given
  When user calls the "DELETEPLACEAPI" with "delete" http request
  Then API call should got success with status code 200
  And In response the "status" contains "OK"
