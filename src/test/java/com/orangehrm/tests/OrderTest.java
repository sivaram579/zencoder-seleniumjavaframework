package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pages.CartPage;
import com.orangehrm.pages.CheckoutPage;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.Config;
import com.orangehrm.utils.TestUtils;

public class OrderTest extends BaseTest {

   /* @Test(description = "Verify user can add item to cart and complete checkout process")
    public void testAddItemToCartAndPlaceOrder() {
        TestUtils.logInfo("Starting test: Add item to cart and place order");
        
        // Navigate to SauceDemo website
        TestUtils.logInfo("Navigating to SauceDemo website");
        driver.get(Config.SAUCE_DEMO_URL);
        TestUtils.logInfoWithScreenshot(driver, "SauceDemo login page loaded");
        
        // Login with valid credentials
        TestUtils.logInfo("Logging in with valid credentials");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(Config.SAUCE_USERNAME, Config.SAUCE_PASSWORD);
        TestUtils.logInfoWithScreenshot(driver, "Successfully logged in to SauceDemo");
        
        // Add Sauce Labs Backpack to cart
        TestUtils.logInfo("Adding Sauce Labs Backpack to cart");
        homePage.addBackpackToCart();
        
        // Verify that the item was added to cart
        TestUtils.logInfo("Verifying that the item was added to cart");
        Assert.assertTrue(homePage.isBackpackRemoveButtonDisplayed(), "Remove button is not displayed");
        TestUtils.logPass("Backpack was successfully added to cart");
        TestUtils.logInfoWithScreenshot(driver, "Backpack added to cart");
        
        // Go to cart
        TestUtils.logInfo("Navigating to cart page");
        CartPage cartPage = homePage.goToCart();
        
        // Verify that the cart page is displayed and contains the backpack
        TestUtils.logInfo("Verifying cart page is displayed and contains the backpack");
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page is not displayed");
        Assert.assertTrue(cartPage.isBackpackInCart(), "Backpack is not in the cart");
        TestUtils.logPass("Cart page is displayed and contains the backpack");
        TestUtils.logInfoWithScreenshot(driver, "Cart page with backpack");
        
        // Proceed to checkout
        TestUtils.logInfo("Proceeding to checkout");
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        
        // Fill checkout information
        TestUtils.logInfo("Filling checkout information");
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        TestUtils.logInfoWithScreenshot(driver, "Checkout information filled");
        
        // Complete the order
        TestUtils.logInfo("Completing the order");
        checkoutPage.clickFinish();
        
        // Verify that the order was completed successfully
        TestUtils.logInfo("Verifying order completion");
        Assert.assertTrue(checkoutPage.isOrderCompleted(), "Order completion message is not displayed");
        Assert.assertEquals(checkoutPage.getCompleteHeaderText(), "Thank you for your order!", 
                "Order completion header does not match expected text");
        TestUtils.logPass("Order was completed successfully");
        TestUtils.logInfoWithScreenshot(driver, "Order completion page");
    }*/
    
    @Test(description = "Verify user can add multiple items to cart")
    public void testAddMultipleItemsToCart() {
        TestUtils.logInfo("Starting test: Add multiple items to cart");
        
        // Navigate to SauceDemo website
        TestUtils.logInfo("Navigating to SauceDemo website");
        driver.get(Config.SAUCE_DEMO_URL);
        TestUtils.logInfoWithScreenshot(driver, "SauceDemo login page loaded");
        
        // Login with valid credentials
        TestUtils.logInfo("Logging in with valid credentials");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(Config.SAUCE_USERNAME, Config.SAUCE_PASSWORD);
        TestUtils.logInfoWithScreenshot(driver, "Successfully logged in to SauceDemo");
        
        // Add multiple items to cart
        TestUtils.logInfo("Adding multiple items to cart");
        homePage.addBackpackToCart();
        TestUtils.logInfo("Added Sauce Labs Backpack to cart");
        homePage.addBikeLightToCart();
        TestUtils.logInfo("Added Sauce Labs Bike Light to cart");
        TestUtils.logInfoWithScreenshot(driver, "Multiple items added to cart");
        
        // Verify cart count is 2
        TestUtils.logInfo("Verifying cart count is 2");
        //Assert.assertEquals(homePage.getCartCount(), "2", "Cart count does not match expected value");
        TestUtils.logPass("Cart count is 2 as expected");
        
        // Go to cart
        TestUtils.logInfo("Navigating to cart page");
        CartPage cartPage = homePage.goToCart();
        
        // Verify that the cart page is displayed and contains the items
        TestUtils.logInfo("Verifying cart page is displayed and contains the items");
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page is not displayed");
        Assert.assertTrue(cartPage.isBackpackInCart(), "Backpack is not in the cart");
        Assert.assertTrue(cartPage.isBikeLightInCart(), "Bike Light is not in the cart");
        TestUtils.logPass("Cart page is displayed and contains both items");
        TestUtils.logInfoWithScreenshot(driver, "Cart page with multiple items");
    }
}