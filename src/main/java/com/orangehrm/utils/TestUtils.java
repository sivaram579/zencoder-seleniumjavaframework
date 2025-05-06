package com.orangehrm.utils;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utility class for test-related operations
 */
public class TestUtils {

    /**
     * Logs a step in the Extent Report
     * @param message Message to log
     */
    public static void logInfo(String message) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().log(Status.INFO, message);
        }
    }

    /**
     * Logs a step with a screenshot in the Extent Report
     * @param driver WebDriver instance
     * @param message Message to log
     */
    public static void logInfoWithScreenshot(WebDriver driver, String message) {
        if (ExtentReportManager.getTest() != null) {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            ExtentReportManager.getTest().log(Status.INFO, message);
            ExtentReportManager.getTest().addScreenCaptureFromBase64String(base64Screenshot, "Screenshot");
        }
    }

    /**
     * Logs a passed step in the Extent Report
     * @param message Message to log
     */
    public static void logPass(String message) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().log(Status.PASS, message);
        }
    }

    /**
     * Logs a failed step in the Extent Report
     * @param message Message to log
     */
    public static void logFail(String message) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().log(Status.FAIL, message);
        }
    }

    /**
     * Logs a warning in the Extent Report
     * @param message Message to log
     */
    public static void logWarning(String message) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().log(Status.WARNING, message);
        }
    }

    /**
     * Highlights an element in the page
     * @param driver WebDriver instance
     * @param element Element to highlight
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        // This method can be implemented to highlight elements during test execution
        // Example: Use JavaScript to change the element's border or background color
    }
}