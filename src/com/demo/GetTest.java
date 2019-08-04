/**
 * 
 */
package com.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * @author namra
 *
 */
public class GetTest {

	@Test
	public void GetWeatherDetails() {
		RestAssured.baseURI = "https://api.openweathermap.org/data/2.5";

		Response response = null;

		try {
			response = RestAssured.given().when().queryParam("q", "London")
					.queryParam("appid", "b6907d289e10d714a6e88b30761fae22").get("/weather");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

		Assert.assertTrue(responseBody.toLowerCase().contains("london"), "Validate response body");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Correct Status Code returned");

		Headers allHeaders = response.headers();
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + "Value: " + header.getValue());
		}
	}

}
