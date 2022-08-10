package test_scripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import util.Common_Function;

public class BaseTest {

	Common_Function cfObj = new Common_Function();
	public static AppiumDriver<MobileElement> driver;

	@BeforeClass

	public void setUp() {
		try {
			driver = cfObj.commonStartAndOpenURLBrowser();
			System.out.println("SessionId: " + driver.getSessionId());

		} catch (Exception e)

		{
			e.printStackTrace();
		}

	}

	@AfterClass
	public void tearDown() {
		try {

			driver.resetApp();

			if (driver != null) {
				driver.quit();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
