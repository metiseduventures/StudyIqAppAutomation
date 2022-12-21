package apiUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pojo.login.Login;
import pojo.sendOtp.SendOtp;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;
import util.Constant;

public class LoginApiUtil {

	OtpUtil otpUtilObj;
	APIResponse ap;
	APIUtils apiUtilsObj;
	ConfigFileReader cfReaderObject = new ConfigFileReader();
	public List<String> loginMsgList = new ArrayList<String>();	

	public Login doLoginWeb(String strMobileNumber) {
		SendOtp sendOtpPojoObj;
		String strOtp;
		Map<String, String> formData = null;
		Login login = null;
		try {
			otpUtilObj = new OtpUtil();
			ap = otpUtilObj.sentOtpWeb(strMobileNumber);
			if (ap.code != 200) {

			}
			sendOtpPojoObj = ap.responseData.as(SendOtp.class);
			strOtp = otpUtilObj.getOtp(strMobileNumber, false);
			System.out.println("strOtp: " + strOtp);

			formData = new HashMap<String, String>();

			formData.put("user_id", String.valueOf(sendOtpPojoObj.getData().getUserId()));
			formData.put("otp", strOtp);
			apiUtilsObj = new APIUtils();
			ap = apiUtilsObj.postCall(cfReaderObject.getBaseUrl(), Constant.USER_LOGIN_WEB, formData,
					Constant.CONTENT_TYPE);
			if (ap.code != 200) {
				return null;
			}
			login = ap.responseData.as(Login.class);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return login;

	}
}
