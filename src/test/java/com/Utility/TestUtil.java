package com.Utility;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

public class TestUtil {
	static Screenshot screenshot;
	
    public static String randomString() {
        return UUID.randomUUID().toString().substring(0, 8); // Generate random string
    }

    public static String handleAlert(WebDriver driver) throws InterruptedException {
    	screenshot = new Screenshot();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        // Take a screenshot before handling the alert
        //screenshot.getScreenShot(driver, "AlertScreenshot");
        Alert alert = driver.switchTo().alert();       
        String alertText = alert.getText();
        alert.accept(); // Accept the alert
        screenshot.getScreenShot(driver, "test");
        return alertText;
    }
}
