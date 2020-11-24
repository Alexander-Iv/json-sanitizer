package alexander.ivanov.jsonsanitizer.json;

import alexander.ivanov.jsonsanitizer.json.entity.BaseJsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.JsonEntityException;

import java.util.List;

public class JsonEntityParserImpl implements JsonEntityParser {
    private final JsonEntity<?> rootEntity;

    public JsonEntityParserImpl(String json) {
        try {
            rootEntity = new BaseJsonEntity(json);
        } catch (JsonEntityException e) {
            throw new JsonEntityParserException(e.getMessage());
        }
    }

    @Override
    public List<JsonEntity<?>> getEntityFields() {
        return (List<JsonEntity<?>>)rootEntity.getValue();
    }

    @Override
    public JsonEntity<?> getRootEntity() {
        return rootEntity;
    }
}
