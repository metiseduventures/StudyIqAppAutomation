package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.LibraryPageUtil;
import util.ConfigFileReader;

public class LibraryPageTest extends BaseTest{
	
	LibraryPageUtil libraryPageUtil;
	boolean result = true;
	ConfigFileReader configFileReader= new ConfigFileReader();
	
	@Test
	public void verifyCourseContentFromLibrary() {
		libraryPageUtil = new LibraryPageUtil(driver);
		result = libraryPageUtil.verifyLibraryContent(driver);
		Assert.assertEquals(result, true, libraryPageUtil.libraryPageMsgList.toString());
	}
	
	@Test
	public void verifyMyLibrary() {
		libraryPageUtil = new LibraryPageUtil(driver);
		result = libraryPageUtil.verifyMyLibrary(driver);
		Assert.assertEquals(result, true, libraryPageUtil.libraryPageMsgList.toString());
	}
}
