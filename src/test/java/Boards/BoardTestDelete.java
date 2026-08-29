package Boards;

import io.restassured.response.Response;
import org.example.RequestManager;
import org.example.RequestSpecFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BoardTestDelete {
    public String boardId;
    public int statusCode;

    @BeforeTest
    public void setUp() {
        // Given
        String expectedBoardName = "Board DELETE";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("trello"),
                "/boards", "{\"name\": \"" + expectedBoardName + "\"}");
        boardId = response.jsonPath().getString("id");
    }

    @Test
    public void DeleteBoardTest() {
        // When
        Response response = RequestManager.delete(RequestSpecFactory.getRequestSpec("trello"),
                String.format("/boards/%s", boardId));
        // Then
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        // Using rest API
        response = RequestManager.get(RequestSpecFactory.getRequestSpec("trello"),
                String.format("/boards/%s", boardId));
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }

    @AfterTest
    public void cleanData() {
        if( statusCode != 404) {
            System.out.println(" Clean using DB =P ");
        }
    }
}
