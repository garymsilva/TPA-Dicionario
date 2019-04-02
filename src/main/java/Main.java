import CustomMap.*;

public class Main {
    public static void main(String[] args) {
        CustomMap<String, String> map = new CustomMap<String, String>();
        map.add("um", "1");
        map.add("dois", "2");
        map.add("três", "3");
        map.add("quatro", "4");
        map.add("cinco", "5");
        map.add("seis", "6");
        map.add("sete", "7");

        map.showCollisionsDiagram();
    }
}
