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

class FloatJsonEntityTest {
    private static final Logger logger = LoggerFactory.getLogger(BooleanJsonEntityTest.class);

    @Test
    void floatFieldTest() {
        String json = "{\"float\":0.11111}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        List<JsonEntity<?>> actualJsonEntityFields = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntityFields = {}", actualJsonEntityFields);

        List<JsonEntity<?>> expectedJsonEntityFields = Collections.singletonList(new FloatJsonEntity("float", 0.11111F));
        logger.trace("expectedJsonEntityFields = {}", expectedJsonEntityFields);

        Assertions.assertIterableEquals(actualJsonEntityFields, expectedJsonEntityFields);
    }

    @Test
    void incorrectJsonFloatSeparatorTest() {
        String json = "{\"float\":0,11111}";

        Assertions.assertThrows(JsonEntityParserException.class, () -> new JsonEntityParserImpl(json).getEntityFields(),
                "Unexpected character ('1' (code 49)): was expecting double-quote to start field name");
    }
}
