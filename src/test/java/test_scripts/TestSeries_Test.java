package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.TestSeriesUtil;

public class TestSeries_Test extends BaseTest{
	
	TestSeriesUtil testSeriesUtil;
	
	@Test
	public void verifyFreeTestSeries() {
		boolean result = true;
		testSeriesUtil = new TestSeriesUtil(driver);
		result = testSeriesUtil.verifyFreeTestSeries(driver);
		Assert.assertEquals(result, true, testSeriesUtil.testseriesMsgList.toString());
	}

}
