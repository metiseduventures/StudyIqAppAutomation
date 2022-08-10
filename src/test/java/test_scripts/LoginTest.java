package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.LoginUtil;

public class LoginTest extends BaseTest {

	LoginUtil loginUtilObj;

	@Test
	public void verifyLogin() {

		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		result = loginUtilObj.verifyLogin(driver, "9958544199");
		Assert.assertEquals(result, true, "");

	}
	
	
	@Test
	public void verifyUserSignUp() {

		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		result = loginUtilObj.verifySignUp(driver);
		Assert.assertEquals(result, true, "");

	}

}
