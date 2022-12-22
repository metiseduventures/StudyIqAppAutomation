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
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'ENGLISH')]")
	private MobileElement folder1Element;
	
	public MobileElement folder1() {
		return folder1Element;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'MOCK 1')]")
	private MobileElement subfolder1Element;
	
	public MobileElement subfolder1() {
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
}
