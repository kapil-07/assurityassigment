package com.assurityassignment.steps;

import com.assurityassignment.plugin.CucumberReporterFormatter;
import com.assurityassignment.utils.ExcelUtils;
import com.assurityassignment.utils.RestAssuredUtils;

import cucumber.api.java.en.And;
import static junit.framework.Assert.*;

public class RestSteps {
	

    @And("^user loads the excel sheet \"([^\"]*)\"$")
    public void user_loads_the_excel_sheet_something(String pathToExcel) throws Throwable {
       ExcelUtils.INSTANCE.readExcel(pathToExcel);
       
    }
	
    @SuppressWarnings("deprecation")
	@And("^user validates status code for method \"([^\"]*)\"$")
    public void user_validates_status_code_for_method_something(String methodId) throws Throwable {
    	assertTrue(RestAssuredUtils.INSTANCE.requestAndValidateStatus(CucumberReporterFormatter.getTestID(),methodId ));
    }
    
    @SuppressWarnings("deprecation")
	@And("^user validates response for method \"([^\"]*)\"$")
    public void user_validates_response_for_method_something(String methodId) throws Throwable {
    	assertTrue(RestAssuredUtils.INSTANCE.validateResponse());
    }
}
