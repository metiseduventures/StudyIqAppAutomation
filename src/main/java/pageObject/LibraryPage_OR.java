package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LibraryPage_OR {

	@AndroidFindBy(id = "bt_action_pc")
	private MobileElement goToLibraryBtnElement;

	public MobileElement goToLibraryBtn() {
		return goToLibraryBtnElement;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'My Library')]")
	private List<MobileElement> myLibraryTextElements;

	public List<MobileElement> myLibraryTextList() {
		return myLibraryTextElements;
	}

	@AndroidFindBy(id = "txt_main_title")
	private List<MobileElement> titleOfCoursesElements;

	public List<MobileElement> titleOfCoursesList() {
		return titleOfCoursesElements;
	}

	@AndroidFindBy(id = "ll_course_type_title")
	private List<MobileElement> titleOfCoursesBoxElements;

	public List<MobileElement> titleOfCoursesBoxList() {
		return titleOfCoursesBoxElements;
	}

	@AndroidFindBy(id = "const_sub_course")
	private List<MobileElement> titleOfSubCoursesBoxElements;

	public List<MobileElement> titleOfSubCoursesBoxList() {
		return titleOfSubCoursesBoxElements;
	}

	@AndroidFindBy(id = "txt_course_sub_title")
	private List<MobileElement> titleOfSubCoursesElements;

	public List<MobileElement> titleOfSubCoursesList() {
		return titleOfSubCoursesElements;
	}

	@AndroidFindBy(id = "txt_course_exp")
	private List<MobileElement> courseExpireElements;

	public List<MobileElement> courseExpireList() {
		return courseExpireElements;
	}

	@AndroidFindBy(id = "btn_course_status")
	private List<MobileElement> courseStatusElements;

	public List<MobileElement> courseStatusList() {
		return courseStatusElements;
	}

	@AndroidFindBy(id = "tvFolName")
	private List<MobileElement> topicsOfCourseList;

	public List<MobileElement> getListTopicsOfCourse() {
		return topicsOfCourseList;
	}

	@AndroidFindBy(id = "inner_txt_title")
	private List<MobileElement> titleOfVideosInTopicList;

	public List<MobileElement> getListTitleOfVideosInTopic() {
		return titleOfVideosInTopicList;
	}

	@AndroidFindBy(id = "rl_inner_download")
	private List<MobileElement> downloadBtnElementsInCourseSection;

	public List<MobileElement> downloadBtnListInCS() {
		return downloadBtnElementsInCourseSection;
	}

	@AndroidFindBy(id = "alertTitle")
	private MobileElement titleOfDownloadOptionElement;

	public MobileElement titleOfDownloadOption() {
		return titleOfDownloadOptionElement;
	}

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView")
	private List<MobileElement> downloadOptionList;

	public List<MobileElement> downloadOptions() {
		return downloadOptionList;
	}

	@AndroidFindBy(id = "button2")
	private MobileElement downloadVideoBtnElement;

	public MobileElement downloadVideoBtn() {
		return downloadVideoBtnElement;
	}

	@AndroidFindBy(accessibility = "My Downloads")
	private MobileElement myDownloadsBtnElement;

	public MobileElement myDownloadsBtn() {
		return myDownloadsBtnElement;
	}

	@AndroidFindBy(accessibility = "My Course")
	private MobileElement myCourseBtnElement;

	public MobileElement myCourseBtn() {
		return myCourseBtnElement;
	}

	@AndroidFindBy(accessibility = "My Doubts")
	private MobileElement myDoubtsBtnElement;

	public MobileElement myDoubtsBtn() {
		return myDoubtsBtnElement;
	}

	@AndroidFindBy(accessibility = "My List")
	private MobileElement myListBtnElement;

	public MobileElement myListBtn() {
		return myListBtnElement;
	}

	@AndroidFindBy(accessibility = "My Notes")
	private MobileElement myNotesBtnElement;

	public MobileElement myNotesBtn() {
		return myNotesBtnElement;
	}

	@AndroidFindBy(accessibility = "Notification")
	private MobileElement courseNotificationElement;

	public MobileElement courseNotification() {
		return courseNotificationElement;
	}

	@AndroidFindBy(accessibility = "More")
	private MobileElement moreBtnElement;

	public MobileElement moreBtn() {
		return moreBtnElement;
	}

	@AndroidFindBy(id = "txt_sorry")
	private MobileElement noDataTextDownloads;

	public MobileElement textNoDataInDownload() {
		return noDataTextDownloads;
	}

	@AndroidFindBy(accessibility = "Delete")
	private List<MobileElement> deleteVideoBtnElement;

	public List<MobileElement> deleteVideoBtn() {
		return deleteVideoBtnElement;
	}

	@AndroidFindBy(id = "txt_my_list_title")
	private List<MobileElement> myListTitlesElements;

	public List<MobileElement> myListTitles() {
		return myListTitlesElements;
	}

	@AndroidFindBy(id = "txt_dialog_title")
	private MobileElement dialogTitleInListElement;

	public MobileElement dialogTitleInList() {
		return dialogTitleInListElement;
	}

	@AndroidFindBy(id = "txt_video_name")
	private List<MobileElement> videoNamesInMyList;

	public List<MobileElement> getListOfVideoNamesInMyList() {
		return videoNamesInMyList;
	}

	@AndroidFindBy(id = "delete_video")
	private List<MobileElement> deleteVideoInMyList;

	public List<MobileElement> getListOfDeleteVideosInMyList() {
		return deleteVideoInMyList;
	}

	@AndroidFindBy(id = "text_course_name")
	private List<MobileElement> courseNameInNotesList;

	public List<MobileElement> courseNameInNotes() {
		return courseNameInNotesList;
	}

	@AndroidFindBy(id = "course_date")
	private List<MobileElement> courseDateInNotesList;

	public List<MobileElement> courseDateInNotes() {
		return courseDateInNotesList;
	}

	@AndroidFindBy(id = "go_to_video")
	private List<MobileElement> goToVideoInNotesList;

	public List<MobileElement> goToVideoInNotes() {
		return goToVideoInNotesList;
	}

	@AndroidFindBy(id = "tv_content")
	private List<MobileElement> courseContentInNotesList;

	public List<MobileElement> courseContentInNotes() {
		return courseContentInNotesList;
	}

	@AndroidFindBy(id = "download_img")
	private List<MobileElement> downloadContentInNotesList;

	public List<MobileElement> downloadContentInNotes() {
		return downloadContentInNotesList;
	}

	@AndroidFindBy(id = "edit_img")
	private List<MobileElement> editContentInNotesBtnList;

	public List<MobileElement> editContentInNotesBtn() {
		return editContentInNotesBtnList;
	}

	@AndroidFindBy(id = "delete_img")
	private List<MobileElement> deleteContentInNotesList;

	public List<MobileElement> deleteContentInNotes() {
		return deleteContentInNotesList;
	}

	@AndroidFindBy(id = "et_my_note")
	private MobileElement editTextInNotesElement;

	public MobileElement editTextInNotes() {
		return editTextInNotesElement;
	}

	@AndroidFindBy(id = "btn_submit")
	private MobileElement saveBtnInNotesElement;

	public MobileElement saveBtnInNotes() {
		return saveBtnInNotesElement;
	}

	@AndroidFindBy(id = "android:id/button1")
	private MobileElement deleteNotesElement;

	public MobileElement deleteNote() {
		return deleteNotesElement;
	}

	@AndroidFindBy(id = "rl_more_buy_now")
	private MobileElement buyNowBtnElement;

	public MobileElement buyNowBtn() {
		return buyNowBtnElement;
	}

	@AndroidFindBy(id = "rl_more_support")
	private MobileElement customerSupportBtnElement;

	public MobileElement customerSupportBtn() {
		return customerSupportBtnElement;
	}

	@AndroidFindBy(id = "rl_share")
	private MobileElement shareBtnElement;

	public MobileElement shareBtn() {
		return shareBtnElement;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[2]")
	private MobileElement popUpLaterBtnElement;

	public MobileElement popUpLaterBtn() {
		return popUpLaterBtnElement;
	}

	@AndroidFindBy(xpath = "//androidx.appcompat.app.ActionBar.b[@content-desc=\"Video\"]/android.widget.TextView")
	private MobileElement videoBoxInNotificationElement;

	public MobileElement videoBoxInNotification() {
		return videoBoxInNotificationElement;
	}

	@AndroidFindBy(id = "text_vc_update")
	private List<MobileElement> videoTitlesInNotification;

	public List<MobileElement> videoTitlesInNotificationList() {
		return videoTitlesInNotification;
	}

	@AndroidFindBy(id = "tv_date_vc")
	private List<MobileElement> videoDatesInNotification;

	public List<MobileElement> videoDatesInNotificationList() {
		return videoDatesInNotification;
	}

	@AndroidFindBy(xpath = "//androidx.appcompat.app.ActionBar.b[@content-desc=\"Text\"]/android.widget.TextView")
	private MobileElement textBoxInNotificationElement;

	public MobileElement textBoxInNotification() {
		return textBoxInNotificationElement;
	}

	@AndroidFindBy(id = "text_txt_update")
	private List<MobileElement> textTitlesInNotification;

	public List<MobileElement> textTitlesInNotificationList() {
		return textTitlesInNotification;
	}

	@AndroidFindBy(id = "tv_date_txt_update")
	private List<MobileElement> textDatesInNotification;

	public List<MobileElement> textDatesInNotificationList() {
		return textDatesInNotification;
	}

	@AndroidFindBy(id = "bt_pdf_download")
	private MobileElement pdfDownloadBtnElement;

	public MobileElement pdfDownloadBtn() {
		return pdfDownloadBtnElement;
	}

	@AndroidFindBy(xpath = "//androidx.appcompat.app.ActionBar.b[@content-desc=\"Quiz\"]/android.widget.TextView")
	private MobileElement quizBoxInNotificationElement;

	public MobileElement quizBoxInNotification() {
		return quizBoxInNotificationElement;
	}

	@AndroidFindBy(id = "quiz_txt_update")
	private List<MobileElement> quizTitlesInNotification;

	public List<MobileElement> quizTitlesInNotificationList() {
		return quizTitlesInNotification;
	}

	@AndroidFindBy(id = "tv_date_quiz_update")
	private List<MobileElement> quizDatesInNotification;

	public List<MobileElement> quizDatesInNotificationList() {
		return quizDatesInNotification;
	}

	@AndroidFindBy(id = "img_close")
	private MobileElement closePopUpInMicroElement;

	public MobileElement closePopUpInMicro() {
		return closePopUpInMicroElement;
	}

	@AndroidFindBy(id = "txt_msg_left")
	private MobileElement doubtMsgTextElement;

	public MobileElement doubtMsgText() {
		return doubtMsgTextElement;
	}

	@AndroidFindBy(id = "et_add_doubt_dashboard")
	private MobileElement doubtDashboardMsgTextElement;

	public MobileElement doubtDashboardMsgText() {
		return doubtDashboardMsgTextElement;
	}

	@AndroidFindBy(id = "ll_send_dashboard")
	private MobileElement sendDoubtMsgTextElement;

	public MobileElement sendDoubtMsgText() {
		return sendDoubtMsgTextElement;
	}

	@AndroidFindBy(id = "txt_msg_right")
	private List<MobileElement> sentMsgTextElement;

	public List<MobileElement> sentMsgText() {
		return sentMsgTextElement;
	}

	@AndroidFindBy(id = "tv_offer_title_pack")
	private MobileElement noOfOffersAvailElement;

	public MobileElement noOfOffersAvail() {
		return noOfOffersAvailElement;
	}

	@AndroidFindBy(id = "tv_nav_library")
	private List<MobileElement> listBottomMenuMyLibrary;

	public List<MobileElement> getListBottomMenuMyLibrary() {
		return listBottomMenuMyLibrary;
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.TextView")
	private List<MobileElement> listOfTextViewElements;

	public List<MobileElement> listOfTextView() {
		return listOfTextViewElements;
	}
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Video Courses\"]/android.widget.TextView")
	private MobileElement videoCoursesBtnElement;
	
	public MobileElement videoCoursesBtn() {
		return videoCoursesBtnElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Live Classes\"]/android.widget.TextView")
	private MobileElement liveClassesBtnElement;
	
	public MobileElement liveClassesBtn() {
		return liveClassesBtnElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Test Series\"]/android.widget.TextView")
	private MobileElement testSeriesBtnElement;
	
	public MobileElement testSeriesBtn() {
		return testSeriesBtnElement;
	}
	
	@AndroidFindBy(id = "your_purchases")
	private MobileElement yourPurchaseTextElement;
	
	public MobileElement yourPurchaseText() {
		return yourPurchaseTextElement;
	}
	
	@AndroidFindBy(id = "txt_course_title")
	private List<MobileElement> courseTitlesElement;
	
	public List<MobileElement> listOfCourseTitlesInLib(){
		return courseTitlesElement;
	}
	
	@AndroidFindBy(id = "course_image_card")
	private List<MobileElement> courseImagesElement;
	
	public List<MobileElement> listOfCourseImagesInLib(){
		return courseImagesElement;
	}
	
	@AndroidFindBy(id = "course_status_layout")
	private List<MobileElement> courseValidaitiesElement;
	
	public List<MobileElement> listOfCourseValiditiesInLib(){
		return courseValidaitiesElement;
	}

}
