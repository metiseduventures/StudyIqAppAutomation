package test_scripts;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import applicationUtil.HomePageUtil;
import pojo.testdata.TestData;

public class HomePageTest extends BaseTest {

	HomePageUtil homePageUtilObj;

	@Test
	public void verifyCourseDetailPage() {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifyCourseDetailPage(driver, "9958544199");
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsglist.toString());

	}

	@Test(dataProvider = "getData", enabled = true)
	public void verifySearchCourse(TestData testData) {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifyCourseSearch(driver, testData);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsglist.toString());

	}

	@Test
	public void verifyMyHomePage() {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifyHomePage(driver);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsglist.toString());

	}

	@DataProvider
	public Object[][] getData() throws FileNotFoundException {
		JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/TestData.json"));
		JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
		List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
		}.getType());
		Object[][] returnValue = new Object[testData.size()][1];
		int index = 0;
		for (Object[] each : returnValue) {
			each[0] = testData.get(index++);
		}
		return returnValue;
	}

}
