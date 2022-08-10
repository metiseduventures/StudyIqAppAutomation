package util;

import java.util.Map;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

public class APIResponse {
	public int code;
	public String messageCode;
	public Headers responseHeader;
	public Map<String, String> cookies;
	public Response responseData;
	public Cookies detailedCookies;

	public APIResponse(Response response) {
		this.setcode(response.getStatusCode());
		this.setmessageCode(response.asString());
		this.setresponseHeader(response.getHeaders());
		this.setCookie(response.getCookies());
		this.setResponse(response);
		this.setDetailedCookie(response.getDetailedCookies());
	}

	public void setcode(int code) {
		this.code = code;
	}

	public void setResponse(Response responseData) {
		this.responseData = responseData;
	}

	public void setmessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public void setresponseHeader(Headers responseHeader) {
		this.responseHeader = responseHeader;
	}

	public void setDetailedCookie(Cookies detailedCookies) {
		this.detailedCookies = detailedCookies;
	}

	public int getCode() {
		return code;
	}

	public String getmessageCode() {
		return messageCode;
	}
	public Response getResponse() {
		return responseData;
	}

	public Headers getResponseHeader() {
		return responseHeader;
	}

	public void setCookie(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public Map<String, String> getCookie() {
		return cookies;
	}
	
	public Cookies getDetailedCookie()
	{
		return detailedCookies;
	}

	public Validatable<ValidatableResponse, Response> getFullResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getJsonKeyValue(String strPath)
	{
		return getResponse().jsonPath().get(strPath).toString();
	}
}
