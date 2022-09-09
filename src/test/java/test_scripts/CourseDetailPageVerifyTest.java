package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.CoursePageDetailsVerifyUtil;


public class CourseDetailPageVerifyTest extends BaseTest{

	CoursePageDetailsVerifyUtil coursePageDetailUtil;
	@Test
	public void verifyCourseDetailPage() throws Exception {
		boolean result = true;
		coursePageDetailUtil = new CoursePageDetailsVerifyUtil(driver);
		result = coursePageDetailUtil.verifyCoursePage(driver);
		Assert.assertEquals(result, true, coursePageDetailUtil.cdpVerifyMsgList.toString());
	}
}