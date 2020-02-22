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

public class BooksDataProvider {
	Properties prop = new Properties();

	@BeforeTest
	public void getTestData() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/files/config.properties");
		prop.load(fis);
	}

	@Test(dataProvider = "bookData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = prop.getProperty("HOSTLIB");
		Response response = RestAssured.
				given().header("Content-Type", "application/json")
				.body(Payload.addLibrarBody(isbn, aisle)).
				when().post("/Library/Addbook.php").
				then().assertThat().statusCode(200).extract().response();
		JsonPath jsonResp = ReusableMethods.rawToJson(response);
		System.out.println(jsonResp.get("ID"));
		String bookId = jsonResp.get("ID");

		Response deleteResp = RestAssured.
				given().header("Content-Type", "application/json")
				.body(Payload.deleteBook(bookId)).
				when().post("/Library/DeleteBook.php").
				then().assertThat().statusCode(200).extract().response();
		JsonPath delJsonResp = ReusableMethods.rawToJson(deleteResp);
		System.out.println(delJsonResp.get("msg"));
		System.out.println(deleteResp.toString());
	}

	@DataProvider(name = "bookData")
	public Object[][] getData() {
		return new Object[][] { { "002348", "ABG" }, { "001236", "ABDE" } };
	}
}
