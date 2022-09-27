package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.FeedPageUtil;

public class FeedTest extends BaseTest {

	FeedPageUtil feedPageUtil;

	@Test
	public void verifyFeed() {
		boolean result = true;
		feedPageUtil = new FeedPageUtil(driver);
		result = feedPageUtil.verifyFeedPage(driver);
		Assert.assertEquals(result, true, feedPageUtil.feedPageMsglist.toString());
	}
}
