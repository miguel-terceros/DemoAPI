import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardTestBasic {

    @Test
    public void testPOSTBoard() {
        // Given
        String expectedBoardName = "Test POST board";
        Response response = RestAssured.given()
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "{REPLACE_KEY}")
                .queryParam("token", "{REPLACE_TOKEN}")
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\": \"" + expectedBoardName + "\"}")
                .post("/boards");

        String actualBoardName = response.jsonPath().getString("name");
        Assert.assertEquals(actualBoardName, expectedBoardName);

        // When
        String boardId = response.jsonPath().getString("id");
        String expectedNewBoardName = "RestAssure PUT 2026";
        response = RestAssured.given()
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "{REPLACE_KEY}")
                .queryParam("token", "{REPLACE_TOKEN}")
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\": \"" + expectedNewBoardName + "\"}")
                .put(String.format("/boards/%s", boardId));

        // Then
        actualBoardName = response.jsonPath().getString("name");
        Assert.assertEquals(actualBoardName, expectedNewBoardName);

        // Clean
        response = RestAssured.given()
                .baseUri("https://api.trello.com/1")
                .queryParam("key", "{REPLACE_KEY}")
                .queryParam("token", "{REPLACE_TOKEN}")
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format("/boards/%s", boardId));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
