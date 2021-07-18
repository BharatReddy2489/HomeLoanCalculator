package com.qa.homeloancalculator.ElementsUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsUtil {
	
	private WebDriver driver;
	private static final Integer TIME_FIVE_SECONDS = 5;

	
	public ElementsUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
     * Finds the element in the browser by locator passed in as an argument and then
     * returns enabled status of the {@link WebElement}
     *
     * @param locator {@link By} which locates elements by the value of the "id",
     *        "css", "xpath" attributes.
     * @return True if the element is enabled, false otherwise.
     */
	
	protected final WebElement find(By locator) {
        WebElement webElement;
        try {
             webElement = driver.findElement(locator);
        }
        catch(Exception e) {
            webElement = null;
        }
        return webElement;
    }
	
	protected void selectWebElementsByJS(By locator) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", find(locator));
	}
	
	protected String getTextFromWebElementsByJS(By locator) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return (String) js.executeScript("return arguments[0].value", find(locator));
	}
	
	protected final boolean isSelected(By locator) {
         return find(locator).isSelected();
    }
	
	protected void selectByVisibleText(By locator, String value) throws Exception {
		Select s = new Select(find(locator));
		s.selectByVisibleText(value);
	}
	
	 /**
     * waits for the {@link WebElement} to be displayed passed in as an argument
     * locator by the specified optional timeout
     * 
     * @param locator {@link By} which locates elements by the value of the "id",
     *        "css", "xpath" attributes.
     * @param timeout The timeout in seconds when an expectation is called
     * @return
     */
    protected final boolean waitForIsDisplayed(By locator, Integer... timeout) throws Exception {
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeout.length > 0 ? timeout[0] : null));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

 

    /**
     * waits for the {@link WebElement} to be clickable passed in as an argument
     * locator by the specified optional timeout
     * 
     * @param locator {@link By} which locates elements by the value of the "id",
     *        "css", "xpath" attributes.
     * @param timeout The timeout in seconds when an expectation is called
     * @return
     */
    protected final boolean waitForIsClickable(By locator, Integer... timeout) throws Exception {
        try {
            waitFor(ExpectedConditions.elementToBeClickable(locator), (timeout.length > 0 ? timeout[0] : null));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

 

    /**
     * waits for the {@link WebElement} to be visible passed in as an argument
     * locator by the specified optional timeout
     * 
     * @param locator {@link By} which locates elements by the value of the "id",
     *        "css", "xpath" attributes.
     * @param timeout The timeout in seconds when an expectation is called
     * @return
     */
    protected final boolean waitForIsInvisibility(By locator, Integer... timeout) throws Exception {
        try {
            waitForCondition(ExpectedConditions.invisibilityOfElementLocated(locator),
                    (timeout.length > 0 ? timeout[0] : null));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

 

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) throws Exception {
        timeout = timeout != null ? timeout : TIME_FIVE_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

 

    private void waitForCondition(ExpectedCondition<Boolean> condition, Integer timeout) throws Exception {
        timeout = timeout != null ? timeout : TIME_FIVE_SECONDS;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }
	
}
