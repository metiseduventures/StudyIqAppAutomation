package test_scripts;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import util.Common_Function;

public class BaseTest {

	Common_Function cfObj = new Common_Function();
	public static AppiumDriver<MobileElement> driver;
	
	public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

	@BeforeMethod
	public void setUp() {
		try {
			driver = cfObj.commonStartAndOpenURLBrowser();
			System.out.println("SessionId: " + driver.getSessionId());

		} catch (Exception e)

		{
			e.printStackTrace();
		}

	}

	@AfterMethod
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
