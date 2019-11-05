package com.assurityassignment.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.assurityassignment.pojo.ExcelPojo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public enum RestAssuredUtils {
	
	INSTANCE;
	private static final Logger LOGGER = Logger.getLogger(RestAssuredUtils.class);
	private JSONObject request = null;
	private JSONObject actualResponse = null;
	private int statusCode = 0;
	ExcelPojo methodPOJO = null;
	RestAssuredUtils() {
		request = new JSONObject();
		actualResponse = new JSONObject();
	}

	
	public boolean requestAndValidateStatus (String testId, String methodId) {
		
		this.methodPOJO = ExcelUtils.INSTANCE.getMethodPOJO(testId, methodId);
		RequestSpecification request = RestAssured.given();
		
		LOGGER.info("*** Checking whether any nested value is present in the query Parameter ***");
		String finalEndpoint;
		if(methodPOJO.getQueryString() == null) {
			LOGGER.info(" Query String is not present for Method "+methodId);
			finalEndpoint = methodPOJO.getEndpoint();
		}else {
			finalEndpoint = methodPOJO.getEndpoint()+"?"+getNestedAPIValue(testId, methodPOJO.getQueryString());
		}
		
		LOGGER.info("**** Checking whether any nested value is present in request headers and replacing them ***");
		methodPOJO.getRequestHeaders().replaceAll((headerKey, headerValue) -> getNestedAPIValue(testId, headerValue.toString()));
		request.headers(methodPOJO.getRequestHeaders());
		Response response = null ;
		request.baseUri(methodPOJO.getServerURL());

		switch(methodPOJO.getHttpMethod().trim().toLowerCase()) {
		
		case "get":
			response = request.request(Method.GET, finalEndpoint);
			this.statusCode = response.getStatusCode();
			break;
		case "post":
			request.body(getRequestBody(testId, methodPOJO.getRequestMap()));
			response = request.request(Method.POST, finalEndpoint);
			this.statusCode = response.getStatusCode();
			break;
		case "put":
			request.body(getRequestBody(testId, methodPOJO.getRequestMap()));
			response = request.request(Method.PUT,finalEndpoint );
			this.statusCode = response.getStatusCode();
			break;
			
		case "delete":
			request.body(getRequestBody(testId, methodPOJO.getRequestMap()));
			response = request.request(Method.DELETE,finalEndpoint );
			this.statusCode = response.getStatusCode();
			break;
		
		}
		
		JSONParser parser = new JSONParser();
		
		try {
			this.actualResponse = (JSONObject) parser.parse(response.asString());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return (this.statusCode == Integer.parseInt(methodPOJO.getExpectedResponseStatus()));
	}


	private String getRequestBody(String testId, LinkedHashMap<String, String> requestMap) {
		JSONObject jsonRequestObject= null;
		
		Iterator<String> iterator = requestMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			String value = requestMap.get(key);
			if(key.equals("Request")) {
				try(FileReader fileReader = new FileReader(value)){
					JSONParser parser = new JSONParser();
					jsonRequestObject = (JSONObject) parser.parse(fileReader);
					
				}catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
			}else {
				try {
					PropertyUtils.setProperty(jsonRequestObject, key, getNestedAPIValue(testId, value));
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}
		this.request = jsonRequestObject;
		return jsonRequestObject.toJSONString();
	}

	public boolean validateResponse() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		LinkedHashMap<String, Object> expectedResponseMap = this.methodPOJO.getExpectedResponseMap();
		Iterator<String> iterator = expectedResponseMap.keySet().iterator();
		boolean flag = true;
		while(iterator.hasNext()) {
			String key = iterator.next();
			Object value = expectedResponseMap.get(key);
			if(value.toString().startsWith("*{")) {
				if(! PropertyUtils.getProperty(actualResponse, key).toString().contains(value.toString().subSequence(2, value.toString().length() - 1))) {
					flag = false;
					LOGGER.info("Actual Response ==> "+key+"doesnt Contain "+ value.toString().subSequence(2, value.toString().length() - 1) + " ===> Fail");
				}else {
					LOGGER.info("Actual Response ==> "+key+" Contains "+ value.toString().subSequence(2, value.toString().length() - 1) + " ===> Passed");
				}
			}else {
				if(!value.equals(PropertyUtils.getProperty(this.actualResponse, key))){
					flag = false;
					LOGGER.info("Actual Response ==> "+key+"doesnt Contain "+ value.toString() + " ===> Fail");
				}else {
					LOGGER.info("Actual Response ==> "+key+" Contains "+ value.toString() + " ===> Passed");
				}
			}
			}
		return flag;
		}
	
	public String getNestedAPIValue(String testId, String nestedString) {
		Pattern pattern = Pattern.compile("\\$\\{[0-9a-zA-Z.\\[\\]]*\\}");
		Matcher matcher = pattern.matcher(nestedString);
		StringBuilder newString = new StringBuilder(nestedString);
		while(matcher.find()) {
			String replaceableValue = nestedString.substring(matcher.start() + 2, matcher.end() -1);
			String replaceValueWithMethod = replaceableValue.substring(0, replaceableValue.indexOf("."));
			String replaceValueFrom = replaceableValue.substring(replaceableValue.indexOf(".")+1).split("\\.")[0];
			ExcelPojo methodPojo = ExcelUtils.INSTANCE.getMethodPOJO(testId, replaceValueWithMethod);
			switch(replaceValueFrom) {
			case "response":
				Object replaceWithValue = methodPojo.getActualResponseMap().get(replaceValueFrom);
				newString.replace(newString.indexOf("${"+replaceableValue+"}"),("${"+replaceableValue+"}").length()+newString.indexOf("${"+replaceableValue+"}"),replaceWithValue.toString() );
			}
		}
		
		return newString.toString();
	}
}
