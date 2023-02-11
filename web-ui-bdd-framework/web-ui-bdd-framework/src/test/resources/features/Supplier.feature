Feature: Verify Supplier module
	Verify the Supplier functionalities for Purna application
	

@PurnaTest11
Scenario Outline: Verify that user is able to create supplier
Given User is logged into Purna application "<TestCase_ID>" "Login"
When User clicks on Main Menu
When User clicks on supplier link
When User clicks on New Supplier button
When User enters below supplier details
|John|9876543211|London|john@gmail.com|Matt|GST123|CULCN101R|
When User clicks on supplier Save button
When User clicks on Supplier details button
Then Supplier should be created

Examples:
      | TestCase_ID |
      | Purna_TC_001|





