package alexander.ivanov.jsonsanitizer.json.entity.mapper;

import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.NullJsonEntity;
import com.fasterxml.jackson.core.JsonToken;

public class NullJsonMapper extends AbstractJsonMapper<String> {
    public NullJsonMapper(JsonToken jsonToken, String name, String value) {
        super(jsonToken, name, value);
    }

    @Override
    boolean jsonTokenValidate() {
        return getJsonToken() == JsonToken.VALUE_NULL;
    }

    @Override
    JsonEntity<String> initEntity(String name, String value) {
        return new NullJsonEntity(name, value);
    }

    @Override
    String mapValue(String value) {
        return "null";
    }
}
