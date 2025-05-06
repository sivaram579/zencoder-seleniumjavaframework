package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Initialize the ExtentReports instance
     * @return ExtentReports instance
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    /**
     * Create a new ExtentReports instance
     * @return ExtentReports instance
     */
    private static ExtentReports createInstance() {
        // Create a timestamp for unique report name
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportName = "TestReport_" + timestamp + ".html";
        
        // Create reports directory if it doesn't exist
        File reportsDir = new File("test-output/reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }
        
        // Set up the HTML reporter
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/reports/" + reportName);
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Test Execution Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        
        // Create ExtentReports and attach the HTML reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        // Set system info
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Browser", System.getProperty("browser", "chrome"));
        
        // Add CI/CD information if running in GitHub Actions
        if (System.getenv("GITHUB_ACTIONS") != null) {
            extent.setSystemInfo("CI/CD", "GitHub Actions");
            extent.setSystemInfo("Workflow", System.getenv("GITHUB_WORKFLOW"));
            extent.setSystemInfo("Run ID", System.getenv("GITHUB_RUN_ID"));
            extent.setSystemInfo("Repository", System.getenv("GITHUB_REPOSITORY"));
        }
        
        return extent;
    }

    /**
     * Get the current test instance
     * @return ExtentTest instance
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Set the current test instance
     * @param extentTest ExtentTest instance
     */
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    /**
     * Remove the current test instance
     */
    public static void removeTest() {
        test.remove();
    }
}