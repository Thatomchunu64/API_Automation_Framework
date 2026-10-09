package requestBuilders.testimonials;


import io.restassured.response.Response;
import payloadBuilders.testimonials.TestimonialsPayload;

import static commons.Routes.*;
import static io.restassured.RestAssured.given;
import static payloadBuilders.testimonials.TestimonialsPayload.createTestimonialPayload;
import static requestBuilders.auth.AuthRequestBuilder.token;



public class TestimonialRequestBuilder {


    public static String  testimonialID;

    public static Response createTestimonialRequest(String title, String content, int rating, boolean isPublic) {

        Response response = given()
                .baseUri(BASE_URL)
                .basePath(TESTIMONIALS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(createTestimonialPayload(title, content, rating, isPublic))
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .response();

        testimonialID = response.jsonPath().getString("data.Id");
        System.out.println("Testimonial ID: " + testimonialID);

        return response;
    }

    public static Response updateTestimonialRequest(String title, String content, int rating) {

        return given()
                .baseUri(BASE_URL)
                .basePath(MODIFY_TESTIMONIALS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("id", testimonialID)
                .body(TestimonialsPayload.updateTestimonialPayload(title, content, rating))
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .response();

    }

    public static Response deleteTestimonialRequest() {

        return given()
                .baseUri(BASE_URL)
                .basePath(MODIFY_TESTIMONIALS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("id", testimonialID)
                .when()
                .delete()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response getPublicTestimonialsRequest() {

        return given()
                .baseUri(BASE_URL)
                .basePath(GET_PUBLIC_TESTIMONIALS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response getMyTestimonialsRequest() {

        return given()
                .baseUri(BASE_URL)
                .basePath(GET_MY_TESTIMONIALS)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}
