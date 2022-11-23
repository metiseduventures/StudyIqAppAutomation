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
		strEnv = "prod";
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
		strDeviceName = "RZ8T11F8CYW";
		strDeviceVersion = "12.0";
		strDeviceIndex = System.getProperty("deviceIndex");
		strDeviceIndex = "0";
		strFilePath = System.getProperty("apkId");
		strFilePath = "lt://APP10160571881669016963353488";
		strUserMobileNumber = System.getProperty("mobileNumber");
		strUserMobileNumber = "9958544199";
		isTrueCallerFeature = true;
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
	
	public String getVideoSlug() {
		String strVideoSlug = properties.getProperty("video");
		if (strVideoSlug != null)
			return strVideoSlug;
		else
			throw new RuntimeException(strVideoSlug + "not specified in the Configuration properties file.");
	}
	
	public String getBooksSlug() {
		String strBooksSlug = properties.getProperty("books");
		if (strBooksSlug != null)
			return strBooksSlug;
		else
			throw new RuntimeException(strBooksSlug + "not specified in the Configuration properties file.");
	}
	
	public String getLiveSlug() {
		String strLiveSlug = properties.getProperty("live");
		if (strLiveSlug != null)
			return strLiveSlug;
		else
			throw new RuntimeException(strLiveSlug + "not specified in the Configuration properties file.");
	}
	
	public String getTestseriesSlug() {
		String strTestseriesSlug = properties.getProperty("testseries");
		if (strTestseriesSlug != null)
			return strTestseriesSlug;
		else
			throw new RuntimeException(strTestseriesSlug + "not specified in the Configuration properties file.");
	}
	
	public String getBooksCrossSellSlug() {
		String strBooksCSSlug = properties.getProperty("booksCrossSell");
		if (strBooksCSSlug != null)
			return strBooksCSSlug;
		else
			throw new RuntimeException(strBooksCSSlug + "not specified in the Configuration properties file.");
	}
	
	public String getVideoCrossSellSlug() {
		String strVideoCSSlug = properties.getProperty("videoCrossSell");
		if (strVideoCSSlug != null)
			return strVideoCSSlug;
		else
			throw new RuntimeException(strVideoCSSlug + "not specified in the Configuration properties file.");
	}
	
	public String getLiveCrossSellSlug() {
		String strLiveCSSlug = properties.getProperty("liveCrossSell");
		if (strLiveCSSlug != null)
			return strLiveCSSlug;
		else
			throw new RuntimeException(strLiveCSSlug + "not specified in the Configuration properties file.");
	}
	
	public String getTestseriesCrossSellSlug() {
		String strTestSeriesCSSlug = properties.getProperty("testseriesCrossSell");
		if (strTestSeriesCSSlug != null)
			return strTestSeriesCSSlug;
		else
			throw new RuntimeException(strTestSeriesCSSlug + "not specified in the Configuration properties file.");
	}

}
