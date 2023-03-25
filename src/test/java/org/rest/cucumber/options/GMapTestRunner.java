package org.rest.cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/org/rest/cucumber/features",
        glue = {"org.rest.cucumber.stepdefinitions"},
        //tags = "@DeletePlace",
        plugin ={"pretty","json:target/jsonReports/Gmap-cucumber-reports.json"}
)
public class GMapTestRunner {
}
