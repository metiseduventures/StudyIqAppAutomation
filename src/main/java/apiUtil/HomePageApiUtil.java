package apiUtil;
import pojo.menuList.MenuList;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class HomePageApiUtil {

	
	public ConfigFileReader cfReaderObject = new ConfigFileReader();
	public APIUtils apiUtilsObj = new APIUtils();
	APIResponse ap = null;
	
	public MenuList getHomePageMenu() {
		MenuList menuListObj = null;
		try {

			ap = apiUtilsObj.getCall(cfReaderObject.getBaseUrl(), "web/get_homemenu", null);
			if (ap.code != 200) {
				return null;
			}

			menuListObj = ap.responseData.as(MenuList.class);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return menuListObj;

	}
}
