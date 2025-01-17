package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	//@Test(priority=1)
	void testCookies() {
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC", "AZ6Zc-XHIHI3fo91EowXA-V7vw8p7N2utb-IQ1h057F7RIe3tGuolA3glA")
			.cookie("NID","=520=LExWsIpgiskdYzqRd5sk2eADP5nLAMKj_ujKFLPn5t_PHxXdpKJABYJYBlT6zbqUsTdpX7-35BxIa9KFt0q_27gBKtpGnvVZ7k373v9GJp0QpELjpmdqkyA8_SiCvLFSyjIxXAXzRjTkxcCQ6CWxgjnylCepRv5ieUMiytc0LyfPD817GqA1UGM5Acn9_GCc8LNbJgzjNCxhRQ")
			.log().all();
	}
	
	@Test(priority=2)
	void testCookiesInfo() {
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
//		String cookie_value1=res.getCookie("AEC");
//		System.out.println("Value cookie is =====>"+cookie_value1);
//		
//		String cookie_value2=res.getCookie("NID");
//		System.out.println("Value cookie is =====>"+cookie_value2);
//		
		//get all cookies info
		Map<String,String> cookies_values=res.getCookies();
		//System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet()) {
			String cookie_value=res.getCookie(k);
			System.out.println(k+"    "+cookie_value);
		}
		
		
		
			
	}

}
