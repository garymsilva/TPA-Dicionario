import CustomMap.*;

public class Main {
    public static void main(String[] args) {

        CustomMap<Integer> map = new CustomMap<Integer>(10);
        map.add("um", 1);
        map.add("dois", 2);
        map.add("tres", 3);
        map.add("quatro", 4);

        try {
            int item = map.find("quatro");
            System.out.println(item);
            map.remove("quatro");
            try {
                item = map.find("quatro");
                System.out.println(item);
            } catch (Exception e) {
                System.out.println("Objeto não encontrado!");
            }
        } catch (Exception e) {
            System.out.println("Objeto não encontrado!");
        }
    }
}
