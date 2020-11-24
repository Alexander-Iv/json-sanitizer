package alexander.ivanov.jsonsanitizer.model;

import alexander.ivanov.jsonsanitizer.type.annotation.Entity;
import alexander.ivanov.jsonsanitizer.type.annotation.StringField;

import java.util.Objects;

@Entity
public class SimpleStringData {
    @StringField
    private String foo;

    public SimpleStringData() {
    }

    public SimpleStringData(String foo) {
        this.foo = foo;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleStringData that = (SimpleStringData) o;
        return Objects.equals(foo, that.foo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo);
    }

    @Override
    public String toString() {
        return "SimpleStringData{" +
                "foo='" + foo + '\'' +
                '}';
    }
}
