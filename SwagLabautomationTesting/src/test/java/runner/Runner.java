package runner;

import org.testng.annotations.Listeners;

import MyListners.Listners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//import mylistners.Listners;

@CucumberOptions(
		//plugin = {"html:target/Report.html"},
features = "src/test/resources/Feature/Login.feature",
glue = {"steps"})

@Listeners(Listners.class)

public class Runner extends AbstractTestNGCucumberTests {

}
