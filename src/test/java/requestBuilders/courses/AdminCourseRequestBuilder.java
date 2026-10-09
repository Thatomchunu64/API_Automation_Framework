package requestBuilders.courses;


import commons.Routes;
import io.restassured.response.Response;
import payloadBuilders.courses.CoursesPayload;

import static io.restassured.RestAssured.given;
import static requestBuilders.admin.AdminRequestBuilder.adminToken;


public class AdminCourseRequestBuilder {

    public static String courseID;

    public static Response createCourseRequest
            (String title,
             String description,
             String content,
             String thumbnailUrl,
             String meetingUrl,
             String duration,
             String level,
             String category,
             boolean isPublished,
             boolean isFeatured,
             int sortOrder) {

        Response response = given()
                .baseUri(Routes.BASE_URL)
                .basePath(Routes.CREATE_ADMIN_COURSE)
                .contentType("application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(CoursesPayload.createCoursePayload(title, description, content, thumbnailUrl, meetingUrl, duration, level, category, isPublished, isFeatured, sortOrder))
                .when()
                .post()
                .then()
                .extract()
                .response();

        courseID = response.body().jsonPath().getString("data.id");
        return response;
    }

    public static Response updateCourseRequest(String title, String description, String level, boolean isPublished) {

        return given()
                .baseUri(Routes.BASE_URL)
                .basePath(Routes.MODIFY_ADMIN_COURSE)
                .contentType("application/json")
                .header("Authorization", "Bearer " + adminToken)
                .pathParam("id", courseID)
                .body(CoursesPayload.updateCoursePayload(title, description, level, isPublished))
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public static Response deleteCourseRequest() {

        return given()
                .baseUri(Routes.BASE_URL)
                .basePath(Routes.MODIFY_ADMIN_COURSE)
                .contentType("application/json")
                .header("Authorization", "Bearer " + adminToken)
                .pathParam("id", courseID)
                .when()
                .delete()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response getAllCoursesRequest() {

        return given()
                .baseUri(Routes.BASE_URL)
                .basePath(Routes.GET_ADMIN_COURSES)
                .contentType("application/json")
                .header("Authorization", "Bearer " + adminToken)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }


}
