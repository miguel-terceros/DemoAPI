import io.restassured.response.Response;
import org.example.RequestManager;
import org.example.RequestSpec;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardTest {

    @Test
    public void testBoardAPI() {
        // Given
        String expectedBoardName = "Rest Assured v2";
        Response response = RequestManager.post(RequestSpec.getRequestSpec(), "/boards",
                "{\"name\": \"" + expectedBoardName + "\"}");

        String actualBoardName = response.jsonPath().getString("name");
        Assert.assertEquals(actualBoardName, expectedBoardName);

        // When
        String boardId = response.jsonPath().getString("id");
        String expectedNewBoardName = "Rest Assured PUT v2";
        response = RequestManager.put(RequestSpec.getRequestSpec(), String.format("/boards/%s", boardId),
                "{\"name\": \"" + expectedNewBoardName + "\"}");

        actualBoardName = response.jsonPath().getString("name");
        Assert.assertEquals(actualBoardName, expectedNewBoardName);

        // Clean
        response = RequestManager.delete(RequestSpec.getRequestSpec(), String.format("/boards/%s", boardId));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        response = RequestManager.get(RequestSpec.getRequestSpec(), String.format("/boards/%s", boardId));
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }
}
