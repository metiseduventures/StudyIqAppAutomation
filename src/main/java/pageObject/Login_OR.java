package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Login_OR {

	@AndroidFindBy(id = "et_mobile")
	private MobileElement textMobileNo;

	@AndroidFindBy(id = "btn_getOtp")
	private MobileElement btnGetOtp;

	@AndroidFindBy(id = "et_otp")
	private List<MobileElement> textOtp;

	@AndroidFindBy(id = "btn_login_reg")
	private MobileElement btnLogin;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement btnPermissionAllowed;

	public MobileElement getTextMobileNo() {
		return textMobileNo;
	}

	public MobileElement getBtnGetOtp() {
		return btnGetOtp;
	}

	public List<MobileElement> getTextOtp() {
		return textOtp;
	}

	public MobileElement getBtnLogin() {
		return btnLogin;
	}

	public MobileElement getBtnPermissionAllowed() {
		return btnPermissionAllowed;
	}
	
	@AndroidFindBy(id = "et_name")
	private MobileElement textUserName;
	
	
	public MobileElement getTextUserName() {
		return textUserName;
	}

	public MobileElement getTextUserEmail() {
		return textUserEmail;
	}

	@AndroidFindBy(id = "et_email")
	private MobileElement textUserEmail;
	
	
	@AndroidFindBy(id = "tv_login_skip")
	private MobileElement btnSkipLogin;

	public MobileElement getBtnSkipLogin() {
		return btnSkipLogin;
	}
	
	
}
