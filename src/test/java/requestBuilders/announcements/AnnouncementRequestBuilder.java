package requestBuilders.announcements;


import commons.Routes;
import io.restassured.response.Response;
import requestBuilders.auth.AuthRequestBuilder;

import static io.restassured.RestAssured.given;

public class AnnouncementRequestBuilder {

    public static Response getPublicAnnouncements() {

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


}
