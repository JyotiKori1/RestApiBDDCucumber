@auth
Feature: Generate duration based Auth Token
  This feature includes tests that test services using Auth Token

   Background: : Generate Service Auth Token
     Given Service Auth is up and running
     When The Auth Generate request is sent
     Then Token is generated
     And Get the Token Value

     Scenario Outline: Update Coffee Flavour
       Given New Coffee Flavour add request
       When Request is sent to update existing Coffee with CoffeeId as "<CoffeeId>"
       Then Coffee is updated successfully
       Examples:
       |CoffeeId|
       |'1'       |
       |'2'       |

