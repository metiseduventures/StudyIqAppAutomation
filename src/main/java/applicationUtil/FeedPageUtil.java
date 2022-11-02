package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.FeedPage_OR;
import util.Common_Function;

public class FeedPageUtil {

	FeedPage_OR feedPageORObj;
	public Common_Function cfObj = new Common_Function();
	public LoginUtil loginutillObj;
	public List<String> feedPageMsglist = new ArrayList<String>();

	public FeedPageUtil(AppiumDriver<MobileElement> driver) {

		feedPageORObj = new FeedPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), feedPageORObj);
	}

	public boolean verifyFeedPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginutillObj = new LoginUtil(driver);
		try {

			result = loginutillObj.doSignUp(driver);
			if (!result) {
				feedPageMsglist.addAll(loginutillObj.loginMsgList);
				return result;
			}

			result = verifyFeedBtnOnHomeBottom(driver);
			if (!result) {
				return result;
			}

			result = verifyLangSelector(driver);
			if (!result) {
				return result;
			}

			result = verifyTrendingSlider(driver);
			if (!result) {
				return result;
			}

			result = verifyFeedType(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			feedPageMsglist.add("verifyFeedPage_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyFeedBtnOnHomeBottom(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.getListBottomMenuMyFeed().get(0), 10);
			if (!result) {
				feedPageMsglist.add("The text of my feed on bottom is not visible");
				return result;
			}
			String feedBottomText = feedPageORObj.getListBottomMenuMyFeed().get(0).getText();

			cfObj.commonClick(feedPageORObj.getListBottomMenuMyFeed().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.listOfTextView().get(0), 10);
			if (!result) {
				feedPageMsglist.add("My feed title is not visible at top");
				return result;
			}
			String feedPageText = feedPageORObj.listOfTextView().get(0).getText();
			if (feedBottomText.equalsIgnoreCase(feedPageText)) {
				result = true;
			} else {
				feedPageMsglist.add("The title and bottom text are not same");
				return false;
			}

		} catch (Exception e) {
			feedPageMsglist.add("verifyFeedBtnOnHomeBottom_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyLangSelector(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.langFeedBtn(), 10);
			if (!result) {
				feedPageMsglist.add("The language change btn in feed is not visible");
				return result;
			}

			cfObj.commonClick(feedPageORObj.langFeedBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_title", "id", 10);
			if (!result) {
				feedPageMsglist.add("The title of change lang is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.descOfLangFeed(), 10);
			if (!result) {
				feedPageMsglist.add("The desc of change lang is not visible");
				return result;
			}

			List<MobileElement> langs = feedPageORObj.listOfFeedLang();
			List<MobileElement> langTitles = feedPageORObj.listOfFeedTitleLang();

			for (int i = 0; i < langTitles.size(); i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver, langTitles.get(i), 10);
				if (!result) {
					feedPageMsglist.add("The name of lang in feed is not visible");
					return result;
				}

				String firstLangTitle = langTitles.get(i).getText();
				if (firstLangTitle.equalsIgnoreCase("English")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.feedLangTickMark(), 10);
					if (!result) {
						feedPageMsglist.add("The selected lang is not english");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.feedLangClose(), 10);
					if (!result) {
						feedPageMsglist.add("The close btn is not visible");
						return result;
					}

					cfObj.commonClick(feedPageORObj.feedLangClose());
					break;
				}
			}

			cfObj.commonClick(feedPageORObj.langFeedBtn());

			// in this loop, just click on every lang and check their toast
			for (int i = 0; i < langTitles.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, langTitles.get(i), 10);
				if (!result) {
					feedPageMsglist.add("The name of lang in feed is not visible");
					return result;
				}

				String firstLangTitle = langTitles.get(i).getText();
				if (firstLangTitle.equalsIgnoreCase("English")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.feedLangTickMark(), 10);
					if (!result) {
						feedPageMsglist.add("The selected lang is not english");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.feedLangClose(), 10);
					if (!result) {
						feedPageMsglist.add("The close btn is not visible");
						return result;
					}

					cfObj.commonClick(feedPageORObj.feedLangClose());
					cfObj.commonClick(feedPageORObj.langFeedBtn());

				} else {
					cfObj.commonClick(langs.get(i));

					String toastMsgLangChange = feedPageORObj.toastFeedLang().getAttribute("name");

					if (toastMsgLangChange.equalsIgnoreCase("Feed Language saved successfully")) {
						cfObj.commonClick(feedPageORObj.langFeedBtn());
					} else {
						feedPageMsglist.add("The language not changed as the toast not visible");
						return false;
					}
				}
			}

			for (int i = 0; i < langTitles.size(); i++) {

				String firstLangTitle = langTitles.get(i).getText();
				if (firstLangTitle.equalsIgnoreCase("English")) {
					cfObj.commonClick(langs.get(i));
					return true;
				}
			}

		} catch (Exception e) {
			feedPageMsglist.add("verifyLangSelector_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyTrendingSlider(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int count = 2;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.titleThumbnailSlider(), 10);
			if (!result) {
				System.out.println("The trending section is not visible");
				return true;
			}

			for (int i = 0; i < count; i++) {
				result = feedPageORObj.videoThumbnailSlider().isDisplayed();
				if (!result) {
					feedPageMsglist.add("The image is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.titleThumbnailSlider(), 10);
				if (!result) {
					feedPageMsglist.add("The treding title is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.descThumbnailSlider(), 10);
				if (!result) {
					feedPageMsglist.add("The desc is not visible");
					return result;
				}

				cfObj.swipeLeftOnElement(feedPageORObj.videoThumbnailSlider(), driver);
			}

			cfObj.swipeRightOnElement(feedPageORObj.videoThumbnailSlider(), driver);

			String desc = feedPageORObj.descThumbnailSlider().getText();

			cfObj.commonClick(feedPageORObj.videoThumbnailSlider());

			if (desc.equalsIgnoreCase(feedPageORObj.titleOfFeedInBox().getText())) {

				result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.dateOfFeedInBox(), 10);
				if (!result) {
					feedPageMsglist.add("The publishing date text is not visible");
					return result;
				}

				cfObj.scrollUtill(driver, 1);
			} else {
				feedPageMsglist.add("The description is not same as next page title");
				return false;
			}

			driver.navigate().back();

		} catch (Exception e) {
			feedPageMsglist.add("verifyTrendingSlider_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyFeedType(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// click all feed types
			List<MobileElement> typesOfFeed = feedPageORObj.listOfTypesOfFeed();

			cfObj.scrollUtillTheElementFound(driver, "tv_feed_des", "id");

			// api ke through mil jayega no. of feed types
			// TOTAL NO.OF ELEMENTS/WINDOW SCREEN - 6/3

			for (int j = 0; j < 2; j++) {

				for (int i = j; i < j + 3; i++) {

					result = cfObj.commonWaitForElementToBeVisible(driver, typesOfFeed.get(i), 10);
					if (!result) {
						feedPageMsglist.add("The element text is not visible");
						return result;
					}

					cfObj.commonClick(typesOfFeed.get(i));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_title", "id", 5);
					if (result) {

						System.out.println("Their is no element in the feed type");

					} else {

						result = cfObj.commonWaitForElementToBeVisible(driver,
								feedPageORObj.listOftitleOfFeedtypeElements().get(0), 10);
						if (!result) {
							feedPageMsglist.add("The title of feed element is not visible");
						}

						String titleOfFeedTypeElement = feedPageORObj.listOftitleOfFeedtypeElements().get(0).getText();

						if (titleOfFeedTypeElement.equalsIgnoreCase("all")) {

						} else if (titleOfFeedTypeElement.equalsIgnoreCase("current affairs")) {

							result = feedTypeBoxVerify(driver, titleOfFeedTypeElement);
							if (!result) {
								return result;
							}

						} else if (titleOfFeedTypeElement.equalsIgnoreCase("articles")) {

							result = feedTypeBoxVerify(driver, titleOfFeedTypeElement);
							if (!result) {
								return result;
							}

						} else if (titleOfFeedTypeElement.equalsIgnoreCase("videos")) {

							result = feedTypeBoxVerify(driver, titleOfFeedTypeElement);
							if (!result) {
								return result;
							}

						} else if (titleOfFeedTypeElement.equalsIgnoreCase("quizzes")) {

							result = cfObj.commonWaitForElementToBeVisible(driver,
									feedPageORObj.listOfDateOfFeedtypeElements().get(0), 10);
							if (!result) {
								feedPageMsglist.add("The date of the quiz is not visible");
								return result;
							}

							result = feedPageORObj.listOficonOfFeedtypeElements().get(0).isDisplayed();
							if (!result) {
								feedPageMsglist.add("The icon of the quiz is not visible");
								return result;
							}

							result = cfObj.commonWaitForElementToBeVisible(driver,
									feedPageORObj.listOfquizNameInFeed().get(0), 10);
							if (!result) {
								feedPageMsglist.add("The name of the quiz is not visible");
								return result;
							}

							result = cfObj.commonWaitForElementToBeVisible(driver,
									feedPageORObj.listOfquizQuesCountInFeed().get(0), 10);
							if (!result) {
								feedPageMsglist.add("The count of ques in the quiz is not visible");
								return result;
							}

							result = cfObj.commonWaitForElementToBeVisible(driver,
									feedPageORObj.listOfquizDurationInFeed().get(0), 10);
							if (!result) {
								feedPageMsglist.add("The duration of the quiz is not visible");
								return result;
							}

							result = cfObj.commonWaitForElementToBeVisible(driver,
									feedPageORObj.listOfquizStartNowInFeed().get(0), 10);
							if (!result) {
								feedPageMsglist.add("The start now in the quiz is not visible");
								return result;
							}

						} else if (titleOfFeedTypeElement.equalsIgnoreCase("Job alerts")) {

							result = feedTypeBoxVerify(driver, titleOfFeedTypeElement);
							if (!result) {
								return result;
							}

						} else {

							feedPageMsglist.add("Extra type not explored");
							return false;
						}
					}
				}

				for (int k = 0; k < 3; k++) {
					cfObj.swipeLeftOnElement(typesOfFeed.get(k), driver);
				}
			}

		} catch (Exception e) {
			feedPageMsglist.add("verifyFeedType_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean feedTypeBoxVerify(AppiumDriver<MobileElement> driver, String titleOfFeedTypeElement) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.listOfDateOfFeedtypeElements().get(0),
					10);
			if (!result) {
				feedPageMsglist.add("The date of the feed element is not visible");
				return result;
			}

			result = feedPageORObj.listOficonOfFeedtypeElements().get(0).isDisplayed();
			if (!result) {
				feedPageMsglist.add("The icon of the feed element is not visible");
				return result;
			}

			result = feedPageORObj.listOfVideoTOfFeedtypeElements().get(0).isDisplayed();
			if (!result) {
				feedPageMsglist.add("The video thumbnails of the feed element is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.listOfDescOfFeedtypeElements().get(0),
					10);
			if (!result) {
				feedPageMsglist.add("The description of the feed element is not visible");
				return result;
			}

			String desc = feedPageORObj.listOfDescOfFeedtypeElements().get(0).getText();
			String descString = desc.replaceAll("\\s", "");

			cfObj.commonClick(feedPageORObj.listOfVideoTOfFeedtypeElements().get(0));

			if (titleOfFeedTypeElement.equalsIgnoreCase(feedPageORObj.listOfTextView().get(0).getText())) {
				String titleInMainPage = feedPageORObj.titleOfFeedInBox().getText();
				String titleInMainPageString = titleInMainPage.replaceAll("\\s", "");

				if (descString.equalsIgnoreCase(titleInMainPageString)) {

					result = cfObj.commonWaitForElementToBeVisible(driver, feedPageORObj.dateOfFeedInBox(), 10);
					if (!result) {
						feedPageMsglist.add("The publishing date text is not visible");
						return result;
					}

					cfObj.scrollUtill(driver, 1);

				} else {
					feedPageMsglist.add("The description of feed box is not same as inside title");
					return false;
				}

			} else {

				feedPageMsglist.add("The text of feed type is not same as next page heading");
				return false;
			}

			driver.navigate().back();

		} catch (Exception e) {
			feedPageMsglist.add("feedTypeBoxVerify_Exception " + e.getMessage());
			result = false;
		}
		return result;

	}

}
