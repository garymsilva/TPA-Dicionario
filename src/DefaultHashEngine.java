public class DefaultHashEngine implements HashEngine {
    @Override
    public long generateHash(String key) {
        long hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (int)key.charAt(i);
        }
        return hash;
    }
}
