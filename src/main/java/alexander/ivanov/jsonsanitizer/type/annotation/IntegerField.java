package alexander.ivanov.jsonsanitizer.type.annotation;

import alexander.ivanov.jsonsanitizer.type.IntegerType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SimpleField(@CommonField(IntegerType.class))
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerField {
}
