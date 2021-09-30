package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class JsonUtils {
    private static Logger logger = LogManager.getLogger(JsonUtils.class);

    public JsonNode readJson(String fileName) {
        JsonNode jsonNode = null;
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonNode = mapper.readValue(in,
                    JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("");
        }


        return jsonNode;
    }

    public String updateReq(JsonNode jsonNode, String path, String value) {
        String req = null;
        ((ObjectNode) jsonNode).put(path, value);

        try {
            ObjectMapper mapper = new ObjectMapper();
            req=mapper.writeValueAsString(jsonNode);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return req;
    }

    public String updateReqWithOptional(JsonNode jsonNode, String path) {
        String req = null;
        ((ObjectNode) jsonNode).remove(path);

        try {
            ObjectMapper mapper = new ObjectMapper();
            req=mapper.writeValueAsString(jsonNode);
            System.out.println();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return req;
    }

    public JSONObject responseUpdate(Response response, String request) {

        System.out.println("Before modifying response :" + response.getBody().asString());
        JSONObject object = new JSONObject(response.getBody().asString());

        object.put("id", 4);
        logger.info(request);

        return object;
    }


    public Map<String, Object> jSonCompare(String request)
    {

        ObjectMapper om = new ObjectMapper();
        Map<String, Object> mapJson = null;
        try {
            mapJson = (Map<String, Object>)(om.readValue(request, Map.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapJson;
    }
}
