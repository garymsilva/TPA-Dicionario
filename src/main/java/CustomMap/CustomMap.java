package CustomMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import com.github.sh0nk.matplotlib4j.*;

public class CustomMap<K, V> {
    private LinkedList<MapItem>[] lists;
    private int size;
    private int declaredSize;
    private int listsLength;
    private HashEngine hashEngine;

    private boolean foundMapItem = false;

    /**
     * Construtor
     */
    public CustomMap() {
        this(100);
    }

    /**
     * Construtor
     * @param mapLength Quantidade estimada de itens que serão armazenados no dicionário.
     */
    public CustomMap(int mapLength) {
        this(mapLength, new DefaultHashEngine());
    }

    /**
     * Construtor
     * @param mapLength Quantidade estimada de itens que serão armazenados no dicionário.
     * @param hashEngine Objeto que contém a função de hash personalizada do usuário.
     */
    public CustomMap(int mapLength, HashEngine hashEngine) {
        this.hashEngine = hashEngine;
        this.initLists(mapLength);
        this.size = 0;
    }

    /**
     * Inicializa o TAD do dicionário.
     * @param declaredSize Quantidade estimada de itens que serão armazenados no dicionário.
     */
    private void initLists(int declaredSize) {
        this.declaredSize = declaredSize;
        this.listsLength = (int)(declaredSize/0.75);  // fator de carga = 0.75
        this.lists = new LinkedList[this.listsLength];
        for (int index = 0; index < this.listsLength; index++) this.lists[index] = new LinkedList<MapItem>();
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
                    this.foundMapItem = true;
                    return item;
                }
                i++;
            }
        }
        this.foundMapItem = false;
        return null;
    }

    /**
     * Recalcula a quantidade ideal de listas encadeadas e reconstrói o dicionário.
     */
    private void resize() {
        LinkedList<MapItem>[] listsBackup = this.lists;
        this.initLists(this.size);

        for (LinkedList<MapItem> list: listsBackup) {
            for (MapItem<K, V> item : list) {
                this.insert(item.getKey(), item.getValue());
            }
        }
    }

    /**
     * Adiciona um item ao dicionário.
     * @param key Chave referência.
     * @param value Valor a ser armazenado.
     */
    public void insert(K key, V value) {
        int index = this.getIndex(key);
        MapItem<K, V> item = new MapItem<>(key, value);
        this.lists[index].add(item);
        this.size++;

        // quando houver mais elementos que listas encadeadas, ocorrerá o redimensionamento
        if (this.size > this.listsLength) {
            this.resize();
        }
    }

    /**
     * Procura por um item no dicionário, a partir da key.
     * @param key Chave referência.
     * @return V, se encontrar. null, caso contrário.
     */
    public V find(K key) {
        MapItem<K, V> result = this.findMapItem(key);
        if (result == null) return null;

        return result.getValue();
    }

    /**
     * Procura um item e o remove do dicionário.
     * @param key Chave referência.
     * @return V, se encontrar. null, caso contrário.
     */
    public V remove(K key) {
        MapItem<K, V> mapItem = this.findMapItem(key);

        // se o item não foi encontrado, retorna null
        if (mapItem == null) return null;

        int index = this.getIndex(key);
        this.lists[index].remove(mapItem);
        return mapItem.getValue();
    }

    /**
     * Diz se o dicionário está vazio.
     * @return true, caso vazio, false caso contrário.
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Informa a quantidade de entradas no dicionário.
     * @return número de entradas.
     */
    public int size() {
        return this.size;
    }

    /**
     * Retorna se a chave foi encontrada no dicionário após a execução do find.
     * @return true se foi encontrada, false caso contrário.
     */
    public boolean NO_SUCH_KEY() {
        return !this.foundMapItem;
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
     * Instancia um novo dicionário clone do original.
     * @return CustomMap
     */
    public CustomMap<K, V> clone() {
        CustomMap<K, V> clone = new CustomMap<K, V>(this.declaredSize, this.hashEngine);
        MapItem<K, V> item;

        for (K key : this.keys()) {
            item = this.findMapItem(key);
            clone.insert(item.getKey(), item.getValue());
        }

        return clone;
    }

    /**
     * Compara este dicionário com a fonte fornecida.
     * @param source Dicionário a ser comparado.
     * @return true, se os dois dicionários forem iguais, false, caso contrário.
     */
    public boolean equals(CustomMap source) {
        if (this.size != source.size()) return false;   // se não tiver o mesmo tamanho -> dicionários diferentes
        for (K key : this.keys()) {                     // procura cada chave deste dicionário, no outro
            Object value = source.find(key);
            if (source.NO_SUCH_KEY()) return false;     // se não encontrar algo -> dicionários diferentes
            if (value != this.find(key)) return false;  // se encontrar, compara os valores obtidos
        }
        return true;
    }

    /**
     * Retorna uma coleção com as entradas {chave, valor} armazenadas no dicionário.
     */
    private LinkedList<Object> entries() {
        // TODO entries
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
