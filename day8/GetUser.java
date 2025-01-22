package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetUser {
	@Test
	void test_GetUser(ITestContext context) {
	//int id=(Integer) context.getAttribute("user_id");//this should come from createUser request
	int id=(Integer) context.getSuite().getAttribute("user_id");
	String bearerToken="5b15380161e6255773aa623558185f0230646083a9bef60512348089b4f2f5c5";
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
		System.out.println("******************************************************");
	}

}
