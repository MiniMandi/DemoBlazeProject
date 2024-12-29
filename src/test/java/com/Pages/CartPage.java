package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    // Locators
    By placeOrderButton = By.xpath("//button[text()='Place Order']");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public CheckOutPage clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
        return new CheckOutPage(driver);
        
    }
}
