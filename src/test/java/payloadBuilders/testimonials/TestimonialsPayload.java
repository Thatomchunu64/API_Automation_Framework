package payloadBuilders.testimonials;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

public class TestimonialsPayload {

    public static JSONObject createTestimonialPayload(String title, String content, int rating, boolean isPublic){

            JSONObject createTestimonial= new JSONObject();
            createTestimonial.put("title", title);
            createTestimonial.put("content", content);
            createTestimonial.put("rating",rating);
            createTestimonial.put("isPublic",isPublic);

            return createTestimonial;
        }

    public static JSONObject updateTestimonialPayload(String title, String content, int rating){

        JSONObject updateTestimonial= new JSONObject();
        updateTestimonial.put("title", title);
        updateTestimonial.put("content", content);
        updateTestimonial.put("rating",rating);

        return updateTestimonial;


    }


}
