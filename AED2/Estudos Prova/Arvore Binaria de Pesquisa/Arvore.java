class No {
    public int elemento;
    public No esq, dir;

    public No(int x) {
        this.elemento = x;
        this.esq = this.dir = null;
    }
}

class Celula {
    public int elemento;
    public Celula prox;

    public Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }
}

class CelulaDupla {
    public int elemento;
    public CelulaDupla prox, ant;

    public CelulaDupla(int x) {
        this.elemento = x;
        this.ant = this.prox = null;
    }
}

class _ListaDupla {
    private CelulaDupla primeiro, ultimo;

    public _ListaDupla() {
        primeiro = new CelulaDupla(-1);
        ultimo = primeiro;
    }

    public CelulaDupla getPrimeiro() {
        return this.primeiro;
    }

    private int tamanho() {
        int j = 0;
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox, j++)
            ;

        return j;
    }

    public void inserirFim(int x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserirInicio(int x) {
        CelulaDupla tmp = new CelulaDupla(x);
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
            CelulaDupla i = primeiro;

            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            CelulaDupla tmp = new CelulaDupla(x);
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
        CelulaDupla tmp = primeiro.prox;
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
            CelulaDupla i = primeiro;

            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            CelulaDupla tmp = i.prox;
            elemento = tmp.elemento;
            i.prox = tmp.prox;
            i.prox.ant = i;

            i = tmp = tmp.prox = tmp.ant = null;

        }

        return elemento;
    }

    public void mostrar() {
        System.out.print("Elementos da Lista Dupla: [ ");
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }

    public void inverter() {
        CelulaDupla i = primeiro.prox;
        CelulaDupla j = ultimo;
        for (int k = 0; k < tamanho() / 2; k++, i = i.prox, j = j.ant) {
            int aux = i.elemento;
            i.elemento = j.elemento;
            j.elemento = aux;
        }

        System.out.println("Invertido!");
    }
}

class _Lista {
    private Celula primeiro, ultimo;

    public _Lista() {
        primeiro = new Celula(-1);
        ultimo = primeiro;
    }

    public Celula getPrimeiro() {
        return this.primeiro;
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

class _Arvore {
    private No raiz;

    public _Arvore() {
        this.raiz = null;
    }

    public void inserir(int x) {
        raiz = inserir(raiz, x);
    }

    private No inserir(No i, int x) {
        if (i == null)
            i = new No(x);
        else if (x < i.elemento)
            i.esq = inserir(i.esq, x);
        else if (x > i.elemento)
            i.dir = inserir(i.dir, x);
        else {
            System.out.println("Elemento ja inserido");
        }

        return i;
    }

    public void inserir2(int x) {
        if (raiz == null) {
            raiz = new No(x);
        } else if (x < raiz.elemento)
            inserir2(x, raiz.esq, raiz);
        else if (x > raiz.elemento)
            inserir2(x, raiz.dir, raiz);
    }

    private void inserir2(int x, No i, No pai) {
        if (i == null) {
            if (x < pai.elemento)
                pai.esq = new No(x);
            else
                pai.dir = new No(x);
        } else if (x < i.elemento)
            inserir2(x, i.esq, i);
        else if (x > i.elemento)
            inserir2(x, i.dir, i);
    }

    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(int x, No i) {
        boolean resp;
        if (i == null)
            resp = false;
        else if (i.elemento == x)
            resp = true;
        else if (x < i.elemento)
            resp = pesquisar(x, i.esq);
        else
            resp = pesquisar(x, i.dir);

        return resp;

    }

    public void remover(int x) {
        raiz = remover(x, raiz);
    }

    private No remover(int x, No i) {
        if (i == null)
            System.out.println("Estrutura Vazia!");
        else if (x < i.elemento)
            i.esq = remover(x, i.esq);
        else if (x > i.elemento)
            i.dir = remover(x, i.dir);
        else if (i.dir == null)
            i = i.esq;
        else if (i.esq == null)
            i = i.dir;
        else
            i.esq = anterior(i, i.esq);

        return i;
    }

    public void remover2(int x) {
        if (raiz == null) {
            System.out.println("Vazio!");
        } else if (x > raiz.elemento)
            remover2(x, raiz.dir, raiz);
        else if (x < raiz.elemento)
            remover2(x, raiz.esq, raiz);
        else if (raiz.dir == null)
            raiz = raiz.esq;
        else if (raiz.esq == null)
            raiz = raiz.dir;
        else {
            raiz.esq = anterior(raiz, raiz.esq);
        }
    }

    private void remover2(int x, No i, No pai) {
        if (i == null) {
            System.out.println("Nao existe esse elemento");
        } else if (x < i.elemento)
            remover2(x, i.esq, i);
        else if (x > i.elemento)
            remover2(x, i.dir, i);
        else if (i.dir == null) {
            if (x < pai.elemento) {
                pai.esq = i.esq;
            } else {
                pai.dir = i.esq;
            }
        } else if (i.esq == null) {
            if (x < pai.elemento) {
                pai.esq = i.dir;
            } else
                pai.dir = i.dir;
        } else
            i.esq = anterior(i, i.esq);
    }

    private No anterior(No i, No j) {
        if (j.dir != null)
            j.dir = anterior(i, j.dir);
        else {
            i.elemento = j.elemento;
            j = j.esq;
        }

        return j;
    }

    public void mostrar() {
        System.out.print("Elementos da Arvore: [ ");
        mostrar(raiz);
        System.out.println("]");
    }

    public void mostrar(No i) {
        if (i != null) {
            mostrar(i.esq);
            System.out.print(i.elemento + " ");
            mostrar(i.dir);
        }
    }

    public int soma() {
        return soma(raiz);
    }

    private int soma(No i) {
        int soma = 0;
        if (i != null) {
            soma = i.elemento + soma(i.esq) + soma(i.dir);
        }

        return soma;
    }

    public int pares() {
        return pares(raiz);
    }

    private int pares(No i) {
        int resp = 0;
        if (i != null) {
            if (i.elemento % 2 == 0)
                resp = 1 + pares(i.esq) + pares(i.dir);
            else {
                resp = 0 + pares(i.esq) + pares(i.dir);
            }
        }

        return resp;
    }

    public boolean equalTree(_Arvore b) {
        return equalTree(raiz, b.raiz);
    }

    private boolean equalTree(No a, No b) {
        boolean resp;
        if (a != null && b != null)
            resp = (a.elemento == b.elemento) && equalTree(a.esq, b.esq) && equalTree(a.dir, b.dir);
        else if (a == null && b == null) {
            resp = true;
        } else {
            resp = false;
        }

        return resp;
    }

    public boolean hasDiv11() {
        return hasDiv11(raiz);
    }

    private boolean hasDiv11(No i) {
        boolean resp = false;
        if (i != null) {
            if (i.elemento % 11 == 0)
                resp = true;
            else
                resp = hasDiv11(i.esq) || hasDiv11(i.dir);
        }

        return resp;
    }

    public static No toAB(Celula i, CelulaDupla j) {
        // ignorando de imediato o nó cabeça;
        Celula p1 = i.prox;
        CelulaDupla p2 = j.prox;
        No resp = null;

        // Aqui intercalo os elementos enquando existirem nas duas arvores
        // simultanemante
        while (p1 != null && p2 != null) {
            resp = insert(resp, p1.elemento);
            resp = insert(resp, p2.elemento);
            p1 = p1.prox;
            p2 = p2.prox;
        }

        // itero individualmente, na lista que sobrou
        while (p1 != null) {
            resp = insert(resp, p1.elemento);
            p1 = p1.prox;
        }

        while (p2 != null) {
            resp = insert(resp, p2.elemento);
            p2 = p2.prox;
        }

        return resp;
    }

    private static No insert(No i, int x) {
        if (i == null)
            i = new No(x);
        else if (x < i.elemento)
            i.esq = insert(i.esq, x);
        else if (x > i.elemento)
            i.dir = insert(i.dir, x);
        else {
        }

        return i;
    }

}

public class Arvore {
    public static void main(String[] args) {
        _Arvore arvore = new _Arvore();

        arvore.inserir(1);
        arvore.inserir(3);
        arvore.inserir(4);
        arvore.inserir(5);
        arvore.inserir(8);
        arvore.inserir(3);
        arvore.inserir(55);
        arvore.remover2(55);
        arvore.inserir2(111);

        arvore.mostrar();

        System.out.println("Soma dos elementos da arvore: " + arvore.soma());
        System.out.println("Quantidade de elementos pares na estrutura: " + arvore.pares());

        _Arvore arvore2 = new _Arvore();

        arvore2.inserir(1);
        arvore2.inserir(3);
        arvore2.inserir(4);
        arvore2.inserir(5);
        arvore2.inserir(8);
        arvore2.inserir(3);
        arvore2.inserir(6);

        boolean equal = arvore.equalTree(arvore2);

        System.out.println(equal ? "Arvores são iguais" : "Arvores não sao iguais");

        System.out.println(arvore.hasDiv11() ? "Possui algum divisivel de 11" : "Nao possui algum dividivel de 11");

        _Lista lista = new _Lista();

        lista.inserirFim(1);
        lista.inserirFim(3);
        lista.inserirFim(5);
        lista.inserirFim(7);

        _ListaDupla listaDupla = new _ListaDupla();

        listaDupla.inserirFim(2);
        listaDupla.inserirFim(4);
        listaDupla.inserirFim(6);
        listaDupla.inserirFim(8);

        No raiz = arvore.toAB(lista.getPrimeiro(), listaDupla.getPrimeiro());
        System.out.print("Elementos da Arvore Convertida: [ ");
        arvore.mostrar(raiz);
        System.out.println("]");
    }
}