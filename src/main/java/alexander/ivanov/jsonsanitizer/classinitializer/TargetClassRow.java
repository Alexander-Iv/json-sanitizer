package alexander.ivanov.jsonsanitizer.classinitializer;

import alexander.ivanov.jsonsanitizer.type.Type;

public class TargetClassRow {
    private String name;
    private Type<?> type;

    public TargetClassRow(String name, Type<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type<?> getType() {
        return type;
    }

    public void setType(Type<?> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TargetClassRow{" +
                "name='" + name + '\'' +
                ", type=" + type +
                "}";
    }
}
