import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.*;
import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;


@CucumberOptions(
		features = {"src/test/resources"},
		tags= {"@auth, @smokeTest, @regression"},
		glue={"stepdefs"},
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		monochrome = true )

class RunCuesTest extends AbstractTestNGCucumberTests {

	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", System.getProperty("os.name"));
		Reporter.setTestRunnerOutput("Sample test runner output message");
	}

	@DataProvider(parallel = false)
	public Object[][] features() {
		return super.features();
	}


}