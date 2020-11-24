package alexander.ivanov.jsonsanitizer.json.entity;

import alexander.ivanov.jsonsanitizer.json.entity.mapper.MapperUtil;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractBaseJsonEntity extends AbstractJsonEntity<List<JsonEntity<?>>> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractBaseJsonEntity.class);

    private static final List<JsonToken> PRIMITIVE_TOKENS = Arrays.asList(
            JsonToken.VALUE_STRING,
            JsonToken.VALUE_NUMBER_INT, JsonToken.VALUE_NUMBER_FLOAT,
            JsonToken.VALUE_TRUE, JsonToken.VALUE_FALSE,
            JsonToken.VALUE_NULL
    );
    private static final List<JsonToken> COMPLEX_TOKENS = Arrays.asList(
            JsonToken.START_OBJECT, JsonToken.END_OBJECT,
            JsonToken.START_ARRAY, JsonToken.END_ARRAY
    );

    private final JsonParser jsonParser;
    private final List<JsonEntity<?>> nestedEntities = new ArrayList<>();
    private JsonToken currentToken;

    public AbstractBaseJsonEntity(String json) {
        super("");
        try {
            this.jsonParser = new JsonFactory().createParser(json);
            init();
        } catch (IOException e) {
            throw new JsonEntityException(e.getMessage());
        }
    }

    public AbstractBaseJsonEntity(String name, JsonParser jsonParser) {
        super(name);
        this.jsonParser = jsonParser;
        init();
    }

    public AbstractBaseJsonEntity(JsonParser jsonParser) {
        this("", jsonParser);
    }

    public JsonParser getJsonParser() {
        return jsonParser;
    }

    public String getJsonParserCurrentName() throws IOException {
        return jsonParser.getCurrentName();
    }

    public JsonToken getCurrentToken() {
        return currentToken;
    }

    public JsonToken setAndGetCurrentToken(JsonToken currentToken) {
        return (this.currentToken = currentToken);
    }

    @Override
    public List<JsonEntity<?>> getValue() {
        return nestedEntities;
    }

    abstract protected boolean hasValue() throws IOException;

    abstract protected boolean isNestedStart() throws IOException;

    abstract protected boolean isNestedEnd() throws IOException;

    abstract protected AbstractNestedJsonEntity getNestedEntity(String name, JsonParser jsonParser) throws IOException;

    abstract public String getEntitiesAsString();

    protected void moveToNextValue() throws IOException {

    }

    public void init() {
        try {
            while (hasValue()) {
                if (isRootEntityStart()) {
                    logger.trace("Object parse is started");
                } else if (isRootEntityEnd()) {
                    logger.trace("Object parse is ended");
                } else {
                    moveToNextValue();

                    if (isNestedEnd()) {
                        continue;
                    }

                    if (isCurrentTokenComplex()) {
                        boolean isNestedStart = isNestedStart();
                        logger.trace("isNestedStart = {}", isNestedStart);
                        if (isNestedStart) {
                            AbstractNestedJsonEntity nestedJsonEntity = getNestedEntity(getJsonParser().currentName(), getJsonParser());
                            logger.trace("nestedJsonEntity = {}", nestedJsonEntity);
                            if (nestedJsonEntity != null) {
                                nestedEntities.add(nestedJsonEntity);
                            }
                        }
                    } else if (isCurrentTokenPrimitive()) {
                        JsonEntity<?> primitiveEntity = MapperUtil.getMappedEntity(
                                getJsonParser().getCurrentToken(),
                                getJsonParser().getCurrentName(),
                                getJsonParser().getText()
                        );
                        nestedEntities.add(primitiveEntity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new JsonEntityException("Incorrect data. Please check json.\n" + e.getMessage());
        }
    }

    private boolean isRootEntityStart() throws IOException {
        return (getJsonParser().currentName() == null &&
                (getCurrentToken() == JsonToken.START_OBJECT || getCurrentToken() == JsonToken.START_ARRAY));
    }

    private boolean isRootEntityEnd() throws IOException {
        return (getJsonParser().currentName() == null &&
                (getCurrentToken() == JsonToken.END_OBJECT || getCurrentToken() == JsonToken.END_ARRAY));
    }

    private boolean isCurrentTokenPrimitive() {
        return PRIMITIVE_TOKENS.contains(currentToken);
    }

    private boolean isCurrentTokenComplex() {
        return COMPLEX_TOKENS.contains(currentToken);
    }
}
