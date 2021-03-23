package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import pageobject.AmazonLoginPage;
import pageobject.ProductSearchPage;
import utils.ResuableFunctions;
import pageobject.ProductCheckoutPage;


class TestCases extends BaseClass {


	//Create the object for AmazonLoginPage class
	static AmazonLoginPage objAmazonLoginPage;

	//Create the object for ProductSearchPagee class
	static ProductSearchPage objProductSearchPage;

	//Create the object for ProductCheckoutPage class
	static ProductCheckoutPage objProductCheckoutPage;

	//Create Object for Common Functions Class
	static ResuableFunctions objResuableFunctions;

	SoftAssert softassert = new SoftAssert();


	String ProductName_SearchScreen;
	String ProductPrice_SearchScreen;
	String ProductName_CheckOutScreen;
	String ProductPrice_CheckOutScreen;
	boolean BuyNowButton_Displayed;
	boolean CheckOutScreen_Displayed;

	@Test(priority=0)
	public void TC_1_Login() throws IOException{

		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test1 = extent.createTest("TC_1_Login", "Verify if user has logged in successfully");

		// log(Status, details)
		test1.log(Status.INFO, "TC_1_Login() started..");

		System.out.println("InsideTC_1_Login");

		//Create Login Page object
		objAmazonLoginPage = new AmazonLoginPage(driver);

		AmazonLoginPage.clickSignInButton();
		test1.log(Status.PASS, "User has successfully clicked on Sign In Button");
		AmazonLoginPage.EnterEmail(configFileReader.getUserName());
		test1.log(Status.PASS, "User has successfully Entered Email address");
		AmazonLoginPage.clickContinueButton();
		test1.log(Status.PASS, "User has successfully clicked on Continue button");
		AmazonLoginPage.enterPassword(configFileReader.getPassword());
		test1.log(Status.PASS, "User has successfully Entered Password");
		AmazonLoginPage.signinSubmitButton();
		test1.log(Status.PASS, "User has successfully clicked on Sign In Submit Button");
		
		//Verify if user has logged in successfully
	
		softassert.assertTrue(AmazonLoginPage.isAmazonHomePageDisplayed(), "User has successfully logged In");
		test1.log(Status.PASS, "User has successfully Logged into Amazon App");
		
		// test with snapshot
        test1.addScreenCaptureFromPath("TC_1_Login_screenshot.png");
        
        test1.log(Status.INFO, "TC_1_Login() Completed..");

	}

	@Test(priority=1)
	public void TC_2_SearchProduct() throws InterruptedException, IOException{
		
		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test2 = extent.createTest("TC_2_SearchProduct", "Verify if user is able to serach the product and navigate to Buy Now screen");

		// log(Status, details)
		test2.log(Status.INFO, "TC_2_SearchProduct started..");

		
		System.out.println("Inside TC_2_SearchProduct");

		//Create Product Search Page object
		objProductSearchPage = new ProductSearchPage(driver);
		objProductCheckoutPage = new ProductCheckoutPage(driver);
		Thread.sleep(2000);
		objProductSearchPage.clickSearchTextBox();
		test2.log(Status.PASS, "User has clicked on Search Box");
		Thread.sleep(2000);
		objProductSearchPage.enterItemToSearch(configFileReader.getSearchItem());
		test2.log(Status.PASS, "User has entered the Item in the search box");
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		test2.log(Status.PASS, "User has cliked on ENter key from Device keyboard");
		System.out.println("After Enter Key");
		objProductSearchPage.selectItemFromList();
		test2.log(Status.PASS, "User has selected one Item form the Searched Item List");
		ProductName_SearchScreen = objProductSearchPage.getProductNameSearchScreen();
		System.out.println("Product Name Search Screen - " +ProductName_SearchScreen);
		ProductPrice_SearchScreen = objProductSearchPage.getProductPriceSearchScreen();
		System.out.println("Product Price Search Screen - " +ProductPrice_SearchScreen);

		//Swipe down to find Add to Cart button	
		ResuableFunctions.scrollToId(driver,"add-to-cart-button");
		test2.log(Status.PASS, "User has scrolled down to click on Add to Cart button");
		//ProductSearchPage.scrollToId(driver,"add-to-cart-button");

		//Click on Buy Now Button
		Thread.sleep(2);
		objProductSearchPage.clickAddtoCartButton();
		test2.log(Status.PASS, "User has clicked on Add to Cart Button");
		objProductSearchPage.clickCheckOutButton();
		test2.log(Status.PASS, "User has clicked on Checkout Button");

		//Verify the the Navigation on Click of Buy Now Button

		softassert.assertTrue(objProductCheckoutPage.verifyCheckoutOutNavigation(), "User has NOT successfully Navigated to Address page");

		test2.log(Status.PASS, "User has navigated to Address Details page.");
		
		// test with snapshot
        test2.addScreenCaptureFromPath("TC_2_SearchProduct_screenshot.png");
		
		test2.log(Status.INFO, "TC_2_SearchProduct Completed..");

	}

	@Test(priority=2)
	public void TC_3_CheckOutProduct() throws InterruptedException, IOException{
		
		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test3 = extent.createTest("TC_3_CheckOutProduct", "Verify if user is able to navigate to Checkout screen");

		// log(Status, details)
		test3.log(Status.INFO, "TC_3_CheckOutProduct started..");
		
		System.out.println("Inside TC_3_CheckOutProduct");

		//Create Product Search Page object
		objProductCheckoutPage = new ProductCheckoutPage(driver);

		objProductCheckoutPage.clickDeliverAddressButton();
		test3.log(Status.PASS, "User has selected the Address");
		Thread.sleep(20000);
		objProductCheckoutPage.clickPaymentOption();
		test3.log(Status.PASS, "User has selected the Payment option");
		objProductCheckoutPage.clickPaymentOptionDropDown();
		objProductCheckoutPage.clickBankName();
		test3.log(Status.PASS, "User has selected the Bank Name");
		objProductCheckoutPage.clickPaymentContinueButton();
		test3.log(Status.PASS, "User has cliked on continue button from Payement Page");

		//Check if user is on CheckOut Page
		softassert.assertTrue(objProductCheckoutPage.verifyCheckOutScreenNavigation(), "User has NOT Navigated successfully to Payment screen");
		
		test3.log(Status.PASS, "User has Navigated successfully to Payment screen");


		ProductPrice_CheckOutScreen = objProductCheckoutPage.getProductPriceCheckOutScreen();
		System.out.println("ProductPrice_CheckOutScreen = " +ProductPrice_CheckOutScreen);

		//Swipe down to Product Name info
		ResuableFunctions.scrollToText(driver,"Return Policy");

		ProductName_CheckOutScreen = objProductCheckoutPage.getProductNameCheckOutScreen();
		System.out.println("ProductName_CheckOutScreen = " +ProductName_CheckOutScreen);
		
		
		test3.log(Status.INFO, "TC_3_CheckOutProduct completed..");
		
	}

	@Test(priority=3)
	public void TC_4_VerifyProdcutInfo() throws InterruptedException, IOException{
		
		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test4 = extent.createTest("TC_4_VerifyProdcutInfo", "Verify the Product name and price in Payement page is matching with Cart");

		// log(Status, details)
		test4.log(Status.INFO, "TC_4_VerifyProdcutInfo started..");
				
		System.out.println("TC_4_VerifyProdcutInfo");

		//Verify Product Name
		softassert.assertEquals(ProductName_SearchScreen, ProductName_CheckOutScreen, "Product Name on Search and Checkout Page are NOT same");	
		test4.log(Status.PASS, "Product Name on Search and Checkout Page are same");

		//Extract integer values from String
		ProductPrice_SearchScreen = ResuableFunctions.extractInt(ProductPrice_SearchScreen);
		System.out.println("ProductPrice_SearchScreen = " +ProductPrice_SearchScreen);
		ProductPrice_CheckOutScreen = ResuableFunctions.extractInt(ProductPrice_CheckOutScreen);
		System.out.println("ProductPrice_CheckOutScreen = " +ProductPrice_CheckOutScreen);

		//Verify Product Price 
		softassert.assertEquals(ProductPrice_SearchScreen, ProductPrice_SearchScreen, "Product Price on Search and Checkout Page are NOT same");
		
		test4.log(Status.PASS, "Product Price on Search and Checkout Page are same");
		
		
		test4.log(Status.INFO, "TC_4_VerifyProdcutInfo Completed..");
		

	}

}


