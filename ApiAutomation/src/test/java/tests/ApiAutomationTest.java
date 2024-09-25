package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class ApiAutomationTest {

	@Test
    public void testGetApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = RestAssured.get("/posts/1");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"),
            "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }
	
    @Test
    public void testPostApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("title", "foo");
        jsonBody.put("body", "bar");
        jsonBody.put("userId", 1);

        Response response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(jsonBody)
            .post("/posts");

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }
}
