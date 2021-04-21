package com.freshworks.freshcinemas.model;

public class Theatre {
	
	private String theatreName;
	private int totalScreens;
	private int basePrice;

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public int getTotalScreens() {
		return totalScreens;
	}

	public void setTotalScreens(int totalScreens) {
		this.totalScreens = totalScreens;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	
	public Theatre(String theatreName,int totalScreens,int basePrice) {
		this.theatreName = theatreName;
		this.totalScreens = totalScreens;
		this.basePrice = basePrice;
	}

	public Theatre() {
	}
}
