package alexander.ivanov.jsonsanitizer.type.annotation;

import alexander.ivanov.jsonsanitizer.type.StringType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SimpleField(@CommonField(StringType.class))
@Retention(RetentionPolicy.RUNTIME)
public @interface StringField {
    //SimpleField value() default @SimpleField(StringType.class);
}
