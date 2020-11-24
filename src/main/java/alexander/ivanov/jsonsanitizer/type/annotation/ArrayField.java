package alexander.ivanov.jsonsanitizer.type.annotation;

import alexander.ivanov.jsonsanitizer.type.ArrayType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ComplexField(@CommonField(ArrayType.class))
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayField {
}
