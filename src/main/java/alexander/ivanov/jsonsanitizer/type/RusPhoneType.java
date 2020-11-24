package alexander.ivanov.jsonsanitizer.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class RusPhoneType extends AbstractSimpleType<RusPhoneType.RusPhone> {
    private static final Logger logger = LoggerFactory.getLogger(RusPhoneType.class);

    @Override
    public RusPhone init(String value) {
        String transformedValue = transformValue(value);
        logger.trace("transformedValue = {}", transformedValue);
        return super.init(transformedValue);
    }

    @Override
    public RusPhone extractFromString(String value) {
        return new RusPhone(value);
    }

    @Override
    public String getRegex() {
        return "(\\+7|7|8)(\\s|)(\\(|)\\d{3}(\\)|)(\\s|)*\\d{3}(-|)\\d{2}(-|)\\d{2}";
    }

    @Override
    public Class<?> getTargetType() {
        return RusPhoneType.RusPhone.class;
    }

    public static class RusPhone {
        private String phoneNumber;

        public RusPhone(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RusPhone rusPhone = (RusPhone) o;
            return phoneNumber.equals(rusPhone.phoneNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(phoneNumber);
        }

        @Override
        public String toString() {
            return "RusPhone{" +
                    "phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }

    private String transformValue(String value) {
        String clearPhone = value.replaceAll("([+()-]|\\s)", "");
        if (clearPhone.startsWith("8") && clearPhone.length() == 11) {
            clearPhone = clearPhone.replaceFirst("8", "7");
        }
        logger.trace("clearPhone = {}, prevValue = {}", clearPhone, value);
        return clearPhone;
    }
}
