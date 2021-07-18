@HomeLoanCalculatorPage
Feature: Get a quick estimate on how much you may be able to borrow based on your current income and existing financial commitments.

Background: User navigates to Calculator page
Given User is on Calculator page

@Regression
Scenario Outline: Test Scenario to get a quick estimate on how much you may be able to borrow based on your current income and existing financial commitments.
When User enters data in Your Details section for <applicationType>, <numberOfDependents> and <propertyType>
And User enters data in Your Earning section for <yourEarning> and <yourOtherIncome>
And User enters data in Your Expenses section for <livingExpenses>, <currentHomeLoanRepayements>, <otherLoanRepayements>, <otherCommitments> and <totalCreditCardLimits>
And Click on Work out how much I could borrow button
Then Verify the estimate customer could borrow <borrowExpected>

Examples:
|applicationType|numberOfDependents|propertyType|yourEarning|yourOtherIncome|livingExpenses|currentHomeLoanRepayements|otherLoanRepayements|otherCommitments|totalCreditCardLimits|borrowExpected|
|single|0|home|80,000|10,000|500|0|100|0|10,000|507,000|

@Smoke
Scenario: Perform functional testing for Start Over button which clears form and validate that all fields retain default values
When User enters data only in Your Earning section for "20000" and "5000" and leaves Your Details with default values
And Enter value in Living Expenses as it required "1000"
And Click on Work out how much I could borrow button
And Click on Start Over icon
Then the form is cleared and fields retain default values

@Smoke
Scenario: Entering only $1 for Living expenses, and leaving all other fields as zero, clicking ‘Work out how much I could borrow’ returns the error message
When User enters only $"1" for Living expenses and leaving all other fields as zero
And Click on Work out how much I could borrow button
Then Validate the error message