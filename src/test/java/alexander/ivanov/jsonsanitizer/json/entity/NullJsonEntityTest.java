package alexander.ivanov.jsonsanitizer.json.entity;

import alexander.ivanov.jsonsanitizer.json.JsonEntityParser;
import alexander.ivanov.jsonsanitizer.json.JsonEntityParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

class NullJsonEntityTest {
    private static final Logger logger = LoggerFactory.getLogger(BooleanJsonEntityTest.class);

    @Test
    void nullFieldTest() {
        String json = "{\"null-field-name\":null}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        List<JsonEntity<?>> actualJsonEntityFields = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntityFields = {}", actualJsonEntityFields);

        List<JsonEntity<?>> expectedJsonEntityFields = Collections.singletonList(new NullJsonEntity("null-field-name", "null"));
        logger.trace("expectedJsonEntityFields = {}", expectedJsonEntityFields);

        Assertions.assertIterableEquals(actualJsonEntityFields, expectedJsonEntityFields);
    }
}
