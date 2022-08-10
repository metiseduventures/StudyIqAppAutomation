package com.listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import util.Common_Function;

public class GetScreenShot {

	static String strRunMode;

	static {
		strRunMode = System.getProperty("runMode");
		//strRunMode = "local";
	}

	public static String capture(WebDriver driver, String screenShotName) throws IOException {
		String dest;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		if (!strRunMode.equalsIgnoreCase("Cloud")) {
			dest = System.getProperty("user.dir") + "/ErrorScreenshots/" + screenShotName + "_"
					+ Common_Function.getCurrentDateTime() + ".png";
		} else {
			dest = System.getProperty("user.dir") + "/ErrorScreenshots/" + screenShotName + "_"
					+ Common_Function.getCurrentDateTime() + ".png";

		}
		System.out.println("dest:" + source.getAbsolutePath().toString());
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		System.out.println("destination:" + destination.getAbsolutePath().toString());
		if (strRunMode.equalsIgnoreCase("Cloud")) {

			dest = dest.replaceAll("@[0-9]", "");
			dest = dest.replace("var/lib/jenkins/workspace/AppAutomation/", "job/AppAutomation/ws/");

		}
		System.out.println("dest:" + dest);
		return dest;
	}

}
