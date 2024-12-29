package com.Utility;

	import java.io.File;
	import java.io.IOException;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.io.FileHandler;

	public class Screenshot 
	{
	    public void getScreenShot(WebDriver driver, String name) 
	    {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File temp = ts.getScreenshotAs(OutputType.FILE);
	        File directory = new File(System.getProperty("user.dir") + "//Screenshot1//");
	        if (!directory.exists()) {
	            directory.mkdir();
	        }
	        File dest = new File(System.getProperty("user.dir") + "//Screenshot1//" + name + System.currentTimeMillis() + ".png");
	        
	        try 
	        {
	            FileHandler.copy(temp, dest);
	        } catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	    }
	}