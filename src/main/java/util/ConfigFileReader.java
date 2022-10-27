package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	public static String strEnv;
	public static String strDeviceName, userName, accessKey, strDeviceVersion, strRunMode,
			strDeviceIndex, strFilePath,strUserMobileNumber;
	public static boolean isTrueCallerFeature;
	public static String strApplicationType;
	static {
		strEnv = System.getProperty("env");
		strEnv = "stag";
		strDeviceName = System.getProperty("deviceId");
		userName = System.getProperty("userName");
		userName = "abhay.rai";
		accessKey = System.getProperty("accessKey");
		accessKey = "f562ULSQYygxPzwtlVtAvfsna7mnAoWTlVlS4BIC3FYUs6Tf1W";
		strDeviceName = System.getProperty("deviceId");
		System.out.println("strDeviceName " + strDeviceName);
		strDeviceVersion = System.getProperty("version");
		strRunMode = System.getProperty("runMode");
		strRunMode = "local";
		strDeviceName = "e252241e";
		strDeviceVersion = "11.0";
		strDeviceIndex = System.getProperty("deviceIndex");
		strDeviceIndex = "0";
		strFilePath = System.getProperty("apkId");
		strFilePath = "bs://af78bcd6b02986208d034c691a4e02f72f686392";
		strUserMobileNumber = System.getProperty("mobileNumber");
		strUserMobileNumber = "9878252339";
	}

	private Properties properties;

	public ConfigFileReader() {
		BufferedReader reader;
		String strPropertyPath = null;
		try {
			if (strEnv.equalsIgnoreCase("stag")) {
				strPropertyPath = "src/main/resources/config/staging.properties";

			} else if (strEnv.equalsIgnoreCase("dev")) {

				strPropertyPath = "src/main/resources/config/dev.properties";

			} else if (strEnv.equalsIgnoreCase("prod")) {

				strPropertyPath = "src/main/resources/config/prod.properties";

			}
			reader = new BufferedReader(new FileReader(strPropertyPath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + strPropertyPath);
		}
	}

	public String getEnv() {
		String strEnv = properties.getProperty("env");
		if (strEnv != null)
			return strEnv;
		else
			throw new RuntimeException(strEnv + "not specified in the Configuration properties file.");
	}

	public String getBaseUrl() {
		String strBaseUrl = properties.getProperty("BaseUrl");
		if (strBaseUrl != null)
			return strBaseUrl;
		else
			throw new RuntimeException("Base url is not defined.");
	}

	public String getOtpToken() {
		String strOtpToken = properties.getProperty("otpToken");
		if (strOtpToken != null)
			return strOtpToken;
		else
			throw new RuntimeException("Otp token is not defined.");
	}

}
