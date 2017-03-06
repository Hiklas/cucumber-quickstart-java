package com.hiklas.cucumber.quickstart;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Runs Cucumber tests by picking up any features files in the 
 * classpath.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"}, 
		// TODO: This is used in the example code but it causes problems with 
		// missing Spring Transaction classes if I add it in.  Not 100% sure 
		// what it is for so leave out for now.
		// glue = {"com.hiklas.cucumber.quickstart.spec", "cucumber.api.spring"},
		features = "src/test/resources/features", 
		format = { "json:target/cucumber-quickstart-java.json" },
		tags = { "~@Ignore" } )
public class RunFunctionalTest {

}
