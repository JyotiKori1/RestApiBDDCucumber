package service;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import model.Shop;
import utils.BaseTestCase;
import utils.JsonUtils;

public class ShopService extends BaseService {
    private Shop defaultShop;
    JsonUtils jsonUtils = new JsonUtils();

    public ShopService() {

        defaultShop = new Shop();

    }


        public String getShopServicePath() {
            return this.ShopServicePath= BaseTestCase.getArgument("SHOP_SERVICE_PATH");
        }


    public Shop getDefaultShop() {
        return this.defaultShop;
    }

    public Response getShopByShopId(int shopId) {
        String USER_SERVICE_PATH = this.getShopServicePath() + "/" + shopId;
        return getAllExistingShops(USER_SERVICE_PATH);
    }

    public Response deleteShopByShopId(int shopId) {
        String USER_SERVICE_PATH = this.getShopServicePath() + "/" + shopId;
        return DELETE_REQUEST_BODY(USER_SERVICE_PATH);
    }

    public Response getShopByShopName(RequestSpecification request, String name) {
        return getShopByShopName(request, getDefaultShop().getName());
    }

    public Response getAllExistingShops() {

        return getAllExistingShops(getShopServicePath());
    }

    public String createShopReq(String path, String value) {
        JsonNode obj = jsonUtils.readJson("inputs/shop.json");
        String req = jsonUtils.updateReq(obj, path, value);
        return req;

    }

    public String createShopReqWoId(String path) {
        JsonNode obj = jsonUtils.readJson("inputs/shop.json");
        String req = jsonUtils.updateReqWithOptional(obj, path);
        return req;

    }

    public Response createShopResp(String req) {

        return POST_REQUEST_BODY(getShopServicePath(), req, 1);

    }

}
