plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Source: https://mvnrepository.com/artifact/io.rest-assured/rest-assured
    // Rest-assured is required by main code (RequestSpec), so move to implementation configuration
    implementation("io.rest-assured:rest-assured:6.0.1")
    // Source: https://mvnrepository.com/artifact/io.cucumber/cucumber-java
    implementation("io.cucumber:cucumber-java:7.34.6")
    // Source: https://mvnrepository.com/artifact/io.cucumber/cucumber-testng
    implementation("io.cucumber:cucumber-testng:7.34.6")

    // Source: https://mvnrepository.com/artifact/com.jayway.jsonpath/json-path
    implementation("com.jayway.jsonpath:json-path:3.0.0")
    // Source: https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
}

tasks.test {
    useTestNG()
    testLogging {
        events("PASSED", "FAILED", "SKIPPED", "STARTED", "STANDARD_OUT", "STANDARD_ERROR")
    }
}