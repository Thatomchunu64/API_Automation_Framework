package payloadBuilders.courses;

import org.json.simple.JSONObject;

public class CoursesPayload {


    public static JSONObject createCoursePayload
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

        JSONObject createCourse = new JSONObject();
        createCourse.put("title", title);
        createCourse.put("description", description);
        createCourse.put("content", content);
        createCourse.put("thumbnailUrl", thumbnailUrl);
        createCourse.put("meetingUrl", meetingUrl);
        createCourse.put("duration", duration);
        createCourse.put("level", level);
        createCourse.put("category", category);
        createCourse.put("isPublished", isPublished);
        createCourse.put("isFeatured", isFeatured);
        createCourse.put("sortOrder", sortOrder);

        return createCourse;
    }


    public static JSONObject updateCoursePayload
            (String title,
             String description,
             String level,
             boolean isPublished) {

        JSONObject updateCourse = new JSONObject();
        updateCourse.put("title", title);
        updateCourse.put("description", description);
        updateCourse.put("level", level);
        updateCourse.put("isPublished", isPublished);

        return updateCourse;

    }

    public static JSONObject updateCourseProgressPayload(int progressPercentage) {

        JSONObject updateCourseProgress = new JSONObject();
        updateCourseProgress.put("progressPercentage", progressPercentage);

        return updateCourseProgress;

    }

}
