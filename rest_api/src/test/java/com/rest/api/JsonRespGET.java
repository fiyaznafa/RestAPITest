package com.rest.api;

import org.testng.annotations.Test;
import com.files.util.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonRespGET {

	@Test
	public void getPlacesAPI() {
		RestAssured.baseURI="https://maps.googleapis.com";
		Response res = RestAssured.given().
		param("query", "12+main+street").
		param("location", "42.3675294,-71.186966").
		param("radius", "10000").
		param("key", "AIzaSyCR1xU-iAuq-voenlzU_vMwvRMizAGdPLo").
		when().
		get("/maps/api/place/textsearch/json").
		then().
		assertThat().
		statusCode(200).and().contentType(ContentType.JSON).extract().response();
		System.out.println(res.asString());
		JsonPath jsonResp = ReusableMethods.rawToJson(res);
		int count = jsonResp.get("results.size()");
		System.out.println("The size of the results "+count);
	}
}
