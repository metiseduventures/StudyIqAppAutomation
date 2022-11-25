package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.ProfilePageUtil;

public class ProfilePageTest extends BaseTest {
	ProfilePageUtil profilePageUtil;

	@Test
	public void verifyProfilePageMain() {
		boolean result = true;
		profilePageUtil = new ProfilePageUtil(driver);
		result = profilePageUtil.verifyProfilePage(driver);
		Assert.assertEquals(result, true, profilePageUtil.ProfilePageMsglist.toString());
	}

}
