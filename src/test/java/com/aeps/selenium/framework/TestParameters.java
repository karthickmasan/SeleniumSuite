package com.aeps.selenium.framework;

public class TestParameters {
	private String testName = null;
	private String testDescription = null;

	public TestParameters(String name, String description) {
		this.testName = name;
		this.testDescription = description;
	}
	
	public TestParameters() {}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public String getTestName() {
		return testName;
	}

	public String getTestDescription() {
		return testDescription;
	}
}
