package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    // Locators
    private final By productsTitle = By.className("title");
    private final By shoppingCartIcon = By.className("shopping_cart_link");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    
    // Product locators
    private final By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By backpackRemoveButton = By.id("remove-sauce-labs-backpack");
    private final By bikeLightAddToCartButton = By.id("add-to-cart-sauce-labs-bike-light");
    private final By bikeLightRemoveButton = By.id("remove-sauce-labs-bike-light");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(productsTitle);
    }

    public boolean isProductTitleDisplayed() {
        return isElementDisplayed(productsTitle);
    }

    public HomePage addBackpackToCart() {
        click(backpackAddToCartButton);
        return this;
    }
    
    public HomePage addBikeLightToCart() {
        click(bikeLightAddToCartButton);
        return this;
    }

    public boolean isBackpackRemoveButtonDisplayed() {
        return isElementDisplayed(backpackRemoveButton);
    }
    
    public boolean isBikeLightRemoveButtonDisplayed() {
        return isElementDisplayed(bikeLightRemoveButton);
    }
    
    public String getCartCount() {
        return getText(shoppingCartBadge);
    }

    public CartPage goToCart() {
        click(shoppingCartIcon);
        return new CartPage(driver);
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }
}
