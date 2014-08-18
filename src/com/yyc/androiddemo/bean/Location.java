package com.yyc.androiddemo.bean;

public class Location {
	
	private String title;
	private String description;
	private String picURL;
	public Location(String title, String description, String picURL) {
		super();
		this.title = title;
		this.description = description;
		this.picURL = picURL;
	}
	public Location() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicURL() {
		return picURL;
	}
	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}
	public String toString(){
		return "Title: "+ this.title + ";Description: " + this.description + ";PicURL: " + this.picURL;
	}
}
