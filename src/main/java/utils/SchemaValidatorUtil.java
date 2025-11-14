package utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import java.io.File;

public class SchemaValidatorUtil {

    public static JsonSchemaValidator matchesSchema(String schemaPath) {
        return JsonSchemaValidator.matchesJsonSchema(
                new File("src/test/resources/" + schemaPath)
        );
    }
}
