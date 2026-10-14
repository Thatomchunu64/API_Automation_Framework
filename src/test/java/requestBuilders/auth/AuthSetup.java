package requestBuilders.auth;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
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


        // Register
        RegistrationRequest(
                firstname,
                lastname,
                email,
                password,
                groupId
        );

        DBPlug.insertUser(email, password);


        // Admin authentication
        adminLoginRequest();

        // Approve user
        approveUserRequest();

        // User authentication
        DBPlug.getLoginDetails(email);
        loginRequest(

                DBPlug.getEmail,
                DBPlug.getPassword
        );
    }

    public static Response loginSetup() throws SQLException {

        DBPlug.getLoginDetails("Fermin99@gmail.com");

        return AuthRequestBuilder.loginRequest(DBPlug.getEmail, DBPlug.getPassword);
    }
}