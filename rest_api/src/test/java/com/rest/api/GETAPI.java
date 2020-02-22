package com.rest.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GETAPI {

	@Test
	public void getPlacesAPI() {
		RestAssured.baseURI="https://maps.googleapis.com";
		RestAssured.given().
		param("query", "12+main+street").
		param("location", "42.3675294,-71.186966").
		param("radius", "10000").
		param("key", "AIzaSyBuCYxhRhNNp_B2lBYhqFDX7RZorIY2wvE").
		when().
		get("/maps/api/place/textsearch/json").
		then().
		assertThat().
		statusCode(200).and().contentType(ContentType.JSON);
	}
}
