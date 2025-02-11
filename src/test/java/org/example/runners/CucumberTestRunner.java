package org.example.runners;

import org.example.reporting.CucumberReportGenerator;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.example.steps",
        plugin = {"json:target/cucumber-report.json"}
)
public class CucumberTestRunner {
    @AfterClass
    public static void tearDown() {
        CucumberReportGenerator.generateReport();
    }
}