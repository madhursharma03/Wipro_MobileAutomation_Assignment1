package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ResuableFunctions {

	public static AndroidDriver<MobileElement> driver;
	

	public ResuableFunctions(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
	}
	
	public static void scrollToId(AndroidDriver<MobileElement> driver, String id) {
		MobileElement el = (MobileElement) driver.findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().resourceId(\"" + id + "\"));");
		//el.click();
	}
	
	public static void scrollToText(AndroidDriver<MobileElement> driver, String text) throws InterruptedException {
		Thread.sleep(5000);
		MobileElement el = (MobileElement) driver.findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().textContains(\"" + text + "\"));");
		
		/*MobileElement el = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable("
	+ "new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().textContains(\"" + text + "\"));");
		//el.click(); */
	}
	
	// Function to extract integer values from  string 
	public static String extractInt(String str) 
    { 
        // Replacing every non-digit number 
        // with a space(" ") 
        str = str.replaceAll("[^\\d]", " "); 
  
        // Remove extra spaces from the beginning 
        // and the ending of the string 
        str = str.trim(); 
  
        // Replace all the consecutive white 
        // spaces with a single space 
        str = str.replaceAll(" +", " "); 
  
        if (str.equals("")) 
            return "-1"; 
  
        return str; 
    } 
	
	public static String getScreenshot()
	{
		
	File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	//TakesScreenshot ts = (TakesScreenshot) driver;
	//File source = ts.getScreenshotAs(OutputType.FILE);
	
	String path = "C:\\Users\\mayuraxi\\AppData\\Local\\Temp\\screenshot"+System.currentTimeMillis()+".png";
	File destination = new File(path);
	try
	{
	FileUtils.copyFile(source, destination); 
	}catch (IOException e)
	{
		System.out.println("Capture Failed Reusbale Function Class "+e.getMessage());
	}
	
	return path;
	
	}
	
}
