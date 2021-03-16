package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
	public void TC_1_Login(){
		System.out.println("InsideTC_1_Login");

		//Create Login Page object
		objAmazonLoginPage = new AmazonLoginPage(driver);

		AmazonLoginPage.clickSignInButton();
		AmazonLoginPage.EnterEmail(configFileReader.getUserName());
		AmazonLoginPage.clickContinueButton();
		AmazonLoginPage.enterPassword(configFileReader.getPassword());
		AmazonLoginPage.signinSubmitButton();

		//Verify if user has logged in successfully
		softassert.assertTrue(AmazonLoginPage.isAmazonHomePageDisplayed(), "User has successfully logged In");
		Reporter.log("User has successfully Logged into Amazon App");

	}

	@Test(priority=1)
	public void TC_2_SearchProduct() throws InterruptedException{
		System.out.println("Inside TC_2_SearchProduct");

		//Create Product Search Page object
		objProductSearchPage = new ProductSearchPage(driver);
		objProductCheckoutPage = new ProductCheckoutPage(driver);
		Thread.sleep(2000);
		objProductSearchPage.clickSearchTextBox();
		Thread.sleep(2000);
		objProductSearchPage.enterItemToSearch(configFileReader.getSearchItem());
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		System.out.println("After Enter Key");
		objProductSearchPage.selectItemFromList();
		ProductName_SearchScreen = objProductSearchPage.getProductNameSearchScreen();
		System.out.println("Product Name Search Screen - " +ProductName_SearchScreen);
		ProductPrice_SearchScreen = objProductSearchPage.getProductPriceSearchScreen();
		System.out.println("Product Price Search Screen - " +ProductPrice_SearchScreen);

		//Swipe down to find ADd to Cart button	
		ResuableFunctions.scrollToId(driver,"add-to-cart-button");
		
		 //ProductSearchPage.scrollToId(driver,"add-to-cart-button");
		 
		//Click on Buy Now Button
		Thread.sleep(2);
		objProductSearchPage.clickAddtoCartButton();
		objProductSearchPage.clickCheckOutButton();
		

		//Verify the the Navigation on Click of Buy Now Button

		softassert.assertTrue(objProductCheckoutPage.verifyCheckoutOutNavigation(), "User has NOT successfully Navigated to Address page");
		
		Reporter.log("User has successfully Navigated to address page");

	}

	@Test(priority=2)
	public void TC_3_CheckOutProduct() throws InterruptedException{
		System.out.println("Inside TC_3_CheckOutProduct");

		//Create Product Search Page object
		objProductCheckoutPage = new ProductCheckoutPage(driver);

		objProductCheckoutPage.clickDeliverAddressButton();
		Thread.sleep(20000);
		objProductCheckoutPage.clickPaymentOption();
		objProductCheckoutPage.clickPaymentOptionDropDown();
		objProductCheckoutPage.clickBankName();
		objProductCheckoutPage.clickPaymentContinueButton();

		//Check if user is on CheckOut Page
		softassert.assertTrue(objProductCheckoutPage.verifyCheckOutScreenNavigation(), "User has NOT Navigated successfully to Payment screen");
		Reporter.log("User has Navigated successfully to Payment screen");


		ProductPrice_CheckOutScreen = objProductCheckoutPage.getProductPriceCheckOutScreen();
		System.out.println("ProductPrice_CheckOutScreen = " +ProductPrice_CheckOutScreen);

		//Swipe down to Product Name info
		ResuableFunctions.scrollToText(driver,"Return Policy");

		ProductName_CheckOutScreen = objProductCheckoutPage.getProductNameCheckOutScreen();
		System.out.println("ProductName_CheckOutScreen = " +ProductName_CheckOutScreen);
	}
	
	@Test(priority=3)
	public void TC_4_VerifyProdcutInfo() throws InterruptedException{
		System.out.println("TC_4_VerifyProdcutInfo");
		
		//Verify Product Name
		softassert.assertEquals(ProductName_SearchScreen, ProductName_CheckOutScreen, "Product Name on Search and Checkout Page are NOT same");	
		Reporter.log("Product Name on Search and Checkout Page are same");
		
		//Extract integer values from String
		ProductPrice_SearchScreen = ResuableFunctions.extractInt(ProductPrice_SearchScreen);
		System.out.println("ProductPrice_SearchScreen = " +ProductPrice_SearchScreen);
		ProductPrice_CheckOutScreen = ResuableFunctions.extractInt(ProductPrice_CheckOutScreen);
		System.out.println("ProductPrice_CheckOutScreen = " +ProductPrice_CheckOutScreen);
		
		//Verify Product Price 
		softassert.assertEquals(ProductPrice_SearchScreen, ProductPrice_SearchScreen, "Product Price on Search and Checkout Page are NOT same");
		Reporter.log("Product Price on Search and Checkout Page are same");
		
	}
	
}


