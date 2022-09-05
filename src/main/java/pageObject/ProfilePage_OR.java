package pageObject;

import java.util.List;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProfilePage_OR {	

	@AndroidFindBy(id = "et_profile_name")
	private MobileElement name;

	public MobileElement inputName() {
		return name;
	}

	@AndroidFindBy(id = "et_profile_address")
	private MobileElement address;

	public MobileElement inputAddress() {
		return address;
	}

	@AndroidFindBy(id = "et_profile_pincode")
	private MobileElement pincode;

	public MobileElement inputPincode() {
		return pincode;
	}

	@AndroidFindBy(id = "et_profile_city")
	private MobileElement city;

	public MobileElement inputCity() {
		return city;
	}

	@AndroidFindBy(id = "et_profile_state")
	private MobileElement state;

	public MobileElement inputState() {
		return state;
	}

	@AndroidFindBy(id = "btn_update")
	private MobileElement updateBtnElement;

	public MobileElement updateBtn() {
		return updateBtnElement;
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
	
	@AndroidFindBy(id = "et_profile_update_mobile")
	private MobileElement updatedNumberInput;
	
	public MobileElement updatedNo() {
		return updatedNumberInput;
	}
	
	@AndroidFindBy(id = "bt_profile_update_get_otp")
	private MobileElement getOtpBtn;
	
	public MobileElement clickOtpButton() {
		return getOtpBtn;
	}
	
	@AndroidFindBy(id = "til_profile_update_otp")
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
	private MobileElement updatedEmailBtn;
	
	public MobileElement updatedMailBtn() {
		return updatedEmailBtn;
	}
	
	@AndroidFindBy(id = "et_profile_update_email")
	private MobileElement updatedEmail;
	
	public MobileElement updatedMail() {
		return updatedEmail;
	}
	
	@AndroidFindBy(id = "iv_profile_update_close")
	private MobileElement closeBtnElement;
	
	public MobileElement closeButton() {
		return closeBtnElement;
	}

}
