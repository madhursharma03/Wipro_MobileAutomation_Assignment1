package pageobject;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCheckoutPage {
	public AndroidDriver<MobileElement> driver;

	public ProductCheckoutPage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);


	}

	// * All MobileElements are identified by @@AndroidFindBy annotation

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='a-autoid-0-announce']")
	MobileElement DeliverAddress_Button;

	@AndroidFindBy(xpath = "//android.view.View[@text='Net Banking']")
	MobileElement Payment_Option;
	
	@AndroidFindBy(xpath = "//*[starts-with(@resource-id,'pp') and ends-with(@resource-id,'113')]")
	MobileElement Payment_Option_DropDown;

	@AndroidFindBy(xpath = "//android.view.View[@text='Axis Bank']")
	MobileElement SelectBank;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
	MobileElement Payment_Continue_Button;

	@AndroidFindBy(xpath = "//*[@class='android.widget.Image']")
	public
	MobileElement ProductName_CheckOutScreen;


	@AndroidFindBy(xpath = "//*[@text='Order Total:']/following-sibling::*")
	MobileElement ProductPrice_CheckOutScreen;
	

	@AndroidFindBy(xpath = "//android.view.View[@text='Order now']")
	public
	MobileElement Order_Now_Page;

	//Click on Delivery Address button
	public void clickDeliverAddressButton(){

		DeliverAddress_Button.click();
	}

	//Select Payment Option
	public void clickPaymentOption(){

		Payment_Option.click();
	}


	//Click Payment Option DD
	public void clickPaymentOptionDropDown(){

		Payment_Option_DropDown.click();
	}


	//Select Bank
	public void clickBankName(){

		SelectBank.click();
	}

	//Click on Payment button
	public void clickPaymentContinueButton(){

		Payment_Continue_Button.click();
	}

	//Get the Product Name info from Checkout screen
	public String getProductNameCheckOutScreen(){

		return  ProductName_CheckOutScreen.getText();

	}

	//Get the Product Price info from Checkout screen
	public String getProductPriceCheckOutScreen(){

		return  ProductPrice_CheckOutScreen.getText();

	}

	public boolean verifyCheckoutOutNavigation(){

		return DeliverAddress_Button.isDisplayed();
	}  

	public boolean verifyCheckOutScreenNavigation(){

		return Order_Now_Page.isDisplayed();
	} 


}
