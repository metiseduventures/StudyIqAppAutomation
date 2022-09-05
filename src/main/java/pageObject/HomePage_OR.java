package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage_OR {

	@AndroidFindBy(id = "iv_home_section_landscape")
	private List<MobileElement> listCourses;

	public List<MobileElement> getListCourses() {
		return listCourses;
	}

	public void setListCourses(List<MobileElement> listCourses) {
		this.listCourses = listCourses;
	}

	@AndroidFindBy(id = "tv_home_section_title")
	private List<MobileElement> listCoursesTitle;

	public List<MobileElement> getListCoursesTitle() {
		return listCoursesTitle;
	}

	@AndroidFindBy(id = "action_search")
	private List<MobileElement> listSearchButton;

	public List<MobileElement> getListSearchButton() {
		return listSearchButton;
	}

	public List<MobileElement> getListSearchTextBox() {
		return listSearchTextBox;
	}

	public List<MobileElement> getListSearchResult() {
		return listSearchResult;
	}

	@AndroidFindBy(id = "et_search")
	private List<MobileElement> listSearchTextBox;

	@AndroidFindBy(id = "item_search_img")
	private List<MobileElement> listSearchResult;

	@AndroidFindBy(id = "tv_nav_library")
	private List<MobileElement> listBottomMenuMyLibrary;

	public List<MobileElement> getListBottomMenuMyLibrary() {
		return listBottomMenuMyLibrary;
	}

	@AndroidFindBy(id = "tv_nav_test_series")
	private List<MobileElement> listBottomMenuMyTestSeries;

	public List<MobileElement> getListBottomMenuMyTestSeries() {
		return listBottomMenuMyTestSeries;
	}

	@AndroidFindBy(id = "iv_quick_login_close")
	private List<MobileElement> listBtnLoginClose;

	public List<MobileElement> getListBtnLoginClose() {
		return listBtnLoginClose;
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.ImageButton")
	private MobileElement navigationScreen;

	public MobileElement openNavigationMenu() {
		return navigationScreen;
	}

	@AndroidFindBy(xpath = "//androidx.appcompat.widget.LinearLayoutCompat/android.widget.CheckedTextView")
	private List<MobileElement> listNavigationMenuElements;

	public List<MobileElement> getListNavigationMenuElements() {
		return listNavigationMenuElements;
	}

}
