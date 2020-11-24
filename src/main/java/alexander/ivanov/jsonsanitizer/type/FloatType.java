package alexander.ivanov.jsonsanitizer.type;

public class FloatType extends AbstractSimpleType<Float> {
    public FloatType() {
    }

    @Override
    public Float init(String value) {
        return super.init(value);
    }

    @Override
    public Float extractFromString(String value) {
        return Float.valueOf(value);
    }

    @Override
    public String getRegex() {
        return "\\d+[.]\\d+";
    }

    @Override
    public Class<?> getTargetType() {
        return Float.class;
    }
}
