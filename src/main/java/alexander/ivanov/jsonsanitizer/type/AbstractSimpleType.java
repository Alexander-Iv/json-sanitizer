package alexander.ivanov.jsonsanitizer.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public abstract class AbstractSimpleType<T> extends AbstractType<T> implements SimpleType<T>, TypeMatcher {
    private static final Logger logger = LoggerFactory.getLogger(AbstractSimpleType.class);

    abstract public T extractFromString(String value);
    abstract public String getRegex();

    @Override
    public T init(String value) {
        if (!isMatch(value)) {
            String message = String.format("Value('%s') isn't match regex('%s')", value, getRegex());
            setMessage(message);

            return null;
        }
        T extractedValue = extractFromString(value);
        logger.trace("extractedValue = {}", extractedValue);
        return setAndGetValue(extractedValue);
    }

    @Override
    public boolean isMatch(String value) {
        logger.trace("AbstractSimpleType.isMatch");
        logger.trace("value = {}", value);
        boolean isMatch = Pattern.matches(getRegex(), value);
        logger.trace("isMatch = {}", isMatch);

        return isMatch;
    }
}
