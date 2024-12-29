package com.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    // Locators
    By usernameField = By.id("loginusername");
    By passwordField = By.id("loginpassword");
    By loginButton = By.xpath("//button[text()='Log in']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
 // Scroll to element
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Methods
    public void setUsername(String username) {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        scrollToElement(usernameInput);
        usernameInput.clear();
        System.out.println("username is:"+username);
        usernameInput.sendKeys(username);
        //driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        //driver.findElement(passwordField).sendKeys(password);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        scrollToElement(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
