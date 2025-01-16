package day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class ClassToCreatePostRequest {
	
	//Post request body using HashMap
    //@Test(priority=1)
    void testPostUsingHashMap() {
        // Create the data for the POST request
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        String[] courseArr = {"C", "C++"};
        data.put("courses", courseArr);

        // Send the POST request and validate the response
        given()
            .contentType("application/json")
            .body(data)
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .body("name", equalTo("Scott"))
            .body("location", equalTo("France"))
            .body("phone", equalTo("123456"))
            .body("courses[0]", equalTo("C"))
            .body("courses[1]", equalTo("C++"))
            .header("Content-Type", "application/json")
            .log().all();
    }
    
    //2)Post request body using org.json library
    
    //@Test(priority=1)
    void testPostusingJsonLibrary() {
        // Create the data for the POST request
        JSONObject data=new JSONObject();
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        String[] courseArr = {"C", "C++"};
        data.put("courses", courseArr);

        // Send the POST request and validate the response
        given()
            .contentType("application/json")
            .body(data.toString()) // here we have to pass data in the string format
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .body("name", equalTo("Scott"))
            .body("location", equalTo("France"))
            .body("phone", equalTo("123456"))
            .body("courses[0]", equalTo("C"))
            .body("courses[1]", equalTo("C++"))
            .header("Content-Type", "application/json")
            .log().all();
    }
    
    //3)Post request body using POJO class
    //@Test(priority=1)
    void testPostusingPOJO() {
        // Create the data for the POST request
    	PojoPostRequest data=new PojoPostRequest();
        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("123456");
      
        String[] courseArr = {"C", "C++"};
        data.setCourses(courseArr);

        // Send the POST request and validate the response
        given()
            .contentType("application/json")
            .body(data) // here we have to pass data in the string format
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .body("name", equalTo("Scott"))
            .body("location", equalTo("France"))
            .body("phone", equalTo("123456"))
            .body("courses[0]", equalTo("C"))
            .body("courses[1]", equalTo("C++"))
            .header("Content-Type", "application/json")
            .log().all();
    }
    
    //4) Post request body using External JSON File
    @Test(priority=1)
    void testPostusingExternalJSONFile() throws FileNotFoundException {
        
    	File f=new File(".\\body.json");
    	FileReader fr=new FileReader(f);
    	JSONTokener jt=new JSONTokener(fr); // for splitting file it to different tokens
    	JSONObject data=new JSONObject(jt);
    	

        // Send the POST request and validate the response
        given()
            .contentType("application/json")
            .body(data.toString()) // here we have to pass data in the string format
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .body("name", equalTo("Scott"))
            .body("location", equalTo("France"))
            .body("phone", equalTo("123456"))
            .body("courses[0]", equalTo("C"))
            .body("courses[1]", equalTo("C++"))
            .header("Content-Type", "application/json")
            .log().all();
    }
    
    //deleting student record
    @Test(priority=2)
    void testDelete() {
    	given()
    	.when()
    		.delete("http://localhost:3000/students/868a")
    	.then()
    		.statusCode(200);
    	
    }  
    
}
