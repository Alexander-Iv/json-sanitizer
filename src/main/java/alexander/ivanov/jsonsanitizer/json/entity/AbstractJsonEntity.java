package alexander.ivanov.jsonsanitizer.json.entity;

import java.util.Objects;

public abstract class AbstractJsonEntity<T> implements JsonEntity<T> {
    private String name;
    private T value;
    private final Class<?> type;

    public AbstractJsonEntity(String name) {
        this.name = name;
        this.type = this.getClass();
    }

    public AbstractJsonEntity(String name, T value) {
        this(name);
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "JsonEntity{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", type=" + type.getSimpleName() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractJsonEntity<?> that = (AbstractJsonEntity<?>) o;

        return name.equals(that.name) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
