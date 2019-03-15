import java.util.LinkedList;

public class CustomMap {
    private LinkedList<MapItem>[] lists;

    CustomMap(int length) {
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

    private String generateHash(MapItem item) {
        // TODO: criar lógica de criação de hash
        return "";
    }

    public void addItem(MapItem item) {
        // TODO: encontrar o melhor lugar pra guardar o item e persistir.
    }



}
