class Celula {
    public int elemento;
    public Celula ant, prox;

    public Celula(int x) {
        this.elemento = x;
        this.prox = this.ant = null;
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
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        tmp.ant = primeiro;

        if (primeiro == ultimo)
            ultimo = tmp;
        else
            tmp.prox.ant = tmp;

        tmp = null;
    }

    public void inserir(int x, int pos) {
        if (pos == 0)
            inserirInicio(x);
        else if (pos == tamanho())
            inserirFim(x);
        else {
            Celula i = primeiro;

            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox.ant = tmp;
            i.prox = tmp;
            tmp.ant = i;

            tmp = i = null;
        }
    }

    public int removerInicio() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Estrutura Vazia");
        Celula tmp = primeiro.prox;
        int elemento = tmp.elemento;
        primeiro.prox = tmp.prox;
        tmp.prox.ant = primeiro;

        tmp = tmp.prox = tmp.ant = null;

        return elemento;
    }

    public int removerFim() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Estrutura Vazia");
        int elemento = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox = ultimo.prox.ant = null;

        return elemento;
    }

    public int remover(int pos) throws Exception {
        int elemento;
        if (primeiro == ultimo || pos < 0 || pos > tamanho() - 1)
            throw new Exception("Estrutura vazia ou posiçao inexistente");
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
            i.prox.ant = i;

            i = tmp = tmp.prox = tmp.ant = null;

        }

        return elemento;
    }

    public void mostrar() {
        System.out.print("Elementos da Lista Dupla: [ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }

    public void inverter() {
        Celula i = primeiro.prox;
        Celula j = ultimo;
        for (int k = 0; k < tamanho() / 2; k++, i = i.prox, j = j.ant) {
            int aux = i.elemento;
            i.elemento = j.elemento;
            j.elemento = aux;
        }

        System.out.println("Invertido!");
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

        lista.mostrar();

        lista.inverter();

        lista.mostrar();

    }
}