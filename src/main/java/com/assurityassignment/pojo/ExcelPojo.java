package com.assurityassignment.pojo;

import java.util.LinkedHashMap;

public class ExcelPojo {

	private String serverURL;
	private String endPoint;
	private LinkedHashMap<String, Object> requestHeaders = new LinkedHashMap<>();
	private LinkedHashMap<String, String> requestMap = new LinkedHashMap<>();
	private LinkedHashMap<String, Object> expectedResponseMap = new LinkedHashMap<>();
	private LinkedHashMap<String, Object> actualResponseMap = new LinkedHashMap<>();
	private String expectedResponseStatus;
	private String httpMethod;
	private String queryString = null;
	
	
	public String getServerURL() {
		return this.serverURL;
	}
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
	public String getEndpoint() {
		return this.endPoint;
	}
	
	public void setEndpoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public LinkedHashMap<String, Object> getRequestHeaders() {
		return requestHeaders;
	}
	public void setRequestHeader(String key, Object value) {
		this.requestHeaders.put(key, value);
	}
	public LinkedHashMap<String, String> getRequestMap() {
		return requestMap;
	}
	public void setRequestMap(String key, String value) {
		this.requestMap.put(key, value);
	}
	public LinkedHashMap<String, Object> getExpectedResponseMap() {
		return expectedResponseMap;
	}
	public void setExpectedResponseMap(String key, Object value) {
		this.expectedResponseMap.put(key, value);
	}
	public LinkedHashMap<String, Object> getActualResponseMap() {
		return actualResponseMap;
	}
	public void setActualResponseMap(LinkedHashMap<String, Object> actualResponseMap) {
		this.actualResponseMap = actualResponseMap;
	}
	public String getExpectedResponseStatus() {
		return expectedResponseStatus;
	}
	public void setExpectedResponseStatus(String expectedResponseStatus) {
		this.expectedResponseStatus = expectedResponseStatus;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
