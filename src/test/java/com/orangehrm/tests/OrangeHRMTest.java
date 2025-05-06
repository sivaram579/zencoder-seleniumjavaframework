package com.orangehrm.tests;

import com.orangehrm.utils.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRMTest extends BaseTest {

    @Test(description = "Verify user can login to OrangeHRM")
    public void testLoginToOrangeHRM() {
        // Navigate to OrangeHRM website
        driver.get(Config.ORANGE_HRM_URL);
        
        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        
        // Enter credentials
        usernameField.sendKeys(Config.ORANGE_USERNAME);
        driver.findElement(By.name("password")).sendKeys(Config.ORANGE_PASSWORD);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        // Verify that login was successful by checking if the dashboard is displayed
        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header is not displayed");
    }
    
    @Test(description = "Verify user can navigate to Admin page")
    public void testNavigateToAdminPage() {
        // Navigate to OrangeHRM website
        driver.get(Config.ORANGE_HRM_URL);
        
        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        
        // Enter credentials
        usernameField.sendKeys(Config.ORANGE_USERNAME);
        driver.findElement(By.name("password")).sendKeys(Config.ORANGE_PASSWORD);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        // Wait for dashboard to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        
        // Navigate to Admin page
        driver.findElement(By.xpath("//span[text()='Admin']")).click();
        
        // Verify that Admin page is displayed
        WebElement adminHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Admin']")));
        Assert.assertTrue(adminHeader.isDisplayed(), "Admin header is not displayed");
    }
}