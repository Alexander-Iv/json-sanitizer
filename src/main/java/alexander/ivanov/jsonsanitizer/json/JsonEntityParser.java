package alexander.ivanov.jsonsanitizer.json;

import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;

import java.util.List;

public interface JsonEntityParser {
    List<JsonEntity<?>> getEntityFields();
    JsonEntity<?> getRootEntity();
}
