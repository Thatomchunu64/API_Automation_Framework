package requestBuilders.courses;

import commons.Routes;
import io.restassured.response.Response;
import payloadBuilders.courses.CoursesPayload;
import requestBuilders.admin.AdminRequestBuilder;
import requestBuilders.auth.AuthRequestBuilder;

import static commons.Routes.*;
import static io.restassured.RestAssured.given;
import static requestBuilders.admin.AdminRequestBuilder.adminToken;
import static requestBuilders.auth.AuthRequestBuilder.token;


public class PublicCoursesRequestBuilder {
    public static String publicCourseID;


    public static Response getAllCoursesRequest() {
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(GET_COURSES)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get()
                .then()
                .extract()
                .response();

        publicCourseID = response.body().jsonPath().getString("data.courses[0].Id");
        return response;

    }

    public static Response courseEnrollmentRequest() {

        return given()
                .baseUri(BASE_URL)
                .basePath(COURSE_ENROLL)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("id", publicCourseID)
                .when()
                .post()
                .then()
                .extract()
                .response();

    }

    public static Response updateCourseProgressRequest(int progressPercentage) {

        return given()
                .baseUri(BASE_URL)
                .basePath(COURSE_PROGRESS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("id", publicCourseID)
                .body(CoursesPayload.updateCourseProgressPayload(progressPercentage))
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public static Response getEnrollmentsRequest() {

        return given()
                .baseUri(BASE_URL)
                .basePath(ENROLLMENTS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    //MISSING GET COURSE DETAILS REQUEST BUILDER, SEEMS TO BE AN ERROR WITH THE API, AS IT IS NOT RETURNING THE EXPECTED RESPONSE


}
