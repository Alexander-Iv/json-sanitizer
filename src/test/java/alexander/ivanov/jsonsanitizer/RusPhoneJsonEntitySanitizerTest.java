package alexander.ivanov.jsonsanitizer;

import alexander.ivanov.jsonsanitizer.classinitializer.TargetClassInitializerException;
import alexander.ivanov.jsonsanitizer.model.SimpleRusPhoneData;
import alexander.ivanov.jsonsanitizer.type.RusPhoneType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RusPhoneJsonEntitySanitizerTest {
    private static final Logger logger = LoggerFactory.getLogger(RusPhoneJsonEntitySanitizerTest.class);

    @Test
    void rusPhoneStartsWithEightTest() {
        String json = "\n{\"rusPhone\": \"8 (950) 288-56-23\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleRusPhoneData.class);
        SimpleRusPhoneData actualData = jsonEntitySanitizer.sanitize();

        SimpleRusPhoneData expectedData = new SimpleRusPhoneData(new RusPhoneType.RusPhone("79502885623"));

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void rusPhoneStartsWithEightWithoutSeparatorsTest() {
        String json = "\n{\"rusPhone\": \"8(950)2885623\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleRusPhoneData.class);
        SimpleRusPhoneData actualData = jsonEntitySanitizer.sanitize();

        SimpleRusPhoneData expectedData = new SimpleRusPhoneData(new RusPhoneType.RusPhone("79502885623"));

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void rusPhoneStartsWithSevenTest() {
        String json = "\n{\"rusPhone\": \"7 (950) 288-56-23\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleRusPhoneData.class);
        SimpleRusPhoneData actualData = jsonEntitySanitizer.sanitize();

        SimpleRusPhoneData expectedData = new SimpleRusPhoneData(new RusPhoneType.RusPhone("79502885623"));

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void rusPhoneStartsWithPlusSevenTest() {
        String json = "\n{\"rusPhone\": \"+7 (950) 288-56-23\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleRusPhoneData.class);
        SimpleRusPhoneData actualData = jsonEntitySanitizer.sanitize();

        SimpleRusPhoneData expectedData = new SimpleRusPhoneData(new RusPhoneType.RusPhone("79502885623"));

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void rusPhoneWithoutCountryCodeTest() {
        String json = "\n{\"rusPhone\": \"(950) 288-56-23\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleRusPhoneData.class);
        Assertions.assertThrows(TargetClassInitializerException.class, jsonEntitySanitizer::sanitize);
    }

    @Test
    void incorrectPhoneTest() {
        String json = "\n{\"rusPhone\": \"260557\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleRusPhoneData.class);
        Assertions.assertThrows(TargetClassInitializerException.class, jsonEntitySanitizer::sanitize);
    }
}
