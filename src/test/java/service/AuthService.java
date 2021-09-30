package service;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import model.Shop;
import utils.BaseTestCase;
import utils.JsonUtils;

public class AuthService extends BaseService {


        private Shop defaultShop;
    protected String AuthServicePath;
        JsonUtils jsonUtils = new JsonUtils();

        public AuthService() {

            defaultShop = new Shop();

        }
    public Shop getDefaultShop() {
        return this.defaultShop;
    }


        public String getAuthServicePath() {
            return this.AuthServicePath= BaseTestCase.getArgument("AUTH_SERVICE_PATH");
        }



    public JsonNode createAuthReq(String fileName) {
        JsonNode obj = jsonUtils.readJson(fileName);
        return obj;

    }

    public String reqUpdate(JsonNode obj, String path, String Value )
    {
        String req = jsonUtils.updateReq(obj, path, Value);
        return req;
    }

    public Response authTokenResp(String req) {

        return POST_REQUEST_BODY(getAuthServicePath(), req, 2);

    }

    public String getAuthToken(Response response)
    {
        return response.jsonPath().getString("Token");
    }
}
