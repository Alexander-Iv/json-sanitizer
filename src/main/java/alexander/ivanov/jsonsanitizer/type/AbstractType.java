package alexander.ivanov.jsonsanitizer.type;

public abstract class AbstractType<T> implements Type<T> {
    private T value;
    private String message;

    public AbstractType() {
        this(null, "");
    }

    public AbstractType(T value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    public T setAndGetValue(T value) {
        return (this.value = value);
    }
}
