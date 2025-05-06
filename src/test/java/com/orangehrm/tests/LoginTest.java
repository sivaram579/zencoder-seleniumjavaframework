package com.orangehrm.tests;

import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify user can login with valid credentials")
    public void testLoginAndVerifyHomepage() {
        // Navigate to SauceDemo website
        driver.get(Config.SAUCE_DEMO_URL);
        
        // Create an instance of LoginPage
        LoginPage loginPage = new LoginPage(driver);
        
        // Login with valid credentials
        HomePage homePage = loginPage.login(Config.SAUCE_USERNAME, Config.SAUCE_PASSWORD);
        
        // Verify that login was successful by checking if the Products title is displayed
        Assert.assertTrue(homePage.isProductTitleDisplayed(), "Products title is not displayed");
        Assert.assertEquals(homePage.getTitle(), "Products", "Page title does not match expected value");
    }
    
    @Test(description = "Verify error message for invalid credentials")
    public void testLoginWithInvalidCredentials() {
        // Navigate to SauceDemo website
        driver.get(Config.SAUCE_DEMO_URL);
        
        // Create an instance of LoginPage
        LoginPage loginPage = new LoginPage(driver);
        
        // Enter invalid credentials
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();
        
        // Verify that error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"), 
                "Error message does not contain expected text");
    }
}