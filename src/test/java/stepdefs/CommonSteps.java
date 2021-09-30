package stepdefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import service.ShopService;
import utils.AssertValues;
import utils.BaseTestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonSteps extends BaseTestCase {

    private static Logger logger = LogManager.getLogger(CommonSteps.class);

    private World world;
    private String Request;
    private ShopService shopService = new ShopService();
    AssertValues asserValues = new AssertValues();

    public CommonSteps(World world) {
        this.world = world;
    }


    @When("^The specific shop is Creaed$")
    public void the_specific_shop_is_Created() {
        Request = shopService.createShopReqWoId("id");
        world.setResponse(shopService.createShopResp(Request));
        String Id = world.getResponse().getBody().asString();
        logger.info("The parsed id : " + Id);
    }

    }

