package tests.user;



import io.restassured.response.Response;
import org.testng.annotations.Test;
import requestBuilder.user.UserProfileRequestBuilder;
import tests.auth.AuthenticationTests;

import java.io.IOException;

import static requestBuilder.user.UserProfileRequestBuilder.*;


public class UserProfileTests {

    @Test
    public void AuthTest(){

        AuthenticationTests.userRegistrationTest();
        AuthenticationTests.adminLoginTest();
        AuthenticationTests.approveUserTest();
        AuthenticationTests.userLoginTest();

    }


    @Test(dependsOnMethods = "AuthTest")
    public static void getUserProfileTest(){

        Response response = getUserProfileRequest();
        response.then().log().all();

        int statusCode = response.getStatusCode();
        assert statusCode == 200 : "Expected status code 200 but got " + statusCode;


    }

    @Test(dependsOnMethods = "getUserProfileTest")
    public static void uploadImageTest(){

        Response response = uploadProfileImageRequest();
        response.then().log().all();

        int statusCode = response.getStatusCode();
        assert statusCode == 200 : "Expected status code 200 but got " + statusCode;
    }

    @Test(dependsOnMethods = "uploadImageTest")
    public static void updateUserProfileTest() throws IOException {

        String testfirstname= "Johhny";
        String testlastname= "Bling";
        String testaboutme = "IM A RICH MF";

        Response response = updateUserProfileRequest(testfirstname,testlastname,testaboutme);
        response.then().log().all();

        int statusCode = response.getStatusCode();
        assert statusCode == 200 : "Expected status code 200 but got " + statusCode;

    }

    @Test(dependsOnMethods = "updateUserProfileTest")
    public static void updateUserPasswordTest(){

        String newPassword= "JBlingbling@64";
        String oldPassword = "Pirates@123";
        Response response = UserProfileRequestBuilder.updateUserPasswordRequest(oldPassword, newPassword);
        response.then().log().all();

        int statusCode = response.getStatusCode();
        assert statusCode == 200 : "Expected status code 200 but got " + statusCode;

    }




}
