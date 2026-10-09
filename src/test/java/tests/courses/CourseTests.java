package tests.courses;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestBuilders.auth.AuthSetup;
import requestBuilders.courses.PublicCoursesRequestBuilder;

import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;

public class CourseTests {

    @BeforeClass
    public static void authenticationTest() throws SQLException {

        AuthSetup.setupAuthenticatedUser();

    }


    @Test
    public static void getAllCoursesTest() {

        PublicCoursesRequestBuilder.getAllCoursesRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

    }

    @Test(priority = 1)
    public static void courseEnrollmentTest() {

        PublicCoursesRequestBuilder.courseEnrollmentRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .body("success", equalTo(true));

    }

    @Test(priority = 2)
    public static void updateCourseProgressTest() {

        int progressPercentage = 50;

        PublicCoursesRequestBuilder.updateCourseProgressRequest(progressPercentage)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }


}
