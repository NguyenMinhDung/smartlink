package com.winds.smartlink.utils;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
	public static <T> T parseJson(String json, Class<T> clazz) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		T obj = mapper.readValue(json, clazz);
		return obj;
	}
	
	public static <T> String toJson(T obj) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		mapper.setDateFormat(df);
		
		return mapper.writeValueAsString(obj);
	}
}
