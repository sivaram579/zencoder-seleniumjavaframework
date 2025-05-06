package com.orangehrm.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.orangehrm.listeners.TestListener;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(TestListener.class)
public class BaseTest {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        // Get browser from system property or default to chrome
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        
        switch (browser) {
            case "firefox":
                setupFirefoxDriver();
                break;
            case "edge":
                setupEdgeDriver();
                break;
            case "chrome":
            default:
                setupChromeDriver();
                break;
        }
        
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }
    
    private void setupChromeDriver() {
        // Setup WebDriverManager for Chrome
        WebDriverManager.chromedriver().setup();
        
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        // For GitHub Actions headless environment
        if (isRunningInCI()) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        
        // Initialize the WebDriver
        driver = new ChromeDriver(options);
    }
    
    private void setupFirefoxDriver() {
        // Setup WebDriverManager for Firefox
        WebDriverManager.firefoxdriver().setup();
        
        // Configure Firefox options
        FirefoxOptions options = new FirefoxOptions();
        
        // For GitHub Actions headless environment
        if (isRunningInCI()) {
            options.addArguments("--headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }
        
        // Initialize the WebDriver
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }
    
    private void setupEdgeDriver() {
        // Setup WebDriverManager for Edge
        WebDriverManager.edgedriver().setup();
        
        // Configure Edge options
        EdgeOptions options = new EdgeOptions();
        
        // For GitHub Actions headless environment
        if (isRunningInCI()) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        
        // Initialize the WebDriver
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }
    
    private boolean isRunningInCI() {
        return System.getenv("CI") != null && System.getenv("CI").equals("true");
    }
    
    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
    
    /**
     * Takes a screenshot and saves it to the specified directory
     * @param name Name of the screenshot file
     * @return Path to the screenshot file
     */
    public String takeScreenshot(String name) {
        // Create a timestamp for unique screenshot name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = name + "_" + timestamp + ".png";
        
        // Create screenshots directory if it doesn't exist
        File screenshotsDir = new File("test-output/screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }
        
        // Take screenshot
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = "test-output/screenshots/" + screenshotName;
        
        try {
            FileHandler.copy(source, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return destination;
    }
}
