package stepdefs;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Shop;

public class World {
    private Response response;
    private RequestSpecification request;
    private Shop shop;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
