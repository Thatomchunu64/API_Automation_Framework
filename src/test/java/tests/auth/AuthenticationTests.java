package tests.auth;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requestBuilders.admin.AdminRequestBuilder.adminLoginRequest;
import static requestBuilders.admin.AdminRequestBuilder.approveUserRequest;
import static requestBuilders.auth.AuthRequestBuilder.RegistrationRequest;
import static requestBuilders.auth.AuthRequestBuilder.loginRequest;


import com.github.javafaker.Faker;


import commons.Routes;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestBuilders.admin.AdminRequestBuilder;
import utils.DBPlug;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Random;


public class AuthenticationTests {



    public static String firstname;
    public static String lastname;
    public static String email;
    public static String password;
    public static String groupId;

    private static final Random random = new Random();



    public static int randomNumber() {
        return random.nextInt(1000);
    }


    public static Faker fake = new Faker();

    @BeforeClass
    public static void setUpData() {

        firstname = fake.name().firstName();
        lastname = fake.name().lastName();
        email = firstname + randomNumber()+"@gmail.com";
        password = fake.dragonBall().character() + "@2026";
        groupId = "5328c91e-fc40-11f0-8e00-5000e6331276"; // Assuming groupId is a String, you can change it as needed



    }

    @Test
    public static void userRegistrationTest() throws SQLException {

        RegistrationRequest(firstname, lastname, email, password, groupId)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .body("success", equalTo(true));

        DBPlug.insertUser(email,password);

    }


    @Test(priority = 1)
    public static void adminLoginTest() {

       adminLoginRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))//other way of asserting
                .body("message", equalTo("Login successful"))//other way of asserting
                .body("data.token",notNullValue());

    }


    @Test(dependsOnMethods = {"userRegistrationTest", "adminLoginTest"})
    public static void approveUserTest() {

        approveUserRequest()
                .then()
                .log()
                .all()
                .body("data.approvalStatus", equalTo("approved"))
                .body("message", equalTo("User approved successfully"));


    }

    @Test (dependsOnMethods = "approveUserTest")
    public static void userLoginTest() throws SQLException {
        DBPlug.getLoginDetails(email);

        loginRequest(DBPlug.getEmail, DBPlug.getPassword)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))//other way of asserting
                .body("message", equalTo("Login successful"))//other way of asserting
                .body("data.token",notNullValue());//verify the token is generated/present and it is not empty

    }

    @Test
    public static void negativeUserLoginTest() {

        loginRequest(email, password)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(401)
                .body("success", equalTo(false))//other way of asserting
                .body("message", equalTo("Invalid email or password"))//other way of asserting
                .body("error_code", equalTo("INVALID_CREDENTIALS"));

    }


    @Test(dependsOnMethods = "adminLoginTest")
    public static void schemaValidatorTest(){

        Response response = AdminRequestBuilder.adminLoginRequest();

        try {
            String savedSchema = Files.readString(Paths.get(Routes.JSON_SCHEMA_PATH+"AdminLoginSchema.json"));
            System.out.println("Loaded Schema: "+ savedSchema);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

        String schemaPath = Paths.get(Routes.JSON_SCHEMA_PATH+"AdminLoginSchema.json").toAbsolutePath().toString();
        response.then().assertThat().body(io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema(Paths.get(schemaPath).toFile()));

    }




}


