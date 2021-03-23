package tests;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ResuableFunctions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ReportingClass {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	AndroidDriver<MobileElement> driver;

	@BeforeSuite
	public void reportSetup() {

		// start reporters
		htmlReporter = new ExtentHtmlReporter("extent.html");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}
	
	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if (result.getStatus()==ITestResult.FAILURE)
		{
			String temp = ResuableFunctions.getScreenshot();	
			ExtentTest test = extent.createTest("Screenshot log", "Taking the Screenshot");
			
			try {
				test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Capture Failed Reporting Class "+e.getMessage());
			}
		}
			
	}
	
	
	

	@AfterSuite
	public void reportTeardown() {
		// calling flush writes everything to the log file
		extent.flush();
	}

}
