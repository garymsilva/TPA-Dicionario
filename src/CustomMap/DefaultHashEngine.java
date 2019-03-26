package CustomMap;

class DefaultHashEngine implements HashEngine {

    DefaultHashEngine() {}

    @Override
    public Long generateHash(Object key) {
        String stringKey = key.toString();
        long hash = 0;
        for (int i = 0; i < stringKey.length(); i++) {
            hash += (int)stringKey.charAt(i);
        }
        return hash;
    }
}
