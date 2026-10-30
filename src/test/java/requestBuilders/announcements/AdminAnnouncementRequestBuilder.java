package requestBuilders.announcements;

import commons.Routes;
import io.restassured.response.Response;
import payloadBuilders.announcements.AdminAnnouncementPayload;
import requestBuilders.auth.AuthRequestBuilder;

import static io.restassured.RestAssured.given;


public class AdminAnnouncementRequestBuilder {

    public static String announcementID;


    public static Response getAllAnnouncements() {

        return given()
                .baseUri(Routes.BASE_URL)
                .basePath(Routes.ANNOUNCEMENTS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthRequestBuilder.token)
                .when()
                .get()
                .then()
                .extract()
                .response();

    }

    public static Response createAnnouncementRequest(String title, String content) {

        Response response = given()
                .baseUri(Routes.BASE_URL)
                .basePath(Routes.ANNOUNCEMENTS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthRequestBuilder.token)
                .body(AdminAnnouncementPayload.createAnnouncementPayload(title, content))
                .when()
                .post()
                .then()
                .extract()
                .response();

        announcementID = response.body().jsonPath().getString("data.id");// I dont wanna do it like so
        return response;


    }

    public static Response updateAnnouncementRequest(String title, String content, boolean isActive) {

        return given()
                .baseUri(Routes.BASE_URL)
                .basePath(Routes.MODIFY_ANNOUNCEMENT)
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthRequestBuilder.token)
                .pathParam("id", announcementID)
                .body(AdminAnnouncementPayload.updateAnnouncementPayload(title, content, isActive))
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public static Response deleteAnnouncementRequest() {

        return given()
                .baseUri(Routes.BASE_URL)
                .basePath(Routes.MODIFY_ANNOUNCEMENT)
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthRequestBuilder.token)
                .pathParam("id", announcementID)
                .when()
                .delete()
                .then()
                .extract()
                .response();


    }


}
