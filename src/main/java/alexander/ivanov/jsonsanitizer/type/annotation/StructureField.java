package alexander.ivanov.jsonsanitizer.type.annotation;

import alexander.ivanov.jsonsanitizer.type.StructureType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ComplexField(@CommonField(StructureType.class))
@Retention(RetentionPolicy.RUNTIME)
public @interface StructureField {
}
