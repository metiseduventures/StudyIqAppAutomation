package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CoursePageDetailsVerify_OR {

	@AndroidFindBy(id = "txt_course_title")
	private List<MobileElement> titleOfPageElement;

	public List<MobileElement> titleOfPage() {
		return titleOfPageElement;
	}

	@AndroidFindBy(id = "btn_buy_one")
	private List<MobileElement> buyNowBtnElementAbove;

	public List<MobileElement> buyNowBtnAbove() {
		return buyNowBtnElementAbove;
	}
	
	@AndroidFindBy(id = "btn_buy_two")
	private MobileElement buyNowBtnElementBelow;

	public MobileElement buyNowBtnBelow() {
		return buyNowBtnElementBelow;
	}

	@AndroidFindBy(id = "txt_description")
	private MobileElement courseDescriptionElement;

	public MobileElement courseDescription() {
		return courseDescriptionElement;
	}

	@AndroidFindBy(id = "btn_show_content")
	private MobileElement viewCourseContentElement;

	public MobileElement viewCourseContent() {
		return viewCourseContentElement;
	}

	@AndroidFindBy(id = "txt_select_language")
	private MobileElement selectLangElement;

	public MobileElement selectLang() {
		return selectLangElement;
	}

	@AndroidFindBy(id = "android:id/button1")
	private MobileElement continueBtnLangElement;

	public MobileElement continueBtnLang() {
		return continueBtnLangElement;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Share\"]")
	private MobileElement shareCourseElement;

	public MobileElement shareCourse() {
		return shareCourseElement;
	}

	@AndroidFindBy(id = "tvFolName")
	private List<MobileElement> namesOfCourseContentElements;

	public List<MobileElement> namesOfCourseContentElements() {
		return namesOfCourseContentElements;
	}

	@AndroidFindBy(id = "constFolder")
	private List<MobileElement> openDrawerOfContentElements;

	public List<MobileElement> openDrawerIcons() {
		return openDrawerOfContentElements;
	}

	@AndroidFindBy(id = "inner_txt_title")
	private List<MobileElement> insideOfnamesOfCourseContentElements;
	
	public List<MobileElement> insideNamesOfCourseContentElements(){
		return insideOfnamesOfCourseContentElements;
	}
	
	@AndroidFindBy(id = "constraint_load_more")
	private MobileElement loadMoreCourseContentElement;
	
	public MobileElement loadMoreCourseContent() {
		return loadMoreCourseContentElement;
	}
	
	@AndroidFindBy(id = "tv_rv_main_load")
	private MobileElement loadMoreCourseContentTextElement;
	
	public MobileElement loadMoreCourseContentText() {
		return loadMoreCourseContentTextElement;
	}
	
	@AndroidFindBy(id = "txt_exam_covered_title")
	private List<MobileElement> examCoveredTxtElement;
	
	public List<MobileElement> examCoveredTxt() {
		return examCoveredTxtElement;
	}
	
	@AndroidFindBy(id = "txt_author_name")
	private MobileElement authorNameElement;
	
	public MobileElement authorName() {
		return authorNameElement;
	}
	
	@AndroidFindBy(id = "txt_author_designation")
	private MobileElement authorDesiginationElement;
	
	public MobileElement authorDesigination() {
		return authorDesiginationElement;
	}
	
	@AndroidFindBy(id = "txt_author_bio")
	private List<MobileElement> authorBioElements;
	
	public List<MobileElement> authorBios(){
		return authorBioElements;
	}
	
	@AndroidFindBy(id = "img_author")
	private MobileElement authorImageElement;
	
	public MobileElement authorImage() {
		return authorImageElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.LinearLayout")
	private MobileElement authorBoxElement;
	
	public MobileElement authorBox() {
		return authorBoxElement;
	}

	@AndroidFindBy(id = "txt_emi")
	private MobileElement emiTextElement;
	
	public MobileElement emiText() {
		return emiTextElement;
	}

	@AndroidFindBy(id = "btn_lang")
	private List<MobileElement> demoVideoLangElement;
	
	public List<MobileElement> demoVideoLang(){
		return demoVideoLangElement;
	}
	
	@AndroidFindBy(id = "demo_image")
	private List<MobileElement> demoVideoElements;
	
	public List<MobileElement> demoVideos(){
		return demoVideoElements;
	}
	
	@AndroidFindBy(id = "txt_discount_price_2")
	private List<MobileElement> coursePriceAtAboveElement;
	
	public List<MobileElement> coursePriceAtAbove() {
		return coursePriceAtAboveElement;
	}
	
	@AndroidFindBy(id = "txt_main_price")
	private MobileElement coursePriceAtBottomElement;
	
	public MobileElement mainPriceAtBottom() {
		return coursePriceAtBottomElement;
	}
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.TextView")
	private List<MobileElement> buyNowAllTxtElements;
	
	public List<MobileElement> buyNowAllTxt(){
		return buyNowAllTxtElements;
	}
	
	@AndroidFindBy(id = "img_bundle_course")
	private List<MobileElement> freeCoursesElements;
	
	public List<MobileElement> freeCourses(){
		return freeCoursesElements;
	}
	
	@AndroidFindBy(id = "txt_testimonial_title")
	private MobileElement titleOfTestimonialElement;
	
	public MobileElement titleOfTestimonial() {
		return titleOfTestimonialElement;
	}
	
	@AndroidFindBy(id = "img_testimonial")
	private List<MobileElement> imageTestimonialList;
	
	public List<MobileElement> imageTestimonials(){
		return imageTestimonialList;
	}
	
	@AndroidFindBy(id = "txt_name")
	private List<MobileElement> nameTestimonialList;
	
	public List<MobileElement> nameTestimonials(){
		return nameTestimonialList;
	}
	
	@AndroidFindBy(id = "txt_des")
	private List<MobileElement> desTestimonialList;
	
	public List<MobileElement> desTestimonials(){
		return desTestimonialList;
	}
	
	@AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Course Thumbnail!\"])")
	private List<MobileElement> playTestimonialList;
	
	public List<MobileElement> playTestimonials(){
		return playTestimonialList;
	}
	
	@AndroidFindBy(id = "tv_home_view_all")
	private List<MobileElement> viewAllList;
	
	public List<MobileElement> viewAll(){
		return viewAllList;
	}
	
	@AndroidFindBy(id = "iv_view_all_thumb")
	private List<MobileElement> viewAllCoursesElements;
	
	public List<MobileElement> viewAllCourses(){
		return viewAllCoursesElements;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'txt_total_')]")
	private List<MobileElement> courseInfo;
	
	public List<MobileElement> listOfCourseInfo(){
		return courseInfo;
	}
	
	@AndroidFindBy(id = "ll_demo_container")
	private MobileElement containerOfDemo;
	
	public MobileElement containerOfDemoVideos() {
		return containerOfDemo;
	}
	
	@AndroidFindBy(id = "exo_content_frame")
	private MobileElement frameOfDemoVideo;
	
	public MobileElement frameDemoVideo() {
		return frameOfDemoVideo;
	}
	
	@AndroidFindBy(id = "tv_similar_courses")
	private MobileElement similarCoursesText;
	
	public MobileElement similarCoursesText() {
		return similarCoursesText;
	}
	
	@AndroidFindBy(id = "course_image_card")
	private List<MobileElement> crossSellImageList;
	
	public List<MobileElement> listOfCrossSellImage(){
		return crossSellImageList;
	}
	
	@AndroidFindBy(id = "txt_course_title")
	private List<MobileElement> crossSellTitleList;
	
	public List<MobileElement> listOfCrossSellTitle(){
		return crossSellTitleList;
	}
	
	@AndroidFindBy(id = "price")
	private List<MobileElement> crossSellPriceList;
	
	public List<MobileElement> listOfCrossSellPrice(){
		return crossSellPriceList;
	}
	
	@AndroidFindBy(id = "tv_view_all")
	private MobileElement crossViewAllBtn;
	
	public MobileElement crossViewAllBtn() {
		return crossViewAllBtn;
	}
	
	@AndroidFindBy(id = "tv_nav_home")
	private List<MobileElement> listBottomMenuMyHome;

	public List<MobileElement> getListBottomMenuMyHome() {
		return listBottomMenuMyHome;
	}
	
	@AndroidFindBy(id = "tv_offer_title_pack")
	private MobileElement noOfOffersAvailElement;

	public MobileElement noOfOffersAvail() {
		return noOfOffersAvailElement;
	}
	
	@AndroidFindBy(id = "btn_buy_one")
	private MobileElement buyNowBtn;
	
	public MobileElement buyNowButton() {
		return buyNowBtn;
	}
}
