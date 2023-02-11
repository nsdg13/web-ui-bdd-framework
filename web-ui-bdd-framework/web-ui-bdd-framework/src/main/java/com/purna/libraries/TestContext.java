package com.purna.libraries;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;

import java.util.HashMap;

public class TestContext {
	private WebDriver driver = null;
	private PageObjectManager pageObjectManager = null;
	private Scenario scenario = null;
	private String testCaseID = null;
	private HashMap<String, String> mapTestData = null;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}

	public void setPageObjectManager(PageObjectManager pageObjectManager) {
		this.pageObjectManager = pageObjectManager;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public String getTestCaseID() {
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}

	public HashMap<String, String> getMapTestData() {
		return mapTestData;
	}

	public void setMapTestData(HashMap<String, String> mapTestData) {
		this.mapTestData = mapTestData;
	}
}
