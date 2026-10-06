package tests.user;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestBuilders.auth.AuthRequestBuilder;
import requestBuilders.auth.AuthSetup;
import requestBuilders.user.UserProfileRequestBuilder;
import utils.DBPlug;


import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requestBuilders.user.UserProfileRequestBuilder.*;


public class UserProfileTests {

    @BeforeClass
    public void AuthTest() throws SQLException {
        AuthSetup.setupAuthenticatedUser();
    }


    @Test
    public static void getUserProfileTest() {

        getUserProfileRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));


    }

    @Test(dependsOnMethods = "getUserProfileTest")
    public static void uploadImageTest() {

        uploadProfileImageRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));


    }

    @Test(dependsOnMethods = "uploadImageTest")
    public static void updateUserProfileTest() throws IOException {

        String testfirstname = "Johhny";
        String testlastname = "Bling";
        String testaboutme = "IM A RICH MF";

        updateUserProfileRequest(testfirstname, testlastname, testaboutme)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

    }

    @Test(dependsOnMethods = "updateUserProfileTest")
    public static void updateUserPasswordTest() throws SQLException {

        String newPassword = "BlingBlingBoy@64";
        String oldPassword = DBPlug.getPassword;

        UserProfileRequestBuilder.updateUserPasswordRequest(oldPassword, newPassword)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Password updated successfully"));
        DBPlug.updatePassword(newPassword); // Update the password in the database for future tests

    }

    @Test(dependsOnMethods = "updateUserPasswordTest")
    public static void updatedPasswordUserLoginTest() {

        AuthRequestBuilder.loginRequest(DBPlug.getEmail, "BlingBlingBoy@64")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Login successful"))
                .body("data.token", notNullValue());

    }

}
