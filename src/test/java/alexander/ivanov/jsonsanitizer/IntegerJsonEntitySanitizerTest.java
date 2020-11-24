package alexander.ivanov.jsonsanitizer;

import alexander.ivanov.jsonsanitizer.classinitializer.TargetClassInitializerException;
import alexander.ivanov.jsonsanitizer.model.SimpleIntegerData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerJsonEntitySanitizerTest {
    private static final Logger logger = LoggerFactory.getLogger(IntegerJsonEntitySanitizerTest.class);

    @Test
    void integerAsStringTest() {
        String json = "\n{\"foo\": \"123\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleIntegerData.class);
        SimpleIntegerData actualData = jsonEntitySanitizer.sanitize();

        SimpleIntegerData expectedData = new SimpleIntegerData(123);

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void integerAsIncorrectIntegerTest() {
        String json = "\n{\"foo\": \"123абв\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleIntegerData.class);
        Assertions.assertThrows(TargetClassInitializerException.class, jsonEntitySanitizer::sanitize);
    }

    @Test
    void integerAsIntegerTest() {
        String json = "\n{\"foo\": 123}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleIntegerData.class);
        SimpleIntegerData actualData = jsonEntitySanitizer.sanitize();

        SimpleIntegerData expectedData = new SimpleIntegerData(123);

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }
}
