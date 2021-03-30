package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;


public class ProductSearchPage {
	public AndroidDriver<MobileElement> driver;

	public ProductSearchPage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);


	}

	// * All MobileElements are identified by @@AndroidFindBy annotation

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']")
	MobileElement Search_TextBox;

	@AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='com.amazon.mShop.android.shopping:id/list_product_linear_layout'])[2]")
	MobileElement Select_Item_List;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title'])[2]")
	public
	MobileElement ProductName_ProductSearchScreen;

	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'rupees')]")
	public
	MobileElement ProductPrice_ProductSearchScreen;
	//@AndroidFindBy(xpath = "//android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View/android.widget.EditText")
	//MobileElement ProductPrice_ProductSearchScreen;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='a-autoid-15']")
	public 
	MobileElement BuyNow_Button;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='add-to-cart-button']")
	public 
	MobileElement AddtoCart_Button;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Proceed to checkout']")
	public 
	MobileElement ProceedtoCheckout_Button;
	

	//Enter Item in search text box

	public void clickSearchTextBox(){
		Search_TextBox.click();

	}

	public void enterItemToSearch(String item){

		Search_TextBox.sendKeys(item);
	}

	//Select Item from Search List
	public void selectItemFromList(){

		Select_Item_List.click();
	}	

	//Get the Product Name info
	public String getProductNameSearchScreen(){

		return  ProductName_ProductSearchScreen.getText();

	}

	//Get the Product Price info
	public String getProductPriceSearchScreen(){

		return  ProductPrice_ProductSearchScreen.getText();

	}

	//Click on Buy Now button
	public void clickBuyNowButton(){

		BuyNow_Button.click();
	}	
	
	public void clickAddtoCartButton(){

		AddtoCart_Button.click();
	}	
	
	public void clickCheckOutButton(){

		ProceedtoCheckout_Button.click();
	}	
	
	
/*
	public static void scrollToId(AndroidDriver<MobileElement> driver, String id) {
		MobileElement el = (MobileElement) driver.findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().resourceId(\"" + id + "\"));");
		//el.click();
	}
	
	public static void scrollToText(AndroidDriver<MobileElement> driver, String text) {
		MobileElement el = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable("
				+ "new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().textContains(\"" + text + "\"));");
		//el.click();
	}
*/
	





}