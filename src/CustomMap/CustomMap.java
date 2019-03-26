package CustomMap;

import java.util.LinkedList;

public class CustomMap<K, V> {
    private LinkedList<MapItem>[] lists;
    private int size;
    private int listsLength;
    private HashEngine hashEngine;

    public CustomMap() {
        this(10);
    }

    public CustomMap(int length) {
        this(length, new DefaultHashEngine());
    }

    public CustomMap(int length, HashEngine hashEngine) {
        this.listsLength = length;
        this.hashEngine = hashEngine;
        this.initLists();
        this.size = 0;
    }

    /**
     * Inicializa o TAD do dicionário.
     */
    private void initLists() {
        this.lists = new LinkedList[this.listsLength];
        for (int i = 0; i < this.listsLength; i++) this.lists[i] = new LinkedList<MapItem>();
    }

    /**
     * Calcula em qual Lista do Vetor lists o item deverá ser inserido.
     * @param key Chave referência.
     * @return número que representa qual a LinkedList daquele valor.
     */
    private int getIndex(K key) {
        Number hash = this.hashEngine.generateHash(key.toString());
        return hash.intValue() % this.lists.length;
    }

    /**
     * Procura um MapItem no dicionário a partir de uma key.
     * @param key Chave referência.
     * @return Objeto que contém a key, caso encontre. null, caso contrário.
     */
    private MapItem<K, V> findMapItem(K key) {
        int index = this.getIndex(key);

        // verificar se a lista tem algo
        if (!this.lists[index].isEmpty()) {
            int i = 0;
            while (i < this.lists[index].size()) {
                MapItem item = this.lists[index].get(i);
                if (item != null && item.getKey().equals(key)) {
                    return item;
                }
                i++;
            }
        }
        return null;
    }

    /**
     * Informa a quantidade de entradas no dicionário.
     * @return número de entradas.
     */
    public int size() {
        return this.size;
    }

    /**
     * Diz se o dicionário está vazio.
     * @return true, caso vazio, false caso contrário.
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Adiciona um item ao dicionário.
     * @param key Chave referência.
     * @param item Valor a ser armazenado.
     */
    public void add(K key, V item) {
        int index = this.getIndex(key);
        this.lists[index].add(new MapItem<K, V>(key, item));
        this.size++;
    }

    /**
     * Procura por um item no dicionário, a partir da key.
     * @param key Chave referência.
     * @return V, se encontrar. null, caso contrário.
     */
    public V find(K key) {
        try {
            MapItem<K, V> result = this.findMapItem(key);
            if (result == null) return null;
            return result.getValue();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Procura um item e o remove um item do dicionário.
     * @param key Chave referência.
     * @return V, se encontrar. null, caso contrário.
     */
    public V remove(K key) {
        MapItem<K, V> mapItem;
        try {
            mapItem = this.findMapItem(key);
        } catch (Exception e) {
            return null;
        }
        // se o item não foi encontrado, retorna null
        if (mapItem == null) return null;

        int index = this.getIndex(key);
        this.lists[index].remove(mapItem);
        return mapItem.getValue();
    }

    /**
     * Retorna uma coleção com as chaves armazenadas no dicionário.
     */
    public LinkedList<K> keys() {
        LinkedList<MapItem> list;
        LinkedList<K> keysList = new LinkedList<>();

        for (int i = 0; i < this.listsLength; i++) {
            list = this.lists[i];
            for (MapItem<K, V> mapItem : list) {
                keysList.add(mapItem.getKey());
            }
        }

        return keysList;
    }

    /**
     * Retorna uma coleção com os valores armazenados no dicionário.
     */
    public LinkedList<V> values() {
        LinkedList<MapItem> list;
        LinkedList<V> valuesList = new LinkedList<>();

        for (int i = 0; i < this.listsLength; i++) {
            list = this.lists[i];
            for (MapItem<K, V> mapItem : list) {
                valuesList.add(mapItem.getValue());
            }
        }

        return valuesList;
    }

    /**
     * Retorna uma coleção com as entradas {chave, valor} armazenadas no dicionário.
     */
    private LinkedList<Object> entries() {
        // TODO
        return null;
    }
}
