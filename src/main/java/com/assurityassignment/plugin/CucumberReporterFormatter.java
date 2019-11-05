package com.assurityassignment.plugin;

import java.util.List;

import org.apache.log4j.Logger;


import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;

public class CucumberReporterFormatter implements Reporter, Formatter{

	private static String testId = null;
	private static final Logger LOGGER = Logger.getLogger(CucumberReporterFormatter.class);
	@Override
	public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
		
		
	}

	@Override
	public void uri(String uri) {
		
		
	}

	@Override
	public void feature(Feature feature) {
		
		
	}

	@Override
	public void scenarioOutline(ScenarioOutline scenarioOutline) {
	
		
	}

	@Override
	public void examples(Examples examples) {
		
		
	}

	@Override
	public void startOfScenarioLifeCycle(Scenario scenario) {
		
		
	}

	@Override
	public void background(Background background) {
		
		
	}

	@Override
	public void scenario(Scenario scenario) {
		
		List<Tag> scenarioTags = scenario.getTags();
		for(Tag tag : scenarioTags) {
			if(tag.getName().startsWith("@Test_Id_")) {
			
				this.testId = tag.getName().substring(("@Test_Id_").length());
				LOGGER.info("Scenario Test ID is "+ this.testId);
				LOGGER.info("Running the scenario for Test ID "+ this.testId);
				LOGGER.info("Steps involved in a scenario are :");
			}
		}
		if(this.testId == null) {
			LOGGER.error("Scenario doesnt contain Test_Id_ tag, please mention Test_Id_ tag");
		}
		
	}

	@Override
	public void step(Step step) {
		LOGGER.info("Step ===> "+step.getName());
		
		
	}

	@Override
	public void endOfScenarioLifeCycle(Scenario scenario) {
		
		
	}

	@Override
	public void done() {
		
		
	}

	@Override
	public void close() {
		
		
	}

	@Override
	public void eof() {
		
		
	}

	@Override
	public void before(Match match, Result result) {
	
		
	}

	@Override
	public void result(Result result) {
		
		
	}

	@Override
	public void after(Match match, Result result) {
	
		
	}

	@Override
	public void match(Match match) {
		
		
	}

	@Override
	public void embedding(String mimeType, byte[] data) {
	
		
	}

	@Override
	public void write(String text) {
		
		
	}
	
	public static String getTestID() {
		return testId;
	}

}
