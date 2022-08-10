package apiUtil;

import java.util.HashMap;
import java.util.Map;

import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;
import util.Constant;

public class OtpUtil {
	
	public ConfigFileReader cfReaderObject = new ConfigFileReader();
	public APIUtils apiUtilsObj = new APIUtils();

	public String getOtp(String strMobileNumber) {
		String strOtp = null;
		APIResponse ap = null;
		Map<String, String> formData;
		try {
			formData = new HashMap<String, String>();
			formData.put("mobile", strMobileNumber);
			formData.put("otp_token", cfReaderObject.getOtpToken());
			ap = apiUtilsObj.postCall(cfReaderObject.getBaseUrl(), Constant.USER_OTP, formData, Constant.CONTENT_TYPE);
			System.out.println(ap.getmessageCode());

			strOtp = ap.getJsonKeyValue("otp");

		} catch (Exception e) {
		}
		return strOtp;

	}

}
