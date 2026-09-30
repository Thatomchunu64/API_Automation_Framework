package tests.user;


import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestBuilder.admin.AdminRequestBuilder;
import requestBuilder.auth.AuthRequestBuilder;
import requestBuilder.user.UserProfileRequestBuilder;


import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static requestBuilder.user.UserProfileRequestBuilder.*;


public class UserProfileTests {


    public static String firstname = "Jerry";
    public static String lastname = "Springer";
    public static String email = "springer" + randomNumber() + "@example.com";
    public static String password = "@12312341234";
    public static String groupId = "5328c91e-fc40-11f0-8e00-5000e6331276";

    public static int randomNumber() {
        return (int) (Math.random() * 1000);
    }

    @BeforeClass
    public void AuthTest() {

        AuthRequestBuilder.RegistrationRequest(firstname, lastname, email, password, groupId);
        AdminRequestBuilder.adminLoginRequest();
        AdminRequestBuilder.approveUserRequest();
        AuthRequestBuilder.loginRequest(email, password);

    }


    @Test
    public static void getUserProfileTest() {

        Response response = getUserProfileRequest();
        response.then().log().all();

        int statusCode = response.getStatusCode();
        assert statusCode == 200 : "Expected status code 200 but got " + statusCode;


    }

    @Test(dependsOnMethods = "getUserProfileTest")
    public static void uploadImageTest() {

        Response response = uploadProfileImageRequest();
        response.then().log().all();

        int statusCode = response.getStatusCode();
        assert statusCode == 200 : "Expected status code 200 but got " + statusCode;
    }

    @Test(dependsOnMethods = "uploadImageTest")
    public static void updateUserProfileTest() throws IOException {

        String testfirstname = "Johhny";
        String testlastname = "Bling";
        String testaboutme = "IM A RICH MF";

        Response response = updateUserProfileRequest(testfirstname, testlastname, testaboutme);
        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo(true));

    }

    @Test(dependsOnMethods = "updateUserProfileTest")
    public static void updateUserPasswordTest() {

        String newPassword = "BlingBlingBoy@64";
        String oldPassword = password;

        Response response = UserProfileRequestBuilder.updateUserPasswordRequest(oldPassword, newPassword);
        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Password updated successfully"));

    }


}
