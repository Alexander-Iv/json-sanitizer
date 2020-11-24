package alexander.ivanov.jsonsanitizer.json.entity;

import alexander.ivanov.jsonsanitizer.json.JsonEntityParser;
import alexander.ivanov.jsonsanitizer.json.JsonEntityParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

class BooleanJsonEntityTest {
    private static final Logger logger = LoggerFactory.getLogger(BooleanJsonEntityTest.class);

    @Test
    void booleanTrueJsonFieldTest() {
        String json = "{\"bool\":true}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        List<JsonEntity<?>> actualJsonEntityFields = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntityFields = {}", actualJsonEntityFields);

        List<JsonEntity<?>> expectedJsonEntityFields = Collections.singletonList(new BooleanJsonEntity("bool", true));
        logger.trace("expectedJsonEntityFields = {}", expectedJsonEntityFields);

        Assertions.assertIterableEquals(actualJsonEntityFields, expectedJsonEntityFields);
    }

    @Test
    void booleanFalseJsonFieldTest() {
        String json = "{\"bool\":false}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        List<JsonEntity<?>> actualJsonEntityFields = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntityFields = {}", actualJsonEntityFields);

        List<JsonEntity<?>> expectedJsonEntityFields = Collections.singletonList(new BooleanJsonEntity("bool", false));
        logger.trace("expectedJsonEntityFields = {}", expectedJsonEntityFields);

        Assertions.assertIterableEquals(actualJsonEntityFields, expectedJsonEntityFields);
    }
}
