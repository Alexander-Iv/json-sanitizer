package alexander.ivanov.jsonsanitizer.json.entity.mapper;

import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import com.fasterxml.jackson.core.JsonToken;

public abstract class AbstractJsonMapper<T> {
    private final JsonToken jsonToken;
    private final String name;
    private String value;

    public AbstractJsonMapper(JsonToken jsonToken, String name, String value) {
        this.jsonToken = jsonToken;
        this.name = name;
        this.value = value;
    }

    abstract boolean jsonTokenValidate();
    abstract JsonEntity<T> initEntity(String name, T value);
    abstract T mapValue(String value);

    public JsonToken getJsonToken() {
        return jsonToken;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public JsonEntity<T> map() {
        return jsonTokenValidate() ? initEntity(name, mapValue(value)): null;
    }
}
