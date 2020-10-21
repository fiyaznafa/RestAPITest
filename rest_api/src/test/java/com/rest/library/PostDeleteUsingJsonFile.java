package com.rest.library;

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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostDeleteUsingJsonFile {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getTestData() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/files/config.properties");
		prop.load(fis);
	}
	
	
	@Test
	public void TestPostRequest() throws IOException {
		String postData=ReusableMethods.GenerateString("./src/main/resources/TestData/testData.json");
		RestAssured.baseURI = prop.getProperty("HOSTLIB");
		Response res = RestAssured.given().queryParam("key", prop.getProperty("KEY")).body(postData).when()
				.post(GetTestData.postDataResourceLibApi()).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().extract().response();
		JsonPath jsonResp = ReusableMethods.rawToJson(res);
		System.out.println(jsonResp.get("ID"));
		String bookId = jsonResp.get("ID");

		Response deleteResp = RestAssured.
				given().header("Content-Type", "application/json")
				.body(Payload.deleteBook(bookId)).
				when().post(GetTestData.postDataDeleteResourceLibApi()).
				then().assertThat().statusCode(200).extract().response();
		JsonPath delJsonResp = ReusableMethods.rawToJson(deleteResp);
		System.out.println(delJsonResp.get("msg"));		
	}
}

