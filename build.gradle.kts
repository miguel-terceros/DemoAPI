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
    testImplementation("io.rest-assured:rest-assured:6.0.1")
    // Source: https://mvnrepository.com/artifact/io.cucumber/cucumber-java
    implementation("io.cucumber:cucumber-java:7.34.6")
    // Source: https://mvnrepository.com/artifact/io.cucumber/cucumber-testng
    implementation("io.cucumber:cucumber-testng:7.34.6")
}

tasks.test {
    useTestNG()
    testLogging {
        events("PASSED", "FAILED", "SKIPPED", "STARTED", "FINISHED")
    }
}