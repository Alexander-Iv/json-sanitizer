package alexander.ivanov.jsonsanitizer;

import alexander.ivanov.jsonsanitizer.classinitializer.TargetClassInitializerUtil;

public class JsonEntitySanitizerImpl implements JsonEntitySanitizer {
    private final Class<?> targetClass;
    private final String json;

    public JsonEntitySanitizerImpl(String json, Class<?> targetClass) {
        this.json = json;
        this.targetClass = targetClass;
    }

    @Override
    public <T> T sanitize() {
        return (T) TargetClassInitializerUtil.initTargetClass(json, targetClass);
    }
}
