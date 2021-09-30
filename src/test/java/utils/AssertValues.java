package utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import stepdefs.CommonSteps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class AssertValues {

    private static Logger logger = LogManager.getLogger(AssertValues.class);

    public static final int SUCCESS_STATUS_CODE = 200;
    public static final int DELETE_SUCCESS_STATUS_CODE = 204;
    JsonUtils jsonUtils =new JsonUtils();

    public void verifyResponseStatusValue(Response response) {
        assertThat(response.getStatusCode(), is(SUCCESS_STATUS_CODE));
        logger.info("Response Code :" + response.getStatusCode());
    }

    public void verifyDeleteResponseStatusValue(Response response) {
        assertThat(response.getStatusCode(), is(DELETE_SUCCESS_STATUS_CODE));

        logger.info("Response Code :" + response.getStatusCode());

    }
    public void verifyResponseBodyValue(Response response, String request) {

       String UpdateResponseForId= jsonUtils.responseUpdate(response, request).toString();
        try {
            Map<String, Object> Actual=jsonUtils.jSonCompare(UpdateResponseForId);
            Map<String, Object> Expected=jsonUtils.jSonCompare(request);
          Assert.assertTrue(Actual.equals(Expected));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
