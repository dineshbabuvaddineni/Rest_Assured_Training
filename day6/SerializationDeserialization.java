package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;

// Pojo    -----Serialize---> JSON Object -----> Deserialize----> Pojo
public class SerializationDeserialization {

    @Test(priority=1)
    void convertPojo2Json() throws JsonProcessingException {
        // Create a POJO object
        student stupojo = new student();
        stupojo.setName("scott");
        stupojo.setLocation("France");
        stupojo.setPhone("1234577");
        String coursesArr[] = { "C", "C++" };
        stupojo.setCourses(coursesArr);

        // Convert Java object --> JSON object (serialization)
        ObjectMapper objMapper = new ObjectMapper();
        String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
        System.out.println("Serialized JSON:");
        System.out.println(jsondata);
    }

    @Test(priority=2)
    void convertJson2Pojo() throws JsonMappingException, JsonProcessingException {
        // JSON data as a String
        String jsondata = "{\r\n"
                + "  \"name\": \"scott\",\r\n"
                + "  \"location\": \"France\",\r\n"
                + "  \"phone\": \"1234577\",\r\n"
                + "  \"courses\": [\"C\", \"C++\"]\r\n"
                + "}";

        // Convert JSON data --> POJO object (deserialization)
        ObjectMapper objMapper = new ObjectMapper();
        student stupojo = objMapper.readValue(jsondata, student.class);

        // Print the values from the deserialized POJO
        System.out.println("Deserialized POJO:");
        System.out.println("Name: " + stupojo.getName());
        System.out.println("Location: " + stupojo.getLocation());
        System.out.println("Phone: " + stupojo.getPhone());
        System.out.println("Courses: " + String.join(", ", stupojo.getCourses()));
    }
}
