import CustomMap.*;

class Pessoa {
    private String nome;
    private int idade;

    Pessoa(String n, int i) {
        this.idade = i;
        this.nome = n;
    }

}

public class Main {
    public static void main(String[] args) {

        CustomMap<Pessoa, Integer> map = new CustomMap<Pessoa, Integer>(10);
        Pessoa aluno1 = new Pessoa("aluno1", 15);
        Pessoa aluno2 = new Pessoa("aluno2", 15);
        Pessoa aluno3 = new Pessoa("aluno3", 15);
        Pessoa aluno4 = new Pessoa("aluno4", 15);
        Pessoa aluno5 = new Pessoa("aluno5", 15);
        map.add(aluno1, 1);
        map.add(aluno2, 2);
        map.add(aluno3, 3);
        map.add(aluno4, 4);
        map.add(aluno5, 5);

        try {
            int item = map.find(aluno2);
            System.out.println("item encontrado"+item);
            for (Integer i : map.values()) {
                System.out.println("item: "+i);
            }
            map.remove(aluno3);
            for (Integer i : map.values()) {
                System.out.println("item: "+i);
            }
        } catch (Exception e) {
            System.out.println("Objeto não encontrado!");
        }
    }
}
