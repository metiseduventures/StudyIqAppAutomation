package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.LoginSignup_NegativeUtil;
import util.ConfigFileReader;

public class LoginNegativeTest extends BaseTest{
	
	LoginSignup_NegativeUtil loginNegativeUtil;

	@Test
	public void verifyLogin() {

		boolean result = true;
		loginNegativeUtil = new LoginSignup_NegativeUtil(driver);
		result = loginNegativeUtil.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
		Assert.assertEquals(result, true, loginNegativeUtil.loginSignUpNegMsgList.toString());

	}

	@Test
	public void verifyUserSignUp() {

		boolean result = true;
		loginNegativeUtil = new LoginSignup_NegativeUtil(driver);
		result = loginNegativeUtil.verifySignUp(driver);
		Assert.assertEquals(result, true, loginNegativeUtil.loginSignUpNegMsgList.toString());

	}
}
