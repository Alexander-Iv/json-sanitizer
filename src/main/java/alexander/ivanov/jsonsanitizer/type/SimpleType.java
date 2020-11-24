package alexander.ivanov.jsonsanitizer.type;

public interface SimpleType<T> extends Type<T> {
    T init(String value);
}
