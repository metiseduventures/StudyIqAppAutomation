package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginSignup_Negative_OR {

	@AndroidFindBy(id = "teMobile")
	private MobileElement numberInputBoxElement;

	public MobileElement numberInputBox() {
		return numberInputBoxElement;
	}

	@AndroidFindBy(id = "com.truecaller:id/continueWithDifferentNumber")
	private MobileElement manualDetailBtnElement;

	public MobileElement manualDetailBtn() {
		return manualDetailBtnElement;
	}

	@AndroidFindBy(id = "com.google.android.gms:id/button_area")
	private MobileElement noneOfTheAboveElement;

	public MobileElement noneOfTheAboveBtn() {
		return noneOfTheAboveElement;
	}

	@AndroidFindBy(id = "com.google.android.gms:id/credential_picker_options")
	private MobileElement mobilePresentElement;

	public MobileElement mobilePresentOnPopuP() {
		return mobilePresentElement;
	}

	@AndroidFindBy(id = "btn_getOtp")
	private MobileElement continueBtnElement;

	public MobileElement continueBtn() {
		return continueBtnElement;
	}

	@AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.EditText")
	private List<MobileElement> otpBoxElements;

	public List<MobileElement> otpBoxes() {
		return otpBoxElements;
	}

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement btnPermissionAllowed;

	public MobileElement getBtnPermissionAllowed() {
		return btnPermissionAllowed;
	}

	@AndroidFindBy(id = "tvYourName")
	private MobileElement nameTxtHeadingElement;

	public MobileElement nameTxtHeading() {
		return nameTxtHeadingElement;
	}

	@AndroidFindBy(id = "teName")
	private MobileElement nameInputElement;

	public MobileElement nameInputBox() {
		return nameInputElement;
	}

	@AndroidFindBy(id = "teEmail")
	private MobileElement emailInputElement;

	public MobileElement emailInputBox() {
		return emailInputElement;
	}

	@AndroidFindBy(id = "tvYourEmail")
	private MobileElement emailTxtHeadingElement;

	public MobileElement emailTxtHeading() {
		return emailTxtHeadingElement;
	}

	@AndroidFindBy(id = "search_src_text")
	private MobileElement searchGoalInputElement;

	public MobileElement searchGoalInputBox() {
		return searchGoalInputElement;
	}

	@AndroidFindBy(id = "ll_select_exam")
	private List<MobileElement> goals;

	public List<MobileElement> listOfGoals() {
		return goals;
	}
	
	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private MobileElement toastFeedLangElement;

	public MobileElement toastFeedLang() {
		return toastFeedLangElement;
	}
	
	@AndroidFindBy(id = "errorViewName")
	private MobileElement errorNumberName;

	public MobileElement ErrorsNo() {
		return errorNumberName;
	}
	
	@AndroidFindBy(id = "errorViewEmail")
	private MobileElement errorNumberEmail;

	public MobileElement ErrorEmail() {
		return errorNumberEmail;
	}
	
	@AndroidFindBy(id = "tvDidNotRecieveLabel")
	private MobileElement errorOtp;

	public MobileElement ErrorOtp() {
		return errorOtp;
	}

}
