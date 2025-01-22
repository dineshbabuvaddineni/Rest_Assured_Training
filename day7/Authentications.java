package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
		@Test(priority=1)
		void testBasicAuthentication() {
			given()
				.auth().basic("postman", "password")
			.when()
				.get("https://postman-echo.com/basic-auth")
			.then()
				.statusCode(200)
				.body("authenticated",equalTo(true))
				.log().all();
		}
		
		@Test(priority=2)
		void testDigestAuthentication() {
			given()
				.auth().basic("postman", "password")
			.when()
				.get("https://postman-echo.com/basic-auth")
			.then()
				.statusCode(200)
				.body("authenticated",equalTo(true))
				.log().all();
		}
		
		@Test(priority=3)
		void testpreemptiveAuthentication() {
			given()
				.auth().basic("postman", "password")
			.when()
				.get("https://postman-echo.com/basic-auth")
			.then()
				.statusCode(200)
				.body("authenticated",equalTo(true))
				.log().all();
		}
		
		@Test(priority=4)
		void testBeareTokenAuthentication() {
			String bearerToken="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";
			given()
				.header("Authorization","Bearer "+bearerToken)
			.when()
				.get("https://api.github.com/user/repos")
			.then()
				.statusCode(200)
				.log().all();	
		}
		//@Test(priority=5)
		void testOAuth1Authentication(){
			given()
				.auth().oauth("consumerKey","consumerSecrat","accessToken","tokenSecrate")
			.when()
				.get("url")
			
			.then()
				.statusCode(200)
				.log().all();
		}
		
		@Test(priority=6)
		void testOAuth2Authentication(){
			given()
				.auth().oauth2("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")
			.when()
				.get("https://api.github.com/user/repos")
			
			.then()
				.statusCode(200)
				.log().all();
		}
		
		@Test(priority=7)
		void testAPIKeyAuthentication(){
			given()
				//.queryParam("appid", "e5550166569096a03119c49d63d41f97")
			.when()
				.get("https://api.weatherapi.com/v1/current.json?key=f18e99eeed8c435c8b9175928252001&q=London&aqi=no")
			.then()
				.statusCode(200)
				.log().all();

		}
		@Test(priority=8)
		void testAPIKeyAuthenticationMethod2()
		{
			given()
				.queryParam("appid", "f18e99eeed8c435c8b9175928252001")
				.pathParam("myPath","/v1/current.json")
				.queryParam("q", "London")
				.queryParam("aqi","no")
			.when()
				.get("https://api.weatherapi.com/{myPath}")
			.then()
				.statusCode(200)
				.log().all();	
		}
		
}
