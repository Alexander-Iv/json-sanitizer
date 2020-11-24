package alexander.ivanov.jsonsanitizer.json.entity.mapper;

import alexander.ivanov.jsonsanitizer.json.entity.FloatJsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import com.fasterxml.jackson.core.JsonToken;

public class FloatJsonMapper extends AbstractJsonMapper<Float> {
    public FloatJsonMapper(JsonToken jsonToken, String name, String value) {
        super(jsonToken, name, value);
    }

    @Override
    boolean jsonTokenValidate() {
        return getJsonToken() == JsonToken.VALUE_NUMBER_FLOAT;
    }

    @Override
    JsonEntity<Float> initEntity(String name, Float value) {
        return new FloatJsonEntity(name, value);
    }

    @Override
    Float mapValue(String value) {
        return Float.parseFloat(value);
    }
}
