import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAPI {

    @Test
    public void testPOSTProject() {

        Response response = RestAssured.given()
                .baseUri("")
                .header("", "")
                .contentType(ContentType.JSON)
                .when()
                .body(" ")
                .post("/services");
        String propertyID = response.jsonPath().getString("id");
        String propertyName = response.jsonPath().getString("name");

        Assert.assertEquals(propertyName, "expected_name");
    }
}
