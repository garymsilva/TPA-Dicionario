package CustomMap;

class MapItem<K, V> {
    private K key;
    private V value;

    MapItem(K key, V value) {
        this.setKey(key);
        this.setValue(value);
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    void setKey(K key) {
        this.key = key;
    }

    void setValue(V value) {
        this.value = value;
    }
}
