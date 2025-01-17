package day4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {

    @Test(priority = 1)
    void testJsonResponse() {
        // Approach 1 - Direct Validation
       /* given()
            .contentType(ContentType.JSON) // Corrected: Use ContentType.JSON without quotes
        .when()
            .get("http://localhost:3000/store")
        .then()
            .statusCode(200)
            .header("Content-Type", "application/json; charset=utf-8") // Ensure the header value matches the server's response
            .body("book[2].title", equalTo("Pride and Prejudice")); // Validating the title of the 3rd book (index starts at 0)
	*/
        // Approach 2 - Using Response Object
        Response res = given()
            .contentType(ContentType.JSON)
        .when()
            .get("http://localhost:3000/store");

        // Validating Status Code
        Assert.assertEquals(res.getStatusCode(), 200, "Status code does not match!");

        // Validating Content-Type header
        Assert.assertEquals(res.header("Content-Type"), "application/json");

        // Validating a specific field in the response
        String bookName = res.jsonPath().getString("book[2].title"); // Get the title of the 3rd book
        Assert.assertEquals(bookName, "Pride and Prejudice", "Book title does not match!");
    }

    @Test(priority = 2)
    void testJsonResponseBodyData() {
        // Fetching the response
        Response res = given()
            .contentType(ContentType.JSON)
        .when()
            .get("http://localhost:3000/store");

        // Convert response body to JSONObject
        JSONObject jo = new JSONObject(res.asString()); // Corrected: Use `res.asString()` to get the response body as a string
        /*for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
            // Iterate through the array and print the title of each book
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).getString("title");
            System.out.println("Book " + (i + 1) + ": " + bookTitle);
        }*/
        
        //Search for the title of the book in json  -validation1
        boolean status=false;
        for(int i=0; i<jo.getJSONArray("book").length();i++) {
        	String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
        	if(bookTitle.equals("The Adventures of Tom Sawyer")) {
        		status=true;
        		break;
        		
        	}
        	
        }
        
        Assert.assertEquals(status, true);
        
        //Validate total price of books validation2
        
        double totalprice=0;
        for(int i=0; i<jo.getJSONArray("book").length();i++) {
        	String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
        	
        	totalprice=totalprice+Double.parseDouble(price);
        	
        }
        
        System.out.println("Total price of books is:"+ totalprice);
        Assert.assertEquals(totalprice,331.5);
        
        		
    }
}
