package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.PageFactory;

import apiUtil.OtpUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.HomePage_OR;
import pageObject.Login_OR;
import pageObject.ProfilePage_OR;
import pojo.testdata.TestData;
import util.Common_Function;

public class ProfilePageUtil {

	ProfilePage_OR profilePage_OR;
	public Common_Function cfObj = new Common_Function();
	public HomePage_OR homePageORObj;
	public LoginUtil loginUtillObj;
	public HomePageUtil homePageUtil;
	Login_OR login_OR;
	public List<String> ProfilePageMsglist = new ArrayList<String>();
	OtpUtil otpUtilObj;

	public ProfilePageUtil(AppiumDriver<MobileElement> driver) {
		profilePage_OR = new ProfilePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), profilePage_OR);
	}

	public boolean verifyProfilePage(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		loginUtillObj = new LoginUtil(driver);
		try {
			boolean checkLoginPageOrNot = loginUtillObj.checkSignUpLoginPage(driver);
			if (checkLoginPageOrNot == true) {
				loginUtillObj = new LoginUtil(driver);

				result = loginUtillObj.doSignUp(driver);
				if (!result) {
					ProfilePageMsglist.addAll(loginUtillObj.loginMsgList);
					return result;
				}
			} else {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 30);
				if (result) {
					cfObj.commonClick(cfObj.commonGetElement(driver, ConstantUtil.IMG_CLOSE, "id"));
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 30);
				if (!result) {
					ProfilePageMsglist.add("Home page not opened after login");
					return result;
				}
			}

			result = openNavigationAndClickProfile(driver);
			if (!result) {
				return result;
			}

			result = verifyInputBoxes(driver);
			if (!result) {
				return result;
			}

			result = inputDataIntoBoxes(driver, testData);
			if (!result) {
				return result;
			}

			result = clickUpdateBtn(driver);
			if (!result) {
				return result;
			}

			result = openNavigationAndClickProfile(driver);
			if (!result) {
				return result;
			}

			result = checkUpdationDoneOrNot(testData);
			if (!result) {
				return result;
			}

			result = changeNumberInProfile();
			if (!result) {
				return result;
			}

			result = openNavigationAndClickProfile(driver);
			if (!result) {
				return result;
			}

			result = changeEmailInProfile();
			if (!result) {
				return result;
			}

			result = returnToHomePage(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("verifyProfilePageException " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean openNavigationAndClickProfile(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		homePageUtil = new HomePageUtil(driver);
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 30);

			if (!result) {
				ProfilePageMsglist.add("Home page not opened in proper state");
				return result;
			}

			homePageUtil.openNaviagationMenu();

			result = homePageUtil.openMyProfilePage();
			if (!result) {
				ProfilePageMsglist.addAll(homePageUtil.homePageMsglist);
				return false;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("popUpCloseException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyInputBoxes(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "et_profile_name", "id", 10);
			if (!result) {
				ProfilePageMsglist.add("input name box not present");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "et_profile_address", "id", 10);
			if (!result) {
				ProfilePageMsglist.add("input address box not present");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "et_profile_pincode", "id", 10);
			if (!result) {
				ProfilePageMsglist.add("input pincode box not present");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "et_profile_city", "id", 10);
			if (!result) {
				ProfilePageMsglist.add("input city box not present");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "et_profile_state", "id", 10);
			if (!result) {
				ProfilePageMsglist.add("input state box not present");
				return result;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("verifyInputBoxesPresentException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean inputDataIntoBoxes(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		try {
			result = cfObj.commonSetTextTextBox(profilePage_OR.inputName(), testData.getName());
			if (!result) {
				ProfilePageMsglist.add("input_name not working");
				return result;
			}

			result = cfObj.commonSetTextTextBox(profilePage_OR.inputAddress(), testData.getAddress());
			if (!result) {
				ProfilePageMsglist.add("input_address not working");
				return result;
			}

			result = cfObj.commonSetTextTextBox(profilePage_OR.inputPincode(), testData.getPincode());
			if (!result) {
				ProfilePageMsglist.add("input_pincode not working");
				return result;
			}

			cfObj.commonClick(profilePage_OR.inputCity());

			String cityString = testData.getCity();
			cfObj.scrollIntoText(driver, cityString);

			List<MobileElement> optionsCity = profilePage_OR.cityOptions();
			for (int i = 0; i < optionsCity.size(); i++) {
				String cityOptionString = optionsCity.get(i).getText();
				if (cityOptionString.equalsIgnoreCase(cityString)) {
					cfObj.commonClick(optionsCity.get(i));
					result = true;
					break;
				}
				result = false;
			}
			if (!result) {
				ProfilePageMsglist.add("input_city not working");
				return result;
			}

			cfObj.commonClick(profilePage_OR.inputState());

			String stateString = testData.getState();
			cfObj.scrollIntoText(driver, stateString);

			List<MobileElement> optionsState = profilePage_OR.stateOptions();
			for (int i = 0; i < optionsState.size(); i++) {
				String stateOptionString = optionsState.get(i).getText();
				if (stateOptionString.equalsIgnoreCase(stateString)) {
					cfObj.commonClick(optionsState.get(i));
					result = true;
					break;
				}
				result = false;
			}

			if (!result) {
				ProfilePageMsglist.add("input_state not working");
				return result;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("inputDataInBoxesException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickUpdateBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(profilePage_OR.updateBtn());

		} catch (Exception e) {
			ProfilePageMsglist.add("clickUpdateBtnException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean checkUpdationDoneOrNot(TestData testData) {
		boolean result = true;
		try {

			String UpdatedName = profilePage_OR.inputName().getText();
			if (UpdatedName.equalsIgnoreCase(testData.getName())) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated name is different");
				return false;
			}

			String UpdatedAddress = profilePage_OR.inputAddress().getText();
			if (UpdatedAddress.equalsIgnoreCase(testData.getAddress())) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated address is different");
				return false;
			}

			String UpdatedPincode = profilePage_OR.inputPincode().getText();
			if (UpdatedPincode.equalsIgnoreCase(testData.getPincode())) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated pincode is different");
				return false;
			}

			String UpdatedCity = profilePage_OR.inputCity().getText();
			if (UpdatedCity.equalsIgnoreCase(testData.getCity())) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated city is different");
				return false;
			}

			String UpdatedState = profilePage_OR.inputState().getText();
			if (UpdatedState.equalsIgnoreCase(testData.getState())) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated state is different");
				return false;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("checkUpdationDoneOrNotException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean changeNumberInProfile() {
		boolean result = true;
		String strOtp = null;
		otpUtilObj = new OtpUtil();

		try {
			String strMobileNumberString = Common_Function.randomPhoneNumber(10, "9");
			System.out.println("strMobileNo: " + strMobileNumberString);

			cfObj.commonClick(profilePage_OR.updatedNoBtn());

			result = cfObj.commonSetTextTextBox(profilePage_OR.updatedNo(), strMobileNumberString);
			if (!result) {
				ProfilePageMsglist.add("Not able to enter mobile number");
				return false;
			}

			cfObj.commonClick(profilePage_OR.clickOtpButton());

			strOtp = otpUtilObj.getOtp(strMobileNumberString, true);
			if (strOtp == null) {
				ProfilePageMsglist.add("Error in getting otp");
				return false;
			}

			cfObj.commonClick(profilePage_OR.inputOtp());

			result = cfObj.commonSetTextTextBox(profilePage_OR.inputOtp(), strOtp);
			if (!result) {
				ProfilePageMsglist.add("Not able to enter otp");
				return false;
			}

			cfObj.commonClick(profilePage_OR.submitOtpBtn());

		} catch (Exception e) {
			ProfilePageMsglist.add("changeNumberInProfileException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean changeEmailInProfile() {
		boolean result = true;
		try {
			String randomNumber = Common_Function.randomPhoneNumber(10, "9");
			String emailIdString = "TestUser" + randomNumber + "@gmail.com";
			System.out.println("Email id: " + emailIdString);

			cfObj.commonClick(profilePage_OR.updatedMailBtn());

			cfObj.commonSetTextTextBox(profilePage_OR.updatedMail(), emailIdString);
			if (!result) {
				ProfilePageMsglist.add("Not able to enter email id");
				return false;
			}
			System.out.println("The code is working fine of email change in profile but no OTP");

			cfObj.commonClick(profilePage_OR.closeButton());

		} catch (Exception e) {
			ProfilePageMsglist.add("changeEmailInProfileException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean returnToHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK)); // Go back to page

		} catch (Exception e) {
			ProfilePageMsglist.add("returnToHomePageException" + e.getMessage());
			result = false;
		}
		return result;
	}
}
