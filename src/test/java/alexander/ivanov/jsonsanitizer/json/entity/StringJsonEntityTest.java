package alexander.ivanov.jsonsanitizer.json.entity;

import alexander.ivanov.jsonsanitizer.json.JsonEntityParser;
import alexander.ivanov.jsonsanitizer.json.JsonEntityParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

class StringJsonEntityTest {
    private static final Logger logger = LoggerFactory.getLogger(BooleanJsonEntityTest.class);

    @Test
    void stringFieldTest() {
        String json = "{\"string\":\"some text\"}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        List<JsonEntity<?>> actualJsonEntityFields = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntityFields = {}", actualJsonEntityFields);

        List<JsonEntity<?>> expectedJsonEntityFields = Collections.singletonList(new StringJsonEntity("string", "some text"));
        logger.trace("expectedJsonEntityFields = {}", expectedJsonEntityFields);

        Assertions.assertIterableEquals(actualJsonEntityFields, expectedJsonEntityFields);
    }
}
