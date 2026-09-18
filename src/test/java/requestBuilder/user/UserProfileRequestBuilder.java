package requestBuilder.user;



import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloadBuilder.user.UserPayload;

import static commons.Routes.*;
import static io.restassured.RestAssured.given;
import static requestBuilder.auth.AuthRequestBuilder.token;

public class UserProfileRequestBuilder {

    public static Response getUserProfileRequest(){

        return given()
                .baseUri(BASE_URL)
                .basePath(USER_PROFILE)
                .contentType("application/json")
                .header("Authorization",token)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response();

    }

    //missing upload profile image request

    public static Response updateUserPasswordRequest(String currentPassword, String newPassword){

        return given()
                .baseUri(BASE_URL)
                .basePath(UPDATE_USER_PASSWORD)
                .contentType("application/json")
                .header("Authorization",token)
                .body(UserPayload.updateUserPasswordPayload(currentPassword, newPassword))
                .when()
                .put();

    }

    public static Response updateUserProfileRequest(String firstname, String lastname, String profileImage, String profilePic, String aboutMe){

        return given()
                .baseUri(BASE_URL)
                .basePath(USER_PROFILE)
                .contentType("application/json")
                .header("Authorization",token)
                .body(UserPayload.updateUserProfilePayload(firstname, lastname, profileImage, profilePic, aboutMe))
                .when()
                .put()
                .then()
                .extract().response();

    }

    public static Response uploadProfilePicRequest(){

        return given()
                .baseUri(BASE_URL)
                .basePath(UPLOAD_PROFILE_IMAGE)
                .contentType("multipart/data")
                .header("Authorization",token)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

    }


}
