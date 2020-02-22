package com.files.util;

public class Payload {

	public static String addPayloadBody() {
	
		String body="{\r\n" + "    \"location\":{\r\n" + "        \"lat\" : -38.383494,\r\n"
				+ "        \"lng\" : 33.427362\r\n" + "    },\r\n" + "    \"accuracy\":50,\r\n"
				+ "    \"name\":\"Frontline house\",\r\n" + "    \"phone_number\":\"(+91) 983 893 3937\",\r\n"
				+ "    \"address\" : \"29, side layout, cohen 09\",\r\n"
				+ "    \"types\": [\"shoe park\",\"shop\"],\r\n" + "    \"website\" : \"http://google.com\",\r\n"
				+ "    \"language\" : \"French-IN\"\r\n" + "}\r\n" + "\r\n" + "";
		return body;
	}

	public static String deletePayloadBody(String place_id) {
		String body="{\r\n" + "    \"place_id\":\"" + place_id + "\"          \r\n" + "}\r\n" + "";
		return body;
	}
	
	public static String addLibrarBody(String ISBN, String Aisle) {
		String body="{\r\n" + 
				"\r\n" + 
				"\"name\":\"LearnJava\",\r\n" + 
				"\"isbn\":\""+ISBN+"\",\r\n" + 
				"\"aisle\":\""+Aisle+"\",\r\n" + 
				"\"author\":\"Fiyaz\"\r\n" + 
				"}\r\n" + 
				" \r\n" + 
				"";
		return body;
	}
	
	public static String deleteBook(String id) {
	
		String body="{\r\n" + 
				"\"ID\" : \""+id+"\"\r\n" + 
				"}\r\n" + 
				"";
		return body;
	}
	
	
}
