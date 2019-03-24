package CustomMap;

import java.util.LinkedList;

public class CustomMap<T> {
    private LinkedList<MapItem>[] lists;
    private int size;
    private int listsLenght;
    private HashEngine hashEngine;

    public CustomMap() {
        this(10);
    }

    public CustomMap(int length) {
        this(length, new DefaultHashEngine());
    }

    public CustomMap(int length, HashEngine hashEngine) {
        this.listsLenght = length;
        this.hashEngine = hashEngine;
        this.initLists();
        this.size = 0;
    }

    /**
     * Inicializa o TAD do dicionário.
     */
    private void initLists() {
        this.lists = new LinkedList[this.listsLenght];
        for (int i = 0; i < this.listsLenght; i++) this.lists[i] = new LinkedList<MapItem>();
    }

    /**
     * Calcula em qual Lista do Vetor lists o item deverá ser inserido.
     * @param key Chave referência.
     * @return número que representa qual a LinkedList daquele valor.
     */
    private int getIndex(String key) {
        long hash = this.hashEngine.generateHash(key);
        int index = (int)(hash % this.lists.length);
        return index;
    }

    /**
     * Procura um MapItem no dicionário a partir de uma key.
     * @param key Chave referência.
     * @return Objeto que contém a key, caso encontre. null, caso contrário.
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
        if (this.size > 0) return true;
        return false;
    }

    /**
     * Adiciona um item ao dicionário.
     * @param key Chave referência.
     * @param item Valor a ser armazenado.
     */
    public void add(String key, T item) {
        int index = this.getIndex(key);
        this.lists[index].add(new MapItem(key, item));
        this.size++;
    }

    /**
     * Procura por um item no dicionário, a partir da key.
     * @param key Chave referência.
     * @return T, se encontrar. null, caso contrário.
     */
    public T find(String key) {
        return this.findMapItem(key).getValue();
    }

    /**
     * Procura um item e o remove um item do dicionário.
     * @param key Chave referência.
     * @return T, se encontrar. null, caso contrário.
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

    /**
     * Retorna uma coleção com as chaves armazenadas no dicionário.
     */
    private LinkedList<String> keys() {
        // TODO
        return null;
    }

    /**
     * Retorna uma coleção com os valores armazenados no dicionário.
     */
    private LinkedList<T> values() {
        // TODO
        return null;
    }

    /**
     * Retorna uma coleção com as entradas {chave, valor} armazenadas no dicionário.
     */
    private LinkedList<Object> entries() {
        // TODO
        return null;
    }
}
