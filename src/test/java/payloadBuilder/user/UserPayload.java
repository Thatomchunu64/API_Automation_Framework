package payloadBuilder.user;


import org.json.simple.JSONObject;

public class UserPayload {

    public static JSONObject updateUserProfilePayload(
            String firstName,
            String lastName,
            String profilePicture,
            String profileImage,
            String aboutMe) {

        JSONObject updateUserProfile = new JSONObject();
        updateUserProfile.put("firstName", firstName);
        updateUserProfile.put("lastName", lastName);
        updateUserProfile.put("profilePicture",profilePicture);
        updateUserProfile.put("profileImage",profileImage);
        updateUserProfile.put("aboutMe",aboutMe);
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
