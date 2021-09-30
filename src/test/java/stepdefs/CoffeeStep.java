package stepdefs;

import com.fasterxml.jackson.databind.JsonNode;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AuthService;
import service.CoffeeService;
import utils.AssertValues;

public class CoffeeStep {

    // GLOBAL CLASS VARIABLES
    private static Logger logger = LogManager.getLogger(ShopStep.class);

    private World world;
    private String Request;
    private JsonNode jsonNode;
    private CoffeeService coffeeService = new CoffeeService();
    AuthService authService = new AuthService();
    AssertValues asserValues = new AssertValues();
    //private static String AuthToken;

    public CoffeeStep(World world) {
        this.world = world;
    }

    @Given("^New Coffee Flavour add request$")
    public void new_Coffee_Flavour_add_request()
    {
         jsonNode= coffeeService.upateCoffeeReq("inputs/coffee.json");


    }

    @When("^Request is sent to update existing Coffee with CoffeeId as  \"([^\"]*)\" $")
    public void request_is_sent_to_update_existing_Coffee_with_CoffeeId_as_CfId(String CoffeeId)
    {
        Request=authService.reqUpdate(jsonNode,"id",CoffeeId);
        world.setResponse(coffeeService.updateCoffeeResp(Request, authService.getAuthToken(world.getResponse())));

    }

    @Then("^Coffee is updated successfully$")
    public void coffee_is_updated_successfully()
    {
        asserValues.verifyResponseStatusValue(world.getResponse());
    }
}
