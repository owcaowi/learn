package com.example.aprzybylo.learn.Interfaces;

import com.example.aprzybylo.learn.Model.Flickr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;


public interface FlickrAPI {

	@Headers({"Content-Type: application/json"})
	@GET("rest/?method=flickr.photos.getRecent&api_key=13f32569bda25887d84a9b6190264a54" +
			"&format=json&nojsoncallback=1")
	Call<Flickr> loadChanges( @Header("User-Agent") String user_agent );


}
