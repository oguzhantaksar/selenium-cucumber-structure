package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "tako.stepDefinitions",
monochrome = true ,tags = "@OrderTests", plugin = {"html:target/cucumber.html"})
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
}