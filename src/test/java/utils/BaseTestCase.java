package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import io.restassured.RestAssured;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Class for all test framework utilities. Also contains all before and after setup steps.
 * This class will act as a test runner for cucumber from within testNG.
 * Dynamic data allocation from each feature file is done here.
 *
 * @Author Jyoti Kori
 * @Date 09/30/2021
 */

public class BaseTestCase {


    private static Logger logger = LogManager.getLogger(BaseTestCase.class);

    public static String getArgument(String argument) {

        String resourceName = "test.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String baseurl = props.getProperty(argument);

        System.out.println(baseurl);
        return props.getProperty(argument);

    }


    // ================================================================================================================
    // TESTNG ANNOTATIONS & CUCUMBER SETUP
    // ================================================================================================================

    /**
     * Before entire test suite we need to setup everything we will need.
     */
    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        logger.info("Cucumber Test Framework for QE-PurchaseData initialized!");
        logger.info("Logging initialized: All logs are located at " + System.getProperty("user.dir") + "/src/logs/shops-qe-tests.log");
        //Initialize();
        logger.info("Done with BeforeSuite setup! TESTS BEGINNING!\n\n");


    } // End TestSetup

    /**
     * Before class setup to initialize the cucumber runner.
     */
    @BeforeClass(alwaysRun = true)
    public void setupClass() {

        //testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    /**
     * After class to tear down the runner for cucumber.
     */
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        //  testNGCucumberRunner.finish();
    }

    /**
     * After the entire test suite clean up rest assured
     */
    @AfterSuite(alwaysRun = true)
    public void cleanUp() {
        RestAssured.reset();
        logger.info("\n\n");
        logger.info("Rest Assured framework has been reset because all tests have been executed.");
        logger.info("TESTING COMPLETE: SHUTTING DOWN FRAMEWORK!!");
    } // end cleanUp
}

