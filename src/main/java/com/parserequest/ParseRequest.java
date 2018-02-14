package com.parserequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseRequest {

	public void parseRequest(String requestString) {

		String[] req = requestString.split("\\|");

		JSONObject mainJson = new JSONObject();
		mainJson.put("id", req[1]);

		String[] nameSplit = req[2].split("<");

		JSONObject nameJson = new JSONObject();
		nameJson.put("first", nameSplit[1].replace("<", "").replace(">", ""));
		nameJson.put("middle", nameSplit[2].replace("<", "").replace(">", ""));
		nameJson.put("last", nameSplit[3].replace("<", "").replace(">", ""));

		mainJson.put("name", nameJson);

		mainJson.put("dob", req[3]);

		String[] citySplit = req[4].split(",");

		JSONArray locationsArrays = new JSONArray();

		for (int i = 0; i < citySplit.length; i++) {

			String[] fetchDetails = citySplit[i].split(">");
			JSONObject locationJson = new JSONObject();
			locationJson.put("name", fetchDetails[0].replace("<", "").replace(">", ""));

			JSONObject coordsJson = new JSONObject();
			coordsJson.put("long", fetchDetails[1].replace("<", "").replace(">", ""));
			coordsJson.put("lat", fetchDetails[1].replace("<", "").replace(">", ""));

			locationJson.put("coords", coordsJson);
			locationsArrays.put(locationJson);
		}

		mainJson.put("locations", locationsArrays);
		mainJson.put("imageId", req[5]);

		System.out.println(mainJson);

	}

	public static void main(String[] args) {
		ParseRequest parseRequest = new ParseRequest();
		parseRequest.parseRequest(args[0]);
	}

}
