package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.HomePage_OR;
import pojo.testdata.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class HomePageUtil {

	HomePage_OR homePageORObj;
	public Common_Function cfObj = new Common_Function();
	public LoginUtil loginutillObj;
	public List<String> homePageMsglist = new ArrayList<String>();

	public HomePageUtil(AppiumDriver<MobileElement> driver) {

		homePageORObj = new HomePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), homePageORObj);
	}

	public boolean clickOnCourseOnHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			int courseSize = homePageORObj.getListCourses().size();
			System.out.println("courseSize: " + courseSize);
			if (homePageORObj.getListCourses().size() == 0) {
				return false;
			}

			System.out.println("Course Attribute: " + homePageORObj.getListCoursesTitle().get(0).getText().toString());
			System.out.println(
					"Course Attribute: " + homePageORObj.getListCoursesTitle().get(0).getAttribute("text").toString());

			// Click on course on home page
			cfObj.commonClick(homePageORObj.getListCourses().get(0));

			// wait for course detail page to be opened

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from home page");
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyCourseDetailPage(AppiumDriver<MobileElement> driver, String strMobileNo) {
		boolean result = true;
		try {
			loginutillObj = new LoginUtil(driver);

			result = loginutillObj.verifyLogin(driver, strMobileNo);

			if (!result) {
				return result;
			}

			result = clickOnCourseOnHomePage(driver);

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyCourseSearch(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		try {
			loginutillObj = new LoginUtil(driver);

			result = loginutillObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);

			if (!result) {
				homePageMsglist.addAll(loginutillObj.loginMsgList);
				return result;
			}

			result = searchCourse(driver, testData.getCourseName());

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("verifyCourseSearch_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean searchCourse(AppiumDriver<MobileElement> driver, String strCourse) {
		boolean result = true;
		try {

			if (homePageORObj.getListSearchButton().size() == 0) {
				homePageMsglist.add("Search button is not display on home page");
				return false;
			}
			// Click on search icon
			clickOnSearchIcon();
			// Click on search box
			clickOnSearchInputBox();

			// enter course name
			result = cfObj.commonSetTextTextBox(homePageORObj.getListSearchTextBox().get(0), strCourse);

			if (!result) {
				return result;
			}

			if (homePageORObj.getListSearchResult().size() == 0) {
				homePageMsglist.add("No result found using search text: " + strCourse);
				return false;
			}

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("searchCourse_Exception: " + e.getMessage());
		}

		return result;
	}

	public void clickOnSearchIcon() {
		try {
			cfObj.commonClick(homePageORObj.getListSearchButton().get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnSearchInputBox() {
		try {
			cfObj.commonClick(homePageORObj.getListSearchTextBox().get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verifyMylibraryForNewUser(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			clickOnMyLibrary();
			// wait for no item in message
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.TEXT_SORRY, "id", 30);
			if (!result) {
				System.out.println("no conent message is not display for new user");
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public void clickOnMyLibrary() {
		try {
			cfObj.commonClick(homePageORObj.getListBottomMenuMyLibrary().get(0));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnTestSeries() {
		try {

			cfObj.commonClick(homePageORObj.getListBottomMenuMyTestSeries().get(0));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyMylibraryForGuestUser(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginutillObj = new LoginUtil(driver);

			result = loginutillObj.clickOnSkipLogin(driver);
			if (!result) {
				homePageMsglist.addAll(loginutillObj.loginMsgList);
				return result;
			}

			clickOnMyLibrary();

			// wait for login page to be opened

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.TEXT_BOX_MOBILE, "id", 20);

			if (!result) {
				homePageMsglist.add("Login Page not opened when click on my library for guest user");
				return result;
			}

			// Close login pop up

			result = closeLoginPopUp(driver);

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("verifyMylibraryForGuestUser_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean closeLoginPopUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(homePageORObj.getListBtnLoginClose().get(0));

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("closeLoginPopUp_Exception: " + e.getMessage());
		}
		return result;
	}

	public void openNaviagationMenu() {
		try {
			cfObj.commonClick(homePageORObj.openNavigationMenu());

		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
	}

	public boolean openMyProfilePage() {
		boolean result = true;
		try {
			List<MobileElement> navigationMenuElements = homePageORObj.getListNavigationMenuElements();

			for (int i = 0; i < navigationMenuElements.size(); i++) {
				String navigationMenuText = navigationMenuElements.get(i).getText();
				if (navigationMenuText.equalsIgnoreCase("My Profile")) {
					cfObj.commonClick(navigationMenuElements.get(i));
					return true;
				}
			}
			result = false;
			homePageMsglist.add("The navigation menu buttons not working");

		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
		return result;
	}
}
