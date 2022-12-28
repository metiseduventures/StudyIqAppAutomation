package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TestSeries_OR {
	
	@AndroidFindBy(id = "tv_nav_home")
	private List<MobileElement> listBottomMenuMyHome;

	public List<MobileElement> getListBottomMenuMyHome() {
		return listBottomMenuMyHome;
	}
	
	@AndroidFindBy(id = "btn_check_free_course")
	private MobileElement addtoLibElement;
	
	public MobileElement addToLibButton() {
		return addtoLibElement;
	}
	
	@AndroidFindBy(id = "tvFolName")
	private List<MobileElement> folder1Element;
	
	public List<MobileElement> folder1() {
		return folder1Element;
	}
	
	@AndroidFindBy(id = "tvSubFolName")
	private List<MobileElement> subfolder1Element;
	
	public List<MobileElement> subfolder1() {
		return subfolder1Element;
	}
	
	@AndroidFindBy(id = "tv_test_name")
	private List<MobileElement> listOfTestName;

	public List<MobileElement> getListOfTestName() {
		return listOfTestName;
	}
	
	@AndroidFindBy(id = "tv_test_details")
	private List<MobileElement> listOfTestDetails;

	public List<MobileElement> getListOfTestDetails() {
		return listOfTestDetails;
	}
	
	@AndroidFindBy(id = "tv_start_now")
	private List<MobileElement> listOfTestStatus;

	public List<MobileElement> getListOfTestStatus() {
		return listOfTestStatus;
	}
	
	@AndroidFindBy(id = "start_test")
	private MobileElement startTestElement;
	
	public MobileElement startTestButton() {
		return startTestElement;
	}
	
	@AndroidFindBy(id = "txt_gotit")
	private MobileElement swipePopUpGotItElement;
	
	public MobileElement swipePopUpGotItBtn() {
		return swipePopUpGotItElement;
	}
	
	@AndroidFindBy(id = "submit_test")
	private MobileElement submitTestBtn;
	
	public MobileElement submitTestButton() {
		return submitTestBtn;
	}
	
	@AndroidFindBy(id = "savenext")
	private MobileElement save_NextElement;
	
	public MobileElement save_NextButton() {
		return save_NextElement;
	}
	
	@AndroidFindBy(id = "option_index")
	private List<MobileElement> optionsInQues;
	
	public List<MobileElement> listOfOptionsInQues() {
		return optionsInQues;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private MobileElement pauseButtonElement;
	
	public MobileElement pauseButton() {
		return pauseButtonElement;
	}
	
	@AndroidFindBy(id = "q_number")
	private MobileElement questionNoElement;
	
	public MobileElement quesNo() {
		return questionNoElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'YES, PAUSE')]")
	private MobileElement yesPauseElement;
	
	public MobileElement yesPausePopUpButton() {
		return yesPauseElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'SUBMIT')]")
	private MobileElement submitPopUpButton;
	
	public MobileElement submitPopUpButton() {
		return submitPopUpButton;
	}
	
	@AndroidFindBy(id = "text")
	private MobileElement selectLangPopUp;
	
	public MobileElement selectLangPopup() {
		return selectLangPopUp;
	}
	
	@AndroidFindBy(id = "quiz_language")
	private MobileElement quizLangElement;
	
	public MobileElement quizLang() {
		return quizLangElement;
	}
	
	@AndroidFindBy(id = "constFolder")
	private List<MobileElement> openDrawerOfContentElements;

	public List<MobileElement> openDrawerIcons() {
		return openDrawerOfContentElements;
	}
	
	@AndroidFindBy(id = "btn_show_content")
	private MobileElement viewCourseContentElement;

	public MobileElement viewCourseContent() {
		return viewCourseContentElement;
	}
	
	@AndroidFindBy(id = "tvFolName")
	private List<MobileElement> namesOfCourseContentElements;

	public List<MobileElement> namesOfCourseContentElements() {
		return namesOfCourseContentElements;
	}
	
	@AndroidFindBy(id = "tvSubFolName")
	private List<MobileElement> namesOfsubCourseContentElements;

	public List<MobileElement> namesOfsubCourseContentElements() {
		return namesOfsubCourseContentElements;
	}
	
	@AndroidFindBy(id = "btn_buy_two")
	private MobileElement buyNowBtnElementBelow;

	public MobileElement buyNowBtnBelow() {
		return buyNowBtnElementBelow;
	}
	
	@AndroidFindBy(id = "tv_offer_title_pack")
	private MobileElement noOfOffersAvailElement;

	public MobileElement noOfOffersAvail() {
		return noOfOffersAvailElement;
	}
	
	@AndroidFindBy(id = "fbMessage")
	private MobileElement comingSoonPopUp;

	public MobileElement comingSoonPopUp() {
		return comingSoonPopUp;
	}
	
	@AndroidFindBy(id = "view_solution_container")
	private MobileElement SolutionBtn;
	
	public MobileElement solutionButton() {
		return SolutionBtn;
	}
	
	@AndroidFindBy(id = "btn_show_correct_ans")
	private MobileElement showCorrectAnsElement;
	
	public MobileElement showCorrectAns() {
		return showCorrectAnsElement;
	}
	
	@AndroidFindBy(id = "toolbar_sub_text")
	private MobileElement resultOverviewElement;
	
	public MobileElement resultOverviewButton() {
		return resultOverviewElement;
	}
	
	@AndroidFindBy(id = "title")
	private List<MobileElement> titlesOfResultAnalysis;
	
	public List<MobileElement> titlesOfResultAnalysis(){
		return titlesOfResultAnalysis;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private MobileElement backBtn;
	
	public MobileElement backButton() {
		return backBtn;
	}

}
