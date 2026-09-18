package commons;

public final class Routes {
    public static final String BASE_URL = "https://ndosiautomation.co.za";
    public static final String BASE_PATH = "/APIDEV";

    //authorization endpoints
    public static final String LOGIN = BASE_PATH + "/login";
    public static final String REGISTER = BASE_PATH + "/register";
    public static final String FORGOT_PASSWORD = BASE_PATH + "/forgot-password";
    public static final String RESET_PASSWORD = BASE_PATH + "/reset-password";
    public static final String VERIFY_RESET_TOKEN = BASE_PATH + "/verify-reset-token";

    //USER PROFILE endpoints
    public static final String USER_PROFILE = BASE_PATH + "/profile";
    public static final String UPLOAD_PROFILE_IMAGE = BASE_PATH + "/profile/image";
    public static final String UPDATE_USER_PASSWORD = BASE_PATH + "/profile/password";
    public static final String GET_TODAYS_INSTRUCTORS = BASE_PATH + "/my-instructors/today";

    //ADMIN USERS endpoints
    public static final String ADMIN_USERS = BASE_PATH + "/admin/users";
    public static final String DELETE_ADMIN_USER = BASE_PATH + "/admin/users/" + "{id}";
    public static final String ADMIN_USER_DETAILS = BASE_PATH + "/admin/users/" + "{id}";
    public static final String  BULK_DELETE_ADMIN_USERS = BASE_PATH + "/admin/users/bulk-delete";
    public static final String  UPDATE_USER_ROLE = BASE_PATH + "/admin/users/{id}/role";
    public static final String  EDIT_USER_STATUS = BASE_PATH + "/admin/users/{id}/status";
    public static final String  UPDATE_USER_GROUP = BASE_PATH + "/admin/users/{id}/group";

    //ADMIN DASHBOARD endpoints
    public static final String ADMIN_DASHBOARD = BASE_PATH + "/admin/dashboard";
    public static final String ACTIVITY_LOGS = BASE_PATH + "/admin/activity-logs";


    //TESTIMONIALS endpoints
    public static final String TESTIMONIALS= BASE_PATH + "/testimonials";
    public static final String GET_TESTIMONIALS_CREATE = BASE_PATH + "/my-testimonials";
    public static final String MODIFY_TESTIMONIALS = BASE_PATH + "/testimonials/" + "{id}";


    //ADMIN TESTIMONIALS endpoints
    public static final String ADMIN_TESTIMONIALS = BASE_PATH + "/admin/testimonials";
    public static final String TOGGLE_TESTIMONIALS = BASE_PATH + "/admin/testimonials/" + "{id}";

    //USER COURSES endpoints
    public static final String COURSES = BASE_PATH + "/courses";
    public static final String GET_COURSE_BY_ID = BASE_PATH + "/courses/" + "{id}";
    public static final String COURSE_ENROLL = BASE_PATH + "/courses/" + "{id}/enroll";
    public static final String COURSE_PROGRESS = BASE_PATH + "/courses/" + "{id}/progress";
    public static final String ENROLLMENTS = BASE_PATH + "/my-enrollments";

    //ADMIN COURSES endpoints
    public static final String ADMIN_COURSES = BASE_PATH + "/admin/courses";
    public static final String CREATE_ADMIN_COURSE = BASE_PATH + "/admin/courses";
    public static final String MODIFY_ADMIN_COURSE = BASE_PATH + "/admin/courses/" + "{id}";

    //ADMIN APPROVAL endpoints
    public static final String GET_PENDING_APPROVALS = BASE_PATH + "/admin/approvals";
    public static final String APPROVE_USER = BASE_PATH + "/admin/users/{id}/approve";
    public static final String BULK_APPROVE_USERS = BASE_PATH + "/admin/approvals/bulk";
    public static final String REJECT_USER = BASE_PATH + "/admin/users/{id}/reject";

    //GROUPS endpoints
    public static final String GET_GROUPS = BASE_PATH + "/groups";//GET ACTIVE GROUPS

    //ADMIN GROUPS endpoints
    public static final String ADMIN_GROUPS = BASE_PATH + "/admin/groups";// GET ALL GROUPS
    public static final String CREATE_GROUP = BASE_PATH + "/admin/groups";
    public static final String MODIFY_GROUP = BASE_PATH + "/admin/groups/" + "{id}";
    public static final String GET_STUDENT_GROUP = BASE_PATH + "/admin/groups/" + "{id}";

    //ENROLLMENTS endpoints
    public static final String MY_ENROLLMENT_STATUS = BASE_PATH + "/my-enrollment-status";

    //ADMIN ENROLLMENTS endpoints
    public static final String ADMIN_ENROLLMENTS = BASE_PATH + "/admin/enrollments";// GET ALL ENROLLMENTS
    public static final String GET_SINGLE_ENROLLMENT = BASE_PATH + "/admin/enrollments/"+ "{id}";
    public static final String ENROLL_STUDENT = BASE_PATH + "/admin/enrollments";
    public static final String BULK_ENROLL_STUDENTS = BASE_PATH + "/admin/enrollments/bulk";
    public static final String ENROLL_GROUP = BASE_PATH + "/admin/enrollments/group";
    public static final String ENROLLMENT_STATS = BASE_PATH + "/admin/enrollments/stats";
    public static final String PENDING_ENROLLMENTS = BASE_PATH + "/admin/enrollments/pending";
    public static final String MODIFY_ENROLLMENT = BASE_PATH + "/admin/enrollments/" + "{id}";
    public static final String APPROVE_ENROLLMENT = BASE_PATH + "/admin/enrollments/" + "{id}/approve";
    public static final String BULK_APPROVE_ENROLLMENTS = BASE_PATH + "/admin/enrollments/bulk-approve";
    public static final String REJECT_ENROLLMENT = BASE_PATH + "/admin/enrollments/" + "{id}/reject";
    public static final String ENROLLMENT_HISTORY = BASE_PATH + "/admin/enrollments/" + "{id}/history";
    public static final String COMPLETE_ENROLLMENT = BASE_PATH + "/admin/enrollments/" + "{id}/complete";
    public static final String COMPLETE_GROUPS_ENROLLMENT = BASE_PATH + "/admin/enrollments/group/complete";


    //STUDENT TASKS endpoints
    public static final String STUDENT_TASKS = BASE_PATH + "/my-instructor-tasks";
    public static final String COMPLETE_TASK = BASE_PATH + "/my-instructor-tasks/" + "{id}/complete";


    //STUDENT RECORDINGS endpoints
    public static final String GET_RECORDINGS = BASE_PATH + "/recordings/my";
    public static final String GET_SINGLE_RECORDING = BASE_PATH + "/recordings/" + "{id}";
    public static final String GET_GROUP_RECORDINGS = BASE_PATH + "/recordings/group/" + "{groupId}";
    public static final String CREATE_RECORDING = BASE_PATH + "/recordings/";
    public static final String MODIFY_RECORDING = BASE_PATH + "/recordings/" + "{id}";

    //ADMIN RECORDINGS endpoints
    public static final String ADMIN_RECORDINGS = BASE_PATH + "/recordings/admin";

    //GRADUATES endpoints
    public static final String GRADUATES = BASE_PATH + "/graduates";





    public static final String REFRESH = BASE_PATH + "/auth/refresh";




    //ANNOUNCEMENTS endpoints
    public static final String ANNOUNCEMENTS = BASE_PATH + "/announcements";

    //ADMIN ANNOUNCEMENTS endpoints
    public static final String ADMIN_ANNOUNCEMENTS = BASE_PATH + "/admin/announcements";
    public static final String CREATE_ANNOUNCEMENT = BASE_PATH + "/admin/announcements";
    public static final String MODIFY_ANNOUNCEMENT = BASE_PATH + "/admin/announcements/" + "{id}";

    private Routes() {
    }
}
