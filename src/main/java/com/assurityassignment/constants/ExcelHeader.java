package com.assurityassignment.constants;

public enum ExcelHeader {
	
	Test_ID(0),
	Method_ID(1),
	Server_URL(2),
	Endpoint(3),
	HeaderKey(4),
	HeaderValue(5),
	RequestKey(6),
	RequestValue(7),
	ResponseKey(8),
	ResponseValue(9),
	ExpectedResponseStatus(10),
	HTTPMethod(11),
	QueryString(12);
	
	private int columnNumber;
	
	ExcelHeader(int columnNumber){
		this.columnNumber = columnNumber;
	}
	
	public int getColumnNumber() {
		return this.columnNumber;
	}

}
