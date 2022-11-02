package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.HomePageUtil;

public class HomePageTest extends BaseTest {

	HomePageUtil homePageUtilObj;

	@Test
	public void verifyCourseDetailPage() {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifyCourseDetailPage(driver, "9958544199");
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsglist.toString());

	}

	@Test
	public void verifySearchCourse() {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifyCourseSearch(driver);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsglist.toString());

	}

	@Test
	public void verifyMyHomePage() {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifyHomePage(driver);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsglist.toString());

	}

}
