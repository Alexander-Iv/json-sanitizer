package alexander.ivanov.jsonsanitizer.model;

import alexander.ivanov.jsonsanitizer.type.annotation.Entity;
import alexander.ivanov.jsonsanitizer.type.annotation.StructureField;

import java.util.Map;
import java.util.Objects;

@Entity
public class SimpleStructureData {
    @StructureField
    private Map<String, ?> structure;

    public SimpleStructureData() {
    }

    public SimpleStructureData(Map<String, ?> structure) {
        this.structure = structure;
    }

    public Map<String, ?> getStructure() {
        return structure;
    }

    public void setStructure(Map<String, ?> structure) {
        this.structure = structure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleStructureData that = (SimpleStructureData) o;
        return Objects.equals(structure, that.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structure);
    }

    @Override
    public String toString() {
        return "SimpleStructureData{" +
                "structure=" + structure +
                '}';
    }
}
