package alexander.ivanov.jsonsanitizer;

import alexander.ivanov.jsonsanitizer.classinitializer.TargetClassInitializerException;
import alexander.ivanov.jsonsanitizer.model.SimpleStringData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringJsonEntitySanitizerTest {
    private static final Logger logger = LoggerFactory.getLogger(StringJsonEntitySanitizerTest.class);

    @Test
    void incorrectStringTest() {
        String json = "\n{\"foo\": \"123абв\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStringData.class);
        Assertions.assertThrows(TargetClassInitializerException.class, jsonEntitySanitizer::sanitize);
    }

    @Test
    void enAlphabeticStringTest() {
        String json = "\n{\"foo\": \"abcdefghijklmnopqrstuvwxyz\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStringData.class);
        SimpleStringData actualData = jsonEntitySanitizer.sanitize();

        SimpleStringData expectedData = new SimpleStringData("abcdefghijklmnopqrstuvwxyz");

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void enAlphabeticUpperCaseStringTest() {
        String json = "\n{\"foo\": \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStringData.class);
        SimpleStringData actualData = jsonEntitySanitizer.sanitize();

        SimpleStringData expectedData = new SimpleStringData("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void ruAlphabeticStringTest() {
        String json = "\n{\"foo\": \"абвгдеёжзийклмнопрстуфхцчшщъыьэюя\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStringData.class);
        SimpleStringData actualData = jsonEntitySanitizer.sanitize();

        SimpleStringData expectedData = new SimpleStringData("абвгдеёжзийклмнопрстуфхцчшщъыьэюя");

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void ruAlphabeticUpperCaseStringTest() {
        String json = "\n{\"foo\": \"АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStringData.class);
        SimpleStringData actualData = jsonEntitySanitizer.sanitize();

        SimpleStringData expectedData = new SimpleStringData("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void bothAlphabeticStringTest() {
        String json = "\n{\"foo\": \"abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStringData.class);
        SimpleStringData actualData = jsonEntitySanitizer.sanitize();

        SimpleStringData expectedData = new SimpleStringData("abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя");

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void bothAlphabeticUpperCaseStringTest() {
        String json = "\n{\"foo\": \"ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ\"}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStringData.class);
        SimpleStringData actualData = jsonEntitySanitizer.sanitize();

        SimpleStringData expectedData = new SimpleStringData("ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }
}
