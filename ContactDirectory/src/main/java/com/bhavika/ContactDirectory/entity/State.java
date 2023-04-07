package com.bhavika.ContactDirectory.entity;

import java.util.List;

public class State {
	
	private int stateId;
	private String stateName;
	private List<City> cities;
	
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	public State(int stateId, String stateName, List<City> cities) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.cities = cities;
	}
	public State() {
	}

	
}
