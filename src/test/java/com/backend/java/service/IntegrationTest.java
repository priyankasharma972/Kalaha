package com.backend.java.service;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.backend.spring.boot.controller.GameImplementationApplication;
import io.restassured.http.ContentType;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * GameController Integration Testing
 *
 */
@SpringBootTest(classes = GameImplementationApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@LocalServerPort
	private int port;

	@Test
	public void whenRequestGet_thenOK() {

		given().contentType(ContentType.JSON).when().get("http://localhost:" + port + "/kalahagame/start").then()
				.statusCode(200);

	}

	@Test
	public void whenRequestedPost_thenCreated() {

		String gameInput = "{ \"pit\":2,\"gameImplementation\":{\"board\":[6, 6, 0, 7, 7, 7, 1, 7, 7, 6, 6, 6, 6, 0],\"status\":\"Player1Turn\",\"message\":\"Let's Play KALAHA\",\"player1\":{\"id\":1,\"kalahaBigPit\":6},\"player2\":{\"id\":2,\"kalahaBigPit\":13}}}";

		given().contentType(ContentType.JSON).body(gameInput).when()
				.post("http://localhost:" + port + "/kalahagame/play").then().statusCode(200);
	}

}
