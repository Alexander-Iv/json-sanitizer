package alexander.ivanov.jsonsanitizer.json.entity.mapper;

import alexander.ivanov.jsonsanitizer.json.entity.IntegerJsonEntity;
import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import com.fasterxml.jackson.core.JsonToken;

public class IntegerJsonMapper extends AbstractJsonMapper<Integer> {
    public IntegerJsonMapper(JsonToken jsonToken, String name, String value) {
        super(jsonToken, name, value);
    }

    @Override
    boolean jsonTokenValidate() {
        return getJsonToken() == JsonToken.VALUE_NUMBER_INT;
    }

    @Override
    JsonEntity<Integer> initEntity(String name, Integer value) {
        return new IntegerJsonEntity(name, value);
    }

    @Override
    Integer mapValue(String value) {
        return Integer.parseInt(value);
    }
}
