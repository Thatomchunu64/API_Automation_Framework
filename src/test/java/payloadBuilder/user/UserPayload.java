package payloadBuilder.user;


import org.json.simple.JSONObject;

public class UserPayload {

    public static JSONObject updateUserProfilePayload(String firstname, String lastname, String profileImage, String profilePic, String aboutMe) {

        JSONObject updateUserProfile = new JSONObject();
        updateUserProfile.put("firstname", firstname);
        updateUserProfile.put("lastname", lastname);
        updateUserProfile.put("profilePicture",profilePic);
        updateUserProfile.put("ProfileImage",profileImage);
        updateUserProfile.put("AboutMe",aboutMe);
        return updateUserProfile;

    }

    //Missing Upload profile image payload

    public static JSONObject updateUserPasswordPayload(String currentPassword, String newPassword) {

        JSONObject updateUserPassword = new JSONObject();
        updateUserPassword.put("currentPassword", currentPassword);
        updateUserPassword.put("newPassword", newPassword);
        updateUserPassword.put("confirmPassword", newPassword);

        return updateUserPassword;

    }



}
