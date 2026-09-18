package requestBuilder.user;



import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloadBuilder.user.UserPayload;

import java.io.File;

import static commons.Routes.*;
import static io.restassured.RestAssured.given;
import static requestBuilder.auth.AuthRequestBuilder.token;


public class UserProfileRequestBuilder {

    static String profileImage;

    public static Response getUserProfileRequest(){

        Response response = given()
                .baseUri(BASE_URL)
                .basePath(USER_PROFILE)
                .contentType("application/json")
                .header("Authorization",token)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        profileImage = response.jsonPath().getString("data.ProfileImage");

        return response;

    }

    //missing upload profile image request

    public static Response updateUserPasswordRequest(
            String currentPassword,
            String newPassword) {

        return given()
                .baseUri(BASE_URL)
                .basePath(UPDATE_USER_PASSWORD)
                .contentType("application/json")
                .header("Authorization", token)
                .body(UserPayload.updateUserPasswordPayload(currentPassword, newPassword))
                .when()
                .put();
    }

    public static Response updateUserProfileRequest(
            String firstName,
            String lastName,
            String profilePicture,
            String aboutMe) {

        return given()
                .baseUri(BASE_URL)
                .basePath(USER_PROFILE)
                .header("Authorization",token)
                .contentType("application/json")//not sure, copilot recommended this way
                .body(UserPayload.updateUserProfilePayload(firstName, lastName, profileImage, profilePicture, aboutMe))
                .when()
                .put()
                .then()
                .extract()
                .response();

    }

    public static Response uploadProfileImageRequest(){

        File imageFile = new File("src/test/resources/images/profile.jpg");

        return given()
                .baseUri(BASE_URL)
                .basePath(UPLOAD_PROFILE_IMAGE)
                .multiPart("profileImage",imageFile)
                .header("Authorization",token)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .response();

    }

    public static Response getTodaysInstructors(){

        return given()
                .baseUri(BASE_URL)
                .basePath(GET_TODAYS_INSTRUCTORS)
                .contentType("multipart/data")
                .header("Authorization",token)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }


}
