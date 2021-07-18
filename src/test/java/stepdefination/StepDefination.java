package stepdefination;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.homeloancalculator.driverfactory.DriverFactory;
import com.qa.homeloancalculator.page.HomeLoanCalculatorsPage;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination extends DriverFactory {

	HomeLoanCalculatorsPage hlcp;
	
	@Given("^User is on Calculator page$")
	public void user_is_on_Calculator_page() {
		prop = configProp();
		driver = init_driver(prop);
		hlcp = new HomeLoanCalculatorsPage(driver);
	}

	@When("^User enters data in Your Details section for (.+), (.+) and (.+)$")
    public void user_enters_data_in_your_details_section_for_and(String applicationtype, String numberofdependents, String propertyType) throws Throwable {
		try {
			hlcp.enterYourDetails(applicationtype,numberofdependents,propertyType);
			Assert.assertTrue(hlcp.isSingleApplicationTypeSelected());
			Assert.assertTrue(hlcp.isHomeToLiveInSelected());
		} catch (Exception e) {
			Assert.fail("Failed:User enters data in Your Details section for "+ applicationtype + numberofdependents + propertyType);
		}
    }

	@Then("^Verify the estimate customer could borrow (.+)$")
	public void verify_the_estimate_customer_could_borrow(String borrowexpected) throws Throwable {
		try {
			hlcp.assertBorrowAmount(borrowexpected);
		} catch (Exception e) {
			Assert.fail("Failed:Verify the estimate customer could borrow "+borrowexpected);
		}
    }

    @And("^User enters data in Your Earning section for (.+) and (.+)$")
    public void user_enters_data_in_your_earning_section_for_and(String yourearning, String yourotherincome) throws Throwable {
    	try {
    		hlcp.enterYourEarnings(yourearning, yourotherincome);
		} catch (Exception e) {
			Assert.fail("Failed:User enters data in Your Earning section for "+yourearning+" and "+yourotherincome);
		}
    }

    @And("^User enters data in Your Expenses section for (.+), (.+), (.+), (.+) and (.+)$")
    public void user_enters_data_in_your_expenses_section_for_and(String livingexpenses, String currenthomeloanrepayements, String otherloanrepayements, String othercommitments, String totalcreditcardlimits) throws Throwable {
    	try {
    		hlcp.enterYourExpenses(livingexpenses, currenthomeloanrepayements, otherloanrepayements, othercommitments, totalcreditcardlimits);
		} catch (Exception e) {
			Assert.fail("Failed:User enters data in Your Expenses section for"+ livingexpenses + currenthomeloanrepayements + otherloanrepayements + othercommitments + totalcreditcardlimits );
		}
    }

    @And("^Click on Work out how much I could borrow button$")
    public void click_on_work_out_how_much_i_could_borrow_button() throws Throwable {
    	try {
    		hlcp.clickBtnBorrowCalculator();
		} catch (Exception e) {
			Assert.fail("Failed:Click on Work out how much I could borrow button");
		}
    }
    
    @After
    public void close_browser() {    	
        driver.close();
    }
    
    @AfterStep
	public void addScreenshot(Scenario scenario){

	      final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	      scenario.attach(screenshot, "image/png", scenario.getStatus().toString()); 
		
	}

    @Then("^the form is cleared and fields retain default values$")
    public void the_form_is_cleared_and_fields_retain_default_values() {
    	try {
    		hlcp.validateTheDefaults();
		} catch (Exception e) {
			Assert.fail("Failed:the form is cleared and fields retain default values");
		}
    }

    @And("^Enter value in Living Expenses as it required \"([^\"]*)\"$")
    public void enter_value_in_living_expenses_as_it_required_something(String strArg1) throws Throwable {
    	try {
    		hlcp.enterLivingExpenses(strArg1);
		} catch (Exception e) {
			Assert.fail("Failed:Enter value in Living Expenses as it required"+strArg1);
		}
    }
    
    @When("User enters data only in Your Earning section for {string} and {string} and leaves Your Details with default values")
    public void user_enters_data_only_in_your_earning_section_for_and_and_leaves_your_details_with_default_values(String string, String string2) throws Throwable {
    	try {
    		hlcp.enterYourEarnings(string, string2);
		} catch (Exception e) {
			Assert.fail("Failed:User enters data only in Your Earning section for "+string+" and "+string2+" and leaves Your Details with default values");
		}
    }
    @When("Click on Start Over icon")
    public void click_on_start_over_icon() {
    	try {
    		hlcp.clickOnStartOver();
		} catch (Exception e) {
			Assert.fail("Failed:Click on Start Over icon");
		}
    }
    
    @When("User enters only ${string} for Living expenses and leaving all other fields as zero")
    public void user_enters_only_$_for_living_expenses_and_leaving_all_other_fields_as_zero(String string) {
    	try {
    		hlcp.enterLivingExpenses(string);
		} catch (Exception e) {
			Assert.fail("Failed:User enters only "+string+" for Living expenses and leaving all other fields as zero");
		}
    }

    @Then("^Validate the error message$")
    public void validate_the_error_message() {
        Assert.assertTrue(hlcp.borrowErrorTextDisplayed());
    }
    
}