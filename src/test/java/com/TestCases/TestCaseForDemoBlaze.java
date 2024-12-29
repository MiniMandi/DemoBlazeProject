package com.TestCases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Listeners.ExtentManager;
import com.Pages.CartPage;
import com.Pages.CheckOutPage;
import com.Pages.HomePage;
import com.Pages.LoginPage;
import com.Pages.ProductPage;
import com.Pages.SignUpPage;
import com.Utility.Screenshot;
import com.Utility.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestCaseForDemoBlaze {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    Screenshot screenShot;
    HomePage homePage;
    SignUpPage signUpPage;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckOutPage checkOutPage = new CheckOutPage(driver);;
    String randomUsername;
    String randomPassword;

    @BeforeClass
    public void setup() {
    	extent = ExtentManager.createInstance();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
        homePage = new HomePage(driver);
        screenShot = new Screenshot();
        
    }

    @Test(priority = 1)
    public void testSignUp() throws InterruptedException {
        // Navigate to Sign Up Page
        
    	test = extent.createTest("testSignUp", "Test Sign Up functionality");
    	signUpPage = homePage.goToSignUpPage();

        // Generate random username and password
        randomPassword = TestUtil.randomString();
        randomUsername = randomPassword + "@gmail.com";

        // Log and perform signup
        System.out.println("Registering new user: " + randomUsername);
        signUpPage.signUp(randomUsername, randomPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        
        //screenShot.getScreenShot(driver, "SignUpScreenshotBeforeAlert");

        // Handle alert and validate
        String alertText = TestUtil.handleAlert(driver);
        
        
        Assert.assertEquals(alertText, "Sign up successful.", "Alert text does not match expected value!");
        test.pass("Sign up test passed successfully.");
        screenShot.getScreenShot(driver, "SignUpScreenshot");
        System.out.println("New user successfully created!");
       
        
    }

    @Test(priority = 2)
    public void testLogin() throws InterruptedException {
        // Perform Login after successful sign-up
    	test = extent.createTest("testLogin", "Test Login functionality");
        loginPage = homePage.goToLoginPage(); // Navigate to Login Page
        loginPage.setUsername(randomUsername);
        loginPage.setPassword(randomPassword);
        loginPage.clickLoginButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Validate successful login (e.g., by checking the presence of a logout button or user-specific element)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2"))); // Adjust locator as needed
        boolean isLogoutVisible = driver.findElement(By.id("logout2")).isDisplayed();
        Assert.assertTrue(isLogoutVisible, "Login was not successful!");
        System.out.println("Login successful for user: " + randomUsername);
        screenShot.getScreenShot(driver, "LoginScreenshot");
        test.pass("Login test passed successfully.");
    }
    
    @Test(priority = 3)
    public void testProduct() throws InterruptedException {
        // Perform Login after successful sign-up
    	test = extent.createTest("testProductSelect", "Test Product Select functionality");
        productPage = homePage.goToProductPage();
        productPage.clickAddToCart();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        
        String alertText = TestUtil.handleAlert(driver);
        Assert.assertEquals(alertText, "Product added.", "Alert text does not match expected value!");
        System.out.println("Product added to cart successfully");
        screenShot.getScreenShot(driver, "ProductAddScreenshot");
        test.pass("Product add test passed successfully.");

    }

    @Test(priority = 4)
    public void testCartPage() {
        // Perform Login after successful sign-up
    	test = extent.createTest("testAddToCart", "Test Add To Cart functionality");
        cartPage = homePage.goToCartPage();
        checkOutPage= cartPage.clickPlaceOrder();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        
     // Define the purchase button locator
        By purchaseButton = By.xpath("//button[text()='Purchase']");

        // Wait for the purchase button to be visible
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseButton));

        // Assert that the purchase button is displayed on the page
        Assert.assertTrue(buttonElement.isDisplayed(), "Purchase button is not displayed on the page!");
        screenShot.getScreenShot(driver, "AddToCartScreenshot");
        test.pass("Add to cart test passed successfully.");
        
        
        
        //wait.until(ExpectedConditions.alertIsPresent());
        
        //checkOutPage.fillForm("Test User","India","Pune","12345","December","2024");
        //String alertText = TestUtil.handleAlert(driver);
        //Assert.assertEquals(alertText, "Product added.", "Alert text does not match expected value!");
        
        
    }
    
    @Test(priority = 5)
    public void testCheckOutPage() {
        // Perform Login after successful sign-up
    	test = extent.createTest("testCheckOut", "Test CheckOut functionality");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        checkOutPage.fillForm("Test User","India","Pune","12345","December","2024");
        checkOutPage.clickPurchaseButton();
        
        
     // Wait until the success message appears
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Thank you for your purchase!']")));

        // Assert that the success message text matches the expected value
        String actualMessage = successMessage.getText();
        Assert.assertEquals(actualMessage, "Thank you for your purchase!", "Success message does not match the expected value!");
        screenShot.getScreenShot(driver, "CheckoutScreenshot");
        test.pass("CheckOut test passed successfully.");
        
        
    }
    
    @AfterClass
    public void tearDown() {
        extent.flush();
    }
}
