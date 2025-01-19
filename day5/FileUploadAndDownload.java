package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {
		@Test(priority=1)
		void singleFileUpload() {
			
			File myfile=new File("D:\\AutomationWorkSpaces\\POSTMAN\\students.json");
			given()
				.multiPart("file",myfile)
				.contentType("multipart/form-data")
				
			.when()
				.post("http://localhost:8080/uploadFile")
			.then()
				.statusCode(200)
				.body("fileName",equalTo("students.json"))
				.log().all();
		}
		
		/*
		@Test(priority=2)
		void multipleFilesUpload() {
			File myfile1=new File("D:\\AutomationWorkSpaces\\POSTMAN\\students.json");
			File myfile2=new File("D:\\AutomationWorkSpaces\\POSTMAN\\Books.csv");
			
			File filearr[]= {myfile1,myfile2};
			
			given()
				.multiPart("files",filearr)
				.contentType("multipart/form-data")
				
			.when()
				.post("http://localhost:8080/uploadMultipleFiles")
			.then()
				.statusCode(200)
				//.body("[0].fileName",equalTo("students.json"))
				//.body("[1].fileName",equalTo("Books.csv"))
				//.log().all();	
		}*/
		
		@Test(priority=3)
		void fileDownload() {
			given()
			
			.when()
				.get("http://localhost:8080/downloadFile/students.json")
			.then()
				.statusCode(200)
				.log().body();
		}
		
		
		

}
