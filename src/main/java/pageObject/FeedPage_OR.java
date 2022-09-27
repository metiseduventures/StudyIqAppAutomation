package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FeedPage_OR {

	@AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.TextView")
	private List<MobileElement> listOfTextViewElements;

	public List<MobileElement> listOfTextView() {
		return listOfTextViewElements;
	}

	@AndroidFindBy(id = "tv_nav_feed")
	private List<MobileElement> listBottomMenuMyFeed;

	public List<MobileElement> getListBottomMenuMyFeed() {
		return listBottomMenuMyFeed;
	}

	@AndroidFindBy(id = "action_language_change_feed")
	private MobileElement langFeedBtnElement;

	public MobileElement langFeedBtn() {
		return langFeedBtnElement;
	}

	@AndroidFindBy(id = "tv_title")
	private MobileElement titleOfLangFeedElement;

	public MobileElement titleOfLangFeed() {
		return titleOfLangFeedElement;
	}

	@AndroidFindBy(id = "tv_desc")
	private MobileElement descOfLangFeedElement;

	public MobileElement descOfLangFeed() {
		return descOfLangFeedElement;
	}

	@AndroidFindBy(id = "cv_feed_lang")
	private List<MobileElement> feedLangElements;

	public List<MobileElement> listOfFeedLang() {
		return feedLangElements;
	}

	@AndroidFindBy(id = "feed_lang_title")
	private List<MobileElement> feedLangTitleElements;

	public List<MobileElement> listOfFeedTitleLang() {
		return feedLangTitleElements;
	}

	@AndroidFindBy(id = "iv_feed_lang")
	private MobileElement feedLangTickMarkElement;

	public MobileElement feedLangTickMark() {
		return feedLangTickMarkElement;
	}

	@AndroidFindBy(id = "iv_close")
	private MobileElement feedLangCloseElement;

	public MobileElement feedLangClose() {
		return feedLangCloseElement;
	}

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private MobileElement toastFeedLangElement;

	public MobileElement toastFeedLang() {
		return toastFeedLangElement;
	}

	@AndroidFindBy(id = "feed_video_thumbnail")
	private MobileElement videoThumbnailSliderElement;

	public MobileElement videoThumbnailSlider() {
		return videoThumbnailSliderElement;
	}

	@AndroidFindBy(id = "feed_trending_title")
	private MobileElement titleThumbnailSliderElement;

	public MobileElement titleThumbnailSlider() {
		return titleThumbnailSliderElement;
	}

	@AndroidFindBy(id = "feed_trending_des")
	private MobileElement descThumbnailSliderElement;

	public MobileElement descThumbnailSlider() {
		return descThumbnailSliderElement;
	}

	@AndroidFindBy(id = "tv_feed_type")
	private List<MobileElement> typesOfFeedList;

	public List<MobileElement> listOfTypesOfFeed() {
		return typesOfFeedList;
	}

	@AndroidFindBy(id = "tv_feed_title")
	private List<MobileElement> titleOfFeedtypeElementsList;

	public List<MobileElement> listOftitleOfFeedtypeElements() {
		return titleOfFeedtypeElementsList;
	}

	@AndroidFindBy(id = "tv_feed_date")
	private List<MobileElement> dateOfFeedtypeElementsList;

	public List<MobileElement> listOfDateOfFeedtypeElements() {
		return dateOfFeedtypeElementsList;
	}

	@AndroidFindBy(id = "iv_feed_common")
	private List<MobileElement> iconOfFeedtypeElementsList;

	public List<MobileElement> listOficonOfFeedtypeElements() {
		return iconOfFeedtypeElementsList;
	}

	@AndroidFindBy(id = "feed_video_thumbnail")
	private List<MobileElement> videoTOfFeedtypeElementsList;

	public List<MobileElement> listOfVideoTOfFeedtypeElements() {
		return videoTOfFeedtypeElementsList;
	}

	@AndroidFindBy(id = "tv_feed_des")
	private List<MobileElement> descOfFeedtypeElementsList;

	public List<MobileElement> listOfDescOfFeedtypeElements() {
		return descOfFeedtypeElementsList;
	}

	@AndroidFindBy(id = "fd_title")
	private MobileElement titleOfFeedInBoxElement;

	public MobileElement titleOfFeedInBox() {
		return titleOfFeedInBoxElement;
	}

	@AndroidFindBy(id = "fd_publish_date")
	private MobileElement dateOfFeedInBoxElement;

	public MobileElement dateOfFeedInBox() {
		return dateOfFeedInBoxElement;
	}

	@AndroidFindBy(id = "tv_feed_quiz_name")
	private List<MobileElement> quizNameInFeedList;

	public List<MobileElement> listOfquizNameInFeed() {
		return quizNameInFeedList;
	}

	@AndroidFindBy(id = "tv_feed_quiz_count")
	private List<MobileElement> quizQuesCountInFeedList;

	public List<MobileElement> listOfquizQuesCountInFeed() {
		return quizQuesCountInFeedList;
	}

	@AndroidFindBy(id = "tv_feed_quiz_duration")
	private List<MobileElement> quizDurationInFeedList;

	public List<MobileElement> listOfquizDurationInFeed() {
		return quizDurationInFeedList;
	}

	@AndroidFindBy(id = "tv_feed_quiz_status")
	private List<MobileElement> quizStartNowInFeedList;

	public List<MobileElement> listOfquizStartNowInFeed() {
		return quizStartNowInFeedList;
	}

}
