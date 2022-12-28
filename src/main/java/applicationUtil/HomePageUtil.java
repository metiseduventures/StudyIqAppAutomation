package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CoursePageDetailsVerify_OR;
import pageObject.HomePage_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class HomePageUtil {

	HomePage_OR homePageORObj;
	public Common_Function cfObj = new Common_Function();
	public LoginUtil loginutillObj;
	public List<String> homePageMsglist = new ArrayList<String>();
	CoursePageDetailsVerify_OR cpdv_ORObj;
	CourseDetailPage courseDetailPageUtil;

	public HomePageUtil(AppiumDriver<MobileElement> driver) {

		homePageORObj = new HomePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), homePageORObj);
	}

	public boolean verifyHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginutillObj = new LoginUtil(driver);
		try {

			result = loginutillObj.doSignUp(driver);
			if (!result) {
				homePageMsglist.addAll(loginutillObj.loginMsgList);
				return result;
			}

			result = verifyHomeTabElements(driver);
			if (!result) {
				return result;
			}

			result = verifyImgSlider(driver);
			if (!result) {
				return result;
			}

			result = verifyCoursesOnHomePage(driver);
			if (!result) {
				return result;
			}

			result = verifyHomePageBottomNavbar(driver);
			if (!result) {
				return result;
			}

			result = verifyHomePageTopToolbar(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			homePageMsglist.add("verifyHomePage_Exception");
			result = false;
		}
		return result;
	}

	public boolean verifyHomePageTopToolbar(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean bool = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyHome().get(0), 10);
			if (!result) {
				homePageMsglist.add("The text of my home on bottom is not visible");
				return result;
			}

			cfObj.commonClick(homePageORObj.getListBottomMenuMyHome().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.listOfTextView().get(0), 10);
			if (!result) {
				homePageMsglist.add("The home page title is not visible at top");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.searchCourseBtn(), 10);
			if (!result) {
				homePageMsglist.add("The search course btn is not visible");
				return result;
			}

			cfObj.commonClick(homePageORObj.searchCourseBtn());

			cfObj.commonSetTextTextBox(homePageORObj.inputSearchBox(), "bank");

			driver.hideKeyboard();

			List<MobileElement> listOfTitles = homePageORObj.listOfSearchItemsTitle();

			for (int i = 0; i < listOfTitles.size(); i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.listOfSearchItemsTitle().get(i),
						10);
				if (!result) {
					homePageMsglist.add("The search item title is not visible");
					return result;
				}
			}
			cfObj.scrollUtill(driver, 1);

			cfObj.commonClick(homePageORObj.listOfSearchItemsImg().get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from search");
				return result;
			}

			cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBtnBuyNow().get(0), 5);
			if (!result) {
				homePageMsglist.add("Buy btn is not visible");
				return result;
			}

			cfObj.commonClick(homePageORObj.getListBtnBuyNow().get(0));

			while (bool) {
				driver.navigate().back();
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 5);
				if (result) {
					bool = false;
				} else {
					bool = true;
				}
			}

			result = verifyNavigationMenu(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			homePageMsglist.add("verifyHomePageToolbar_Exception");
			result = false;
		}
		return result;
	}

	public boolean verifyNavigationMenu(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean bool = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.openNavigationMenu(), 10);
			if (!result) {
				homePageMsglist.add("The naviagtion menu btn not visible");
				return result;
			}
			openNaviagationMenu();

			List<MobileElement> navigationMenuElements = homePageORObj.getListNavigationMenuElements();

			result = cfObj.commonWaitForElementToBeVisible(driver, navigationMenuElements.get(0), 10);
			if (!result) {
				homePageMsglist.add("The home btn is not visible");
				return result;
			}
			cfObj.commonClick(navigationMenuElements.get(0));

			openNaviagationMenu();

			cfObj.scrollIntoText(driver, "Log out");

			int n = navigationMenuElements.size();
			for (int i = 1; i < n; i++) {
				bool = true;

				cfObj.scrollIntoText(driver, "Log out");

				result = cfObj.commonWaitForElementToBeVisible(driver, navigationMenuElements.get(i), 10);
				if (!result) {
					homePageMsglist.add("The menu element is not visible");
					return result;
				}

				String logOutText = navigationMenuElements.get(i).getText();
				if (logOutText.equalsIgnoreCase("Log out")) {
					cfObj.commonClick(navigationMenuElements.get(i));

					cfObj.commonClick(homePageORObj.logoutBtn());

					break;
				}

				cfObj.commonClick(navigationMenuElements.get(i));

				while (bool) {
					driver.navigate().back();
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 5);
					if (result) {
						bool = false;
					} else {
						bool = true;
					}
				}

				openNaviagationMenu();

			}

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("verifyNavigationMenu_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyHomeTabElements(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean cond = true;

		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyHome().get(0), 10);
			if (!result) {
				homePageMsglist.add("The button of my home on bottom is not visible");
				return result;
			}

			cfObj.commonClick(homePageORObj.getListBottomMenuMyHome().get(0));

			List<MobileElement> textOfTabElements = homePageORObj.listOfHomeTabText();
			List<MobileElement> imgOfTabElements = homePageORObj.listOfHomeTabImgs();
			int n = textOfTabElements.size();
			for (int i = 0; i < n - 1; i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, textOfTabElements.get(i), 10);
				if (!result) {
					homePageMsglist.add("The text in tab is not visible");
					return result;
				}

				result = imgOfTabElements.get(i).isDisplayed();
				if (!result) {
					homePageMsglist.add("The img is not displayed with text");
					return result;
				}

				cfObj.commonClick(textOfTabElements.get(i));

			}

			cfObj.swipeLeftOnElement(textOfTabElements.get(n - 1), driver);

			while (cond) {
				cfObj.swipeRightOnElement(homePageORObj.listOfHomeTabText().get(0), driver);

				if (homePageORObj.listOfHomeTabText().get(0).getText().equalsIgnoreCase("Home")) {
					cfObj.commonClick(homePageORObj.listOfHomeTabText().get(0));
					cond = false;
				}
			}

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("verifyHomeTabElements_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean verifyImgSlider(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int count = 4;
		try {
			for (int k = 0; k < count; k++) {
				result = homePageORObj.imgSlider().isDisplayed();
				if (!result) {
					homePageMsglist.add("The image is not visible");
					return result;
				}

				cfObj.swipeLeftOnElement(homePageORObj.imgSlider(), driver);
			}

			cfObj.swipeRightOnElement(homePageORObj.imgSlider(), driver);
			cfObj.commonClick(homePageORObj.imgSlider());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from home page");
				return result;
			}

			driver.navigate().back();

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("verifyImgSlider_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyCoursesOnHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean bool = true;
		courseDetailPageUtil = new CourseDetailPage(driver);
		try {
			List<MobileElement> titlesOfSectionCourses = homePageORObj.sectionOfCoursesTitles();

			for (int i = 0; i < titlesOfSectionCourses.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.sectionOfCoursesTitles().get(i),
						10);
				if (!result) {
					homePageMsglist.add("The title of section course is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.viewAllBtnList().get(i), 10);
				if (!result) {
					homePageMsglist.add("The view all is not visible");
					return result;
				}
			}

			cfObj.commonClick(homePageORObj.getListCourses().get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from home page");
				return result;
			}

			while (bool) {
				driver.navigate().back();
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 5);
				if (result) {
					bool = false;
				} else {
					bool = true;
				}
			}

			cfObj.commonClick(homePageORObj.viewAllBtnList().get(0));

			List<MobileElement> titleOfCoursesInViewAll = homePageORObj.listOftitleOfCoursesInViewAll();
			List<MobileElement> imgOfCoursesInViewAll = homePageORObj.listOfImgOfCoursesInViewAll();

			for (int i = 0; i < titleOfCoursesInViewAll.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, titleOfCoursesInViewAll.get(i), 10);
				if (!result) {
					homePageMsglist.add("The title of the course is not visible");
					return result;
				}

				result = imgOfCoursesInViewAll.get(i).isDisplayed();
				if (!result) {
					homePageMsglist.add("The img of the course is not visible");
					return result;
				}

				cfObj.scrollUtill(driver, 1);
			}

			cfObj.commonClick(imgOfCoursesInViewAll.get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from view all");
				return result;
			}

			driver.navigate().back();

			cfObj.swipeLeftOnElement(homePageORObj.getListCourses().get(0), driver);

			cfObj.scrollUtill(driver, 5);

			cfObj.swipeLeftOnElement(homePageORObj.getListCourses().get(0), driver);
			cfObj.swipeRightOnElement(homePageORObj.getListCourses().get(0), driver);

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("verifyCoursesOnHomePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyHomePageBottomNavbar(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			// my library
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyLibrary().get(0),
					10);
			if (!result) {
				homePageMsglist.add("The text of my library on bottom is not visible");
				return result;
			}
			String libraryBottomText = homePageORObj.getListBottomMenuMyLibrary().get(0).getText();

			cfObj.commonClick(homePageORObj.getListBottomMenuMyLibrary().get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tiSearch", "id", 5);
			if (!result) {
				homePageMsglist.add("The search box of library is not visible");
				return result;
			}

			String libraryPageText = "My Library";
			if (libraryBottomText.equalsIgnoreCase(libraryPageText)) {
				result = true;
			} else {
				homePageMsglist.add("The title and bottom text are not same");
				return false;
			}

			// my testseries
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyTestSeries().get(0),
					10);
			if (!result) {
				homePageMsglist.add("The text of my testseries on bottom is not visible");
				return result;
			}
			String testseriesBottomText = homePageORObj.getListBottomMenuMyTestSeries().get(0).getText();

			cfObj.commonClick(homePageORObj.getListBottomMenuMyTestSeries().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.listOfTextView().get(0), 10);
			if (!result) {
				homePageMsglist.add("My testseries title is not visible at top");
				return result;
			}
			String testSeriesPageText = homePageORObj.listOfTextView().get(0).getText();
			if (testseriesBottomText.equalsIgnoreCase(testSeriesPageText)) {
				result = true;
			} else {
				homePageMsglist.add("The title and bottom text are not same");
				return false;
			}

			// my books
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyBook().get(0), 10);
			if (!result) {
				homePageMsglist.add("The text of my books on bottom is not visible");
				return result;
			}
			String booksBottomText = homePageORObj.getListBottomMenuMyBook().get(0).getText();

			cfObj.commonClick(homePageORObj.getListBottomMenuMyBook().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.listOfTextView().get(0), 10);
			if (!result) {
				homePageMsglist.add("My books title is not visible at top");
				return result;
			}
			String booksPageText = homePageORObj.listOfTextView().get(0).getText();
			if (booksBottomText.equalsIgnoreCase(booksPageText)) {
				result = true;
			} else {
				homePageMsglist.add("The title and bottom text are not same");
				return false;
			}

			// my home
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyHome().get(0), 10);
			if (!result) {
				homePageMsglist.add("The text of my home on bottom is not visible");
				return result;
			}
			String homeBottomText = homePageORObj.getListBottomMenuMyHome().get(0).getText();

			cfObj.commonClick(homePageORObj.getListBottomMenuMyHome().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.listOfTextView().get(0), 10);
			if (!result) {
				homePageMsglist.add("My home title is not visible at top");
				return result;
			}
			String homePageText = homePageORObj.listOfTextView().get(0).getText();
			if (homePageText.equalsIgnoreCase(homeBottomText)) {
				result = true;
			} else {
				homePageMsglist.add("The title and bottom text are not same");
				return false;
			}

			// my feed
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyFeed().get(0), 10);
			if (!result) {
				homePageMsglist.add("The text of my feed on bottom is not visible");
				return result;
			}
			String feedBottomText = homePageORObj.getListBottomMenuMyFeed().get(0).getText();

			cfObj.commonClick(homePageORObj.getListBottomMenuMyFeed().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.listOfTextView().get(0), 10);
			if (!result) {
				homePageMsglist.add("My feed title is not visible at top");
				return result;
			}
			String feedPageText = homePageORObj.listOfTextView().get(0).getText();
			if (feedBottomText.equalsIgnoreCase(feedPageText)) {
				result = true;
			} else {
				homePageMsglist.add("The title and bottom text are not same");
				return false;
			}

		} catch (Exception e) {
			result = false;

			homePageMsglist.add("verifyHomePageNavbar_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnCourseOnHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strCourse = "GS - Dr";
		try {
			int courseSize = homePageORObj.getListCourses().size();
			System.out.println("courseSize: " + courseSize);
			if (homePageORObj.getListCourses().size() == 0) {
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

			cfObj.commonClick(homePageORObj.getListSearchResult().get(0));

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from home page");
				return result;
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public boolean clickOnTestseries(AppiumDriver<MobileElement> driver, String testName) {
		boolean result = true;
		String strCourse = testName;
		try {
			int courseSize = homePageORObj.getListCourses().size();
			System.out.println("courseSize: " + courseSize);
			if (homePageORObj.getListCourses().size() == 0) {
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

			cfObj.commonClick(homePageORObj.getListSearchResult().get(0));

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from home page");
				return result;
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickOnLiveCourseOnHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strCourse;
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("prod")) {
				strCourse = "complete ncert";

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

				cfObj.commonClick(homePageORObj.getListSearchResult().get(0));

			} else {

				strCourse = "UPSC/IAS";

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

				cfObj.commonClick(homePageORObj.getListSearchResult().get(0));

			}

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from home page");
				return result;
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickOnBookOnHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strCourse;
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("prod")) {
				strCourse = "India Polity";

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

				cfObj.commonClick(homePageORObj.getListSearchResult().get(0));

			} else {

				strCourse = "ssc book";

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

				cfObj.commonClick(homePageORObj.getListSearchResult().get(0));

			}

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from home page");
				return result;
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickOnTestSeriesOnHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strCourse;
		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("prod")) {
				strCourse = "upsc prelims test";

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

				cfObj.commonClick(homePageORObj.getListSearchResult().get(0));

			} else {

				strCourse = "p3-";

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

				cfObj.commonClick(homePageORObj.getListSearchResult().get(0));

			}

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.BUY_ONE, "id", 30);
			if (!result) {
				System.out.println("Course detail page not opened when click on course from home page");
				return result;
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

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyHome().get(0), 10);
			if (!result) {
				homePageMsglist.add("The button of my home on bottom is not visible");
				return result;
			}

			cfObj.commonClick(homePageORObj.getListBottomMenuMyHome().get(0));

			result = clickOnCourseOnHomePage(driver);

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyCourseSearch(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			loginutillObj = new LoginUtil(driver);

			result = loginutillObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);

			if (!result) {
				homePageMsglist.addAll(loginutillObj.loginMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getListBottomMenuMyHome().get(0), 10);
			if (!result) {
				homePageMsglist.add("The button of my home on bottom is not visible");
				return result;
			}

			cfObj.commonClick(homePageORObj.getListBottomMenuMyHome().get(0));

			result = searchCourse(driver);

		} catch (Exception e) {
			result = false;
			homePageMsglist.add("verifyCourseSearch_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean searchCourse(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strCourse = "punjab";
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

	public void openNaviagationMenu() {
		try {
			cfObj.commonClick(homePageORObj.openNavigationMenu());

		} catch (Exception e) {
			System.out.println("openNavigationMenuException " + e.getMessage());
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
			return result;
		}
		return result;
	}

}