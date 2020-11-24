package alexander.ivanov.jsonsanitizer.model;

import alexander.ivanov.jsonsanitizer.type.RusPhoneType;
import alexander.ivanov.jsonsanitizer.type.annotation.Entity;
import alexander.ivanov.jsonsanitizer.type.annotation.IntegerField;
import alexander.ivanov.jsonsanitizer.type.annotation.RusPhoneField;
import alexander.ivanov.jsonsanitizer.type.annotation.StringField;

import java.util.Objects;

@Entity
public class SimpleData {
    @IntegerField
    private Integer foo;

    @StringField
    private String bar;

    @RusPhoneField
    private RusPhoneType.RusPhone baz;

    public SimpleData() {
    }

    public SimpleData(Integer foo, String bar, RusPhoneType.RusPhone baz) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
    }

    public Integer getFoo() {
        return foo;
    }

    public void setFoo(Integer foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public RusPhoneType.RusPhone getBaz() {
        return baz;
    }

    public void setBaz(RusPhoneType.RusPhone baz) {
        this.baz = baz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleData that = (SimpleData) o;
        return Objects.equals(foo, that.foo) &&
                Objects.equals(bar, that.bar) &&
                Objects.equals(baz, that.baz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo, bar, baz);
    }

    @Override
    public String toString() {
        return "SimpleData{" +
                "foo=" + foo +
                ", bar='" + bar + '\'' +
                ", baz=" + baz +
                '}';
    }
}
