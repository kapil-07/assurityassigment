package com.assurityassignment.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
 features = "Feature"
 ,glue={"com.assurityassignment.steps"}
 ,monochrome = false
 ,format = {"html:target"}
 ,plugin = {"com.assurityassignment.plugin.CucumberReporterFormatter"}
 ,tags = {"@Test_Id_1234"}
 )
 
public class RunnerClass {

}
