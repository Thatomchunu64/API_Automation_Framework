package tests.auth;

import static requestBuilder.admin.AdminRequestBuilder.adminLoginRequest;
import static requestBuilder.admin.AdminRequestBuilder.approveUserRequest;
import static requestBuilder.auth.AuthRequestBuilder.RegistrationRequest;

import io.restassured.response.Response;

import org.testng.annotations.Test;


public class AuthenticationTests {

    public static String firstname = "Jack";
    public static String lastname = "Sparrow";
    public static String email = "sparrow@example.com";
    public static String password = "Pirates@123";
    public static String groupId = "5328c91e-fc40-11f0-8e00-5000e6331276";

    @Test
    public void userRegistrationTest() {

        Response response = RegistrationRequest(firstname, lastname, email, password, groupId);
        response.then().log().all();

        int statusCode = response.getStatusCode();
        assert statusCode == 201 : "Expected status code 200 but got " + statusCode;

    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void adminLoginTest() {
        Response response = adminLoginRequest();
        response.then().log().all();

    }


    @Test(dependsOnMethods = "adminLoginTest")
    public void approveUserTest() {
        Response response = approveUserRequest();
        response.then().log().all();

        int statusCode = response.getStatusCode();
        assert statusCode == 200 : "Expected status code 200 but got " + statusCode;
    }
}


