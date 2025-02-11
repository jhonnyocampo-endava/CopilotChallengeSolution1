package org.example.reporting;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import java.io.File;
import java.util.Collections;

public class CucumberReportGenerator {
    public static void generateReport() {
        File reportOutputDirectory = new File("target");
        String buildNumber = "1";
        String projectName = "AutomationProject2";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Chrome");

        ReportBuilder reportBuilder = new ReportBuilder(Collections.singletonList("target/cucumber-report.json"), configuration);
        reportBuilder.generateReports();
    }
}