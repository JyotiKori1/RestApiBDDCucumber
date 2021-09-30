package stepdefs;

import com.fasterxml.jackson.databind.JsonNode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AuthService;
import service.BaseService;
import service.ShopService;
import utils.AssertValues;

public class AuthStep  {

    // GLOBAL CLASS VARIABLES
    private static Logger logger = LogManager.getLogger(ShopStep.class);

    private World world;
    private String Request;
    private AuthService authService = new AuthService();
    AssertValues asserValues = new AssertValues();
    private static String AuthToken;

    public AuthStep(World world) {
        this.world = world;
    }

    @Given("^Service Auth is up and running$")
    public void service_auth_is_up_and_running() {
        System.out.println("Service Auth is up and running");
    }

    @When("^The Auth Generate request is sent$")
    public void the_auth_generate_request_is_Sent()
    {

        JsonNode jsonNode= authService.createAuthReq("inputs/Auth.json");
        String Request=authService.reqUpdate(jsonNode,"Username","abcd");
        Request=authService.reqUpdate(jsonNode,"Password","xyzw");
        world.setResponse(authService.authTokenResp(Request));
    }

    @Then("^Token is generated$")
    public void token_is_generated()
    {
        asserValues.verifyResponseStatusValue(world.getResponse());
    }

    @And("^Get the Token Value$")
    public void get_the_Token_Value()
    {
        AuthToken=authService.getAuthToken(world.getResponse());
      logger.info(AuthToken);
    }

}
