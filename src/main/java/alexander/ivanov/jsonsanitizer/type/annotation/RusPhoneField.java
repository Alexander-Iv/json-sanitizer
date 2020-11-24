package alexander.ivanov.jsonsanitizer.type.annotation;

import alexander.ivanov.jsonsanitizer.type.RusPhoneType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SimpleField(@CommonField(RusPhoneType.class))
@Retention(RetentionPolicy.RUNTIME)
public @interface RusPhoneField {
}
