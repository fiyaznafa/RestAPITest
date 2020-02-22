package com.rest.api;

import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.files.util.GetTestData;
import com.files.util.Payload;
import com.files.util.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostReqDelPlace {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getTestData() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/files/config.properties");
		prop.load(fis);
	}
	
	
	@Test
	public void TestPostRequest() {

		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = RestAssured.given().queryParam("key", prop.getProperty("KEY")).body(Payload.addPayloadBody()).when()
				.post(GetTestData.postDataResource()).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK")).extract().response();
		
		String place_id = ReusableMethods.rawToJson(res).get("place_id");
		Response deletResponse = RestAssured.given().queryParam("key", "qaclick123")
				.body(Payload.deletePayloadBody(place_id)).when()
				.post("/maps/api/place/delete/json").then().assertThat().statusCode(200).and()
				.body("status", equalTo("OK")).extract().response();
		System.out.println(deletResponse.asString());

	}

}
