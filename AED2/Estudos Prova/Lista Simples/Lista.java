class Celula {
    public int elemento;
    public Celula prox;

    public Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }
}

class _Lista {
    private Celula primeiro, ultimo;

    public _Lista() {
        primeiro = new Celula(-1);
        ultimo = primeiro;
    }

    private int tamanho() {
        int j = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox, j++)
            ;

        return j;
    }

    public void inserirFim(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if (primeiro == ultimo) {
            ultimo = tmp;
        }

        tmp = null;

    }

    public void inserir(int x, int pos) {

        if (pos == 0)
            inserirInicio(x);
        else if (pos == tamanho())
            inserirFim(x);
        else {
            Celula j = primeiro;
            for (int i = 0; i < pos; i++, j = j.prox)
                ;
            Celula tmp = new Celula(x);
            tmp.prox = j.prox;
            j.prox = tmp;
            tmp = j = null;
        }

    }

    public int removerInicio() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Estrutura Vazia");

        Celula tmp = primeiro.prox;
        primeiro.prox = tmp.prox;
        int elemento = tmp.elemento;
        tmp = tmp.prox = null;

        return elemento;
    }

    public int removerFim() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Estrutura Vazia");

        Celula i;

        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;

        int elemento = ultimo.elemento;

        ultimo = i;
        i = ultimo.prox = null;

        return elemento;
    }

    public int remover(int pos) throws Exception {
        int elemento = 0;
        if (primeiro == ultimo || pos < 0 || pos > tamanho() - 1)
            throw new Exception("Estrutura Vazia");
        else if (pos == 0)
            elemento = removerInicio();
        else if (pos == tamanho() - 1)
            elemento = removerFim();
        else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = i.prox;
            elemento = tmp.elemento;
            i.prox = tmp.prox;
            i = tmp = tmp.prox = null;

        }

        return elemento;
    }

    public int remover_2() throws Exception {
        int elemento = 0;
        if (primeiro == ultimo)
            throw new Exception("Estrutura Vazia");
        else if (tamanho() == 2)
            elemento = removerFim();
        else {
            Celula i = primeiro.prox;
            Celula tmp = i.prox;
            elemento = tmp.elemento;
            i.prox = tmp.prox;

            tmp = tmp.prox = i = null;
        }

        return elemento;
    }

    public void inverter() {
        Celula fim = ultimo;

        while (primeiro.prox != fim) {
            Celula nova = new Celula(primeiro.prox.elemento);
            nova.prox = fim.prox;
            fim.prox = nova;
            Celula tmp = primeiro.prox;
            primeiro.prox = tmp.prox;
            nova = tmp = tmp.prox = null;

            if (ultimo == fim)
                ultimo = ultimo.prox;
        }

        fim = null;

        System.out.println("Invertido!");
    }

    public void mostrar() {
        System.out.print("Elementos da Lista: [ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }
}

public class Lista {
    public static void main(String[] args) {

        _Lista lista = new _Lista();

        lista.inserirFim(1);
        lista.inserirFim(3);
        lista.inserirFim(5);
        lista.inserirFim(7);
        try {
            lista.removerInicio();
            lista.removerFim();
            lista.remover(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lista.inserirFim(10);
        lista.inserirFim(12);
        lista.inserirFim(14);
        lista.inserir(16, 2);

        try {
            lista.remover_2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        lista.mostrar();

        lista.inverter();

        lista.mostrar();

    }
}