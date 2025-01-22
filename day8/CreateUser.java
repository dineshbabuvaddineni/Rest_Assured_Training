package day8;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;

public class CreateUser {
	
	@Test
	void test_CreateUser(ITestContext context) {
		
		Faker faker=new Faker();
		JSONObject data=new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken="5b15380161e6255773aa623558185f0230646083a9bef60512348089b4f2f5c5";
		
		int id=given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
			System.out.println("Generated id is:"+id);
			//context.setAttribute("user_id", id); //we are creating like global variable to make the id available to other classes	System.out.println("******************************************************");
			context.getSuite().setAttribute("user_id", id);
	}

}
