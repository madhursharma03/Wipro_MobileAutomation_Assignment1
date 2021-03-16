package pageobject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;


public class AmazonLoginPage {

	public AndroidDriver<MobileElement> driver;

	public AmazonLoginPage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}


	// * All MobileElements are identified by @@AndroidFindBy annotation

	@AndroidFindBy(id= "com.amazon.mShop.android.shopping:id/sign_in_button")
	static
	MobileElement Sign_In_Button;

	@AndroidFindBy(xpath= "//android.widget.EditText[@resource-id='ap_email_login']")
	static
	MobileElement Email_Textbox;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='continue']")
	static
	MobileElement Continue_Button;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_password']")
	static
	MobileElement Password_Textbox;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='signInSubmit']")
	static
	MobileElement Signin_Submit_Button;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Home\"]")
	static
	MobileElement AmazonLogo;


	//Click on sign in button
	public static void clickSignInButton(){

		Sign_In_Button.click();
	}

	//Enter Email ID in Email address text box
	public static void EnterEmail(String email){

		Email_Textbox.sendKeys(email);
	}

	//Click on Continue button
	public static void clickContinueButton(){

		Continue_Button.click();
	}

	//Enter Password text box
	public static void enterPassword(String password){

		Password_Textbox.sendKeys(password);
	}

	//Click on SignIn button from  Password Page
	public static void signinSubmitButton(){

		Signin_Submit_Button.click();
	}  

	public static boolean isAmazonHomePageDisplayed(){

		return AmazonLogo.isDisplayed();
	}  

}
