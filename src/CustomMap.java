import java.util.LinkedList;

public class CustomMap<T> {
    private LinkedList<MapItem>[] lists;
    private int size;

    CustomMap(int length) {
        this.size = 0;
        this.initLists(length);
    }

    /**
     * Inicializa o TAD do dicionário.
     * @param length
     */
    private void initLists(int length) {
        this.lists = new LinkedList[length];
        for (int i = 0; i < length; i++) this.lists[i] = new LinkedList<MapItem>();
    }

    private long generateHash(String key) {
        // TODO: criar lógica de criação de hash
        long hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (int)key.charAt(i);
        }
        return hash;
    }

    public void add(String key, T item) {
        // TODO: encontrar o melhor lugar pra guardar o item e persistir.
        long hash = this.generateHash(key);
        int index = (int)(hash % this.lists.length);
        this.lists[index].add(new MapItem(key, item));
        this.size++;
    }
}
