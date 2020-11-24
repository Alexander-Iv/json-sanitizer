package alexander.ivanov.jsonsanitizer.json.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class NestedArrayJsonEntity extends AbstractNestedJsonEntity {
    private static final Logger logger = LoggerFactory.getLogger(NestedArrayJsonEntity.class);

    public NestedArrayJsonEntity(String name, JsonParser jsonParser) {
        super(name, jsonParser);
    }

    @Override
    public String getJsonParserCurrentName() throws IOException {
        return getJsonParser().getCurrentName() == null ? getName() : getJsonParser().getCurrentName();
    }

    @Override
    public String getEntitiesAsString() {
        return getValue().toString();
    }

    @Override
    protected boolean hasValue() throws IOException {
        logger.trace("getCurrentToken() = {}, getJsonParser().currentToken() = {}, getJsonParserCurrentName() = {}",
                getCurrentToken(), getJsonParser().currentToken(), getJsonParserCurrentName());

        return setAndGetCurrentToken(getJsonParser().currentToken()) != JsonToken.END_ARRAY && getJsonParserCurrentName().equals(getName());
    }

    @Override
    protected boolean isNestedStart() throws IOException {
        boolean isNestedArrayStart = (getJsonParser().currentName() != null && getCurrentToken() == JsonToken.START_ARRAY);
        logger.trace("isNestedArrayStart = {}, {}, {}, {}\n", isNestedArrayStart, getJsonParser().currentName(), getJsonParserCurrentName(), getCurrentToken());

        return (getJsonParser().currentName() != null && getCurrentToken() == JsonToken.START_ARRAY);
    }

    @Override
    protected boolean isNestedEnd() throws IOException {
        logger.trace("getJsonParserCurrentName() = {}, {}", getJsonParserCurrentName(), getName());
        return (getCurrentToken() == JsonToken.END_ARRAY);
    }

    @Override
    protected AbstractNestedJsonEntity getNestedEntity(String name, JsonParser jsonParser) {
        return new NestedArrayJsonEntity(name, jsonParser);
    }
}
