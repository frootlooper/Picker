/*
 * This is a model of each restaurant that is pulled from Google API.
 * Then it is saved in the database for faster searching, etc.
 */

package com.Picker.model;

import java.util.List;

public class Restaurant {
	private String name;
	private String id;
	private String address;
	private double rating;
	private double priceLevel;
	private float lat;
	private float lng;
	private List<RestaurantType> types;
	
	@Override
	public String toString() {
		return "Name: "+name
				+", Address: "+address
				+", Rating: "+rating
				+", Price Level: "+priceLevel
				+", Latitude: "+lat
				+", Longitude: "+lng
				+", ID: "+id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getPriceLevel() {
		return priceLevel;
	}
	public void setPriceLevel(double priceLevel) {
		this.priceLevel = priceLevel;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public List<RestaurantType> getTypes() {
		return types;
	}
	public void setTypes(List<RestaurantType> types) {
		this.types = types;
	}
}
