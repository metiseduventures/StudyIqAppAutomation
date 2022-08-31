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

			result = enterMobileNumber(strMobileNo);
			if (!result) {
				return result;
			}
			result = clickOnGetOtp();
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

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean enterMobileNumber(String strMobileNo) {
		boolean result = true;
		try {

			cfObj.commonClick(loginPageObj.getTextMobileNo());

			result = cfObj.commonSetTextTextBox(loginPageObj.getTextMobileNo(), strMobileNo);

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean enterOtp(AppiumDriver<MobileElement> driver, String strOtp) {
		boolean result = true;
		try {

			cfObj.commonClick(loginPageObj.getTextOtp().get(0));

			result = cfObj.commonSetTextTextBox(loginPageObj.getTextOtp().get(0), strOtp);

			cfObj.hideKeyBoard(driver);

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickOnGetOtp() {
		boolean result = true;
		try {
			cfObj.commonClick(loginPageObj.getBtnGetOtp());

			// wait for enter OTP box to be enable
			if (loginPageObj.getTextOtp().size() == 0) {
				loginMsgList.add("Enter otp text box not display after click on get otp button");
				return false;
			}

		} catch (Exception e) {

			result = false;

		}
		return result;
	}

	public boolean clickOnLoginButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(loginPageObj.getBtnLogin());
			// check if permission is display then allow permission

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.PERMISSION_ALLOW, "id", 30);
			if (result) {
				cfObj.commonClick(loginPageObj.getBtnPermissionAllowed());
			}

			// close pop up on home page
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 30);
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

			cfObj.commonClick(loginPageObj.getTextUserName());

			result = cfObj.commonSetTextTextBox(loginPageObj.getTextUserName(), strName);
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

			cfObj.commonClick(loginPageObj.getTextUserEmail());

			result = cfObj.commonSetTextTextBox(loginPageObj.getTextUserEmail(), strEmail);
			cfObj.hideKeyBoard(driver);

		} catch (Exception e) {
			result = false;
			loginMsgList.add("enterUerEmail_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnSkipLogin(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(loginPageObj.getBtnSkipLogin());
			// check if permission is display then allow permission

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.PERMISSION_ALLOW, "id", 30);
			if (result) {
				cfObj.commonClick(loginPageObj.getBtnPermissionAllowed());
			}

			// close pop up on home page
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 30);
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
			loginMsgList.add("clickOnSkipLogin_Exception: " + e.getMessage());
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
			result = enterMobileNumber(strMobileNo);
			if (!result) {
				return result;
			}
			result = clickOnGetOtp();
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

}
