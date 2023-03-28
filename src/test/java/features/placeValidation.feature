Feature: Validate Place's API

  @AddPlace @Regression
  Scenario Outline: Verify AddPlaceAPI
    Given Add Place Payload with "<name>" "<address>" "<latitude>" "<longitude>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then API call got success with status code 200
    And "status" in response body is "OK"
    And "place_id" in response body is generated
    Examples:
      |name         |address                    |latitude          |longitude      |
      |BishShop2    |29, BIS layout11, cohen 21 |   -38.469494     | 30.427362     |
      |BishShop34   |23, BIS Ghay, cohen 21     |   -28.469494     | 20.427362     |

  @GET @Regression
  Scenario Outline: Verify GetPlaceAPI
    Given Get Place Payload with place_id "<placeID>"
    When User calls "GetPlaceAPI" with "GET" http request
    Then API call got success with status code 200
    And "name" in response body is generated
    Examples:
      |placeID                                |
      | 6f04d045410fe213a08bf59e1c82943d      |
      | 94bb6b3981a79aabd8c1b6682e9801b6      |
  @Delete
  Scenario Outline: Verify DeletePlaceAPI
    Given Delete Place Payload with place_id "<placeID>"
    When User calls "DeletePlaceAPI" with "DELETE" http request
    Then API call got success with status code 200
    And "status" in response body is "OK"
    Examples:
      |placeID|
      | 4d93efe319339e30790ddba455d91cec      |
      | 5543b4af3d54f2e4ac504960178394a0      |

  @End2End @SMOKE
  Scenario Outline: Verify End2End API
    Given Add Place Payload with "<name>" "<address>" "<latitude>" "<longitude>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then API call got success with status code 200
    And "status" in response body is "OK"
    And "place_id" in response body is generated
    Given Get Place Payload with place_id ""
    When User calls "GetPlaceAPI" with "GET" http request
    Then API call got success with status code 200
    And "name" in response body is "<name>"
    Given Delete Place Payload with place_id ""
    When User calls "DeletePlaceAPI" with "DELETE" http request
    Then API call got success with status code 200
    And "status" in response body is "OK"
    Given Get Place Payload with place_id ""
    When User calls "GetPlaceAPI" with "GET" http request
    Then API call got success with status code 404
    And "msg" in response body is "Get operation failed, looks like place_id  doesn't exists"

    Examples:
      |name          |address                   |latitude          |longitude       |
      |BishQws1      |29, BIS layout11, ewan 21 |   -38.469494     | 30.427362     |
      |BishQsw13     |23, BIS Ghay, dae 21      |  -28.469494      | 20.427362     |