package com.orangehrm.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.utils.TestUtils;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    protected void click(By locator) {
        TestUtils.logInfo("Clicking on element: " + locator.toString());
        waitForElementClickable(locator).click();
    }
    
    protected void sendKeys(By locator, String text) {
        TestUtils.logInfo("Typing '" + text + "' into element: " + locator.toString());
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    protected String getText(By locator) {
        String text = waitForElementVisible(locator).getText();
        TestUtils.logInfo("Getting text from element: " + locator.toString() + ", Text: " + text);
        return text;
    }
    
    protected boolean isElementDisplayed(By locator) {
        try {
            boolean isDisplayed = waitForElementVisible(locator).isDisplayed();
            TestUtils.logInfo("Checking if element is displayed: " + locator.toString() + ", Result: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            TestUtils.logInfo("Element not displayed: " + locator.toString() + ", Error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Takes a screenshot and logs it to the report
     * @param message Message to log with the screenshot
     */
    protected void takeScreenshot(String message) {
        TestUtils.logInfoWithScreenshot(driver, message);
    }
}
