package alexander.ivanov.jsonsanitizer.json.entity;

public interface JsonEntity<T> {
    String getName();
    T getValue();
    Class<?> getType();
}
