package alexander.ivanov.jsonsanitizer.json.entity;

import alexander.ivanov.jsonsanitizer.json.JsonEntityParser;
import alexander.ivanov.jsonsanitizer.json.JsonEntityParserException;
import alexander.ivanov.jsonsanitizer.json.JsonEntityParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

class IntegerJsonEntityTest {
    private static final Logger logger = LoggerFactory.getLogger(BooleanJsonEntityTest.class);

    @Test
    void integerFieldTest() {
        String json = "{\"integer\":123}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        List<JsonEntity<?>> actualJsonEntityFields = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntityFields = {}", actualJsonEntityFields);

        List<JsonEntity<?>> expectedJsonEntityFields = Collections.singletonList(new IntegerJsonEntity("integer", 123));
        logger.trace("expectedJsonEntityFields = {}", expectedJsonEntityFields);

        Assertions.assertIterableEquals(actualJsonEntityFields, expectedJsonEntityFields);
    }

    @Test
    void integerFieldWithLeadingZeroTest() {
        String json = "{\"integer\":0123}";

        Assertions.assertThrows(JsonEntityParserException.class, () -> new JsonEntityParserImpl(json).getEntityFields(),
                "Invalid numeric value: Leading zeroes not allowed");
    }
}
