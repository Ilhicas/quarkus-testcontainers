package com.ilhicas;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;

@Testcontainers
@QuarkusTest
class QuarkusTestContainersTest {

    @Container
    static PostgreSQLContainer db = new PostgreSQLContainer<>("postgres:11-alpine")
            .withDatabaseName("testcontainers")
            .withUsername("andre")
            .withPassword("ilhicas")
            .withExposedPorts(5432)
            // Below should not be used - Function is deprecated and for simplicity of test , You should override your properties at runtime
            .withCreateContainerCmdModifier(cmd -> {
                cmd
                        .withHostName("localhost")
                        .withPortBindings(new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432)));
            });

    
    @Test
    public void testGETHelloEndpoint() {
        given()
          .when().get("/person")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testPOSTHelloEndpoint() {
        given()
          .when().post("/person")
          .then()
             .statusCode(200);
    }

}