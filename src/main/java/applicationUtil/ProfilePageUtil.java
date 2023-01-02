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
import util.Common_Function;
import util.Common_Function.direction;

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

	public boolean verifyProfilePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtillObj = new LoginUtil(driver);
		try {

			result = loginUtillObj.doSignUp(driver);
			if (!result) {
				ProfilePageMsglist.addAll(loginUtillObj.loginMsgList);
				return result;
			}

			result = openNavigationAndClickProfile(driver);
			if (!result) {
				return result;
			}

			result = verifyInputBoxes(driver);
			if (!result) {
				return result;
			}

			result = inputDataIntoBoxes(driver);
			if (!result) {
				return result;
			}

			result = clickUpdateBtn(driver);
			if (!result) {
				return result;
			}

			result = checkUpdationDoneOrNot(driver);
			if (!result) {
				return result;
			}

			result = changeNumberInProfile(driver);
			if (!result) {
				return result;
			}

			result = changeEmailInProfile(driver);
			if (!result) {
				return result;
			}

			result = verifySignOut(driver);
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

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfInputBoxes().get(0), 10);
			if (!result) {
				ProfilePageMsglist.add("input name box not present");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfInputBoxes().get(3), 10);
			if (!result) {
				ProfilePageMsglist.add("input address box not present");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfInputBoxes().get(4), 10);
			if (!result) {
				ProfilePageMsglist.add("input pincode box not present");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfInputBoxes().get(1), 10);
			if (!result) {
				ProfilePageMsglist.add("email box box not present");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfInputBoxes().get(2), 10);
			if (!result) {
				ProfilePageMsglist.add("phone box not present");
				return result;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("verifyInputBoxesPresentException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean inputDataIntoBoxes(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonSetTextTextBox(profilePage_OR.listOfInputBoxes().get(0), "shubh");
			if (!result) {
				ProfilePageMsglist.add("input_name not working");
				return result;
			}

			driver.hideKeyboard();

			result = cfObj.commonSetTextTextBox(profilePage_OR.listOfInputBoxes().get(3), "#1776, nanital");
			if (!result) {
				ProfilePageMsglist.add("input_address not working");
				return result;
			}

			driver.hideKeyboard();

			result = cfObj.commonSetTextTextBox(profilePage_OR.listOfInputBoxes().get(4), "122003");
			if (!result) {
				ProfilePageMsglist.add("input_pincode not working");
				return result;
			}

			driver.hideKeyboard();

		} catch (Exception e) {
			ProfilePageMsglist.add("inputDataInBoxesException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickUpdateBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.scrollUtillNtimes(driver, 1, direction.DOWN);

			cfObj.commonClick(profilePage_OR.listOfButtons().get(2));

			String toastMsgLangChange = profilePage_OR.toastFeedLang().getAttribute("name");

			if (toastMsgLangChange.equalsIgnoreCase("Profile updated successfully")) {
				return true;
			} else {
				ProfilePageMsglist.add("The profile changed toast not visible");
				return false;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("clickUpdateBtnException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean checkUpdationDoneOrNot(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String UpdatedAddress = profilePage_OR.listOfInputBoxes().get(3).getText();
			if (UpdatedAddress.equalsIgnoreCase("#1776, nanital")) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated address is different");
				return false;
			}

			String UpdatedPincode = profilePage_OR.listOfInputBoxes().get(4).getText();
			if (UpdatedPincode.equalsIgnoreCase("122003")) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated pincode is different");
				return false;
			}

			String UpdatedCity = profilePage_OR.listOfInputBoxes().get(5).getText();
			if (UpdatedCity.equalsIgnoreCase("GURUGRAM")) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated city is different");
				return false;
			}

			String UpdatedState = profilePage_OR.listOfInputBoxes().get(6).getText();
			if (UpdatedState.equalsIgnoreCase("HARYANA")) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated state is different");
				return false;
			}

			// scroll method to top
			cfObj.scrollUtillNtimes(driver, 1, direction.UP);

			String UpdatedName = profilePage_OR.listOfInputBoxes().get(0).getText();
			if (UpdatedName.equalsIgnoreCase("shubh")) {
				result = true;
			} else {
				ProfilePageMsglist.add("The updated name is different");
				return false;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("checkUpdationDoneOrNotException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean changeNumberInProfile(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// scroll method to bottom
			cfObj.scrollUtillNtimes(driver, 1, direction.DOWN);

			String strMobileNumberString = Common_Function.randomPhoneNumber(10, "3");
			System.out.println("strMobileNo: " + strMobileNumberString);

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfButtons().get(1), 5);
			if (!result) {
				ProfilePageMsglist.add("The edit number icon not visible");
				return result;
			}

			cfObj.commonClick(profilePage_OR.listOfButtons().get(1));

			result = cfObj.commonSetTextTextBox(profilePage_OR.updatedNoInput(), strMobileNumberString);
			if (!result) {
				ProfilePageMsglist.add("Not able to enter mobile number");
				return false;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.clickUpdateContinueBtn(), 5);
			if (!result) {
				ProfilePageMsglist.add("The continue button not visible");
				return result;
			}

			cfObj.commonClick(profilePage_OR.clickUpdateContinueBtn());

			String msgOtpSent = profilePage_OR.otpSentMsg().getText();

			if (msgOtpSent.equalsIgnoreCase("OTP has been sent to verify Mobile number")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.verifyOtpButton(), 5);
				if (!result) {
					ProfilePageMsglist.add("The submit button is not visible");
					return result;
				}

				((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

				((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

				result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfButtons().get(1), 5);
				if (!result) {
					ProfilePageMsglist.add("After coming back from edit number, it is not profile page");
					return result;
				}

			} else {
				ProfilePageMsglist.add("The otp sent message is wrong for number");
				return false;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("changeNumberInProfileException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean changeEmailInProfile(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String randomNumber = Common_Function.randomPhoneNumber(10, "9");
			String emailIdString = "TestUser" + randomNumber + "@gmail.com";
			System.out.println("Email id: " + emailIdString);

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfButtons().get(0), 5);
			if (!result) {
				ProfilePageMsglist.add("The edit mail icon not visible");
				return result;
			}

			cfObj.commonClick(profilePage_OR.listOfButtons().get(0));

			result = cfObj.commonSetTextTextBox(profilePage_OR.updatedEmailInput(), emailIdString);
			if (!result) {
				ProfilePageMsglist.add("Not able to enter email");
				return false;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.clickUpdateContinueBtn(), 5);
			if (!result) {
				ProfilePageMsglist.add("The continue button not visible");
				return result;
			}

			cfObj.commonClick(profilePage_OR.clickUpdateContinueBtn());

			String msgOtpSent = profilePage_OR.otpSentMsg().getText();

			if (msgOtpSent.equalsIgnoreCase("OTP has been sent to your email address")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.verifyOtpButton(), 5);
				if (!result) {
					ProfilePageMsglist.add("The submit button is not visible");
					return result;
				}

				((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

				((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

				result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.listOfButtons().get(0), 5);
				if (!result) {
					ProfilePageMsglist.add("After coming back from edit email, it is not profile page");
					return result;
				}

			} else {
				ProfilePageMsglist.add("The otp sent msg is wrong for email");
				return false;
			}

		} catch (Exception e) {
			ProfilePageMsglist.add("changeEmailInProfileException" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifySignOut(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.signOutBtn(), 5);
			if (!result) {
				ProfilePageMsglist.add("The sign out is not visible");
				return result;
			}

			cfObj.commonClick(profilePage_OR.signOutBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.logOutBtn(), 5);
			if (!result) {
				ProfilePageMsglist.add("The sign out is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.cancelBtn(), 5);
			if (!result) {
				ProfilePageMsglist.add("The sign out is not visible");
				return result;
			}

			cfObj.commonClick(profilePage_OR.cancelBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.signOutBtn(), 5);
			if (!result) {
				ProfilePageMsglist.add("The sign out is not visible");
				return result;
			}

			cfObj.commonClick(profilePage_OR.signOutBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, profilePage_OR.logOutBtn(), 5);
			if (!result) {
				ProfilePageMsglist.add("The sign out is not visible");
				return result;
			}

			cfObj.commonClick(profilePage_OR.logOutBtn());

		} catch (Exception e) {
			ProfilePageMsglist.add("verifySignOutException" + e.getMessage());
			result = false;
		}
		return result;
	}
}
