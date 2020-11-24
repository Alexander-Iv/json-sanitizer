package alexander.ivanov.jsonsanitizer.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerType extends AbstractSimpleType<Integer> {
    private static final Logger logger = LoggerFactory.getLogger(IntegerType.class);

    public IntegerType() {
    }

    /*@Override
    public Integer init(Integer value) {
        return super.init(value);
    }*/

    @Override
    public Integer init(String value) {
        return super.init(value);
    }

    @Override
    public String getRegex() {
        return "[0-9]+";
    }

    @Override
    public Integer extractFromString(String value) {
        return Integer.valueOf(value);
    }

    @Override
    public Class<?> getTargetType() {
        return Integer.class;
    }
}
