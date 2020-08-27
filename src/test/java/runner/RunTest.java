package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;
//@CucumberOptions(features="features",glue={"stepDefinitions"},plugin = { "pretty" }, monochrome = true)

@RunWith(Cucumber.class)
@CucumberOptions(features="features",glue={"stepDefinitions"},plugin = { "pretty", "html:target/html-cucumber.html" }, monochrome = true)
public class RunTest
{


}
