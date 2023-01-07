package com.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import test_scripts.BaseTest;
import util.ExtentManager;

public class TestListener implements ITestListener {

	// Extent Report Declarations
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");

	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println(result);
		System.out.println(result.getTestName());
		System.out.println((result.getMethod().getMethodName() + " started!"));
		// testWatcher.set(extent.createTest(result.getTestContext().getAttribute("testName").toString()));
		// ExtentTest extentTest =
		// extent.createTest(result.getTestContext().getAttribute("testName").toString(),result.getMethod().getDescription());
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");

	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		String path = null;
		String strSessionId = null;
		try {
			System.out.println((result.getMethod().getMethodName() + " failed!"));
			if (BaseTest.driver != null) {
				strSessionId = BaseTest.driver.toString();
				path = GetScreenShot.capture(BaseTest.driver, result.getMethod().getMethodName());
			}
			System.out.println("strSessionId_inListerner: " + strSessionId);
			test.get().fail(result.getThrowable());
			test.get().fail("details", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			test.get().fail(strSessionId);

		} catch (Exception e) {
			e.printStackTrace();
			test.get().fail(e.getMessage() + strSessionId);
			test.get().fail(strSessionId);

		}

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {

		System.out.println((result.getMethod().getMethodName() + " skipped!"));

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

}
