package data.structures;

public interface ArvoreDeBuscaBinaria<T extends Comparable<T>> {


    void insert(T data);

    boolean exists(T dada);

    ArvoreDeBuscaBinaria<T> retrieve(T data);

    void preOrder();

    void inOrder();

    void posOrder();

    void bfs();
}
