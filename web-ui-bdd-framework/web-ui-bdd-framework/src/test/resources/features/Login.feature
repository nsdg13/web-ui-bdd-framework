Feature: Login Verification
  This feature will verify login validations for P

  @PurnaTest123
  Scenario Outline: Login Scenario one
    Given User is on Purna login page "<TestCase_ID>" "Login"
    When User on Login Page enters valid username and password
    And User clicks on Login button
    Then User should navigates to sales dashboard page
    
    Examples:
      | TestCase_ID |
      | Purna_TC_001|
      
   
 Scenario Outline: Verify create customer functionality
 
