package com.rest.library;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.util.Payload;
import com.files.util.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequestAddBook {
	

	Properties prop = new Properties();
	
	@BeforeTest
	public void getTestData() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/files/config.properties");
		prop.load(fis);
	}

	@Test
	public void addBook() {
		RestAssured.baseURI=prop.getProperty("HOSTLIB");
		Response response = RestAssured.given().
		header("Content-Type","application/json").
		body(Payload.addLibrarBody("0012456","001ABC")).
		when().
		post("/Library/Addbook.php").
		then().
		assertThat().statusCode(200).
		extract().response();
		JsonPath jsonResp = ReusableMethods.rawToJson(response);
		System.out.println(jsonResp.get("ID")) ;
		String bookId=jsonResp.get("ID");
		
		Response deleteResp = RestAssured.given().
				header("Content-Type","application/json").
				body(Payload.deleteBook(bookId)).
				when().
				post("/Library/DeleteBook.php").
				then().
				assertThat().statusCode(200).
				extract().response();
				jsonResp = ReusableMethods.rawToJson(deleteResp);
				System.out.println(jsonResp.get("msg")) ;
	}
	
	
	/*
	 * @DataProvider() public void getData() { Object[][]= {{"",""}} }
	 */
	
	
}
