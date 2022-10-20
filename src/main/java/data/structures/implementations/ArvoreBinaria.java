package data.structures.implementations;

import data.structures.interfaces.ArvoreDeBuscaBinaria;

public class ArvoreBinaria<T extends Comparable<T>> implements ArvoreDeBuscaBinaria<T> {

    private T data;
    private ArvoreDeBuscaBinaria<T> esquerdo;
    private ArvoreDeBuscaBinaria<T> direiro;

    @Override
    public void insert(T data) {

    }

    @Override
    public boolean exists(T dada) {
        return false;
    }

    @Override
    public ArvoreDeBuscaBinaria<T> retrieve(T data) {
        return null;
    }

    @Override
    public void preOrder() {

    }

    @Override
    public void inOrder() {

    }

    @Override
    public void posOrder() {

    }

    @Override
    public void bfs() {

    }
}
