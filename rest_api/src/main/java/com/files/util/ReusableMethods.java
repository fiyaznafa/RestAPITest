package com.files.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static JsonPath rawToJson(Response res) {
		String response = res.asString();
		JsonPath resJson = new JsonPath(response);
		return resJson;
	}
	
	
	public static String GenerateString(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
