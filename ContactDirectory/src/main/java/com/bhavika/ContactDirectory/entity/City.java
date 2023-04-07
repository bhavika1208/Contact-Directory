package com.bhavika.ContactDirectory.entity;

public class City {
	
	private int cityId;
	private String cityName;
	private int inState;
	
	public City() {
	}

	public City(int cityId, String cityName, int inState) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.inState = inState;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getInState() {
		return inState;
	}

	public void setInState(int inState) {
		this.inState = inState;
	}	
		
}
