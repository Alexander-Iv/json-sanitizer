package alexander.ivanov.jsonsanitizer.type;

public class UnknownType implements Type<Void> {
    @Override
    public void setValue(Void value) {

    }

    @Override
    public Void getValue() {
        return null;
    }

    @Override
    public Class<?> getTargetType() {
        return null;
    }

    @Override
    public void setMessage(String message) {

    }

    @Override
    public String getMessage() {
        return null;
    }
}
