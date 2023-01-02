package pageObject;

import java.util.List;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProfilePage_OR {
	
	@AndroidFindBy(xpath = "//android.widget.EditText")
	private List<MobileElement> listOfInputElements;

	public List<MobileElement> listOfInputBoxes() {
		return listOfInputElements;
	}

	@AndroidFindBy(xpath = "//android.widget.Button")
	private List<MobileElement> listOfButtons;

	public List<MobileElement> listOfButtons() {
		return listOfButtons;
	}

	@AndroidFindBy(className = "android.widget.CheckedTextView")
	private List<MobileElement> optionsOfCity;

	public List<MobileElement> cityOptions() {
		return optionsOfCity;
	}

	@AndroidFindBy(className = "android.widget.CheckedTextView")
	private List<MobileElement> optionsOfState;

	public List<MobileElement> stateOptions() {
		return optionsOfState;
	}

	@AndroidFindBy(id = "iv_profile_mobile_edit")
	private MobileElement updatedNumberBtn;

	public MobileElement updatedNoBtn() {
		return updatedNumberBtn;
	}

	@AndroidFindBy(id = "teMobile")
	private MobileElement updatedNumberInput;

	public MobileElement updatedNoInput() {
		return updatedNumberInput;
	}
	
	@AndroidFindBy(id = "teEmail")
	private MobileElement updatedEmailInput;

	public MobileElement updatedEmailInput() {
		return updatedEmailInput;
	}
	
	@AndroidFindBy(id = "snackbar_text")
	private MobileElement otpSentMsgElement;
	
	public MobileElement otpSentMsg() {
		return otpSentMsgElement;
	}

	@AndroidFindBy(id = "btn_getOtp")
	private MobileElement getOtpBtn;

	public MobileElement clickUpdateContinueBtn() {
		return getOtpBtn;
	}
	
	@AndroidFindBy(id = "btn_getOtp")
	private MobileElement getOtpBtn1;

	public MobileElement verifyOtpButton() {
		return getOtpBtn1;
	}

	@AndroidFindBy(id = "et_profile_update_otp")
	private MobileElement inputOTP;

	public MobileElement inputOtp() {
		return inputOTP;
	}

	@AndroidFindBy(id = "bt_profile_update_submit")
	private MobileElement submitOtpElement;

	public MobileElement submitOtpBtn() {
		return submitOtpElement;
	}

	@AndroidFindBy(id = "iv_profile_email_edit")
	private MobileElement editEmailBtn;

	public MobileElement editMailBtn() {
		return editEmailBtn;
	}

	@AndroidFindBy(id = "iv_profile_mobile_edit")
	private MobileElement editNumberBtn;

	public MobileElement editNumberBtn() {
		return updatedNumberBtn;
	}

	@AndroidFindBy(id = "et_email")
	private MobileElement updatedEmail;

	public MobileElement updatedMail() {
		return updatedEmail;
	}

	@AndroidFindBy(id = "iv_profile_update_close")
	private MobileElement closeBtnElement;

	public MobileElement closeButton() {
		return closeBtnElement;
	}

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private MobileElement toastFeedLangElement;

	public MobileElement toastFeedLang() {
		return toastFeedLangElement;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Sign Out')]")
	private MobileElement signOutButtonElement;
	
	public MobileElement signOutBtn() {
		return signOutButtonElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Logout')]")
	private MobileElement logOutButtonElement;
	
	public MobileElement logOutBtn() {
		return logOutButtonElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Cancel')]")
	private MobileElement cancelButtonElement;
	
	public MobileElement cancelBtn() {
		return cancelButtonElement;
	}
}
