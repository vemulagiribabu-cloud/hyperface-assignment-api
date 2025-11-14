package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import endpoints.UserEndpoints;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payloads.UserPayload;
import utils.ReportManager;
import utils.SchemaValidatorUtil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserCrudTest extends BaseTest {

    private ExtentReports extent;
    private ExtentTest test;
    private int createdUserId;

    @BeforeClass
    public void setup() {
        // attach request spec at RestAssured level
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;

        extent = ReportManager.getExtentReports("reports/API-TestReport.html");
    }

    @Test(priority = 1)
    public void tc01_createUser() {
        test = extent.createTest("TC01 - Create User");

        // Arrange
        var payload = UserPayload.createUserJson("John Doe", "jdoe", "jdoe@example.com");

        // Act
        Response resp = UserEndpoints.createUser(payload.toString());
        test.info("Request body: " + payload.toString());
        test.info("Response: " + resp.asString());

        // Assert
        Assert.assertTrue(resp.statusCode() == 201 || resp.statusCode() == 200, "Unexpected status");
        // JSONPlaceholder returns id 11 for created
        createdUserId = resp.jsonPath().getInt("id");
        Assert.assertTrue(createdUserId > 0, "id should be present");

        extent.flush();
    }

    @Test(priority = 2)
    public void tc02_getUser() {
        test = extent.createTest("TC02 - Get User");

        int idToGet = 1;  // JSONPlaceholder stable user

        Response resp = UserEndpoints.getUser(idToGet);
        test.info("GET /users/" + idToGet + " -> " + resp.asString());

        Assert.assertEquals(resp.statusCode(), 200);

        resp.then().assertThat()
            .body(SchemaValidatorUtil.matchesSchema("schemas/user_schema.json"));
    }

    @Test(priority = 3)
    public void tc03_updateUser() {
        test = extent.createTest("TC03 - Update User");

        int idToUpdate = 1; // Always use stable user
        var updatePayload = UserPayload.updateUserJson("John Doe Updated");

        Response resp = UserEndpoints.updateUser(idToUpdate, updatePayload.toString());
        test.info("PUT response: " + resp.asString());

        Assert.assertTrue(resp.statusCode() == 200 || resp.statusCode() == 201);

        // JSONPlaceholder sometimes returns HTML
        if (resp.contentType().contains("application/json")) {
            Assert.assertTrue(resp.jsonPath().getString("name").contains("John"));
        }
    }

    @Test(priority = 4)
    public void tc04_deleteUser() {
        test = extent.createTest("TC04 - Delete User");

        int idToDelete = createdUserId > 0 ? createdUserId : 1;
        Response resp = UserEndpoints.deleteUser(idToDelete);
        test.info("DELETE response code: " + resp.statusCode());

        // JSONPlaceholder returns 200 or 204 but doesn't actually remove persistent data
        Assert.assertTrue(resp.statusCode() == 200 || resp.statusCode() == 204);
    }

    @AfterClass
    public void tearDown() {
        if (extent != null) extent.flush();
    }
}
