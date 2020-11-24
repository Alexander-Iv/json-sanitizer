package alexander.ivanov.jsonsanitizer.type;

public interface ComplexType<T> extends Type<T> {
    T init(T value);
}
