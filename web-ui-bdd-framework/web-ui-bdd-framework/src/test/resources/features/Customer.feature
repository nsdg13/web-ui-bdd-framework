Feature: Customer module verification
  This feature will verify Customer functionalities
  
  
  @MyPurnaTest
  Scenario Outline: Verify that user is able to create the Customer
  Given User is on Purna login page "<TestCase_ID>" "Customer"
  When User on Login Page enters valid username and password
  And User clicks on Login button
  When User clicks on Main menu
  When User clicks on Customer link
  When User clicks clicks on New customer button
  When User enters the customer details
  |123321|Sachin|9898976556|Pune|GST123|
  When User clicks on Save button
  When User click on Ok button on popup
  When User clicks on Customer details button
  Then User verfies that customer is created
  
  Examples:
      | TestCase_ID |
      | Purna_TC_001|
  
  
