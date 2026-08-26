package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class RequestSpec {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getValue("baseUri"))
                .addQueryParam("key", Environment.getInstance().getValue("credentials.owner.key"))
                .addQueryParam("token", Environment.getInstance().getValue("credentials.owner.token"))
                .build();
    }
}
