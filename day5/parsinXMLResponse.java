package day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class parsinXMLResponse {
	
	@Test
	void testXMLResponse() {
		
		//Approach 1
		/*given()
		
        .when()
        	.get("http://restapi.adequateshop.com/api/Traveler?page=1")
        .then()
        	.statusCode(200)
        	.header("Content-Type", "application/xml")
			.body("TravelerInformationResponse.page",equalTo("1"))
			.body("TravelinformationResponse.travelers.Travelerinformation[0].name",equalTo("Vijay Bharath Reddy"));
		*/
		
		//Approach 2
				Response res=given()
				
		        .when()
		        	.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		        
				Assert.assertEquals(res.getStatusCode(), 200);
				Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
				
				String pageNo=res.xmlPath().get("TravelerInformationResponse.page").toString();
				Assert.assertEquals(pageNo, "1");
				
				String travelName=res.xmlPath().get("TravelinformationResponse.travelers.Travelerinformation[0].name").toString();
				Assert.assertEquals(travelName, "Vijay Bharath Reddy");		
			
	}
	
	@Test
	void testXMLResponseBody() {
		Response res=
		given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj =new XmlPath(res.asString());
		List<String> travellers=xmlobj.getList("TravelinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(),10);
		
		//verify traveller name is present in response
		List<String> traveller_names=xmlobj.getList("TravelinformationResponse.travelers.Travelerinformation.name");
		
		boolean status=false;
		for(String travellername: traveller_names) {
			System.out.println(travellername);
			if(travellername.equals("Vijay Bharath Reddy")) {
				status=true;
				break;	
			}
			
		}
		
		Assert.assertEquals(status, true);
		
	}

}
