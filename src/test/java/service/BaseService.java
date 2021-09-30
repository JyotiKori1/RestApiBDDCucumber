package service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import utils.BaseTestCase;

public class BaseService extends BaseTestCase {

    protected String baseURI;
    protected String ShopServicePath;


    public String getBaseUURI() {
        return this.baseURI=BaseTestCase.getArgument("baseURI");
}

    public String getBaseUURIAuth() {
        return this.baseURI=BaseTestCase.getArgument("baseURIAuth");
    }



    public RequestSpecification getRequestWithJSONHeaders() {
        RequestSpecification r = RestAssured.given();
        r.header("Content-Type", "application/json");
        return r;
    }

    public Response sendRequest(RequestSpecification request, String url) {
        Response response = null;


        response = RestAssured.when().get(url);

        return response;
    }

    public Response getAllExistingShops(String path) {
        Response response = null;

      //  RequestSpecification r = RestAssured.given();
    //    r.header("Content-Type", "application/json");
        String uri=getBaseUURI()+path;
        response = RestAssured.
                given()
             //   .contentType("application/json")
                .accept("application/json")
                .log()
                .all()
                .when()
                .get(uri)
                .then()
                .extract().response();

        System.out.println("REST-ASSURED: The response from the request is: " + response.getBody().toString());

        return response;
    }


    /**
     * REST ASSURED POST request method with body
     *
     * @param path destination of the request
     * @param body the body we wish to post with the request
     * @return Response object that has the REST response
     */
    public Response POST_REQUEST_BODY(String path, Object body, int EnvOption) {
        String uri;
        if(EnvOption==1)
        uri=getBaseUURI()+path;
        else
             uri=getBaseUURIAuth()+path;
        Response postResponse = RestAssured.given()
                .body(body)
                .contentType("application/json")
                .accept("application/json")
                .log()
                .all()
                .when()
                .post(uri)
                .then()
                .extract()
                .response();
        // log then response
      // logger.debug("REST-ASSURED: The response from the request is: " + postResponse.asString());
        return postResponse;
    } // end POST_REQUEST

    /**
     * REST ASSURED PUT request method with body
     *
     * @param path destination of the request
     * @param body the body we wish to post with the request
     * @return Response object that has the REST response
     */
    public Response PUT_REQUEST_BODY_WITH_AUTH(String path, Object body, String Token) {
        String uri=getBaseUURIAuth()+path;
        Response putResponse = RestAssured.given()
                .body(body)
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer "+Token)
                .log()
                .all()
                .when()
                .put(uri)
                .then()
                .extract()
                .response();
        // log then response
        // logger.debug("REST-ASSURED: The response from the request is: " + postResponse.asString());
        return putResponse;
    } // end PUT_REQUEST


    /**
     * REST ASSURED DELETE request method without body
     *
     * @param path destination of the request

     * @return Response object that has the REST response
     */
    public Response DELETE_REQUEST_BODY(String path) {
        String uri=getBaseUURI()+path;
        Response postResponse = RestAssured.given()
                .log()
                .all()
                .when()
                .delete(uri)
                .then()
                .extract()
                .response();
        // log then response
        // logger.debug("REST-ASSURED: The response from the request is: " + postResponse.asString());
        return postResponse;
    } // end DELETE_REQUEST


    protected JSONObject createJSONPayload(Object pojo) {


        return new JSONObject(pojo);
    }


}
