package alexander.ivanov.jsonsanitizer.json.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.stream.Collectors;

public final class BaseJsonEntity extends AbstractBaseJsonEntity {
    private static final Logger logger = LoggerFactory.getLogger(BaseJsonEntity.class);

    public BaseJsonEntity(String json) {
        super(json);
    }

    @Override
    public String toString() {
        return getEntitiesAsString();
    }

    @Override
    public String getEntitiesAsString() {
        return getValue()
                .stream()
                .map(jsonEntity -> "\n\t" + jsonEntity)
                .collect(Collectors.joining());
    }

    @Override
    protected boolean hasValue() throws IOException {
        return setAndGetCurrentToken(getJsonParser().nextValue()) != null;
    }

    @Override
    protected boolean isNestedStart() throws IOException {
        String info = String.format("%s, %s, %s\n",
                getJsonParserCurrentName(),
                getName(),
                getCurrentToken());
        logger.trace("\n{}", info);

        return isArrayStart() || isObjectStart();
    }

    @Override
    protected boolean isNestedEnd() throws IOException {
        boolean result = false;
        if (!getName().equals("")) {
            boolean isNamesEqual = getName().equals(getJsonParserCurrentName());
            result = isNamesEqual && (isArrayEnd() || isObjectEnd());
            logger.trace("getName() = {}, getJsonParserCurrentName() = {}, isNamesEqual = {}",
                    getName(), getJsonParserCurrentName(), isNamesEqual);
            logger.trace("result = {}", result);
        }

        return result;
    }

    @Override
    protected AbstractNestedJsonEntity getNestedEntity(String name, JsonParser jsonParser) throws IOException {
        String info = String.format("%s, %s, %s",
                getJsonParserCurrentName(),
                getName(),
                getCurrentToken());
        logger.trace("{}", info);

        return getNestedEntityByJsonToken(name, jsonParser);
    }

    private AbstractNestedJsonEntity getNestedEntityByJsonToken(String name, JsonParser jsonParser) {
        return isObjectStart() ? new NestedObjectJsonEntity(name, jsonParser)
                : isArrayStart() ? new NestedArrayJsonEntity(name, jsonParser)
                : null;
    }

    private boolean isObjectStart() {
        return getCurrentToken() == JsonToken.START_OBJECT;
    }

    private boolean isArrayStart() {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    private boolean isObjectEnd() {
        return getCurrentToken() ==  JsonToken.END_OBJECT;
    }

    private boolean isArrayEnd() {
        return getCurrentToken() ==  JsonToken.END_ARRAY;
    }
}
