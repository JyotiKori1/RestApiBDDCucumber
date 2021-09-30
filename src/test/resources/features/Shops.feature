@regression
Feature: Coffee Shops Tests
  This feature includes tests that test the Shops RESTFul services


  @smokeTest
Scenario: Get all the existing shops
  Given Coffee shop is up and running
  Then All the existing shops are found


Scenario: Create new Coffee Shop
  Given Valid coffee shop request
  When I send request to create coffee shop
  Then shop is created successfully with valid status code
  And shop is created with values passed in the request


  Scenario: Create new Coffee Shop without optional field
  Given Valid coffee shop request wihout Id attribute
  When I send request to create coffee shop
  Then shop is created successfully with valid status code

 Scenario: Search for the existing shops
    Given Coffee shop is up and running
    When The specific shop is Creaed
    Then The shop can be located by its id

 Scenario: Delete the existing shops
   Given Coffee shop is up and running
   When The specific shop is Creaed
   Then The shop can be deleted by its id

   Scenario: Generate Service Auth Token
     Given Service Auth is up and running
     When The Auth Generate request is sent
     Then Token is generated

