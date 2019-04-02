package CustomMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import com.github.sh0nk.matplotlib4j.*;
import com.github.sh0nk.matplotlib4j.builder.PlotBuilder;
import com.sun.javafx.property.adapter.PropertyDescriptor;

public class CustomMap<K, V> {
    private LinkedList<MapItem>[] lists;
    private int size;
    private int listsLength;
    private HashEngine hashEngine;

    /**
     * Construtor
     */
    public CustomMap() {
        this(100);
    }

    /**
     * Construtor
     * @param length Quantidade estimada de itens que serão armazenados no dicionário.
     */
    public CustomMap(int length) {
        this(length, new DefaultHashEngine());
    }

    /**
     * Construtor
     * @param length Quantidade estimada de itens que serão armazenados no dicionário.
     * @param hashEngine Objeto que contém a função de hash personalizada do usuário.
     */
    public CustomMap(int length, HashEngine hashEngine) {
        this.listsLength = (int)(length/0.75);  // fator de carga = 0.75
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
        Number hash = this.hashEngine.generateHash(key);
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
        this.lists[index].add(new MapItem<>(key, item));
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
        LinkedList<V> valuesList = new LinkedList<V>();

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

    /**
     * Obtém as colisões do dicionário.
     * @return Integer[] - Vetor com a quantidade de colisões em cada índice do HashMap.
     */
    public Integer[] getCollisions() {
        Integer[] collisions = new Integer[this.listsLength];
        for (int i = 0; i < this.listsLength; i++) {
            collisions[i] = this.lists[i].size();
        }
        return collisions;
    }

    /**
     * Exibe o gráfico de colisões.
     */
    public void showCollisionsDiagram() {
        Plot plot = Plot.create();
        plot.plot().add(Arrays.asList(this.getCollisions()));
        plot.xlabel("Índice da lista");
        plot.ylabel("Quantidade de entradas");
        plot.title("Colisões");
        try {
            plot.show();
        } catch (PythonExecutionException | IOException e) {
            e.printStackTrace();
        }
    }
}
