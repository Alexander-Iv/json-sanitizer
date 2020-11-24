package alexander.ivanov.jsonsanitizer.json.entity.mapper;

import alexander.ivanov.jsonsanitizer.json.entity.BooleanJsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import com.fasterxml.jackson.core.JsonToken;

public class BooleanJsonMapper extends AbstractJsonMapper<Boolean> {
    public BooleanJsonMapper(JsonToken jsonToken, String name, String value) {
        super(jsonToken, name, value);
    }

    @Override
    boolean jsonTokenValidate() {
        return getJsonToken() == JsonToken.VALUE_TRUE || getJsonToken() == JsonToken.VALUE_FALSE;
    }

    @Override
    JsonEntity<Boolean> initEntity(String name, Boolean value) {
        return new BooleanJsonEntity(name, value);
    }

    @Override
    Boolean mapValue(String value) {
        return Boolean.parseBoolean(value);
    }
}
