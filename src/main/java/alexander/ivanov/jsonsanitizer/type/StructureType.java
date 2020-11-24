package alexander.ivanov.jsonsanitizer.type;

import java.util.Map;

public class StructureType extends AbstractComplexType<Map<String, Object>> {
    public StructureType() {
    }

    @Override
    public Map<String, Object> init(Map<String, Object> value) {
        return super.init(value);
    }

    @Override
    public Class<?> getTargetType() {
        return Map.class;
    }
}
