package data.structures;

public interface Busca<T extends Comparable<T>> {

    int buscaSequencial(T[] elements);

    int buscaBinaria(T[] elements);
}
