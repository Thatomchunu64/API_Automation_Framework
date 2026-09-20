package requestBuilder.user;



import io.restassured.RestAssured;
import io.restassured.response.Response;
import payloadBuilder.user.UserPayload;
import requestBuilder.auth.AuthRequestBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import static commons.Routes.*;
import static io.restassured.RestAssured.given;
import static requestBuilder.auth.AuthRequestBuilder.token;


public class UserProfileRequestBuilder {

   public static String profileImage;




    public static Response getUserProfileRequest(){

        Response response = given()
                .baseUri(BASE_URL)
                .basePath(USER_PROFILE)
                .contentType("application/json")
                .header("Authorization","Bearer " + token)
                .when()
                .get()
                .then()
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
                .header("Authorization", "Bearer " + token)
                .body(UserPayload.updateUserPasswordPayload(currentPassword, newPassword))
                .when()
                .put();
    }

    public static Response updateUserProfileRequest(
            String firstName,
            String lastName,
            String aboutMe) throws IOException {


        String profilePicture = "data:image/jpeg;base64," +
                Base64.getEncoder().encodeToString(
                        Files.readAllBytes(
                                Path.of("src/test/resources/images/profile.jpg")
                        )
                );



        return given()
                .baseUri(BASE_URL)
                .basePath(USER_PROFILE)
                .header("Authorization","Bearer " + token)
                .contentType("application/json")
                .body(UserPayload.updateUserProfilePayload(firstName, lastName, profilePicture,profileImage, aboutMe))
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
                .header("Authorization","Bearer " + token)
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
                .header("Authorization","Bearer " + token)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }


}
