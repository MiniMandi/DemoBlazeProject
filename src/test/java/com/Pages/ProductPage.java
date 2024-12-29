package com.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    WebDriver driver;

    // Locators
    //By addToCartButton = By.linkText("Add to cart");
    

 // Using XPath to locate "Add to cart" button
    @FindBy(xpath = "//a[text()='Add to cart' and @onclick='addToCart(1)']")
    WebElement addToCartBtn;

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods
    public void clickAddToCart() {
        //driver.findElement(addToCartButton).click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the element to be clickable
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartButton.click();
    }
}
