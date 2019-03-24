package CustomMap;

class MapItem<V> {
    private String key;
    private V value;

    MapItem() {}

    MapItem(String key, V value) {
        this.setKey(key);
        this.setValue(value);
    }

    String getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    void setKey(String key) {
        this.key = key;
    }

    void setValue(V value) {
        this.value = value;
    }
}
