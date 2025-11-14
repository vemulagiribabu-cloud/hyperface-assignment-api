package payloads;

import org.json.JSONObject;

public class UserPayload {

    public static JSONObject createUserJson(String name, String username, String email) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("username", username);
        obj.put("email", email);
        return obj;
    }

    public static JSONObject updateUserJson(String updatedName) {
        JSONObject obj = new JSONObject();
        obj.put("name", updatedName);
        return obj;
    }
}
