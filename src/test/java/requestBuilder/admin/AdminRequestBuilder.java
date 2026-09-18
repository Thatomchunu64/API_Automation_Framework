package requestBuilder.admin;

import static commons.Routes.APPROVE_USER;
import static commons.Routes.BASE_URL;
import static io.restassured.RestAssured.given;
import static requestBuilder.auth.AuthRequestBuilder.loginRequest;
import static requestBuilder.auth.AuthRequestBuilder.registerUserId;


import io.restassured.response.Response;


public class AdminRequestBuilder {

    public static String adminToken;
    public static String adminEmail = "demonslayer@gmail.com";
    public static String adminPassword = "Hashira@2026";
    public static Response response;

    public static Response adminLoginRequest() {

        response = loginRequest(adminEmail, adminPassword);
        adminToken = response.jsonPath().getString("data.token");


        return response;

    }


    public static Response approveUserRequest() {

        return given()
                .baseUri(BASE_URL)
                .basePath(APPROVE_USER)
                .contentType("application/json")
                .header("Authorization", "Bearer " + adminToken)
                .pathParam("id", registerUserId)
                .when()
                .put();
    }


}
