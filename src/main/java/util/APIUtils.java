package util;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

	public APIResponse ap;

	public APIResponse postCall(String baseUri, String endPoint, Map<String,String> formData, String strHeader) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/x-www-form-urlencoded");
			if (strHeader != null) {
				request.header("Authorization", strHeader);
			}
			request.formParams(formData);
			request.log().all();
			Response response = request.post(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {

		}
		return ap;
	}

	// Function to hit the Get API
	public APIResponse getCall(String strBaseUrl, String endPoint, String strHeader) {
		try {
			RestAssured.baseURI = strBaseUrl;
			System.out.println("base" + strBaseUrl);
			RequestSpecification request = RestAssured.given();
			if (strHeader != null) {
				request.header("Authorization", strHeader);
			}
			request.log().all();
			Response response = request.get(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {

		}
		return ap;
	}

	// Function to hit the PUT API to make some changes
	public APIResponse putCall(String baseUri, String endPoint, Object postData, String strHeader) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			if (strHeader != null) {
				request.header("Authorization", strHeader);
				request.header("Content-Type", "application/json");
			}
			request.log().all();
			request.body(postData.toString());
			Response response = request.put(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {

		}
		return ap;
	}

	public APIResponse patchCall(String strBaseUrl, String endPoint, Object postData, String strHeader) {
		try {
			RestAssured.baseURI = strBaseUrl;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			if (strHeader != null) {
				request.header("Authorization", strHeader);
			}
			request.body(postData.toString());
			request.log().all();
			Response response = request.patch(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {

		}
		return ap;
	}

	// Function to hit the PUT API to make some changes
	public APIResponse putCallJWT(String baseUri, String endPoint, Object postData, String strHeader) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			if (strHeader != null) {
				request.header("Authorization", strHeader);
				request.header("Content-Type", "application/json");
			}
			request.log().all();
			request.body(postData.toString());
			Response response = request.put(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {

		}
		return ap;
	}

	public APIResponse postCallWithBasicAuth(String baseUri, String endPoint, Object postData, String strHeader,
			String strUserName, String strPassword) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			if (strHeader != null) {
				request.header("Authorization", strHeader);
			}
			request.auth().basic(strUserName, strPassword);
			request.body(postData.toString());
			request.log().all();
			Response response = request.post(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {

		}
		return ap;
	}
}
