package tests.testimonials;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requestBuilders.auth.AuthSetup;
import requestBuilders.testimonials.TestimonialRequestBuilder;

import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;

public class TestimonialTests {

    @BeforeClass
    public static void authenticateUserTest() throws SQLException {

        AuthSetup.setupAuthenticatedUser();
    }

    @Test
    public static void getPublicTestimonialsTest() {
        TestimonialRequestBuilder.getPublicTestimonialsRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    public static void getMyTestimonialsTest() {

        TestimonialRequestBuilder.getMyTestimonialsRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    public static void createTestimonialTest() {

        String title = "Mid Service";
        String content = "I had an ok service, wasnt that bad or good!";
        int rating = 3;
        boolean isPublic = true;

        TestimonialRequestBuilder.createTestimonialRequest(title, content, rating, isPublic)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .body("success", equalTo(true));
    }

    @Test(dependsOnMethods = "createTestimonialTest")
    public static void updateTestimonialTest() {

        String updatedTitle = "Updated Testimonial Title";
        String updatedContent = "This is the updated content of the testimonial.";
        int updatedRating = 4;

        TestimonialRequestBuilder.updateTestimonialRequest(updatedTitle, updatedContent, updatedRating)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test(dependsOnMethods = "createTestimonialTest", priority = 3)
    public static void deleteTestimonialTest() {

        TestimonialRequestBuilder.deleteTestimonialRequest()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }
}
