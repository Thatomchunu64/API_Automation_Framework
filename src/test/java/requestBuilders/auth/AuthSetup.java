package requestBuilders.auth;

import com.github.javafaker.Faker;
import utils.DBPlug;

import java.sql.SQLException;

import static requestBuilders.admin.AdminRequestBuilder.adminLoginRequest;
import static requestBuilders.admin.AdminRequestBuilder.approveUserRequest;
import static requestBuilders.auth.AuthRequestBuilder.RegistrationRequest;
import static requestBuilders.auth.AuthRequestBuilder.loginRequest;
import static tests.auth.AuthenticationTests.randomNumber;

public class AuthSetup {

    public static Faker fake = new Faker();

    public static void setupAuthenticatedUser() throws SQLException {


        // Generate user
        String firstname = fake.name().firstName();
        String lastname = fake.name().lastName();
        String email = firstname + randomNumber() + "@gmail.com";
        String password = fake.dragonBall().character() + "@2026";
        String groupId = "5328c91e-fc40-11f0-8e00-5000e6331276";

        // Prepare DB
        DBPlug.insertUser(email, password);
        DBPlug.getLoginDetails(email);

        // Register
        RegistrationRequest(
                firstname,
                lastname,
                email,
                password,
                groupId
        );

        // Admin authentication
        adminLoginRequest();

        // Approve user
        approveUserRequest();

        // User authentication
        loginRequest(
                DBPlug.getEmail,
                DBPlug.getPassword
        );
    }
}