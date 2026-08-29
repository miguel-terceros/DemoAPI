package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Utility class for executing HTTP requests using RestAssured.
 *
 * <p>This class centralizes the common CRUD operations used by the project,
 * allowing the caller to pass a preconfigured {@link RequestSpecification} and
 * endpoint, while keeping the request body and response handling consistent.</p>
 */
public class RequestManager {

    /**
     * Sends an HTTP POST request with a JSON body.
     *
     * @param requestSpec the request specification to be used for the call
     * @param endpoint the API endpoint to invoke, for example "/boards"
     * @param body the JSON payload to send in the request body
     * @return the {@link Response} returned by the server
     */
    public static Response post(final RequestSpecification requestSpec, final String endpoint, final String body) {
        final Response response = RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(endpoint);

        System.out.println("POST");
        System.out.println(response.prettyPrint());
        return response;
    }

    /**
     * Sends an HTTP PUT request with a JSON body.
     *
     * @param requestSpec the request specification to be used for the call
     * @param endpoint the target API endpoint to update
     * @param body the JSON payload with the updated values
     * @return the {@link Response} returned by the server
     */
    public static Response put(final RequestSpecification requestSpec, final String endpoint, final String body) {
        final Response response = RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .put(endpoint);

        System.out.println("PUT");
        System.out.println(response.prettyPrint());
        return response;
    }

    /**
     * Sends an HTTP GET request.
     *
     * @param requestSpec the request specification to be used for the call
     * @param endpoint the API endpoint to retrieve
     * @return the {@link Response} returned by the server
     */
    public static Response get(final RequestSpecification requestSpec, final String endpoint) {
        final Response response = RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);

        System.out.println("GET");
        System.out.println(response.prettyPrint());
        return response;
    }

    /**
     * Sends an HTTP DELETE request.
     *
     * @param requestSpec the request specification to be used for the call
     * @param endpoint the API endpoint to delete
     * @return the {@link Response} returned by the server
     */
    public static Response delete(final RequestSpecification requestSpec, final String endpoint) {
        final Response response = RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint);

        System.out.println("DELETE");
        System.out.println(response.prettyPrint());
        return response;
    }
}
