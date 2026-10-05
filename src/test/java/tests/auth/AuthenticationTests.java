package tests.auth;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requestBuilders.admin.AdminRequestBuilder.adminLoginRequest;
import static requestBuilders.admin.AdminRequestBuilder.approveUserRequest;
import static requestBuilders.auth.AuthRequestBuilder.RegistrationRequest;
import static requestBuilders.auth.AuthRequestBuilder.loginRequest;


import com.github.javafaker.Faker;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AuthenticationTests {

    public static String firstname;
    public static String lastname;
    public static String email;
    //  public static String email = "sparrow" + randomNumber() + "@example.com"
    public static String password;
    public static String groupId;

  /*  public static int randomNumber() {
        return (int) (Math.random() * 1000);
    }*/


    static Faker fake = new Faker();

    @BeforeClass
    public static void setUpData() {

        firstname = fake.name().firstName();
        lastname = fake.name().lastName();
        email = firstname + "34@gmail.com";
        password = fake.dragonBall().character() + "@2026";
        groupId = "5328c91e-fc40-11f0-8e00-5000e6331276"; // Assuming groupId is a String, you can change it as needed

    }

    @Test
    public static void userRegistrationTest() {

        Response response = RegistrationRequest(firstname, lastname, email, password, groupId);
        response.then()
                .log()
                .all();

        int statusCode = response.getStatusCode();
        assert statusCode == 201 : "Expected status code 201 but got " + statusCode;

    }

    @Test(priority = 1)
    public static void adminLoginTest() {

        Response response = adminLoginRequest();
        response.then()
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

        Response response = approveUserRequest();
        response.then()
                .log()
                .all()
                .body("data.approvalStatus", equalTo("approved"))
                .body("message", equalTo("User approved successfully"));

        int statusCode = response.getStatusCode();
        assert statusCode == 200 : "Expected status code 200 but got " + statusCode;

    }

    @Test(dependsOnMethods = "approveUserTest")
    public static void userLoginTest() {

        Response response = loginRequest(email, password);
        response.then()
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

        Response response = loginRequest(email, password);
        response.then().log()
                .all()
                .assertThat()
                .statusCode(401)
                .body("success", equalTo(false))//other way of asserting
                .body("message", equalTo("Invalid email or password"))//other way of asserting
                .body("error_code", equalTo("INVALID_CREDENTIALS"));

    }




}


