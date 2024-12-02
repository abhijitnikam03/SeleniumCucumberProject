package com.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resource/features/practice.feature"}, 
	//	tags = "@Positive_UnFreezeNSDLMaker",
		glue = { "apphook","com/stepdef"}, 
		dryRun = false, 
		plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
		monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
	
	

}