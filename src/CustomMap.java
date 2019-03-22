import java.util.LinkedList;

public class CustomMap<T> {
    private LinkedList<MapItem>[] lists;
    private int size;
    private int listsLenght;
    private HashEngine hashEngine;

    CustomMap() {
        this(10, new DefaultHashEngine());
    }

    CustomMap(int length) {
        this(length, new DefaultHashEngine());
    }

    CustomMap(int length, HashEngine hashEngine) {
        this.listsLenght = length;
        this.hashEngine = hashEngine;
        this.initLists(length);
        this.size = 0;
    }

    /**
     * Inicializa o TAD do dicionário.
     * @param length
     */
    private void initLists(int length) {
        this.lists = new LinkedList[length];
        for (int i = 0; i < length; i++) this.lists[i] = new LinkedList<MapItem>();
    }

    /**
     * Calcula em qual Lista do Vetor lists o item deverá ser inserido.
     * @param key
     * @return
     */
    private int getIndex(String key) {
        long hash = this.hashEngine.generateHash(key);
        int index = (int)(hash % this.lists.length);
        return index;
    }

    /**
     * Adiciona um item ao dicionário.
     * @param key
     * @param item
     */
    public void add(String key, T item) {
        int index = this.getIndex(key);
        this.lists[index].add(new MapItem(key, item));
        this.size++;
    }

    /**
     * Encontra um item a partir de uma key e retorna seu MapItem
     * @param key
     * @return
     */
    private MapItem<T> findMapItem(String key) {
        int index = this.getIndex(key);

        // verificar se a lista tem algo
        if (!this.lists[index].isEmpty()) {
            int i = 0;
            while (i < this.lists[index].size()) {
                MapItem item = this.lists[index].get(i);
                if (item != null && item.getKey() == key) {
                    return item;
                }
                i++;
            }
        }
        return null;
    }

    /**
     * Encontra um item no dicionário, a partir da key. Retorna o value correspondente.
     * @param key
     * @return
     */
    public T find(String key) {
        return this.findMapItem(key).getValue();
    }

    /**
     * Remove um item do dicionário.
     * @param key
     * @return
     */
    public T remove(String key) {
        try {
            MapItem<T> mapItem = this.findMapItem(key);
            int index = this.getIndex(key);
            this.lists[index].remove(mapItem);
            return mapItem.getValue();
        } catch (Exception e) {
            return null;
        }
    }
}
