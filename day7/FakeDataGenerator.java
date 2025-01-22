package day7;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class FakeDataGenerator {

    @Test
    void testGenerateDummyData() {
        Faker faker = new Faker();

        // Generate dummy data
        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String password = faker.internet().password();
        String phoneNo = faker.phoneNumber().cellPhone();
        String email = faker.internet().safeEmailAddress();

        // Print the dummy data to the console
        System.out.println("Full Name: " + fullName);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Phone Number: " + phoneNo);
        System.out.println("Email Address: " + email);
    }
}
