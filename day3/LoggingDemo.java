package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import jdk.internal.net.http.common.Log;

public class LoggingDemo {
	
	@Test(priority=1)
	void testLogs() {
		
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			//.log().all(); //It will print everything in the response
			//.log().body();
			//.log().cookies();
			.log().headers();

			
		
		
	}
		
	

	

}
