package alexander.ivanov.jsonsanitizer.type;

public class StringType extends AbstractSimpleType<String> {
    public StringType() {
    }

    @Override
    public String init(String value) {
        return super.init(value);
    }

    @Override
    public String extractFromString(String value) {
        return value;
    }

    @Override
    public String getRegex() {
        return "[a-zA-Zа-яА-ЯёЁ]+";
    }

    @Override
    public Class<?> getTargetType() {
        return String.class;
    }
}
