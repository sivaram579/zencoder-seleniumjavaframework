package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    // Locators
    private final By cartTitle = By.className("title");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By backpackItem = By.xpath("//div[text()='Sauce Labs Backpack']");
    private final By bikeLightItem = By.xpath("//div[text()='Sauce Labs Bike Light']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return isElementDisplayed(cartTitle);
    }

    public boolean isBackpackInCart() {
        return isElementDisplayed(backpackItem);
    }
    
    public boolean isBikeLightInCart() {
        return isElementDisplayed(bikeLightItem);
    }

    public CheckoutPage clickCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    public HomePage continueShopping() {
        click(continueShoppingButton);
        return new HomePage(driver);
    }
}
