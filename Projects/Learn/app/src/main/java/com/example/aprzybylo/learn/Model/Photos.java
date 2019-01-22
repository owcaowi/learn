package com.example.aprzybylo.learn.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos implements Serializable
{

	@SerializedName("page")
	@Expose
	private int page;
	@SerializedName("pages")
	@Expose
	private int pages;
	@SerializedName("perpage")
	@Expose
	private int perpage;
	@SerializedName("total")
	@Expose
	private int total;
	@SerializedName("photo")
	@Expose
	private List<Photo> photo = new ArrayList<Photo>();
	private final static long serialVersionUID = 173280326703763540L;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Photos withPage(int page) {
		this.page = page;
		return this;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Photos withPages(int pages) {
		this.pages = pages;
		return this;
	}

	public int getPerpage() {
		return perpage;
	}

	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}

	public Photos withPerpage(int perpage) {
		this.perpage = perpage;
		return this;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Photos withTotal(int total) {
		this.total = total;
		return this;
	}

	public List<Photo> getPhoto() {
		return photo;
	}

	public void setPhoto(List<Photo> photo) {
		this.photo = photo;
	}

	public Photos withPhoto(List<Photo> photo) {
		this.photo = photo;
		return this;
	}

}

