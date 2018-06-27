package com.techelevator;

public class Park {
	
	private long parkId;
	private String name;
	private String location;
	private String dateCreated;
	private int area;
	private int visitorCount;
	private String description;
	
	
	public long getParkId() {
		return parkId;
	}
	public void setParkId(long parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getVisitorCount() {
		return visitorCount;
	}
	public void setVisitorCount(int visitorCount) {
		this.visitorCount = visitorCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
