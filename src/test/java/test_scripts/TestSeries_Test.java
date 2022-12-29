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

import applicationUtil.TestSeriesUtil;
import pojo.testdata.TestDataTest;

public class TestSeries_Test extends BaseTest {

	TestSeriesUtil testSeriesUtil;

	@Test(dataProvider = "getData", enabled = true)
	public void verifyPrimaryNormalTest(TestDataTest testDataTest) {
		boolean result = true;
		testSeriesUtil = new TestSeriesUtil(driver);
		result = testSeriesUtil.verifyPrimaryNormalTest(driver, testDataTest);
		Assert.assertEquals(result, true, testSeriesUtil.testseriesMsgList.toString());
	}
	
	@Test(dataProvider = "getData", enabled = true)
	public void verifySecondaryNormalTest(TestDataTest testDataTest) {
		boolean result = true;
		testSeriesUtil = new TestSeriesUtil(driver);
		result = testSeriesUtil.verifySecondaryNormalTest(driver, testDataTest);
		Assert.assertEquals(result, true, testSeriesUtil.testseriesMsgList.toString());
	}

	@SuppressWarnings("deprecation")
	@DataProvider
	public Object[][] getData() throws FileNotFoundException {
		JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/TestDataTest.json"));
		JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet1");
		List<TestDataTest> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestDataTest>>() {
		}.getType());
		Object[][] returnValue = new Object[testData.size()][1];
		int index = 0;
		for (Object[] each : returnValue) {
			each[0] = testData.get(index++);
		}
		return returnValue;
	}
}
