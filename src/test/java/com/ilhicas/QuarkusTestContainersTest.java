package com.ilhicas;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@Testcontainers
@QuarkusTest
class QuarkusTestContainersTest {

    @Container
    static PostgreSQLContainer db = new PostgreSQLContainer<>("postgres:11-alpine")
            .withDatabaseName("quarkus-testcontainer")
            .withUsername("quarkus-user")
            .withPassword("quarkus-password")
            .withExposedPorts(5432);
    
    @Test
    public void testGETHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testPOSTHelloEndpoint() {
        given()
          .when().post("/hello")
          .then()
             .statusCode(200)
             .body(is("createdHello"));
    }

}