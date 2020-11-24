package alexander.ivanov.jsonsanitizer.type;

public abstract class AbstractComplexType<T> extends AbstractType<T> implements ComplexType<T> {
    @Override
    public T init(T value) {
        return setAndGetValue(value);
    }
}
