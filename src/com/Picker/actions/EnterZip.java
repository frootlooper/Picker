package com.Picker.actions;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import javax.json.JsonArray;

import com.opensymphony.xwork2.ActionSupport;
import com.Picker.model.Restaurant;
import com.Picker.model.ZipCode;

@InterceptorRef(value="authStack") //User must be authenticated
public class EnterZip extends ActionSupport {

	private static final long serialVersionUID = 1L;
	//Move apikey to properties file?
	private static String apikey = "AIzaSyD5DKLxRQV-Dd5oaN48cXsA34bICRUns7M";
	private boolean postBack;
	//Defaulted to my zip code for now, plan to default to each user's
	//individual home zip code in the future
	private Integer enteredZip;
	private ZipCode zipCode;
	private List<Restaurant> restaurants;
	private int enteredRadius = 5000;
	private String currentUsername;

	@Override
	@Action("enterzip")
	public String execute() {
		if (isPostBack()) {
			setLocationFromZip(enteredZip);
			return getRestaurantList();
		}
		return SUCCESS;
	}
	
	public void setLocationFromZip(int zip) {
		setZipCode(new ZipCode());
		getZipCode().setCode(zip);
		
		URL url = null;
		try {
			url = new URL("https://maps.googleapis.com/maps/api/geocode/json?components=postal_code:"+zip+"&key="+apikey);
		} catch (MalformedURLException e) {
			System.out.println("Created a malformed URL");
			e.printStackTrace();
		}
		
		JsonObject jsonObj = getJSONFromURL(url);
		
		JsonArray arr = jsonObj.getJsonArray("results");
		JsonObject o = arr.getJsonObject(0).getJsonObject("geometry").getJsonObject("bounds");
			
		float NElat = o.getJsonObject("northeast").getJsonNumber("lat").bigDecimalValue().floatValue();
		float NElng = o.getJsonObject("northeast").getJsonNumber("lng").bigDecimalValue().floatValue();
		float SWlat = o.getJsonObject("southwest").getJsonNumber("lat").bigDecimalValue().floatValue();
		float SWlng = o.getJsonObject("southwest").getJsonNumber("lng").bigDecimalValue().floatValue();
			
		getZipCode().setLatitude((NElat+SWlat)/2);
		getZipCode().setLongitude((NElng+SWlng)/2);
	}
	
	public String getRestaurantList() {
		restaurants = new ArrayList<Restaurant>();
		String nextPageToken = null;
		
		do {
			//Format URL
			URL url = null;
			try {
				String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
						+zipCode.getLatitude()+","
						+zipCode.getLongitude()
						+"&radius="+enteredRadius
						+"&type=restaurant&key="+apikey;
				if (nextPageToken != null) {
					urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&key="+apikey+"&pagetoken="+nextPageToken;
				}
				url = new URL(urlString);
			} catch (MalformedURLException e) {
				System.out.println("Created a malformed URL");
				e.printStackTrace();
			}
			
			//Get JSON
			JsonObject json = getJSONFromURL(url);
			
			//Check for page token, if any. Will return null if it does not exist
			if (json.getJsonString("next_page_token") != null) {
				nextPageToken = json.getJsonString("next_page_token").getString();
			} else {
				nextPageToken = null;
			}
			
			//Get restaurant list
			JsonArray arr = json.getJsonArray("results");
			Iterator it = arr.iterator();
			while (it.hasNext()) {
				Restaurant r = new Restaurant();
				JsonObject obj = (JsonObject) it.next();
				r.setName(obj.getString("name"));
				r.setAddress(obj.getString("vicinity"));
				if (obj.getJsonNumber("rating") != null) {
					r.setRating(obj.getJsonNumber("rating").doubleValue());
				}
				if (obj.getJsonNumber("price_level") != null) {
					r.setPriceLevel(obj.getJsonNumber("price_level").doubleValue());
				}
				r.setLat(obj.getJsonObject("geometry").getJsonObject("location").getJsonNumber("lat").bigDecimalValue().floatValue());
				r.setLng(obj.getJsonObject("geometry").getJsonObject("location").getJsonNumber("lng").bigDecimalValue().floatValue());
				r.setId(obj.getString("place_id"));
				restaurants.add(r);
			}
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (nextPageToken != null);
		
		System.out.println(restaurants.size());
		
		return "ajax";
	}
	
	public JsonObject getJSONFromURL(URL url) {
		System.out.println(url.toString());
		JsonObject json = null;
		try {
			InputStream is = url.openStream();
			JsonReader reader = Json.createReader(is);
			json = reader.readObject();
			System.out.println("JSON IS: "+json);
			reader.close();
			is.close();
		} catch (IOException e) {
			System.out.println("Error getting JSON from Google API");
			e.printStackTrace();
		}
		return json;
	}

	public Integer getEnteredZip() {
		return enteredZip;
	}

	public void setEnteredZip(Integer enteredZip) {
		this.enteredZip = enteredZip;
	}

	public boolean isPostBack() {
		return postBack;
	}

	public void setPostBack(boolean postBack) {
		this.postBack = postBack;
	}

	public ZipCode getZipCode() {
		return zipCode;
	}

	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	public int getEnteredRadius() {
		return enteredRadius;
	}

	public void setEnteredRadius(int enteredRadius) {
		this.enteredRadius = enteredRadius;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public String getCurrentUsername() {
		return currentUsername;
	}

	public void setCurrentUsername(String currentUsername) {
		this.currentUsername = currentUsername;
	}
}
