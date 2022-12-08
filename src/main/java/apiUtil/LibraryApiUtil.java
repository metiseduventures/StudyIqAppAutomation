package apiUtil;

import java.util.HashMap;
import java.util.Map;
import pojo.myLibrary.MyLibrary;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;
import util.Constant;

public class LibraryApiUtil {

	public ConfigFileReader cfReaderObject = new ConfigFileReader();
	public APIUtils apiUtilsObj = new APIUtils();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MyLibrary getLibraryData(String jwtToken, int strUserId) {
		APIResponse ap = null;
		Map formData;
		MyLibrary myLibraryObj = null;
		try {
			formData = new HashMap();
			formData.put("user_id", strUserId);
			formData.put("api_token", jwtToken);

			ap = apiUtilsObj.postCall(cfReaderObject.getBaseUrl(), Constant.MY_LIBRARY, formData, null);
			System.out.println(ap.getmessageCode());
			myLibraryObj = ap.responseData.as(MyLibrary.class);

		} catch (Exception e) {
		}

		return myLibraryObj;

	}

}
