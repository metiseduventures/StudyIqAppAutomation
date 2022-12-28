package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import apiUtil.OtpUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.LoginSignup_Negative_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class LoginSignup_NegativeUtil {

	LoginSignup_Negative_OR loginPageNegObj;
	public List<String> loginSignUpNegMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	HomePageUtil homePageUtilObj;

	public LoginSignup_NegativeUtil(AppiumDriver<MobileElement> driver) {

		loginPageNegObj = new LoginSignup_Negative_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageNegObj);

	}

	public boolean verifyLogin(AppiumDriver<MobileElement> driver, String strMobileNo) {
		boolean result = true;
		String strOtp = null;
		OtpUtil otpUtilObj;
		try {
			result = check_EnterMobileNumber(strMobileNo, driver);
			if (!result) {
				return result;
			}

			result = checkOtpPage(driver);
			if (!result) {
				return result;
			}

			otpUtilObj = new OtpUtil();
			strOtp = otpUtilObj.getOtp(strMobileNo, false);
			if (strOtp == null) {
				loginSignUpNegMsgList.add("Error in getting otp");
				return false;
			}

			result = enterOtp(driver, strOtp);
			if (!result) {
				return result;
			}

			result = clickOnLoginButton(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean check_EnterMobileNumber(String strMobileNo, AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tvSignOrLogin", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("It is not a login page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageNegObj.numberInputBox(), 10);
			if (!result) {
				loginSignUpNegMsgList.add("The input box of phone number is not visible");
				return result;
			}

			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewName", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg1 = loginPageNegObj.ErrorsNo().getText();
			if (!errorMsg1.equalsIgnoreCase("Please enter 10 digit mobile number.")) {
				loginSignUpNegMsgList.add("The error msg is wrong when there is no input");
				return false;
			}

			cfObj.commonClick(loginPageNegObj.numberInputBox());

			if (ConfigFileReader.isTrueCallerFeature) {

				result = cfObj.commonWaitForElementToBeVisible(driver, loginPageNegObj.manualDetailBtn(), 10);
				if (!result) {
					loginSignUpNegMsgList.add("The truecaller feature has not opened");
					return result;
				}

				cfObj.commonClick(loginPageNegObj.manualDetailBtn());

				result = cfObj.commonWaitForElementToBeVisible(driver, loginPageNegObj.noneOfTheAboveBtn(), 10);
				if (result) {
					cfObj.commonClick(loginPageNegObj.noneOfTheAboveBtn());
				}
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageNegObj.noneOfTheAboveBtn(), 10);
			if (result) {
				cfObj.commonClick(loginPageNegObj.noneOfTheAboveBtn());
			}

			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewName", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg2 = loginPageNegObj.ErrorsNo().getText();
			if (!errorMsg2.equalsIgnoreCase("Please enter 10 digit mobile number.")) {
				loginSignUpNegMsgList.add("The error msg is wrong when there is no input");
				return false;
			}

			loginPageNegObj.numberInputBox().sendKeys("1234");

			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewName", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg3 = loginPageNegObj.ErrorsNo().getText();
			if (!errorMsg3.equalsIgnoreCase("Please enter 10 digit mobile number.")) {
				loginSignUpNegMsgList.add("The error msg is wrong when there is only less input");
				return false;
			}

			loginPageNegObj.numberInputBox().sendKeys("0000000000");

			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewName", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg4 = loginPageNegObj.ErrorsNo().getText();
			if (!errorMsg4.equalsIgnoreCase("Please enter 10 digit mobile number.")) {
				loginSignUpNegMsgList.add("The error msg is wrong when there is wrong number from 0");
				return false;
			}

			loginPageNegObj.numberInputBox().sendKeys(strMobileNo);

			cfObj.commonClick(loginPageNegObj.continueBtn());

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean checkOtpPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// wait for enter OTP box to be enable
			if (loginPageNegObj.otpBoxes().size() == 0) {
				loginSignUpNegMsgList.add("Enter otp text box not display after click on get otp button");
				return false;
			}

			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tvDidNotRecieveLabel", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg5 = loginPageNegObj.ErrorOtp().getText();
			if (!errorMsg5.equalsIgnoreCase("Please enter valid OTP,")) {
				loginSignUpNegMsgList.add("The error msg is wrong when no otp is filled");
				return false;
			}

			cfObj.commonSetTextTextBox(loginPageNegObj.otpBoxes().get(0), "124");

			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tvDidNotRecieveLabel", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg6 = loginPageNegObj.ErrorOtp().getText();
			if (!errorMsg6.equalsIgnoreCase("Please enter valid OTP,")) {
				loginSignUpNegMsgList.add("The error msg is wrong when less otp is filled");
				return false;
			}

			cfObj.commonSetTextTextBox(loginPageNegObj.otpBoxes().get(0), "454567");

			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tvDidNotRecieveLabel", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg7 = loginPageNegObj.ErrorOtp().getText();
			if (!errorMsg7.equalsIgnoreCase("Please enter valid OTP,")) {
				loginSignUpNegMsgList.add("The error msg is wrong when otp is wrong");
				return false;
			}

		} catch (Exception e) {

			result = false;

		}
		return result;
	}

	public boolean enterOtp(AppiumDriver<MobileElement> driver, String strOtp) {
		boolean result = true;
		try {

			cfObj.commonSetTextTextBox(loginPageNegObj.otpBoxes().get(0), strOtp);

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickOnLoginButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			// check if permission is display then allow permission
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.PERMISSION_ALLOW, "id", 10);
			if (result) {
				cfObj.commonClick(loginPageNegObj.getBtnPermissionAllowed());
			}

			// close pop up on home page
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 10);
			if (result) {

				cfObj.commonClick(cfObj.commonGetElement(driver, ConstantUtil.IMG_CLOSE, "id"));

			}
			// wait for home page to be opened

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 30);

			if (!result) {
				loginSignUpNegMsgList.add("Home page not opened after login");
				return result;
			}

		} catch (Exception e) {

			result = false;
			loginSignUpNegMsgList.add("clickOnLoginButton_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean verifySignUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = doSignUp(driver);
			if (!result) {
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.verifyMylibraryForNewUser(driver);

		} catch (Exception e) {
			result = false;
			loginSignUpNegMsgList.add("verifySignUp_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean doSignUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strOtp = null;
		OtpUtil otpUtilObj;
		String strMobileNo = null;
		try {
			strMobileNo = Common_Function.randomPhoneNumber(10, "4");
			System.out.println("strMobileNo: " + strMobileNo);

			result = check_EnterMobileNumber(strMobileNo, driver);
			if (!result) {
				return result;
			}
			result = checkOtpPage(driver);
			if (!result) {
				return result;
			}
			otpUtilObj = new OtpUtil();

			strOtp = otpUtilObj.getOtp(strMobileNo, true);
			if (strOtp == null) {
				loginSignUpNegMsgList.add("Error in getting otp");
				return false;
			}

			result = enterOtp(driver, strOtp);
			if (!result) {
				return result;
			}

			result = enterUserName(driver, "TestUser" + strMobileNo);
			if (!result) {
				return result;
			}
			result = enterUserEmail(driver, "TestUser" + strMobileNo + "@gmail.com");
			if (!result) {
				return result;
			}

			result = chooseExamPreference(driver);
			if (!result) {
				return result;
			}

			result = clickOnLoginButton(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			loginSignUpNegMsgList.add("doSignUp_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterUserName(AppiumDriver<MobileElement> driver, String strName) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageNegObj.nameTxtHeading(), 5);
			if (!result) {
				loginSignUpNegMsgList.add("The number is already used and not for sign up");
				return result;
			}

			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewName", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg01 = loginPageNegObj.ErrorsNo().getText();
			if (!errorMsg01.equalsIgnoreCase("Please enter your name")) {
				loginSignUpNegMsgList.add("The error msg is wrong when no name is entered");
				return false;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewEmail", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg02 = loginPageNegObj.ErrorEmail().getText();
			if (!errorMsg02.equalsIgnoreCase("Please enter your email")) {
				loginSignUpNegMsgList.add("The error msg is wrong when no email is entered");
				return false;
			}

			cfObj.commonClick(loginPageNegObj.nameInputBox());

			cfObj.commonSetTextTextBox(loginPageNegObj.nameInputBox(), strName);
			cfObj.hideKeyBoard(driver);

		} catch (Exception e) {
			result = false;
			loginSignUpNegMsgList.add("enterUserName_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean enterUserEmail(AppiumDriver<MobileElement> driver, String strEmail) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageNegObj.emailTxtHeading(), 5);
			if (!result) {
				loginSignUpNegMsgList.add("This is not name and email page");
				return result;
			}

			cfObj.commonClick(loginPageNegObj.emailInputBox());

			cfObj.commonSetTextTextBox(loginPageNegObj.emailInputBox(), "invalidEmail.com");
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewEmail", "id", 3);
			if (result) {
				loginSignUpNegMsgList.add("The error msg is visible even after email is written in the input box");
				return false;
			}
			
			cfObj.commonClick(loginPageNegObj.continueBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewEmail", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg03 = loginPageNegObj.ErrorEmail().getText();
			if (!errorMsg03.equalsIgnoreCase("Please enter valid email")) {
				loginSignUpNegMsgList.add("The error msg is wrong when email entered is wrong");
				return false;
			}
			
			result = cfObj.commonSetTextTextBox(loginPageNegObj.emailInputBox(), "shubhbansal148@gmail.com");

			cfObj.commonClick(loginPageNegObj.continueBtn());
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "errorViewEmail", "id", 10);
			if (!result) {
				loginSignUpNegMsgList.add("The error msg is not visible");
				return result;
			}

			String errorMsg04 = loginPageNegObj.ErrorEmail().getText();
			if (!errorMsg04.equalsIgnoreCase("This email address is already registered, please enter another email address")) {
				loginSignUpNegMsgList.add("The error msg is wrong when email is already registered");
				return false;
			}

			result = cfObj.commonSetTextTextBox(loginPageNegObj.emailInputBox(), strEmail);
			
			cfObj.hideKeyBoard(driver);

			cfObj.commonClick(loginPageNegObj.continueBtn());

		} catch (Exception e) {
			result = false;
			loginSignUpNegMsgList.add("enterUerEmail_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean chooseExamPreference(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// close pop up on home page
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 10);
			if (result) {

				cfObj.commonClick(cfObj.commonGetElement(driver, ConstantUtil.IMG_CLOSE, "id"));

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageNegObj.searchGoalInputBox(), 10);
			if (!result) {
				System.out.println("The exam preference page is not displayed or search box is not visible");
				return true;
			}

			cfObj.commonSetTextTextBox(loginPageNegObj.searchGoalInputBox(), "s");

			cfObj.commonClick(loginPageNegObj.listOfGoals().get(0));

		} catch (Exception e) {
			result = false;
			loginSignUpNegMsgList.add("chooseExamPreference_Exception: " + e.getMessage());
		}
		return result;
	}

}
