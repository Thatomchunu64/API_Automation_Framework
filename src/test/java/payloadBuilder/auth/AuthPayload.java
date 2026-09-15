package payloadBuilder.auth;

import org.json.simple.JSONObject;

public class AuthPayload {

    public static JSONObject loginUserPayload(String email, String password) {
        JSONObject login = new JSONObject();
        login.put("email", email);
        login.put("password", password);
        return login;
    }

    public static JSONObject registerUserPayload(String firstName, String lastName, String email, String password, String groupId) {
        JSONObject register = new JSONObject();
        register.put("firstName", firstName);
        register.put("lastName", lastName);
        register.put("email", email);
        register.put("password", password);
        register.put("confirmPassword", password);
        register.put("groupId", groupId);
        return register;
    }

    public static JSONObject forgotPasswordPayload(String email) {
        JSONObject forgotPassword = new JSONObject();
        forgotPassword.put("email", email);
        return forgotPassword;
    }

    public static JSONObject resetPasswordPayload(String token, String password) {
        JSONObject resetPassword = new JSONObject();
        resetPassword.put("token", token);
        resetPassword.put("newPassword", password);
        resetPassword.put("confirmPassword", password);
        return resetPassword;
    }

    public static JSONObject verifyTokenPayload(String token) {
        JSONObject verifyToken = new JSONObject();
        verifyToken.put("token", token);
        return verifyToken;
    }

  /*  public static JSONObject refreshToken(String refreshToken) {
        JSONObject payload = new JSONObject();
        payload.put("refreshToken", refreshToken);
        return payload;
    }*/
}
