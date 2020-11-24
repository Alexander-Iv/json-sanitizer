package alexander.ivanov.jsonsanitizer.json.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class NestedObjectJsonEntity extends AbstractNestedJsonEntity {
    private static final Logger logger = LoggerFactory.getLogger(NestedObjectJsonEntity.class);

    public NestedObjectJsonEntity(String name, JsonParser jsonParser) {
        super(name, jsonParser);
    }

    @Override
    protected boolean hasValue() throws IOException {
        logger.trace("hasValue.getCurrentToken() = {}, getJsonParser().currentToken() = {}, getJsonParserCurrentName() = {}, name = {}",
                getCurrentToken(), getJsonParser().currentToken(), getJsonParserCurrentName(), getName()
        );

        return !(getJsonParserCurrentName().equals(getName()) && setAndGetCurrentToken(getJsonParser().getCurrentToken()) == JsonToken.END_OBJECT);
    }

    @Override
    protected boolean isNestedStart() throws IOException {
        boolean isNestedObjectStart = (getJsonParser().currentName() != null && getCurrentToken() == JsonToken.START_OBJECT);
        logger.trace("isNestedObjectStart = {}, {}, {}, {}, getName() = {}",
                isNestedObjectStart, getJsonParser().currentName(), getJsonParserCurrentName(), getCurrentToken(), getName());

        return (getJsonParser().currentName() != null && getCurrentToken() == JsonToken.START_OBJECT);
    }

    @Override
    protected boolean isNestedEnd() throws IOException {
        logger.trace("isNestedEnd.getJsonParserCurrentName() = {}, {}", getJsonParserCurrentName(), getName());

        return (getJsonParserCurrentName().equals(getName()) && getCurrentToken() == JsonToken.END_OBJECT);
    }

    @Override
    protected AbstractNestedJsonEntity getNestedEntity(String name, JsonParser jsonParser) {
        return new NestedObjectJsonEntity(name, jsonParser);
    }
}
