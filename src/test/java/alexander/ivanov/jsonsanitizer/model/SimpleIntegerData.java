package alexander.ivanov.jsonsanitizer.model;

import alexander.ivanov.jsonsanitizer.type.annotation.Entity;
import alexander.ivanov.jsonsanitizer.type.annotation.IntegerField;

import java.util.Objects;

@Entity
public class SimpleIntegerData {
    @IntegerField
    private Integer foo;

    public SimpleIntegerData() {
    }

    public SimpleIntegerData(Integer foo) {
        this.foo = foo;
    }

    public Integer getFoo() {
        return foo;
    }

    public void setFoo(Integer foo) {
        this.foo = foo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleIntegerData that = (SimpleIntegerData) o;
        return Objects.equals(foo, that.foo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo);
    }

    @Override
    public String toString() {
        return "SimpleIntegerData{" +
                "foo=" + foo +
                '}';
    }
}
