package com.yyc.androiddemo.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yyc.androiddemo.bean.Location;

public class JSONParserUtil {

	public static List<Location> getLocationsFromJSON(String jsonString) {
		List<Location> locations = new ArrayList<Location>();
		try {
			JSONObject rootJSON = new JSONObject(jsonString);
			JSONObject resultJSON = rootJSON.getJSONObject("Result");
			JSONArray linksArray = resultJSON.getJSONArray("links");

			for (int i = 0; i < linksArray.length(); i++) {
				Location l = new Location();
				JSONObject locationObject = linksArray.getJSONObject(i);
				l.setTitle(locationObject.optString("articleName"));
				l.setDescription(locationObject.optString("introduction"));
				l.setPicURL("http://sqt.9tong.com"
						+ locationObject.optString("pic"));
				locations.add(l);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return locations;
	}
}