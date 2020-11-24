package alexander.ivanov.jsonsanitizer.json;

import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonEntityUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonEntityUtil.class);

    public static Map<String, JsonEntity<?>> toMap(List<JsonEntity<?>> jsonEntities) {
        Map<String, JsonEntity<?>> mappedEntities = jsonEntities.stream()
                .collect(Collectors.toMap(JsonEntity::getName, jsonEntity -> jsonEntity));

        mappedEntities.forEach((s, jsonEntity) -> logger.trace("s = {}, jsonEntity = {}", s, jsonEntity));

        return mappedEntities;
    }
}
