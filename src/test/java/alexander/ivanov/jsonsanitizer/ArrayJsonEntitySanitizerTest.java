package alexander.ivanov.jsonsanitizer;

import alexander.ivanov.jsonsanitizer.model.SimpleArrayData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ArrayJsonEntitySanitizerTest {
    private static final Logger logger = LoggerFactory.getLogger(ArrayJsonEntitySanitizerTest.class);

    @Test
    void arrayOfStringTest() {
        String json = "\n{\"arr\": [\"qqq\",\"www\",\"eee\",\"rrr\",\"ttt\", \"yyy\"]}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleArrayData.class);
        SimpleArrayData actualData = jsonEntitySanitizer.sanitize();

        SimpleArrayData expectedData = new SimpleArrayData(Arrays.asList("qqq","www","eee","rrr","ttt","yyy"));

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void arrayOfFloatTest() {
        String json = "\n{\"arr\": [1.1,2.2,3.3,4.4,5.5]}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleArrayData.class);
        SimpleArrayData actualData = jsonEntitySanitizer.sanitize();

        SimpleArrayData expectedData = new SimpleArrayData(Arrays.asList(1.1F, 2.2F, 3.3F, 4.4F, 5.5F));

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void arrayOfIntegerTest() {
        String json = "\n{\"arr\": [1,2,3,4,5]}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleArrayData.class);
        SimpleArrayData actualData = jsonEntitySanitizer.sanitize();

        SimpleArrayData expectedData = new SimpleArrayData(Arrays.asList(1,2,3,4,5));

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }
}
