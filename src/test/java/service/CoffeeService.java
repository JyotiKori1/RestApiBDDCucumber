package service;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import model.Shop;
import utils.BaseTestCase;
import utils.JsonUtils;

public class CoffeeService extends BaseService {
    protected String AuthServicePath;
    JsonUtils jsonUtils = new JsonUtils();

    /*public CoffeeService() {

        defaultShop = new Shop();

    }
    public Shop getDefaultShop() {
        return this.defaultShop;
    }
*/

    public String getCoffeeServicePath() {
        return this.AuthServicePath= BaseTestCase.getArgument("COFFEE_SERVICE_PATH");
    }



    public JsonNode upateCoffeeReq(String fileName) {
        JsonNode obj = jsonUtils.readJson(fileName);
        return obj;

    }

    public String reqUpdate(JsonNode obj, String path, String Value )
    {
        String req = jsonUtils.updateReq(obj, path, Value);
        return req;
    }

    public Response updateCoffeeResp(String Request, String AuthToken) {

        return PUT_REQUEST_BODY_WITH_AUTH(getCoffeeServicePath(), Request, AuthToken);

    }


}

