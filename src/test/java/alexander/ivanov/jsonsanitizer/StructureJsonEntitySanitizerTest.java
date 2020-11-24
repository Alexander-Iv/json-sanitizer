package alexander.ivanov.jsonsanitizer;

import alexander.ivanov.jsonsanitizer.model.SimpleStructureData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class StructureJsonEntitySanitizerTest {
    private static final Logger logger = LoggerFactory.getLogger(StructureJsonEntitySanitizerTest.class);

    @Test
    void structureOfStringValuesTest() {
        String json = "\n{" +
                "\n\t\"structure\": {\"a\":\"q\", \"b\":\"w\", \"c\":\"e\", \"d\":\"r\", \"e\":\"t\", \"f\":\"y\"}" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStructureData.class);
        SimpleStructureData actualData = jsonEntitySanitizer.sanitize();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a", "q");
        map.put("b", "w");
        map.put("c", "e");
        map.put("d", "r");
        map.put("e", "t");
        map.put("f", "y");

        SimpleStructureData expectedData = new SimpleStructureData(map);

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void structureOfIntegerValuesTest() {
        String json = "\n{" +
                "\n\t\"structure\": {\"a\":1, \"b\":2, \"c\":3, \"d\":4, \"e\":5}" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStructureData.class);
        SimpleStructureData actualData = jsonEntitySanitizer.sanitize();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);

        SimpleStructureData expectedData = new SimpleStructureData(map);

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void structureOfFloatValuesTest() {
        String json = "\n{" +
                "\n\t\"structure\": {\"a\":1.1, \"b\":2.2, \"c\":3.3, \"d\":4.4, \"e\":5.5}" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStructureData.class);
        SimpleStructureData actualData = jsonEntitySanitizer.sanitize();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a", 1.1F);
        map.put("b", 2.2F);
        map.put("c", 3.3F);
        map.put("d", 4.4F);
        map.put("e", 5.5F);

        SimpleStructureData expectedData = new SimpleStructureData(map);

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }

    @Test
    void structureOfVariousValuesTest() {
        String json = "\n{" +
                "\n\t\"structure\": {\"a\":\"qqq\", \"b\":true, \"c\":1.1, \"d\":123, \"e\":\"abc\", \"f\":false}" +
                "\n}";
        logger.info("json = {}", json);

        JsonEntitySanitizer jsonEntitySanitizer = new JsonEntitySanitizerImpl(json, SimpleStructureData.class);
        SimpleStructureData actualData = jsonEntitySanitizer.sanitize();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("a", "qqq");
        map.put("b", true);
        map.put("c", 1.1f);
        map.put("d", 123);
        map.put("e", "abc");
        map.put("f", false);

        SimpleStructureData expectedData = new SimpleStructureData(map);

        logger.info("actualData = {}", actualData);
        logger.info("expectedData = {}", expectedData);

        Assertions.assertEquals(expectedData, actualData);
    }
}
