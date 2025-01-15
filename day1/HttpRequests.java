package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

/* 
 given()
 	content type, set cookies, add auth, add param, set headers info et....
 	
 when()
 	get, post, put, delete
 	
 then()
 	validate status code, extract response, extract headers cookies & response body .....
 */

public class HttpRequests {
	int id;
	
	@Test(priority=1)
	void getUser() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2)) //by default equalTo come from restassured matchers package
			.log().all();
			
	}
	
	@Test(priority=2)
	void createUser() {
		HashMap data =new HashMap(); //it is the static way of sending data to the variable
		data.put("name","pavan");
		data.put("job","trainer");
			
		int id = given()
			.contentType("application/json")
			.body(data)
		.when()
			 .post("https://reqres.in/api/users")
			 .jsonPath().getInt("id");
		//.then()
			//.statusCode(201)
			//.log().all();
	}
	
	@Test(priority=3,dependsOnMethods= {"createUser"})
	void updateUser() {
		HashMap data =new HashMap(); //it is the static way of sending data to the variable
		data.put("name","pavan");
		data.put("job","trainer");
			
		given()
			.contentType("application/json")
			.body(data)
		.when()
			 .put("https://reqres.in/api/users/"+id)
	
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4)
	void deleteUser() {
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
	 
}
