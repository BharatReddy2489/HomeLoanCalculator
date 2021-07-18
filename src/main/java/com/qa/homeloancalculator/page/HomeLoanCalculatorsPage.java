package com.qa.homeloancalculator.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.homeloancalculator.ElementsUtil.ElementsUtil;

public class HomeLoanCalculatorsPage extends ElementsUtil{

	private By applicationTypeSingle = By.id("application_type_single");
	private By applicationTypeJoint = By.id("application_type_joint");
	private By numberOfDependents = By.cssSelector("select[title='Number of dependants']");
	private By homeToLiveIn = By.id("borrow_type_home");
	private By residentialInvestment = By.id("borrow_type_investment");
	private By yourIncome = By.cssSelector("input[aria-describedby='q2q1i1 q2q1i2']");
	private By yourOtherIncome = By.cssSelector("input[aria-labelledby='q2q2']");
	private By livingExpenses = By.id("expenses");
	private By currentHomeLoanRePayement = By.id("homeloans");
	private By otherLoanRePayments = By.id("otherloans");
	private By otherCommitments = By.cssSelector("input[aria-labelledby='q3q4']");
	private By totalCreditCardLimits = By.cssSelector("input[aria-labelledby='q3q5']");
	private By btnBorrowCalculator = By.id("btnBorrowCalculater");
	private By borrowAmount = By.id("borrowResultTextAmount");
	private By iconrestart = By.className("start-over");
	private By borrowErrorText = By.className("borrow__error__text");

	public HomeLoanCalculatorsPage(WebDriver driver) {
		super(driver);
	}

	public void enterYourDetails(String applicationType, String numOfDependents, String propertyType) throws Exception {
		waitForIsClickable(applicationTypeSingle,20);
		if(applicationType.toLowerCase().equals("single")) {
				selectWebElementsByJS(applicationTypeSingle);
		} else {
				selectWebElementsByJS(applicationTypeJoint);
		}
		selectByVisibleText(numberOfDependents, "0");
		if(propertyType.toLowerCase().equals("home")) {
			selectWebElementsByJS(homeToLiveIn);
	} else {
			selectWebElementsByJS(residentialInvestment);
	}
	}

	public void enterYourEarnings(String Income, String otherIncome) throws Exception {
		waitForIsClickable(yourIncome,30);
		find(yourIncome).sendKeys(Income);
		find(yourOtherIncome).sendKeys(otherIncome);
	}

	public void enterYourExpenses(String livingExp, String currHomeLoanRp, String otherLoanRp, String otherCmts,
			String totalCreditCardLimit) throws Exception {
		waitForIsClickable(livingExpenses,20);
		find(livingExpenses).sendKeys(livingExp);
		find(currentHomeLoanRePayement).sendKeys(currHomeLoanRp);
		find(otherLoanRePayments).sendKeys(otherLoanRp);
		find(otherCommitments).sendKeys(otherCmts);
		find(totalCreditCardLimits).sendKeys(totalCreditCardLimit);
	}
	
	public void enterLivingExpenses(String livingExp) throws Exception {
		waitForIsClickable(livingExpenses,20);
		find(livingExpenses).sendKeys(livingExp);
	}

	public boolean isSingleApplicationTypeSelected() {
		return isSelected(applicationTypeSingle);
	}

	public String numberOfDependentsSelected() {
		Select select = new Select(find(numberOfDependents));
		WebElement option = select.getFirstSelectedOption();
		return option.getText();
	}

	public boolean isHomeToLiveInSelected() {
		return find(homeToLiveIn).isSelected();
	}

	public String getYourIncome() {
		return getTextFromWebElementsByJS(yourIncome);
	}

	public String getYourOtherIncome() {
		return getTextFromWebElementsByJS(yourOtherIncome);
	}

	public String getLivingExpenses() {
		return getTextFromWebElementsByJS(livingExpenses);
	}

	public String getCurrentHomeLoanRepayements() {
		return getTextFromWebElementsByJS(currentHomeLoanRePayement);
	}

	public String getOtherLoanRepayements() {
		return getTextFromWebElementsByJS(otherLoanRePayments);
	}

	public String getOtherCommitments() {
		return getTextFromWebElementsByJS(otherCommitments);
	}

	public String getTotalCreditCardLimits() {
		return getTextFromWebElementsByJS(totalCreditCardLimits);
	}

	public boolean btnBorrowCalculatorIsDisplayed() {
		return find(btnBorrowCalculator).isDisplayed();
	}

	public void clickOnStartOver() {
		find(iconrestart).click();
	}

	public boolean borrowErrorTextDisplayed() {
		return find(borrowErrorText).isDisplayed();
	}
	
	public void clickBtnBorrowCalculator() {
		find(btnBorrowCalculator).click();
	}
	
	public String getBorrowAmount() throws Exception {
		waitForIsDisplayed(borrowAmount, 20);
		return getTextFromWebElementsByJS(borrowAmount);
	}
	
	public void assertBorrowAmount(String expectedBorrowAmount) throws Exception {
		waitForIsDisplayed(borrowAmount, 20);
			Assert.assertEquals(expectedBorrowAmount,getBorrowAmount());
	}
	
	public String getBorrowErrorText() throws Exception {
		waitForIsDisplayed(borrowErrorText, 20);
		return getTextFromWebElementsByJS(borrowErrorText);
	}
	
	public void validateTheDefaults() throws Exception {
		
		waitForIsDisplayed(applicationTypeSingle,20);
		//Validate Application Type default value
		Assert.assertTrue(isSingleApplicationTypeSelected());			

		//Validate Number of Dependents value
		Assert.assertEquals("0", numberOfDependentsSelected());
		
		//Validate Borrow Home Type default value
		Assert.assertTrue(isHomeToLiveInSelected());
		
		//Validate your Earnings default values
		Assert.assertEquals("0",getYourIncome());
		Assert.assertEquals("0", getYourOtherIncome());
		
		//Validate your Expenses default values
		Assert.assertEquals("0", getLivingExpenses());
		Assert.assertEquals("0", getCurrentHomeLoanRepayements());
		Assert.assertEquals("0", getOtherLoanRepayements());
		Assert.assertEquals("0", getOtherCommitments());
		Assert.assertEquals("0", getTotalCreditCardLimits());
		
		//Validate Borrow Calculator button
		Assert.assertTrue(btnBorrowCalculatorIsDisplayed());
		
		
	}
}
