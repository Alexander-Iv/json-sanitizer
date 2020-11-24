package alexander.ivanov.jsonsanitizer.model;

import alexander.ivanov.jsonsanitizer.type.RusPhoneType;
import alexander.ivanov.jsonsanitizer.type.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class FullData {
    @IntegerField
    private Integer foo;

    @IntegerField
    private Integer foo2;

    @StringField
    private String bar;

    @RusPhoneField
    private RusPhoneType.RusPhone baz;

    @RusPhoneField
    private Phone phone;

    @FloatField
    private Float fl;

    @ArrayField
    private List<?> arr;

    @StructureField
    private Map<String, ?> struct;

    public FullData() {
    }

    public FullData(Integer foo, Integer foo2, String bar, RusPhoneType.RusPhone baz, Phone phone, Float fl, List<?> arr, Map<String, ?> struct) {
        this.foo = foo;
        this.foo2 = foo2;
        this.bar = bar;
        this.baz = baz;
        this.phone = phone;
        this.fl = fl;
        this.arr = arr;
        this.struct = struct;
    }

    public Integer getFoo() {
        return foo;
    }

    public void setFoo(Integer foo) {
        this.foo = foo;
    }

    public Integer getFoo2() {
        return foo2;
    }

    public void setFoo2(Integer foo2) {
        this.foo2 = foo2;
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

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Float getFl() {
        return fl;
    }

    public void setFl(Float fl) {
        this.fl = fl;
    }

    public List<?> getArr() {
        return arr;
    }

    public void setArr(List<?> arr) {
        this.arr = arr;
    }

    public Map<String, ?> getStruct() {
        return struct;
    }

    public void setStruct(Map<String, ?> struct) {
        this.struct = struct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullData fullData = (FullData) o;
        return Objects.equals(foo, fullData.foo) &&
                Objects.equals(foo2, fullData.foo2) &&
                Objects.equals(bar, fullData.bar) &&
                Objects.equals(baz, fullData.baz) &&
                Objects.equals(phone, fullData.phone) &&
                Objects.equals(fl, fullData.fl) &&
                Objects.equals(arr, fullData.arr) &&
                Objects.equals(struct, fullData.struct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo, foo2, bar, baz, phone, fl, arr, struct);
    }

    @Override
    public String toString() {
        return "FullData{" +
                "foo=" + foo +
                ", foo2=" + foo2 +
                ", bar='" + bar + '\'' +
                ", baz=" + baz +
                ", phone=" + phone +
                ", fl=" + fl +
                ", arr=" + arr +
                ", struct=" + struct +
                '}';
    }

    public static class Phone {
        private String phoneNumber;

        public Phone() {
        }

        public Phone(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Phone phone = (Phone) o;
            return Objects.equals(phoneNumber, phone.phoneNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(phoneNumber);
        }

        @Override
        public String toString() {
            return "Phone{" +
                    "phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }
}
