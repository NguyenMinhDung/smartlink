package com.winds.smartlink.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
	private static final String HTTP_PORT = "80";
	// HTTP GET request
	public static String get(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();

		return response.toString();
	}

	public static String getDomainAndPort(HttpServletRequest request) {
		if(HTTP_PORT.equals(request.getLocalPort())) {
			return request.getServerName();
		}
		
		return request.getServerName() + ":" + request.getLocalPort();
	}
	
	public static String getPathAfterContextPatḥ̣(String url, String contextPath){
		return url.substring(url.indexOf(contextPath) + contextPath.length());
	}
}
