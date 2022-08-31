package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.LoginUtil;
import util.ConfigFileReader;

public class LoginTest extends BaseTest {

	LoginUtil loginUtilObj;

	@Test
	public void verifyLogin() {

		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		result = loginUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
		Assert.assertEquals(result, true, loginUtilObj.loginMsgList.toString());

	}

	@Test
	public void verifyUserSignUp() {

		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		result = loginUtilObj.verifySignUp(driver);
		Assert.assertEquals(result, true, loginUtilObj.loginMsgList.toString());

	}

}
