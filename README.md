# HashMap - TPA
Dicionário baseado em hash construído com Java na disciplina de Técnicas de Programação Avançada do curso de Bacharelado em Sistemas de Informação do [Ifes - *Campus* Serra](http://www.serra.ifes.edu.br/).

## Modelo
![model](/docs/class_diagram.png)

>**K**: Tipo do dado que será usado como chave. <br>
**V**: Tipo do dado que será armazenado no dicionário.

## Construtores
|Construtor|Descrição|
|-|-|
|`CustomMap()`|Inicializa com `lenght` e `hashEngine` padrões.|
|`CustomMap(int lenght)`|Inicializa com `hashEngine` padrão.|
|`CustomMap(int lenght, HashEngine hashEngine)`|Inicializa com `lenght` e [`hashEngine`](#usando-um-hashengine-customizado) do usuário|

### Usando um HashEngine customizado

Crie uma classe que implemente a interface `HashEngine`, criando um método `generatehash()` próprio, que pode receber qualquer classe no parâmetro `key` e deve retornar um `Number`.

``` Java
import CustomMap.HashEngine

class MyHashEngine implements HashEngine {
    @Override
    public Integer generateHash(String key) {
        /* sua lógica de hashing */
    }
}

```
[Leia mais sobre o Number: `java.lang.Number`](https://docs.oracle.com/javase/7/docs/api/java/lang/Number.html)

## Métodos do dicionário

|Método|Descrição|Retorno|
|-|-|-|
|`add()`|Adiciona um item ao dicionário.|`void`|
|`find()`|Procura um item no dicionário.|`V` ou `null`|
|`remove()`|Procura e remove um item do dicionário.|`V` ou `null`|
|`size()`|Informa a quantidade de itens no dicionário.|`int`|
|`isEmpty()`|Informa se o dicionário está vazio.|`boolean`|
|`keys()`|Obtém uma coleção com as chaves armazenadas.|`LinkedList<K>`|
|`values()`|Obtém uma coleção com os itens armazenados.|`LinkedList<V>`|
|`entries()`|Obtém uma coleção com as entradas chave->valor armazenadas.|não implementado|
