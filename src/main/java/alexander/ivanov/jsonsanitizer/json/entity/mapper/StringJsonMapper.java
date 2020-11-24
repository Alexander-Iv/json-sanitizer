package alexander.ivanov.jsonsanitizer.json.entity.mapper;

import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.StringJsonEntity;
import com.fasterxml.jackson.core.JsonToken;

public class StringJsonMapper extends AbstractJsonMapper<String> {
    public StringJsonMapper(JsonToken jsonToken, String name, String value) {
        super(jsonToken, name, value);
    }

    @Override
    boolean jsonTokenValidate() {
        return getJsonToken() == JsonToken.VALUE_STRING;
    }

    @Override
    JsonEntity<String> initEntity(String name, String value) {
        return new StringJsonEntity(name, value);
    }

    @Override
    String mapValue(String value) {
        return value;
    }
}
