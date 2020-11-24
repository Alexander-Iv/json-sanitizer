package alexander.ivanov.jsonsanitizer;

import alexander.ivanov.jsonsanitizer.classinitializer.TargetClassInitializerException;
import alexander.ivanov.jsonsanitizer.model.FullData;
import alexander.ivanov.jsonsanitizer.model.SimpleData;
import alexander.ivanov.jsonsanitizer.type.RusPhoneType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

class FullJsonEntitySanitizerTest {
    private static final Logger logger = LoggerFactory.getLogger(FullJsonEntitySanitizerTest.class);

    @Test
    void incorrect_FooFieldIsStringTest() {
        String json = "\n{" +
                "\n\t\"foo\": \"abc\", " +
                "\n\t\"bar\": \"asd\", " +
                "\n\t\"baz\": \"8 (950) 288-56-23\"" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleData.class);
        Assertions.assertThrows(TargetClassInitializerException.class, jsonEntitySanitizer::sanitize);
    }

    @Test
    void incorrect_BarFieldIsStringWithDigitTest() {
        String json = "\n{" +
                "\n\t\"foo\": 111, " +
                "\n\t\"bar\": \"123абв\", " +
                "\n\t\"baz\": \"8 (950) 288-56-23\"" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleData.class);
        Assertions.assertThrows(TargetClassInitializerException.class, jsonEntitySanitizer::sanitize);
    }

    @Test
    void incorrect_BazFieldIsStringWithDigitTest() {
        String json = "\n{" +
                "\n\t\"foo\": 111, " +
                "\n\t\"bar\": \"asd\", " +
                "\n\t\"baz\": \"260557\"" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleData.class);
        Assertions.assertThrows(TargetClassInitializerException.class, jsonEntitySanitizer::sanitize);
    }

    @Test
    void correct_SimpleDataTest() {
        String json = "\n{" +
                "\n\t\"foo\": 111, " +
                "\n\t\"bar\": \"asd\", " +
                "\n\t\"baz\": \"8 (950) 288-56-23\"" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleData.class);
        SimpleData actualData = jsonEntitySanitizer.sanitize();

        SimpleData expectedData = new SimpleData(111, "asd", new RusPhoneType.RusPhone("79502885623"));

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void fullDataTest() {
        String json = "\n{" +
                "\n\t\"foo\": 111, " +
                "\n\t\"foo2\": \"123\", " +
                "\n\t\"bar\": \"asd\", " +
                "\n\t\"baz\": \"8 (950) 288-56-23\", " +
                "\n\t\"baz2\": 12345, " +
                "\n\t\"fl\": 123456.789, " +
                "\n\t\"arr\": [1,2,3,4,5], " +
                "\n\t\"struct\": {\"a\":1, \"b\":2, \"c\":3, \"d\":4, \"e\":5}" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, FullData.class);
        FullData actualData = jsonEntitySanitizer.sanitize();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);

        FullData expectedData = new FullData(
                111,
                123,
                "asd",
                new RusPhoneType.RusPhone("79502885623"),
                //будет пропущен, т.к. не совпадают целевые типы: RusPhoneType.RusPhone != FullData.Phone
                //по умолчанию RusPhoneField использует реализацию RusPhoneType, использующий тип RusPhoneType.RusPhone
                null,
                123456.789f,
                Arrays.asList(1,2,3,4,5),
                map
        );

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }
}
