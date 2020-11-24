package alexander.ivanov.jsonsanitizer.type;

import java.util.List;

public class ArrayType extends AbstractComplexType<List<?>> {
    public ArrayType() {
    }

    @Override
    public List<?> init(List<?> value) {
        return super.init(value);
    }

    @Override
    public Class<?> getTargetType() {
        return List.class;
    }
}
