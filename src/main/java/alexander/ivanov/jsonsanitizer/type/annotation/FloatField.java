package alexander.ivanov.jsonsanitizer.type.annotation;

import alexander.ivanov.jsonsanitizer.type.FloatType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SimpleField(@CommonField(FloatType.class))
@Retention(RetentionPolicy.RUNTIME)
public @interface FloatField {
}
