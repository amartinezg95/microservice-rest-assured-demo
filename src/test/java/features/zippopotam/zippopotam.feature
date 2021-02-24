Feature: Validate get http://api.zippopotam.us/ endpoint

  Background:
    Given the request time must be lower than 2 "SECONDS"

  Scenario Outline: search by country and post code
    Given the result in case the response is correct will be a list with places
    When launch get request for the country <country> y and post code <postCode>
    Then El resultado tiene informados los campos
      | country  |
      | places   |
      | postCode |
    Then the http result is 200
    And the value returned in the json path "country" is "United States"
    And the result meets the requirement
    And the value of the list in the json path "places.state" is "California"
    Examples:
      | country | postCode |
      | "us"    | "90210"  |
      | "us"    | "90212"  |