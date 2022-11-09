package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import apiUtil.OtpUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.Login_OR;
import util.Common_Function;

public class LoginUtil {

	Login_OR loginPageObj;
	public List<String> loginMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	HomePageUtil homePageUtilObj;

	public LoginUtil(AppiumDriver<MobileElement> driver) {

		loginPageObj = new Login_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObj);

	}

	public boolean verifyLogin(AppiumDriver<MobileElement> driver, String strMobileNo) {
		boolean result = true;
		String strOtp = null;
		OtpUtil otpUtilObj;
		try {
			result = enterMobileNumber(strMobileNo, driver);
			if (!result) {
				return result;
			}

			result = checkOtpPage();
			if (!result) {
				return result;
			}

			otpUtilObj = new OtpUtil();
			strOtp = otpUtilObj.getOtp(strMobileNo, false);
			if (strOtp == null) {
				loginMsgList.add("Error in getting otp");
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

	public boolean enterMobileNumber(String strMobileNo, AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tvSignOrLogin", "id", 10);
			if (!result) {
				loginMsgList.add("It is not a login page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.numberInputBox(), 10);
			if (!result) {
				loginMsgList.add("The input box of phone number is not visible");
				return result;
			}

			cfObj.commonClick(loginPageObj.numberInputBox());

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.manualDetailBtn(), 10);
			if (!result) {
				loginMsgList.add("The truecaller feature has not opened");
				return result;
			}

			cfObj.commonClick(loginPageObj.manualDetailBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.noneOfTheAboveBtn(), 5);
			if (result) {
				cfObj.commonClick(loginPageObj.noneOfTheAboveBtn());
			}

			loginPageObj.numberInputBox().sendKeys(strMobileNo);

			cfObj.commonClick(loginPageObj.continueBtn());

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean checkOtpPage() {
		boolean result = true;
		try {

			// wait for enter OTP box to be enable
			if (loginPageObj.otpBoxes().size() == 0) {
				loginMsgList.add("Enter otp text box not display after click on get otp button");
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

			cfObj.commonSetTextTextBox(loginPageObj.otpBoxes().get(0), strOtp);

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickOnLoginButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// check if permission is display then allow permission
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.PERMISSION_ALLOW, "id", 5);
			if (result) {
				cfObj.commonClick(loginPageObj.getBtnPermissionAllowed());
			}

			// close pop up on home page
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 5);
			if (result) {

				cfObj.commonClick(cfObj.commonGetElement(driver, ConstantUtil.IMG_CLOSE, "id"));

			}
			// wait for home page to be opened

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 30);

			if (!result) {
				loginMsgList.add("Home page not opened after login");
				return result;
			}

		} catch (Exception e) {

			result = false;
			loginMsgList.add("clickOnLoginButton_Exception: " + e.getMessage());

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
			loginMsgList.add("verifySignUp_Exception: " + e.getMessage());
		}

		return result;

	}

	public boolean enterUserName(AppiumDriver<MobileElement> driver, String strName) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.nameTxtHeading(), 5);
			if (!result) {
				loginMsgList.add("This is not name and email page");
				return result;
			}

			cfObj.commonClick(loginPageObj.nameInputBox());

			cfObj.commonSetTextTextBox(loginPageObj.nameInputBox(), strName);
			cfObj.hideKeyBoard(driver);

		} catch (Exception e) {
			result = false;
			loginMsgList.add("enterUserName_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean enterUerEmail(AppiumDriver<MobileElement> driver, String strEmail) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.emailTxtHeading(), 5);
			if (!result) {
				loginMsgList.add("This is not name and email page");
				return result;
			}

			cfObj.commonClick(loginPageObj.emailInputBox());

			result = cfObj.commonSetTextTextBox(loginPageObj.emailInputBox(), strEmail);
			cfObj.hideKeyBoard(driver);

			cfObj.commonClick(loginPageObj.continueBtn());

		} catch (Exception e) {
			result = false;
			loginMsgList.add("enterUerEmail_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean doSignUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strOtp = null;
		OtpUtil otpUtilObj;
		String strMobileNo = null;
		try {
			strMobileNo = Common_Function.randomPhoneNumber(10, "3");
			System.out.println("strMobileNo: " + strMobileNo);

			result = enterMobileNumber(strMobileNo, driver);
			if (!result) {
				return result;
			}
			result = checkOtpPage();
			if (!result) {
				return result;
			}
			otpUtilObj = new OtpUtil();

			strOtp = otpUtilObj.getOtp(strMobileNo, true);
			if (strOtp == null) {
				loginMsgList.add("Error in getting otp");
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
			result = enterUerEmail(driver, "TestUser" + strMobileNo + "@gmail.com");
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
			loginMsgList.add("doSignUp_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean chooseExamPreference(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// close pop up on home page
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 5);
			if (result) {

				cfObj.commonClick(cfObj.commonGetElement(driver, ConstantUtil.IMG_CLOSE, "id"));

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.searchGoalInputBox(), 5);
			if (!result) {
				System.out.println("The exam preference page is not displayed or search box is not visible");
				return true;
			}

			cfObj.commonSetTextTextBox(loginPageObj.searchGoalInputBox(), "s");

			cfObj.commonClick(loginPageObj.listOfGoals().get(0));

		} catch (Exception e) {
			result = false;
			loginMsgList.add("chooseExamPreference_Exception: " + e.getMessage());
		}
		return result;
	}

}
