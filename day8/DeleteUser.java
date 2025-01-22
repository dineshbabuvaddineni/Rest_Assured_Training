package day8;
import static io.restassured.RestAssured.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	@Test 
	void test_deleteUser(ITestContext context) {
		String bearerToken="5b15380161e6255773aa623558185f0230646083a9bef60512348089b4f2f5c5";
		//int id=(Integer) context.getAttribute("user_id");
		
		int id=(Integer) context.getSuite().getAttribute("user_id");
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
	}
	

}
