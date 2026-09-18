package requestBuilder.auth;

import static commons.Routes.*;
import static io.restassured.RestAssured.given;
import static payloadBuilder.auth.AuthPayload.*;


import io.restassured.response.Response;


public class AuthRequestBuilder {


    public static String registerUserId;
    public static String token;
    public static String verifyToken ;

   /* public static RequestSpecification defaultRequest() {
        return given()
                .baseUri(Routes.BASE_URL)
                .contentType("application/json");
    }*/


    public static Response loginRequest(String email, String password) {
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(LOGIN)
                .body(loginUserPayload(email, password))
                .when()
                .post()
                .then()
                .extract().response();
        token = response.jsonPath().getString("data.token");
        return response;
    }

    public static Response RegistrationRequest(String firstname, String lastname, String email, String password, String groupId) {

        Response response = given()
                .baseUri(BASE_URL)
                .basePath(REGISTER)
                .body(registerUserPayload(firstname, lastname, email, password, groupId))
                .when()
                .post()
                .then()
                .extract().response();

        registerUserId = response.jsonPath().getString("data.id");

        return response;
    }

    public static Response forgotPasswordRequest(String email) {

        return given()
                .baseUri(BASE_URL)
                .basePath(FORGOT_PASSWORD)
                .contentType("application/json")
                .body(forgotPasswordPayload(email))
                .when()
                .post()
                .then()
                .extract().response();
    }

    public static Response verifyTokenRequest(String token) {

        return given()
                .baseUri(BASE_URL)
                .basePath(VERIFY_RESET_TOKEN)
                .contentType("application/json")
                .body(verifyTokenPayload(token))
                .when()
                .post()
                .then()
                .extract().response();
    }

    public static Response resetPasswordRequest(String token, String password) {

        return given()
                .baseUri(BASE_URL)
                .basePath(RESET_PASSWORD)
                .contentType("application/json")
                .body(resetPasswordPayload(token, password))
                .when()
                .post()
                .then()
                .extract().response();
    }
}
