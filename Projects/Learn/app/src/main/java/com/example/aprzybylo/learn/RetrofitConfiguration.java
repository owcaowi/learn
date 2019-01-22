package com.example.aprzybylo.learn;

import com.example.aprzybylo.learn.Interfaces.FlickrAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RetrofitConfiguration {

	static final String BASE_URL = "https://api.flickr.com/services/";
	static final String API_KEY = "13f32569bda25887d84a9b6190264a54";

	private Retrofit retrofit;
	private String userAgent;

	private static final RetrofitConfiguration ourInstance = new RetrofitConfiguration();

	static RetrofitConfiguration getInstance() {
		return ourInstance;
	}

	private RetrofitConfiguration() {

		userAgent = System.getProperty("http.agent");

		Gson gson = new GsonBuilder()
				.setLenient()
				.create();


		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
				.connectTimeout(60, TimeUnit.SECONDS)
				.readTimeout(60, TimeUnit.SECONDS)
				.writeTimeout(60, TimeUnit.SECONDS)
				.build();

		retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();

	}

	public FlickrAPI getApi(){
		return retrofit.create(FlickrAPI.class);
	}

	public String getUserAgent() {
		return userAgent;
	}
}
