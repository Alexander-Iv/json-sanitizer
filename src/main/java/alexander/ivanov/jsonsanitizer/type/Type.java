package alexander.ivanov.jsonsanitizer.type;

public interface Type<T> {
    void setValue(T value);
    T getValue();

    Class<?> getTargetType();

    void setMessage(String message);
    String getMessage();

    /*default void setValue(T value) {}
    default void setMessage(String message) {}
    default String getMessage() {
        return "";
    }

    default Class<?> getType() {
        return this.getClass();
    }

    default String validateTargetType(Class<?> targetType) {
        String message = "";
        if (!isTypeSupport(targetType)) {
            message = "Target type isn't support";
        }
        return message;
    }

    default boolean isTypeSupport(Class<?> targetType) {
        return this.getTargetType().equals(targetType);
    }

    default Class<?> getSourceType() {
        return null;
    }*/
}
