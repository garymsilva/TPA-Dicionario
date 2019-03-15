public class MapItem<Type> {
    private String key;
    private Type value;

    public String getKey() {
        return key;
    }

    public Type getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Type value) {
        this.value = value;
    }
}
