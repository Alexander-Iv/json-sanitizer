package alexander.ivanov.jsonsanitizer.json.entity.mapper;

import alexander.ivanov.jsonsanitizer.json.entity.JsonEntity;
import com.fasterxml.jackson.core.JsonToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class MapperUtil {
    private static final Logger logger = LoggerFactory.getLogger(MapperUtil.class);

    public static JsonEntity<?> getMappedEntity(JsonToken jsonToken, String name, String value) {
        List<AbstractJsonMapper<?>> mappers = Arrays.asList(
                new BooleanJsonMapper(jsonToken, name, value),
                new IntegerJsonMapper(jsonToken, name, value),
                new FloatJsonMapper(jsonToken, name, value),
                new NullJsonMapper(jsonToken, name, value),
                new StringJsonMapper(jsonToken, name, value)
        );

        return mappers.stream()
                .map(AbstractJsonMapper::map)
                .filter(Objects::nonNull)
                .peek(jsonEntity -> logger.trace("jsonEntity = {}", jsonEntity))
                .findFirst()
                .orElse(null);
    }
}
