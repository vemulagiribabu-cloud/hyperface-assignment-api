package endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserEndpoints {

    private static final String USERS = "/users";

    public static Response createUser(String body) {
        return RestAssured.given()
                .body(body)
                .post(USERS);
    }

    public static Response getUser(int id) {
        return RestAssured.get(USERS + "/" + id);
    }

    public static Response updateUser(int id, String body) {
        return RestAssured.given()
                .body(body)
                .put(USERS + "/" + id);
    }

    public static Response deleteUser(int id) {
        return RestAssured.delete(USERS + "/" + id);
    }
}
