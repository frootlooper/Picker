package com.Picker.model;

public class ZipCode {
	private int code;
	private float latitude;
	private float longitude;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Zip code: "+code+", Latitude: "+latitude+", Longitude: "+longitude;
	}
	
}
