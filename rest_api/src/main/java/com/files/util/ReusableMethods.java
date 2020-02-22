package com.files.util;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static JsonPath rawToJson(Response res) {
		String response = res.asString();
		JsonPath resJson = new JsonPath(response);
		return resJson;
	}
	
}
