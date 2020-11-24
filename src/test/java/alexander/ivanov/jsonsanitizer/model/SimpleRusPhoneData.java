package alexander.ivanov.jsonsanitizer.model;

import alexander.ivanov.jsonsanitizer.type.RusPhoneType;
import alexander.ivanov.jsonsanitizer.type.annotation.Entity;
import alexander.ivanov.jsonsanitizer.type.annotation.RusPhoneField;

import java.util.Objects;

@Entity
public class SimpleRusPhoneData {
    @RusPhoneField
    private RusPhoneType.RusPhone rusPhone;

    public SimpleRusPhoneData() {
    }

    public SimpleRusPhoneData(RusPhoneType.RusPhone rusPhone) {
        this.rusPhone = rusPhone;
    }

    public RusPhoneType.RusPhone getRusPhone() {
        return rusPhone;
    }

    public void setRusPhone(RusPhoneType.RusPhone rusPhone) {
        this.rusPhone = rusPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleRusPhoneData that = (SimpleRusPhoneData) o;
        return Objects.equals(rusPhone, that.rusPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rusPhone);
    }

    @Override
    public String toString() {
        return "SimpleRusPhoneData{" +
                "rusPhone=" + rusPhone +
                '}';
    }
}
