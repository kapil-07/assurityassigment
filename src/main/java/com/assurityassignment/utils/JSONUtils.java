package com.assurityassignment.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public enum JSONUtils {
	
	INSTANCE;
	
	private LinkedHashMap<String, Object> jsonMap;
	
	JSONUtils(){
		jsonMap = new LinkedHashMap<>();
	}
	
	public void parseJSONObjecttoMap(JSONObject jsonObject, String root){
		
		
		Iterator<String> iterator = jsonObject.keySet().iterator();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			Object value = jsonObject.get(key);
			
			if(value instanceof JSONArray) {
				if(root.equals("")) {
					root = key.toString();
				}else {
					root = root +"."+ key.toString();
					
				}
				parseJSONArraytoList((JSONArray) value, root);
				if(root.contains(".")) {
					root = root.substring(0, root.lastIndexOf("."));
				}else {
					root = "";
				}
			}else if (value instanceof JSONObject) {
				if(root.equals("")) {
					root = key.toString();
				}else {
					
					root = root + "." + key.toString();
				}
				
				parseJSONObjecttoMap((JSONObject) value, root);
				
				if(root.contains(".")) {
					root = root.substring(0, root.lastIndexOf("."));
				}else {
					root = "";
				}
			}else {
				if(root.equals("")) {
					jsonMap.put(key, value);
				}else {
					jsonMap.put(root+"."+ key, value);
				}
			}
		}
	}
	
	public void parseJSONArraytoList (JSONArray jsonArray , String root) {
		
		for(int i =0 ; i < jsonArray.size() ; i++) {
			Object value = jsonArray.get(i);
			
			root = root + ".["+i+"]";
			if(value instanceof JSONObject) {
				parseJSONObjecttoMap((JSONObject) value, root);
				root = root.substring(0, root.lastIndexOf("."));
			}else if(value instanceof JSONArray) {
				parseJSONArraytoList((JSONArray) value, root);
				root = root.substring(0, root.lastIndexOf("."));
			}else {
				jsonMap.put(root, value);
				root = root.substring(0, root.lastIndexOf("."));
			}
		}
	}
	
	public LinkedHashMap<String, Object> getJSONObjects() {
		return jsonMap;
	}
	public void printJSONObjects() {
		jsonMap.forEach((key, value) -> System.out.println(key +"=======>"+ value));
	}
}
