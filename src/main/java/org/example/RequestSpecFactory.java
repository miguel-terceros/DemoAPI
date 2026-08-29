package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class RequestSpecFactory {
    
    private static final Map<String, Supplier<RequestSpecification>> REQUEST_SPEC_MAP = new HashMap<>();
    static {
        REQUEST_SPEC_MAP.put("trello", RequestSpecFactory::getRequestSpecTrello);
        REQUEST_SPEC_MAP.put("salesforce", RequestSpecFactory::getRequestSpecSalesForce);
    }

    private static RequestSpecification getRequestSpecTrello() {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getValue("baseUri"))
                .addQueryParam("key", Environment.getInstance().getValue("credentials.owner.key"))
                .addQueryParam("token", Environment.getInstance().getValue("credentials.owner.token"))
                .build();
    }

    private static RequestSpecification getRequestSpecSalesForce() {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getValue("baseUri"))
                .addHeader("key", Environment.getInstance().getValue("credentials.owner.token"))
                .build();
    }

    public static RequestSpecification getRequestSpec(final String serviceName) {
        return REQUEST_SPEC_MAP.get(serviceName).get();
    }
}
