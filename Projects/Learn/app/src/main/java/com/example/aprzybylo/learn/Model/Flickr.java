package com.example.aprzybylo.learn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Flickr implements Serializable
{

	@SerializedName("photos")
	@Expose
	private Photos photos;
	@SerializedName("stat")
	@Expose
	private String stat;
	private final static long serialVersionUID = -6552290701441941292L;

	public Photos getPhotos() {
		return photos;
	}

	public void setPhotos(Photos photos) {
		this.photos = photos;
	}

	public Flickr withPhotos(Photos photos) {
		this.photos = photos;
		return this;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Flickr withStat(String stat) {
		this.stat = stat;
		return this;
	}

}