package alexander.ivanov.jsonsanitizer.json.entity;

import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.util.stream.Collectors;

public abstract class AbstractNestedJsonEntity extends AbstractBaseJsonEntity {
    public AbstractNestedJsonEntity(String name, JsonParser jsonParser) {
        super(name, jsonParser);
    }

    @Override
    protected void moveToNextValue() throws IOException {
        setAndGetCurrentToken(getJsonParser().nextValue());
    }

    @Override
    public String toString() {
        return getEntitiesAsString();
    }

    @Override
    public String getEntitiesAsString() {
        return getValue().stream()
                .map(jsonEntity -> getName() + ":" + jsonEntity)
                .collect(Collectors.toList())
                .toString();
    }
}
