package com.winds.smartlink.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityService {
	private VelocityEngine ve;
	private static VelocityService instance = new VelocityService();
	
	private VelocityService(){
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
	}
	
	public static VelocityService getInstance(){
		return instance;
	}
	
	public void writeFile(String template, String output, Map<String, String> data) throws IOException{
		Template t = ve.getTemplate(template);
		VelocityContext context = new VelocityContext();
		
		if(data != null) {
			Set<Entry<String, String>> entries = data.entrySet();
			
			for (Entry<String, String> entry : entries) {
				context.put(entry.getKey(), entry.getValue());		
			}
		}
		
		// Create folder
		new File(FilenameUtils.getFullPath(output)).mkdirs();
		
		// Bind data to template
		FileWriter writer = new FileWriter(output);
		t.merge(context, writer);
		writer.close();
	}
	
	public static void main(String[] args) throws IOException {
		VelocityService vs = VelocityService.getInstance();
		
		Map<String, String> data = new HashMap<String, String>();
		
		vs.writeFile("index.vm", "E:/test/test.html", data);
	}
}
