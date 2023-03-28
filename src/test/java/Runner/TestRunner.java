package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
		features="src/test/java/features",monochrome=true,
		glue= "StepDefinations",
		snippets= CucumberOptions.SnippetType.CAMELCASE,
		tags="@End2End",
		plugin={"pretty","html:target/cucumber.html","json:target/jsonReports/cucumber-report.json"})
//
public class TestRunner extends AbstractTestNGCucumberTests {
		@BeforeClass
		public void before1()
		{
			System.out.println("Print TestNg");
		}
		///there is change here
}
	