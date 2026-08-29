package Boards;

import io.restassured.response.Response;
import org.example.RequestManager;
import org.example.RequestSpecFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BoardTestTags {

    public String boardId;

    @BeforeTest
    public void setUp() {
        // Given
        String expectedBoardName = "Rest Assured v3";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("trello"), "/boards",
                "{\"name\": \"" + expectedBoardName + "\"}");
        boardId = response.jsonPath().getString("id");
    }

    @Test
    public void testBoardAPIWithTags() {
        // When
        String expectedNewBoardName = "Rest Assured PUT v3";
        Response response = RequestManager.put(RequestSpecFactory.getRequestSpec("trello"),
                String.format("/boards/%s", boardId), "{\"name\": \"" + expectedNewBoardName + "\"}");

        // Then
        String actualBoardName = response.jsonPath().getString("name");
        Assert.assertEquals(actualBoardName, expectedNewBoardName);
    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("trello"), String.format("/boards/%s", boardId));
    }
}
