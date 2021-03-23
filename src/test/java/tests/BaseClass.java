package tests;

import utils.ConfigFileReader;  
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class BaseClass extends ReportingClass {

	AndroidDriver<MobileElement> driver;
	ConfigFileReader configFileReader;

	@BeforeTest
	public void setUpAppium() throws MalformedURLException {
		configFileReader= new ConfigFileReader();
		DesiredCapabilities caps = new DesiredCapabilities();
		System.out.println("Before Test");
		System.out.println(configFileReader.getDeviceName());
		
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, configFileReader.getDeviceName());
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, configFileReader.getPlatformName());
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, configFileReader.getPlatformVersion());
		caps.setCapability(MobileCapabilityType.UDID, configFileReader.getUdid());

		//caps.setCapability(MobileCapabilityType.APP, configFileReader.getAppLocation());
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, configFileReader.getAppPackage());
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, configFileReader.getAppActivityName());
		//caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		caps.setCapability(MobileCapabilityType.FULL_RESET, configFileReader.getFullReset());
		caps.setCapability(MobileCapabilityType.NO_RESET, configFileReader.getNoReset());

		URL url = new URL(configFileReader.getAppiumServerURL());
		driver = new AndroidDriver<MobileElement>(url,caps);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);


	}
	

	@AfterTest
	public void afterTest() {
		driver.closeApp();
	}

}
