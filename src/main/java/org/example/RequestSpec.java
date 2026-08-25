package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class RequestSpec {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com/1")
                .addFormParam("key", Environment.getInstance().getValue("credentials.owner.key"))
                .addFormParam("token", Environment.getInstance().getValue("credentials.owner.token"))
                .build();
    }
}
