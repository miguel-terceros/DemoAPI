import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.example.Environment;
import org.example.RequestSpec;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAPI {

    @Test
    public void testGetBoardsBasic() {

        // LVL ZERO -> use RestAssured as basic config
        String expectedBoardId = "5ddaae0248ffb0348c37bf62";
        Response response = RestAssured.given()
                .baseUri("https://api.trello.com/1")
                .formParam("key", "{REPLACE_KEY}")
                .formParam("token", "{REPLACE_TOKEN}")
                .contentType(ContentType.JSON)
                .when()
                .get("/members/me/boards");

        String actualBoardId = response.jsonPath().getString("[0].id");
        Assert.assertEquals(actualBoardId, expectedBoardId);
    }

    @Test
    public void testGetBoardsEnvFile() {

        // LVL ONE -> Implement config file
        String expectedBoardId = "5ddaae0248ffb0348c37bf62";
        Response response = RestAssured.given()
                .baseUri("https://api.trello.com/1")
                .formParam("key", Environment.getInstance().getValue("credentials.owner.key"))
                .formParam("token", Environment.getInstance().getValue("credentials.owner.token"))
                .contentType(ContentType.JSON)
                .when()
                .get("/members/me/boards");

        String actualBoardId = response.jsonPath().getString("[0].id");
        Assert.assertEquals(actualBoardId, expectedBoardId);

    }

    @Test
    public void testGetBoardsRequestSpec() {

        // LV TWO -> Implement RequestSpec
        String expectedBoardId = "5ddaae0248ffb0348c37bf62";
        Response response = RestAssured.given(RequestSpec.getRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .get("/members/me/boards");

        String actualBoardId = response.jsonPath().getString("[0].id");
        Assert.assertEquals(actualBoardId, expectedBoardId);


    }
}
