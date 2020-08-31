package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;
//@CucumberOptions(features="features",glue={"stepDefinitions"},plugin = { "pretty" }, monochrome = true)
//, "html:target/cucumber-html-report"

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",glue={"stepDefinitions"},
        plugin = { "pretty", "json:target/cucumber.json", "junit:target/cucumber.xml" })
public class RunTest
{


}
