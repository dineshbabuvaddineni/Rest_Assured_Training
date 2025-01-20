package day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;

import org.testng.annotations.Test;

public class JSONSchemaValidation {
	
	@Test
	void jsonschemavalidation() {
		given()
		
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("StoreJsonSCHEMA.json"));
			
			
		
	}

}
