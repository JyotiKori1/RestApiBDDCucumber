package stepdefs;

import com.fasterxml.jackson.databind.JsonNode;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Shop;
import service.ShopService;
import utils.AssertValues;
import utils.BaseTestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopStep extends BaseTestCase {

    // GLOBAL CLASS VARIABLES
    private static Logger logger = LogManager.getLogger(ShopStep.class);

    private World world;
    private String Request;
    private ShopService shopService = new ShopService();
    AssertValues asserValues = new AssertValues();

    public ShopStep(World world) {
        this.world = world;
    }

    @Given("^Coffee shop is up and running$")
    public void coffee_shop_is_up_and_running() {
     System.out.println("Coffee shop is up and running");
    }

    @Then("^All the existing shops are found$")
    public void all_the_existing_shops_are_found()  {
        world.setResponse(shopService.getAllExistingShops());
       asserValues.verifyResponseStatusValue(world.getResponse());
      //  throw new PendingException();
    }



    @Given("^Valid coffee shop request$")
            public void valid_coffee_shop_request()
    {
        Request= shopService.createShopReq("name", "RoyalJyotiKori");

    }

    @When("^I send request to create coffee shop$")
    public void i_send_request_to_create_coffee_shop()
    {
        world.setResponse(shopService.createShopResp(Request));
    }

    @Then("^shop is created successfully with valid status code$")
    public void shop_is_created_successfully_with_valid_status_code()
    {
        asserValues.verifyResponseStatusValue(world.getResponse());
    }

    @And("^shop is created with values passed in the request$")
    public void shop_is_created_with_values_passed_in_the_request()
    {
        asserValues.verifyResponseBodyValue(world.getResponse(), Request);
    }

    @Given("^Valid coffee shop request wihout Id attribute$")
    public void valid_coffee_shop_request_without_id_attribute()
    {
        Request= shopService.createShopReqWoId("id");

    }

    @Then("^The shop can be located by its id$")
    public void the_shop_can_be_located_by_its_id()  {
        int Id=world.getResponse().jsonPath().getInt("id");
        logger.info("The parsed id : " +Id);
         world.setResponse(shopService.getShopByShopId(Id));
          asserValues.verifyResponseStatusValue(world.getResponse());
        //  throw new PendingException();
    }

    @Then("^The shop can be deleted by its id$")
    public void the_shop_can_be_deleted_by_its_id()
    {
        int Id=world.getResponse().jsonPath().getInt("id");
        logger.info("The parsed id : " +Id);
        world.setResponse(shopService.deleteShopByShopId(Id));
        asserValues.verifyDeleteResponseStatusValue(world.getResponse());
    }

}
