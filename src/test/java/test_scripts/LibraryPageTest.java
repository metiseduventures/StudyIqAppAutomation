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

import applicationUtil.LibraryPageUtil;
import pojo.testdata.TestData;

public class LibraryPageTest extends BaseTest{
	
	LibraryPageUtil libraryPageUtil;
	boolean result = true;

	@Test(dataProvider = "getData", enabled = true)
	public void verifyLibraryPageMain(TestData testData) {
		libraryPageUtil = new LibraryPageUtil(driver);
		result = libraryPageUtil.verifyLibraryPage(driver, testData);
		Assert.assertEquals(result, true, libraryPageUtil.libraryPageMsgList.toString());
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
