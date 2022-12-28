package util;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mozilla.javascript.ast.WhileLoop;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.HasSupportedPerformanceDataType;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import pojo.device.Environment;

import org.openqa.selenium.*;

@SuppressWarnings("unused")
public class Common_Function {
	public APIUtils apiUtilObj;
	public APIResponse apiResponseObj;

	public AppiumDriver<MobileElement> commonStartAndOpenURLBrowser()
			throws ParserConfigurationException, SAXException, IOException {

		DesiredCapabilities capability = null;
		AppiumDriver<MobileElement> driver = null;
		try {
			capability = new DesiredCapabilities();

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
				capability.setCapability("automationName", getCapebility().get("automationName"));
			}
//			capability.setCapability("deviceName", getCapebility().get("deviceName"));
//			capability.setCapability(CapabilityType.VERSION, getCapebility().get("deviceVersion"));
			capability.setCapability("platformName", "Android");
			// capability.setCapability("clearDeviceLogsOnStart",true);
			capability.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);
			capability.setCapability("appPackage", getCapebility().get("appPackage"));
			capability.setCapability("appActivity", "com.studyiq.android.activities.SplashActivity");
//			capability.setCapability("clearDeviceLogsOnStart", true);
			capability.setCapability("noReset", false);
			capability.setCapability("unicodeKeyboard", false);
			capability.setCapability("resetKeyboard", false);

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				capability.setCapability("app", getCapebility().get("strFilePath"));
				capability.setCapability("isRealMobile", true);
				capability.setCapability("visual", true);
				// capability.setCapability("browserstack.debug", true);
			}
			System.out.println("capability " + capability);
			System.out.println("hubUrl: " + getCapebility().get("remoteAddress"));
			driver = new AndroidDriver<MobileElement>(new URL(getCapebility().get("remoteAddress")), capability);

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}

		catch (

		Exception e) {
			e.printStackTrace();

		} finally {

		}
		return driver;
	}

	public boolean commonWaitForElementToBeVisible(AppiumDriver<MobileElement> driver, MobileElement elementforWait,
			int timeOutInSeconds) {
		boolean result = true;
		try {
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(elementforWait));
			result = element.isDisplayed();

		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible ");
		}

		return result;
	}

	public boolean commonWaitForElementToBeLocatedAndVisible(AppiumDriver<MobileElement> driver, String elementforWait,
			String strfindType, int timeOutInSeconds) {
		boolean result = true;
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementforWait)));
			}
			result = element.isDisplayed();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible: " + elementforWait);
		}
		return result;
	}

	public void commonClick(MobileElement iclickInfo) throws IOException, SAXException, ParserConfigurationException {

		try {
			iclickInfo.click();
			Thread.sleep(500);

		} catch (Exception e) {

		}
	}

	public void commonClick(WebElement iclickInfo) throws IOException, SAXException, ParserConfigurationException {

		try {
			iclickInfo.click();
			Thread.sleep(500);

		} catch (Exception e) {

		}
	}

	/**
	 * @param iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */
	public boolean commonSetTextTextBox(MobileElement iTextBoxInfo, String sText)
			throws IOException, ParserConfigurationException, SAXException {

		boolean Result = false;
		try {
			iTextBoxInfo.clear();
			iTextBoxInfo.click();
			iTextBoxInfo.sendKeys(sText);
			Result = commonVerifyValueTextBox(iTextBoxInfo, sText);

		} catch (Exception ex) {
			Result = false;
		}
		return Result;
	}

	/**
	 * @param iTextBoxInfo   WebElement reference
	 * @param sExpectedValue String type expected value
	 * @return boolean True/False as a result on the basis of verification pass or
	 *         fail
	 * @throws IOException                  if IO exception occurred
	 * @throws SAXException                 if SAX exception occurred
	 * @throws ParserConfigurationException if parser configuration exception
	 *                                      occurred
	 */
	public boolean commonVerifyValueTextBox(WebElement iTextBoxInfo, String sExpectedValue)
			throws IOException, SAXException, ParserConfigurationException {

		String sTempStr = null;
		boolean Result = false;

		try {
			sExpectedValue = sExpectedValue.trim().toLowerCase();

			sTempStr = iTextBoxInfo.getAttribute("text").trim().toLowerCase();

			if ((sTempStr.contains(sExpectedValue))) {
				Result = true;
			} else {
				Result = false;
			}
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	public JsonObject getJsonData(String strfileLocation) {
		JsonElement jsonData = null;
		try {
			jsonData = new JsonParser().parse(new FileReader(strfileLocation));
			System.out.println(jsonData.getAsJsonObject());
			System.out.println(jsonData.getAsJsonObject().get("मराठी").getAsJsonObject().get("title"));
		} catch (Exception e) {

		}
		return jsonData.getAsJsonObject();
	}

	public boolean commonWaitForListSizeTobeGreaterThanZero(AppiumDriver<MobileElement> driver, By locator,
			int timeOutInSeconds) {
		boolean result = true;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			List<WebElement> elementLst = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			if (elementLst.size() == 0) {
				return result;
			}

		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	public boolean commonWaitpresenceOfElementLocated(WebDriver driver, int timeOutInSeconds, By locator)
			throws TimeoutException {

		boolean result = true;
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (element == null) {
				result = false;
			}
		} catch (NoSuchElementException e) {

		} catch (TimeoutException e) {

		} catch (Exception e) {

		}
		return result;
	}

	public Boolean scrollByID(String xpath, AppiumDriver<MobileElement> driver) {
		Boolean result = false;
		try {
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."
					+ "instance(0)).scrollIntoView(new UiSelector().resourceId(\"" + xpath + "\").instance(0));"))
					.click();

			result = true;

			System.out.println("element is displayed ");

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public static String randomPhoneNumber(long len, String start) {
		String num = null;
		try {
			if (len > 10)
				throw new IllegalStateException("To many digits");
			long tLen = (long) Math.pow(10, len - 1) * 9;

			long number = (long) (Math.random() * tLen) + (long) Math.pow(10, len - 1) * 1;

			String tVal = start + number + "";

			num = tVal.substring(0, 10);

			if (num.length() != len) {

				throw new IllegalStateException("The random number '" + num + "' is not '" + len + "' digits");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to generate Random Mobile number");

		}
		return num;
	}

	public static String getCurrentDateTime() {
		LocalDateTime currentDateTime = java.time.LocalDateTime.now();
		return currentDateTime.toString();
	}

	public int getRandomNumber(int start, int end) {
		Random rand = new Random();
		int randomNum = rand.nextInt((end - start) + 1) + start;
		return randomNum;
	}

	/**
	 * @param iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */
	public boolean commonSetTextTextBox_Action(AppiumDriver<MobileElement> driver, String sText)
			throws IOException, ParserConfigurationException, SAXException {

		boolean Result = false;
		try {
			if (!ConfigFileReader.strRunMode.equalsIgnoreCase("Cloud")) {
				@SuppressWarnings("rawtypes")
				List enterText = Arrays.asList("text", sText);

				Map<String, Object> cmd = ImmutableMap.of("command", "input", "args", enterText);

				driver.executeScript("mobile: shell", cmd);
			} else {
				Actions action = new Actions(driver);
				action.sendKeys(sText).perform();
			}
		} catch (Exception ex) {
			Result = false;
		}
		return Result;
	}

	@SuppressWarnings("rawtypes")
	public void sendKeys(AppiumDriver<MobileElement> driver, String sText) {
		try {
			if (!ConfigFileReader.strRunMode.equalsIgnoreCase("Cloud")) {
				List enterText = Arrays.asList("text", sText);

				Map<String, Object> cmd = ImmutableMap.of("command", "input", "args", enterText);

				driver.executeScript("mobile: shell", cmd);
			} else {
				Actions action = new Actions(driver);
				action.sendKeys(sText).perform();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean commonWaitForElementToPresent(AppiumDriver<MobileElement> driver, String elementforWait,
			int timeOutInSeconds) {
		boolean result = true;
		try {
			// Element to be present
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementforWait)));
			result = element.isDisplayed();
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible ");
		}

		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public boolean scrollUtillTheElementFound(AppiumDriver<MobileElement> driver, String elementToFind,
			String strFindBy) {
		boolean result = true, isFound = false;
		int count = 0;
		List<MobileElement> elements = null;
		try {

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			if (strFindBy.equalsIgnoreCase("xpath")) {
				elements = driver.findElements(By.xpath(elementToFind));
			} else if (strFindBy.equalsIgnoreCase("id")) {
				elements = driver.findElements(By.id(elementToFind));
			}
			isFound = elements.size() > 0;
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int startYCoordinate = (int) (height * .5);
			int endYCoordinate = (int) (height * .2);
			while (!isFound && count < 5) {
				count = count + 1;
				action.press(PointOption.point(middleOfX, startYCoordinate))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
				if (strFindBy.equalsIgnoreCase("xpath")) {
					isFound = driver.findElements(By.xpath(elementToFind)).size() > 0;
				} else if (strFindBy.equalsIgnoreCase("id")) {
					isFound = driver.findElements(By.id(elementToFind)).size() > 0;
				}
			}

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("error in scroll");
			result = false;
		}
		return isFound;
	}

	public static String convert(String str) {

		// Create a char array of given String
		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {

			// If first character of a word is found
			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				// If it is in lower-case
				if (ch[i] >= 'a' && ch[i] <= 'z') {

					// Convert into Upper-case
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			}

			// If apart from first character
			// Any one is in Upper-case
			else if (ch[i] >= 'A' && ch[i] <= 'Z')

				// Convert into Lower-Case
				ch[i] = (char) (ch[i] + 'a' - 'A');
		}

		// Convert the char array to equivalent String
		String st = new String(ch);
		System.out.println("new string: " + st);
		return st;
	}

	/**
	 * @param iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */
	@SuppressWarnings("deprecation")
	public boolean commonClearAndSendKeys(AppiumDriver<MobileElement> driver, MobileElement iTextBoxInfo, String sText)
			throws IOException, ParserConfigurationException, SAXException {

		boolean Result = true;
		String prefieldText;
		try {
			iTextBoxInfo.click();
			prefieldText = iTextBoxInfo.getText().toString();
			prefieldText = prefieldText.split(",")[0];
			for (int i = 0; i < prefieldText.length(); i++) {
				((PressesKey) driver).pressKeyCode(67);
				Thread.sleep(500);
			}
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				commonSetTextTextBox_Action(driver, sText);
			} else {
				commonSetTextTextBox_Action(driver, sText);
			}
			driver.hideKeyboard();
		} catch (Exception ex) {
			Result = false;
		}
		return Result;
	}

	public MobileElement commonGetElement(AppiumDriver<MobileElement> driver, String elementforWait,
			String strfindType) {
		MobileElement element = null;
		try {
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = driver.findElement(By.xpath(elementforWait));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(elementforWait));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return element;
	}

	public List<MobileElement> commonGetElements(AppiumDriver<MobileElement> driver, String elementforWait,
			String strfindType) {
		List<MobileElement> element = null;
		try {
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = driver.findElements(By.xpath(elementforWait));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = driver.findElements(By.id(elementforWait));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = driver.findElements(By.className(elementforWait));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return element;
	}

	public List<WebElement> commonWaitForElementToBeGreaterThan(AppiumDriver<MobileElement> driver,
			String elementforWait, String strfindType, int timeOutInSeconds) {
		List<WebElement> elements = null;
		try {
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			if (strfindType.equalsIgnoreCase("xpath")) {
				elements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(strfindType), 1));
			} else if (strfindType.equalsIgnoreCase("id")) {
				elements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id(strfindType), 1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return elements;
	}

	public HashMap<String, Integer> getMemoryInfo(AppiumDriver<MobileElement> driver, String strPackage,
			String strPerfType, int strMemoryCaptureWait) throws Exception {
		List<List<Object>> data = ((HasSupportedPerformanceDataType) driver).getPerformanceData(strPackage, strPerfType,
				strMemoryCaptureWait);
		HashMap<String, Integer> readableData = new HashMap<>();
		for (int i = 0; i < data.get(0).size(); i++) {
			int val;
			if (data.get(1).get(i) == null) {
				val = 0;
			} else {
				val = Integer.parseInt((String) data.get(1).get(i));
			}
			readableData.put((String) data.get(0).get(i), val);
		}
		return readableData;
	}

	public static String getCurrentDateInGivenFormat(String dateFormat) {
		SimpleDateFormat DateFor;
		String stringDate = null;
		try {
			Date date = new Date();
			DateFor = new SimpleDateFormat(dateFormat);
			stringDate = DateFor.format(date);
			System.out.println("Date Format with E, dd MMM yyyy:" + stringDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return stringDate;
	}

	public void closePopUpIfExist(AppiumDriver<MobileElement> driver) {
		List<MobileElement> popups;
		try {
			Thread.sleep(10000);
			popups = commonGetElements(driver, "android.widget.ImageView", "class");
			if (popups.size() == 2) {
				commonClick(popups.get(1));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public String getTimedifference(long start, long end) {
		String strTime = null;
		try {
			NumberFormat formatter = new DecimalFormat("#0.00000");
			strTime = formatter.format((end - start) / 1000d) + " seconds";
			System.out.println(strTime);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}

		return strTime;

	}

	public static String getFutureDateTime(String currentDate, int numberOfDays) {
		String newDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();

			c.setTime(sdf.parse(currentDate));

			// Incrementing the date by n day
			c.add(Calendar.DAY_OF_MONTH, numberOfDays);
			newDate = sdf.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static String getCurrentate(String strPattern) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);

		String date = simpleDateFormat.format(new Date());
		System.out.println(date);
		return date;
	}

	public static Integer RandomNumber(int start, int end) {
		Random rand = new Random();
		int randomNum = rand.nextInt((end - start) + 1) + start;
		return randomNum;

	}

	public static Date convertStringToDate(String strDate) {
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
			date = formatter.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static boolean isSorted(List<Date> dateString) {
		for (int i = 0; i < dateString.size() - 1; ++i) {
			System.out.println(dateString.get(i));
			if (dateString.get(i).compareTo(dateString.get(i + 1)) > 0)
				return false;
		}
		return true;
	}

	public boolean scrollUpUtillTheElementFound(AppiumDriver<MobileElement> driver, String elementToFind) {
		boolean result = true, isFound = false;
		int count = 0;
		try {

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			List<MobileElement> elements = driver.findElements(By.xpath(elementToFind));
			isFound = elements.size() > 0;
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int endYCoordinate = (int) (height * .7);
			int startYCoordinate = (int) (height * .2);
			while (!isFound && count < 5) {
				count = count + 1;
				action.press(PointOption.point(middleOfX, startYCoordinate))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
				isFound = driver.findElements(By.xpath(elementToFind)).size() > 0;
			}

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("error in scroll");
			result = false;
		}
		return isFound;
	}

	/**
	 * @param iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */
	@SuppressWarnings("deprecation")
	public boolean commonClearTextAndSendKeys(AppiumDriver<MobileElement> driver, MobileElement iTextBoxInfo,
			String sText) throws IOException, ParserConfigurationException, SAXException {

		boolean Result = true;
		String prefieldText;
		try {
			iTextBoxInfo.click();
			prefieldText = iTextBoxInfo.getText().toString();
			prefieldText = prefieldText.split(",")[0];
			for (int i = 0; i < prefieldText.length(); i++) {
				((PressesKey) driver).pressKeyCode(67);
			}
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				commonSetTextTextBox_Action(driver, sText);
			} else {
				commonSetTextTextBox_Action(driver, sText);
			}
		} catch (Exception ex) {
			Result = false;
		}
		return Result;
	}

	public String getLangaugeEnum(String strLangauge) {
		String strLanguagekey = null;
		try {
			if (strLangauge.equalsIgnoreCase("English")) {
				strLanguagekey = "en";
			} else if (strLangauge.equalsIgnoreCase("Marathi")) {
				strLanguagekey = "mr";
			} else if (strLangauge.equalsIgnoreCase("Hinglish")) {
				strLanguagekey = "hr";
			} else if (strLangauge.equalsIgnoreCase("Hindi")) {
				strLanguagekey = "hi";
			} else if (strLangauge.equalsIgnoreCase("Panjabi")) {
				strLanguagekey = "pa";
			} else if (strLangauge.equalsIgnoreCase("Tamil")) {
				strLanguagekey = "ta";
			} else if (strLangauge.equalsIgnoreCase("Telugu")) {
				strLanguagekey = "te";
			} else if (strLangauge.equalsIgnoreCase("Gujarati")) {

				strLanguagekey = "gu";
			} else if (strLangauge.equalsIgnoreCase("Assamese")) {

				strLanguagekey = "as";
			} else if (strLangauge.equalsIgnoreCase("Bengali")) {

				strLanguagekey = "bn";
			} else if (strLangauge.equalsIgnoreCase("Kannada")) {

				strLanguagekey = "kn";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return strLanguagekey;

	}

	@SuppressWarnings("rawtypes")
	public void scrollUtill(AppiumDriver<MobileElement> driver, int noOfTime) {
		int count = 0;
		try {

			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int startYCoordinate = (int) (height * .7);
			int endYCoordinate = (int) (height * .6);
			while (count < noOfTime) {
				count = count + 1;
				action.press(PointOption.point(middleOfX, startYCoordinate))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
						.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
			}

		} catch (Exception e) {
			System.out.println("error in scroll");
		}
	}

	public void hideKeyBoard(AppiumDriver<MobileElement> driver) {
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public Map<String, String> getCapebility() {
		Map<String, String> capebilityMap = new HashMap<>();
		String strDeviceIndex;
		try {
			if (ConfigFileReader.strEnv.contains("prod")) {
				capebilityMap.put("appPackage", "com.studyiq.android");

			} else if (ConfigFileReader.strEnv.contains("stag")) {
				capebilityMap.put("appPackage", "com.studyiq.android.stag");
			} else {
				capebilityMap.put("appPackage", "com.studyiq.android.dev");
			}

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

				capebilityMap.put("deviceName", ConfigFileReader.strDeviceName);
				capebilityMap.put("deviceVersion", ConfigFileReader.strDeviceVersion);
				capebilityMap.put("remoteAddress", "http://localhost:4723/wd/hub");
				capebilityMap.put("automationName", "UiAutomator2");

			} else if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				strDeviceIndex = ConfigFileReader.strDeviceIndex;
				int index = Integer.parseInt(strDeviceIndex);
				capebilityMap.put("deviceName", getDeviceData().get(index).getDevice());
				capebilityMap.put("deviceVersion", getDeviceData().get(index).getOsVersion());
				capebilityMap.put("remoteAddress", "https://" + ConfigFileReader.userName + ":"
						+ ConfigFileReader.accessKey + "@mobile-hub.lambdatest.com/wd/hub");
				capebilityMap.put("strFilePath", ConfigFileReader.strFilePath);

			}
		} catch (Exception e) {

		}
		return capebilityMap;
	}

	public List<Environment> getDeviceData() {
		JsonElement jsonData = null;
		List<Environment> testData = null;
		try {
			jsonData = new JsonParser()
					.parse(new FileReader(System.getProperty("user.dir") + "/src/main/resources/device.json"));
			JsonElement dataSet = jsonData.getAsJsonObject().get("environments").getAsJsonArray();
			testData = new Gson().fromJson(dataSet, new TypeToken<List<Environment>>() {
			}.getType());

		} catch (JsonIOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonSyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return testData;
	}

	public void openDeepLink(AppiumDriver<MobileElement> driver, String strDeepLinkUrl) {
		System.out.println(driver.getPlatformName());
		if (driver.getPlatformName().equalsIgnoreCase("ios")) {
			Map<String, String> mobileCommand = new HashMap<String, String>();
			mobileCommand.put("bundleId", "com.apple.mobilesafari");
			driver.executeScript("mobile:launchApp", (Map<String, ?>) mobileCommand);
		} else {
			driver.closeApp();
			driver.get(strDeepLinkUrl);
		}
	}

	public void commonClick_Action(AppiumDriver<MobileElement> driver, MobileElement iclickInfo)
			throws IOException, SAXException, ParserConfigurationException {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(iclickInfo);
			action.click();
			action.perform();
			Thread.sleep(500);

		} catch (Exception e) {

		}
	}

	public boolean scrollUtillTheElementFound(AppiumDriver<MobileElement> driver, String elementToFind) {
		boolean result = true, isFound = false;
		int count = 0;
		try {

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			List<MobileElement> elements = (List<MobileElement>) driver.findElements(By.xpath(elementToFind));
			isFound = elements.size() > 0;
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int startYCoordinate = (int) (height * .7);
			int endYCoordinate = (int) (height * .2);
			while (!isFound && count < 8) {
				count = count + 1;
				action.press(PointOption.point(middleOfX, startYCoordinate))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
				isFound = driver.findElements(By.xpath(elementToFind)).size() > 0;
			}

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("error in scroll");
			result = false;
		}
		return isFound;
	}

	public MobileElement scrollIntoText(AppiumDriver<MobileElement> driver, String visibleText) {
		return driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))"));
	}

	public void swipeLeftOnElement(WebElement element, AppiumDriver<MobileElement> driver) {
		Point point = element.getLocation();
		Dimension eleSize = element.getSize();
		int centerX = point.getX() + (eleSize.getWidth() / 2);
		int centerY = point.getY() + (eleSize.getHeight() / 2);
		int moveToX = point.getX() - 190;
		int moveToY = point.getY() + (eleSize.getHeight() / 2);

		System.out.println(centerX + " and " + centerY);
		System.out.println(moveToX + " and " + moveToY);

		new TouchAction(driver).press(PointOption.point(centerX, centerY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(moveToX, moveToY)).release().perform();
	}

	public void swipeRightOnElement(WebElement element, AppiumDriver<MobileElement> driver) {

		Point point = element.getLocation();
		Dimension eleSize = element.getSize();
		int centerX = point.getX() + (eleSize.getWidth() / 2);
		int centerY = point.getY() + (eleSize.getHeight() / 2);
		int moveToX = point.getX() + (eleSize.getWidth()) + 190;
		int moveToY = point.getY() + (eleSize.getHeight() / 2);

		new TouchAction(driver).press(PointOption.point(centerX, centerY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(moveToX, moveToY)).release().perform();
	}

	public void scrollToMobileElement(MobileElement locator, AppiumDriver<MobileElement> driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", locator);
		} catch (Exception e) {
			System.err.println("Unable to scroll to MobileElement. MobileElement is not visible.");
		}
	}

	public boolean pressAndroidKey(AppiumDriver<MobileElement> driver, key KEY, int noOfTimes) {
		boolean result = true;
		try {
			for (int i = 0; i < noOfTimes; i++) {
				if (KEY == key.BACK) {
					// ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
					((AndroidDriver<MobileElement>) driver)
							.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.BACK));
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			result = false;
			System.out.println(e.getMessage());
		}
		return result;
	}

	public enum key {
		BACK, ENTER
	}

	public void tapOnCenter(AppiumDriver<MobileElement> driver) {
		try {
			new TouchAction(driver).tap(PointOption.point(377, 699)).perform();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean handleHints(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(1000);
			tapOnCenter(driver);
			tapOnCenter(driver);
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("handleHints_Exception: " + e.getMessage());
			return result;
		}
		return result;
	}

}
