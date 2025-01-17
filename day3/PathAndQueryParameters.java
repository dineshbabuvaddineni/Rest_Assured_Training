package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParameters {
	
	@Test
	void testQueryPathParameters() {
		given()
			.pathParam("mypath","users")  //path parameters
			.queryParam("page",2)  //query parameter
			.queryParam("id",5) //query parameter
		
		.when()
			.get("https://reqres.in/api/{mypath}")
			
		.then()
			.statusCode(200)
			.log().all();
	}

}
