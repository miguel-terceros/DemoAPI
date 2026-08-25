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
                .formParam("key", "f443f72c3f84d0828d42f8a689ff27cb")
                .formParam("token", "ATTAb0226443103c5836901e815002c9d6e3057a28c8ea25b87b6bc87ef82468060dC40CBE14")
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
