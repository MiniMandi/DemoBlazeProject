package com.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    WebDriver driver;

    By usernameField = By.id("sign-username"); // Username field
    By passwordField = By.id("sign-password"); // Password field
    By signupButton = By.xpath("//button[text()='Sign up']"); // Sign Up button

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void signUp(String username, String password) {
        //driver.findElement(usernameField).sendKeys(username); // Enter username
        //driver.findElement(passwordField).sendKeys(password); // Enter password
        //driver.findElement(signupButton).click(); // Click Sign-Up button
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        
        // Wait for the username field to be visible and interactable
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameInput.sendKeys(username); // Enter username

        // Wait for the password field to be visible and interactable
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.sendKeys(password); // Enter password

        // Wait for the Sign-Up button to be clickable
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(signupButton));
        signUpBtn.click(); // Click Sign-Up button
    }
}
