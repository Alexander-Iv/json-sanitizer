package alexander.ivanov.jsonsanitizer.model;

import alexander.ivanov.jsonsanitizer.type.annotation.ArrayField;
import alexander.ivanov.jsonsanitizer.type.annotation.Entity;

import java.util.List;
import java.util.Objects;

@Entity
public class SimpleArrayData {
    @ArrayField
    private List<?> arr;

    public SimpleArrayData() {
    }

    public SimpleArrayData(List<?> arr) {
        this.arr = arr;
    }

    public List<?> getArr() {
        return arr;
    }

    public void setArr(List<?> arr) {
        this.arr = arr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleArrayData that = (SimpleArrayData) o;

        return Objects.equals(arr, that.arr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arr);
    }

    @Override
    public String toString() {
        return "SimpleArrayData{" +
                "arr=" + arr +
                '}';
    }
}
