package CustomMap;

class MapItem<T> {
    private String key;
    private T value;

    MapItem() {}

    protected MapItem(String key, T value) {
        this.setKey(key);
        this.setValue(value);
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
