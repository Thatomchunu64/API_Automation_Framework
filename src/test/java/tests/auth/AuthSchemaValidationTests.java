package tests.auth;

import com.github.javafaker.Faker;
import commons.Routes;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import requestBuilders.admin.AdminRequestBuilder;
import requestBuilders.auth.AuthRequestBuilder;
import utils.DBPlug;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static tests.auth.AuthenticationTests.randomNumber;

public class AuthSchemaValidationTests {


    public static Faker fake = new Faker();
    public static String firstname = fake.name().firstName();
    public static String lastname = fake.name().lastName();
    public static String email = firstname + randomNumber() + "@gmail.com";
    public static String password = fake.dragonBall().character() + "@2026";
    public static String groupId = "5328c91e-fc40-11f0-8e00-5000e6331276";

    @Test
    public static void registrationSchemaValidatorTest() throws SQLException {

        Response response = AuthRequestBuilder.RegistrationRequest(firstname, lastname, email, password, groupId);

        Path schemaPath = Paths.get(
                Routes.JSON_SCHEMA_PATH + "UserRegistrationSchema.json").toAbsolutePath();

        response.then()
                .assertThat()
                .statusCode(201)
                .body(matchesJsonSchema(schemaPath.toFile()));

        DBPlug.insertUser(email, password);

    }


    @Test(priority = 1)
    public static void AdminLoginSchemaValidatorTest() {

        Response response = AdminRequestBuilder.adminLoginRequest();

        Path schemaPath = Paths.get(
                Routes.JSON_SCHEMA_PATH + "LoginSchema.json").toAbsolutePath();

        response.then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(schemaPath.toFile()));

    }


    @Test(dependsOnMethods = {"registrationSchemaValidatorTest", "AdminLoginSchemaValidatorTest"})
    public static void UserApprovalSchemaValidatorTest() {

        Response response = AdminRequestBuilder.approveUserRequest();

        Path schemaPath = Paths.get(
                Routes.JSON_SCHEMA_PATH + "UserApprovalSchema.json").toAbsolutePath();

        response.then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(schemaPath.toFile()));

    }

    @Test(dependsOnMethods = "UserApprovalSchemaValidatorTest")
    public static void UserLoginSchemaValidatorTest() throws SQLException {

        DBPlug.getLoginDetails(email);
        Response response = AuthRequestBuilder.loginRequest(email, password);

        Path schemaPath = Paths.get(
                Routes.JSON_SCHEMA_PATH + "LoginSchema.json").toAbsolutePath();

        response.then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(schemaPath.toFile()));


    }

}
