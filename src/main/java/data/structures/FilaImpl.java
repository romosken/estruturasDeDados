package data.structures;


public class FilaImpl<T> implements Fila<T> {

    private final T[] elementos;
    private int proximo;
    private int ultimo;

    public FilaImpl(int size) {
        if (size <= 0)
            throw new RuntimeException("Tamanho inválido!");

        this.elementos = (T[]) new Object[size];
        this.proximo = this.ultimo = -1;
    }


    @Override
    public void enqueue(T data) {
        if (this.isFull())
            throw new RuntimeException("A fila está cheia!");

        if (this.isEmpty())
            this.proximo++;

        if (this.ultimo == this.size() - 1) {
            this.ultimo = -1;
        }

        this.elementos[++ultimo] = data;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty())
            throw new RuntimeException("A fila está vazia!");

        var retorno = this.elementos[this.proximo];
        this.elementos[this.proximo] = null;

        if (this.proximo == this.ultimo) {
            this.proximo = this.ultimo = -1;
            return retorno;
        }

        if (this.proximo == this.size() - 1) {
            this.proximo = 0;
            return retorno;
        }

        this.proximo++;
        return retorno;
    }

    @Override
    public T front() {
        if (this.isEmpty())
            return null;
        return elementos[proximo];
    }
    public T rear() {
        if (this.isEmpty())
            return null;
        return elementos[ultimo];
    }
    @Override
    public int size() {
        return elementos.length;
    }

    @Override
    public boolean isEmpty() {
        return proximo < 0;
    }

    @Override
    public boolean isFull() {
        return ((proximo == 0 && ultimo == size() - 1) || ultimo == proximo - 1);
    }

    @Override
    public int getIndexFront() {
        return proximo;
    }

    @Override
    public int getIndexRear() {
        return ultimo;
    }



    public static void main(String[] args) {
        System.out.println(6 % 5);
    }
}
