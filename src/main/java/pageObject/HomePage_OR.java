package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage_OR {

	@AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.TextView")
	private List<MobileElement> listOfTextViewElements;
	
	public List<MobileElement> listOfTextView() {
		return listOfTextViewElements;
	}
	
	@AndroidFindBy(id = "action_search")
	private MobileElement searchCourseBtnElement;
	
	public MobileElement searchCourseBtn() {
		return searchCourseBtnElement;
	}
	
	@AndroidFindBy(id = "et_search")
	private MobileElement inputSearchBoxElement;
	
	public MobileElement inputSearchBox() {
		return inputSearchBoxElement;
	}
	
	@AndroidFindBy(id = "item_search_title")
	private List<MobileElement> listOfSearchItemTitleElements;
	
	public List<MobileElement> listOfSearchItemsTitle(){
		return listOfSearchItemTitleElements;
	}
	
	@AndroidFindBy(id = "item_search_img")
	private List<MobileElement> listOfSearchItemImgElements;
	
	public List<MobileElement> listOfSearchItemsImg(){
		return listOfSearchItemImgElements;
	}
	
	
	@AndroidFindBy(id = "bt_clear")
	private MobileElement clearInputSearchElement;
	
	public MobileElement clearInputSearch() {
		return clearInputSearchElement;
	}
		
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
	
	@AndroidFindBy(id = "tv_nav_home")
	private List<MobileElement> listBottomMenuMyHome;

	public List<MobileElement> getListBottomMenuMyHome() {
		return listBottomMenuMyHome;
	}

	@AndroidFindBy(id = "tv_nav_library")
	private List<MobileElement> listBottomMenuMyLibrary;

	public List<MobileElement> getListBottomMenuMyLibrary() {
		return listBottomMenuMyLibrary;
	}
	
	@AndroidFindBy(id = "tv_nav_explore")
	private List<MobileElement> listBottomMenuMyExplore;

	public List<MobileElement> getListBottomMenuMyExplore() {
		return listBottomMenuMyExplore;
	}

	@AndroidFindBy(id = "tv_nav_test_series")
	private List<MobileElement> listBottomMenuMyTestSeries;

	public List<MobileElement> getListBottomMenuMyTestSeries() {
		return listBottomMenuMyTestSeries;
	}
	
	@AndroidFindBy(id = "tv_nav_book")
	private List<MobileElement> listBottomMenuMyBook;

	public List<MobileElement> getListBottomMenuMyBook() {
		return listBottomMenuMyBook;
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

	@AndroidFindBy(id = "img_slider")
	private MobileElement imgSliderElement;
	
	public MobileElement imgSlider() {
		return imgSliderElement;
	}
	
	@AndroidFindBy(id = "txt_section_title")
	private List<MobileElement> sectionOfCoursesTitle;
	
	public List<MobileElement> sectionOfCoursesTitles(){
		return sectionOfCoursesTitle;
	}
	
	@AndroidFindBy(id = "tv_home_view_all")
	private List<MobileElement> viewAllBtnElements;
	
	public List<MobileElement> viewAllBtnList(){
		return viewAllBtnElements;
	}
	
	@AndroidFindBy(id = "tv_home_tab")
	private List<MobileElement> listOfHomeTabTexts;
	
	public List<MobileElement> listOfHomeTabText() {
		return listOfHomeTabTexts;
	}
	
	@AndroidFindBy(id = "iv_home_tab")
	private List<MobileElement> listOfHomeTabImgs;
	
	public List<MobileElement> listOfHomeTabImgs() {
		return listOfHomeTabImgs;
	}
	
	@AndroidFindBy(id = "ss_txt_title")
	private List<MobileElement> listOfSubjectTitle;
	
	public List<MobileElement> getListOfSubjectTitle() {
		return listOfSubjectTitle;
	}
	
	@AndroidFindBy(id = "ss_img")
	private List<MobileElement> listOfSubjectImgs;
	
	public List<MobileElement> getListOfSubjectImgs() {
		return listOfSubjectImgs;
	}
	
	@AndroidFindBy(xpath = "//androidx.appcompat.app.ActionBar.b")
	private List<MobileElement> exploreActionBarElements;
	
	public List<MobileElement> exploreActionbarElements(){
		return exploreActionBarElements;
	}
	
	@AndroidFindBy(id = "//androidx.recyclerview.widget.RecyclerView")
	private MobileElement viewOnExploreCourses;
	
	public MobileElement viewOnExploreCoursesPage() {
		return viewOnExploreCourses;
	}
	
	@AndroidFindBy(id = "android:id/button1")
	private MobileElement logoutBtnElement;
	
	public MobileElement logoutBtn() {
		return logoutBtnElement;
	}
	
	@AndroidFindBy(id = "iv_view_all_thumb")
	private List<MobileElement> imgOfCoursesInViewAll;
	
	public List<MobileElement> listOfImgOfCoursesInViewAll(){
		return imgOfCoursesInViewAll;
	}
	
	@AndroidFindBy(id = "tv_view_all_title")
	private List<MobileElement> titleOfCoursesInViewAll;
	
	public List<MobileElement> listOftitleOfCoursesInViewAll(){
		return titleOfCoursesInViewAll;
	}
}
