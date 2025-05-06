package com.orangehrm.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.orangehrm.utils.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

public class TestListener implements ITestListener {
    private ExtentReports extent = ExtentReportManager.getInstance();
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite finished: " + context.getName());
        // Flush the report when all tests are complete
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        // Create a new test in the report
        ExtentTest test = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        ExtentReportManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        // Log the test success
        ExtentReportManager.getTest().log(Status.PASS, 
                MarkupHelper.createLabel(result.getName() + " - Test Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        
        // Log the test failure
        ExtentTest test = ExtentReportManager.getTest();
        test.log(Status.FAIL, 
                MarkupHelper.createLabel(result.getName() + " - Test Failed", ExtentColor.RED));
        test.log(Status.FAIL, result.getThrowable());
        
        // Capture screenshot if WebDriver is available
        Object testClass = result.getInstance();
        try {
            // Get the driver instance using reflection
            WebDriver driver = (WebDriver) testClass.getClass().getSuperclass().getDeclaredField("driver")
                    .get(testClass);
            
            if (driver != null) {
                // Take screenshot and attach to report
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                test.addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
            }
        } catch (Exception e) {
            test.log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
        // Log the test skip
        ExtentReportManager.getTest().log(Status.SKIP, 
                MarkupHelper.createLabel(result.getName() + " - Test Skipped", ExtentColor.ORANGE));
    }
}