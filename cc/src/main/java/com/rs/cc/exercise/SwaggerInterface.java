package com.rs.cc.exercise;

import java.net.HttpURLConnection;

import org.json.simple.JSONArray;

public interface SwaggerInterface {
	
	public String getUsers(HttpURLConnection conn);
	
	public HttpURLConnection connect(String method, String resource);
	
}
