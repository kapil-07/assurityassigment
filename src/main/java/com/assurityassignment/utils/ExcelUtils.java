package com.assurityassignment.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.assurityassignment.constants.ExcelHeader;
import com.assurityassignment.pojo.ExcelPojo;

public enum ExcelUtils {
	
	INSTANCE;
	
	private LinkedHashMap<String, LinkedHashMap<String, ExcelPojo>> excelData = null;
	private static final Logger LOGGER = Logger.getLogger(ExcelUtils.class);
	
	ExcelUtils() {
		excelData = new LinkedHashMap<>();
	}
	
	public void readExcel(String pathToExcel) throws IOException {
		 FileInputStream excelFile = null;
		 Workbook workbook = null;
		 try {

			    excelFile = new FileInputStream(new File(pathToExcel));
			    workbook  = new XSSFWorkbook(excelFile);
	            workbook.setMissingCellPolicy(MissingCellPolicy.RETURN_NULL_AND_BLANK);
	            LOGGER.info("*** Reading the Sheet with Name DataSheet ****");
	            Sheet datatypeSheet = workbook.getSheet("DataSheet");
	            if(datatypeSheet == null) {
	            	LOGGER.error("*** Sheet with Name as DataSheet is not present, please name the sheet as DataSheet ***");
	            }
	            Iterator<Row> iterator = datatypeSheet.iterator();
	            LOGGER.info("**** Skipping the Header row of Excel Sheet ****");
	            iterator.next();
	            String test_Id = null;
            	String method_Id = null;
	            while (iterator.hasNext()) {
	            	Row currentRow = iterator.next();
	            	
	            	
	                if(currentRow.getCell(ExcelHeader.Test_ID.getColumnNumber()) != null) {
	                	test_Id = currentRow.getCell(ExcelHeader.Test_ID.getColumnNumber()).getStringCellValue();
	                	excelData.put(test_Id, new LinkedHashMap<>());
	                }
	                if(currentRow.getCell(ExcelHeader.Method_ID.getColumnNumber()) != null) {
	                	method_Id = currentRow.getCell(ExcelHeader.Method_ID.getColumnNumber()).getStringCellValue();
	                	excelData.get(test_Id).put(method_Id, new ExcelPojo());
	                }
	                	
	                if(currentRow.getCell(ExcelHeader.Server_URL.getColumnNumber()) != null) {
	                		excelData.get(test_Id).get(method_Id).setServerURL(currentRow.getCell(ExcelHeader.Server_URL.getColumnNumber()).getStringCellValue());
	                	}
	                	
	                if(currentRow.getCell(ExcelHeader.Endpoint.getColumnNumber()) != null) {
	                		excelData.get(test_Id).get(method_Id).setEndpoint(currentRow.getCell(ExcelHeader.Endpoint.getColumnNumber()).getStringCellValue());
	                	}
	                	
	                if(currentRow.getCell(ExcelHeader.HeaderKey.getColumnNumber()) != null) {
	                		excelData.get(test_Id).get(method_Id).setRequestHeader(currentRow.getCell(ExcelHeader.HeaderKey.getColumnNumber()).getStringCellValue() , currentRow.getCell(ExcelHeader.HeaderValue.getColumnNumber()).getStringCellValue());
	                		
	                	}
	                	
	                if(currentRow.getCell(ExcelHeader.RequestKey.getColumnNumber()) != null) {
	                		excelData.get(test_Id).get(method_Id).setRequestMap(currentRow.getCell(ExcelHeader.RequestKey.getColumnNumber()).getStringCellValue(), currentRow.getCell(ExcelHeader.RequestValue.getColumnNumber()).getStringCellValue());
	                		
	                	}
	                if(currentRow.getCell(ExcelHeader.ResponseKey.getColumnNumber()) != null) {
	                		excelData.get(test_Id).get(method_Id).setExpectedResponseMap(currentRow.getCell(ExcelHeader.ResponseKey.getColumnNumber()).getStringCellValue() , currentRow.getCell(ExcelHeader.ResponseValue.getColumnNumber()).getStringCellValue());
	                		
	                	}
	                	
	                if(currentRow.getCell(ExcelHeader.ExpectedResponseStatus.getColumnNumber()) != null && !currentRow.getCell(ExcelHeader.ExpectedResponseStatus.getColumnNumber()).getStringCellValue().equals("")) {
	                		excelData.get(test_Id).get(method_Id).setExpectedResponseStatus(currentRow.getCell(ExcelHeader.ExpectedResponseStatus.getColumnNumber()).getStringCellValue());
	                		
	                	}
	                	
	                if(currentRow.getCell(ExcelHeader.HTTPMethod.getColumnNumber()) != null) {
	                		excelData.get(test_Id).get(method_Id).setHttpMethod(currentRow.getCell(ExcelHeader.HTTPMethod.getColumnNumber()).getStringCellValue());
	                		
	                	}
	                	
	                if(currentRow.getCell(ExcelHeader.QueryString.getColumnNumber()) != null) {
	                		excelData.get(test_Id).get(method_Id).setQueryString(currentRow.getCell(ExcelHeader.QueryString.getColumnNumber()).getStringCellValue());
	                	}
	                
	                

	            }
	        } catch (FileNotFoundException e) {
	            LOGGER.error("File is not present at the location "+pathToExcel);
	        } catch (IOException e) {
	            LOGGER.error("There is I/O Exception while reading the file");
	            e.printStackTrace();
	        }finally {
	        	workbook.close();
	        	excelFile.close();
			 }
		 
		
	}
	
	
	public LinkedHashMap<String, LinkedHashMap<String, ExcelPojo>> getExcelData(){
		return excelData;
	}
	
	public ExcelPojo getMethodPOJO(String testId, String methodID){
		return excelData.get(testId).get(methodID);
	}
	
	
}
