package alexander.ivanov.jsonsanitizer.json.entity;

import alexander.ivanov.jsonsanitizer.json.JsonEntityParser;
import alexander.ivanov.jsonsanitizer.json.JsonEntityParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BaseJsonEntityTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseJsonEntityTest.class);

    @Test
    void rootObjectJsonEntityTest() {
        String json = "\n{" +
                "\n\t\"foo\": \"abc\", " +
                "\n\t\"bar\": \"asd\", " +
                "\n\t\"baz\": \"8 (950) 288-56-23\", " +
                "\n\t\"float\": 1.1, "+
                "\n\t\"aaa\": 111, "+
                "\n\t\"bbb\": \"\", "+
                "\n\t\"bool\": false, "+
                "\n\t\"empty\": null, "+
                "\n\t\"arr\": [\"1\",\"2\",\"3\"], "+
                "\n\t\"obj\": {" +
                    "\n\t\t\"a\":1," +
                    "\n\t\t\"qwerty\": {\"bb\":321}," +
                    "\n\t\t\"b\":2" +
                "\n\t}" +
                "\n}";

        logger.trace("json = {}", json);

        List<JsonEntity<?>> actualJsonEntityFields = new JsonEntityParserImpl(json).getEntityFields();
        logger.trace("actualJsonEntityFields = {}", actualJsonEntityFields);
    }

    @Test
    void baseEmptyObjectJsonTest() {
        String json = "{}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        JsonEntity<?> rootEntity = jsonEntityParser.getRootEntity();

        Assertions.assertEquals("", rootEntity.getName());
        Assertions.assertEquals(Collections.EMPTY_LIST, rootEntity.getValue());
    }

    @Test
    void jsonWithPrimitiveFieldsTest() {
        String json = "{" +
                "\"bool-field\": true," +
                "\"integer-field\": 12345," +
                "\"float-field\": 12345.12345," +
                "\"null-field\": null," +
                "\"string-field\": \"some text\"" +
                "}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        List<JsonEntity<?>> actualJsonEntityFields = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntityFields = {}", actualJsonEntityFields);

        List<JsonEntity<?>> expectedJsonEntityFields = Arrays.asList(
                new BooleanJsonEntity("bool-field", true),
                new IntegerJsonEntity("integer-field", 12345),
                new FloatJsonEntity("float-field", 12345.12345F),
                new NullJsonEntity("null-field", "null"),
                new StringJsonEntity("string-field", "some text")
        );
        logger.trace("expectedJsonEntityFields = {}", expectedJsonEntityFields);

        Assertions.assertIterableEquals(expectedJsonEntityFields, actualJsonEntityFields);
    }

    @Test
    void jsonWithNestedObjectTest() {
        String json = "{" +
                "\"obj1\": {" +
                    "\"a1\": 111," +
                    "\"obj1-obj2\": {" +
                        "\"b1\": 222" +
                    "}" +
                "}" +
        "}";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        String actualJsonEntity = jsonEntityParser.getEntityFields().toString();
        logger.trace("actualJsonEntity = {}", actualJsonEntity);

        String expectedNestedJsonEntity = "[[" +
                "obj1:JsonEntity{name='a1', value=111, type=IntegerJsonEntity}, " +
                "obj1:[" +
                    "obj1-obj2:JsonEntity{name='b1', value=222, type=IntegerJsonEntity}]" +
                "]]";
        logger.trace("expectedNestedJsonEntity = {}", expectedNestedJsonEntity);

        Assertions.assertEquals(expectedNestedJsonEntity, actualJsonEntity);
    }

    @Test
    void baseEmptyArrayJsonTest() {
        String json = "[]";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        JsonEntity<?> rootEntity = jsonEntityParser.getRootEntity();

        Assertions.assertEquals("", rootEntity.getName());
        Assertions.assertEquals(Collections.EMPTY_LIST, rootEntity.getValue());
    }

    @Test
    void jsonWithNestedArrayTest() {
        String json = "[1,2,3]";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        List<JsonEntity<?>> actualJsonEntity = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntity = {}", actualJsonEntity);

        List<JsonEntity<?>> expectedNestedJsonEntity = Arrays.asList(
                new IntegerJsonEntity("null", 1),
                new IntegerJsonEntity("null", 2),
                new IntegerJsonEntity("null", 3)
        );
        logger.trace("expectedNestedJsonEntity = {}", expectedNestedJsonEntity);

        Assertions.assertEquals(expectedNestedJsonEntity.toString(), actualJsonEntity.toString());
    }

    @Test
    void jsonWithComplexArrayTest() {
        String json = "[[1,2,3], [4,5,\"abc\"], true]";

        JsonEntityParser jsonEntityParser = new JsonEntityParserImpl(json);
        JsonEntity<?> jsonEntity = jsonEntityParser.getRootEntity();
        logger.trace("jsonEntity = {}", jsonEntity);

        List<JsonEntity<?>> actualJsonEntityFields = jsonEntityParser.getEntityFields();
        logger.trace("actualJsonEntity = {}", actualJsonEntityFields);

        List<JsonEntity<?>> expectedNestedJsonEntityFields = Arrays.asList(
                new IntegerJsonEntity("null", 1),
                new IntegerJsonEntity("null", 2),
                new IntegerJsonEntity("null", 3),
                new IntegerJsonEntity("null", 4),
                new IntegerJsonEntity("null", 5),
                new StringJsonEntity("null", "abc"),
                new BooleanJsonEntity("null", true)
        );
        logger.trace("expectedNestedJsonEntityFields = {}", expectedNestedJsonEntityFields);

        Assertions.assertEquals(expectedNestedJsonEntityFields.toString(), actualJsonEntityFields.toString());
    }
}
