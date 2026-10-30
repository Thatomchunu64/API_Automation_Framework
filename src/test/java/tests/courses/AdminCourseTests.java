package tests.courses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestBuilders.admin.AdminRequestBuilder;
import requestBuilders.courses.AdminCourseRequestBuilder;

import static org.hamcrest.Matchers.equalTo;

public class AdminCourseTests {


    @BeforeClass
    public static void adminLoginTest() {
        AdminRequestBuilder.adminLoginRequest();
    }


    @Test
    public static void createCourseTest() {

        String title = "Psychology 101";
        String description = "This course is about introductory psychology.";
        String content = "Content for Psychology 101.";
        String thumbnailUrl = "https://example.com/thumbnail.jpg";
        String meetingUrl = "https://example.com/meeting";
        String duration = "2 hours";
        String level = "Intermediate";
        String category = "Psychology";
        boolean isPublished = true;
        boolean isFeatured = true;
        int sortOrder = 1;

        AdminCourseRequestBuilder.createCourseRequest(title, description, content, thumbnailUrl, meetingUrl, duration, level, category, isPublished, isFeatured, sortOrder)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .body("success", equalTo(true));

    }

    @Test(priority = 1)
    public static void getCoursesTest() {

        AdminCourseRequestBuilder.getAllCoursesRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }


    @Test(dependsOnMethods = {"createCourseTest"}, priority = 2)
    public static void updateCourseTest() {

        String title = "THIS IS AN UPDATION";
        String description = "WE UPDATING AND NOTHING BUT UPDATING, WHOLE LOTTA UPDATING GOING ON";
        String level = "Intermediate";
        boolean isPublished = false;

        AdminCourseRequestBuilder.updateCourseRequest(title, description, level, isPublished)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test(priority = 3, dependsOnMethods = "updateCourseTest")
    public static void deleteCourseTest() {

        AdminCourseRequestBuilder.deleteCourseRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }


}
