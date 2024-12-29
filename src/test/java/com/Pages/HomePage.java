package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    // Locators
    By signupLink = By.id("signin2");
    By loginLink = By.id("login2");
    By productLink = By.linkText("Samsung galaxy s6"); // Example product link
    By cartLink = By.id("cartur");
    //By checkOutLink = By.linkText("Place Order");
    

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public SignUpPage goToSignUpPage() {
        driver.findElement(signupLink).click(); // Click the Sign-Up link
        return new SignUpPage(driver); // Return a new instance of SignUpPage
    }
    
    public LoginPage goToLoginPage() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
    
    public ProductPage goToProductPage() {
        driver.findElement(productLink).click();
        return new ProductPage(driver);
    }
    
    public CartPage goToCartPage() {
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }
    
    

    // Methods
    public void clickSignup() {
        driver.findElement(signupLink).click();
    }

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    public void clickProduct() {
        driver.findElement(productLink).click();
    }
}
