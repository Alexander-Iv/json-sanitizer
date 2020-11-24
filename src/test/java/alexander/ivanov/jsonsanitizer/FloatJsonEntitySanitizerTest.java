package alexander.ivanov.jsonsanitizer;

import alexander.ivanov.jsonsanitizer.classinitializer.TargetClassInitializerException;
import alexander.ivanov.jsonsanitizer.model.SimpleFloatData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FloatJsonEntitySanitizerTest {
    private static final Logger logger = LoggerFactory.getLogger(FloatJsonEntitySanitizerTest.class);

    @Test
    void floatAsStringTest() {
        String json = "\n{\"foo\": \"123.456\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleFloatData.class);
        SimpleFloatData actualData = jsonEntitySanitizer.sanitize();

        SimpleFloatData expectedData = new SimpleFloatData(123.456F);

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void floatAsIncorrectFloatTest() {
        String json = "\n{\"foo\": \"123.123абв\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleFloatData.class);
        Assertions.assertThrows(TargetClassInitializerException.class, jsonEntitySanitizer::sanitize);
    }

    @Test
    void floatAsFloatTest() {
        String json = "\n{\"foo\": 123.123}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleFloatData.class);
        SimpleFloatData actualData = jsonEntitySanitizer.sanitize();

        SimpleFloatData expectedData = new SimpleFloatData(123.123F);

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }
}
