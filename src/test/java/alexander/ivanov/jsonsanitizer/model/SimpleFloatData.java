package alexander.ivanov.jsonsanitizer.model;

import alexander.ivanov.jsonsanitizer.type.annotation.Entity;
import alexander.ivanov.jsonsanitizer.type.annotation.FloatField;

import java.util.Objects;

@Entity
public class SimpleFloatData {
    @FloatField
    private Float foo;

    public SimpleFloatData() {
    }

    public SimpleFloatData(Float foo) {
        this.foo = foo;
    }

    public Float getFoo() {
        return foo;
    }

    public void setFoo(Float foo) {
        this.foo = foo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleFloatData that = (SimpleFloatData) o;
        return Objects.equals(foo, that.foo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo);
    }

    @Override
    public String toString() {
        return "SimpleFloatData{" +
                "foo=" + foo +
                '}';
    }
}
