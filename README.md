# HashMap - TPA
Dicionário baseado em hash construído com Java na disciplina de Técnicas de Programação Avançada do curso de Bacharelado em Sistemas de Informação do [Ifes - *Campus* Serra](http://www.serra.ifes.edu.br/).

## Modelo
![model](/docs/class_diagram.png)

## Construtores
|Construtor|Descrição|
|-|-|
|`CustomMap()`|Inicializa com `lenght` e `hashEngine` padrões.|
|`CustomMap(int lenght)`|Inicializa com `hashEngine` padrão.|
|`CustomMap(int lenght, HashEngine hashEngine)`|Inicializa com `lenght` e [`hashEngine`](#usando-um-hashengine-customizado) do usuário|

### Usando um HashEngine customizado

``` Java
import CustomMap.HashEngine

class MyHashEngine implements HashEngine {
    @Override
    public Integer generateHash(String key) {
        /* sua lógica de hashing */
    }
}

```
A função de hash deve retornar um herdeiro de Number. <br>
[Leia mais: `java.lang.Number`](https://docs.oracle.com/javase/7/docs/api/java/lang/Number.html)

## Interface

|Método|Descrição|Retorno|
|-|-|-|
|`add()`|Adiciona um item ao dicionário.|`void`|
|`find()`|Procura um item no dicionário.|`V` ou `null`|
|`remove()`|Procura e remove um item do dicionário.|`V` ou `null`|
|`size()`|Informa a quantidade de itens no dicionário.|`int`|
|`isEmpty()`|Informa se o dicionário está vazio.|`boolean`|
|`keys()`|Obtém uma coleção com as chaves armazenadas.|não implementado|
|`values()`|Obtém uma coleção com os itens armazenados.|não implementado|
|`entries()`|Obtém uma coleção com as entradas chave->valor armazenadas.|não implementado|
