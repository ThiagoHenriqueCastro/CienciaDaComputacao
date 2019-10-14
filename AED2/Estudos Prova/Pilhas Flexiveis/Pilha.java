class Celula {
    public int elemento;
    public Celula prox;

    public Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }
}

class _Pilha {
    private Celula topo;

    public _Pilha() {
        this.topo = null;
    }

    public void inserir(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public int remover() throws Exception {
        if (topo == null)
            throw new Exception("Estrutura vazia!");

        Celula tmp = topo;
        topo = topo.prox;
        int elemento = tmp.elemento;
        tmp = tmp.prox = null;

        return elemento;
    }

    public void mostrar() {
        System.out.print("Elementos: [ ");
        for (Celula i = topo; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }

    public int soma() {
        int soma = 0;
        for (Celula i = topo; i != null; i = i.prox)
            soma += i.elemento;

        return soma;
    }

    public int somarRec() {
        return somarRec(topo);
    }

    public int somarRec(Celula i) {
        int soma = 0;
        if (i != null) {
            soma = i.elemento + somarRec(i.prox);
        }

        return soma;
    }

    public int getMaior() {
        int maior = topo.elemento;
        for (Celula i = topo.prox; i != null; i = i.prox) {
            if (i.elemento > maior)
                maior = i.elemento;
        }

        return maior;
    }

    public int getMaior_Recursivo() {
        return getMaior_Recursivo(topo.elemento, topo.prox);
    }

    public int getMaior_Recursivo(int maior, Celula i) {
        int _maior = maior;
        if (i != null) {
            if (i.elemento > maior) {
                _maior = getMaior_Recursivo(i.elemento, i.prox);
            } else {
                _maior = getMaior_Recursivo(maior, i.prox);
            }

        }

        return _maior;
    }

    public int getTamanho() {
        int j = 0;

        for (Celula i = topo; i != null; i = i.prox, j++)
            ;

        return j;
    }

    public void mostrar_insercao_recursivo() {
        System.out.print("Elementos em ordem de insercao (Recursivo): [ ");
        mostrar_insercao_recursivo(topo);
        System.out.println("]");
    }

    public void mostrar_insercao_recursivo(Celula i) {
        if (i != null) {
            mostrar_insercao_recursivo(i.prox);
            System.out.print(i.elemento + " ");
        }
    }
}

public class Pilha {
    public static void main(String[] args) {

        _Pilha pilha = new _Pilha();

        pilha.inserir(1);
        pilha.inserir(3);
        pilha.inserir(5);
        pilha.inserir(9);
        pilha.inserir(7);

        pilha.mostrar();

        System.out.println("Soma: " + pilha.soma());

        System.out.println("Soma Recursiva: " + pilha.somarRec());

        System.out.println("Maior Elemento: " + pilha.getMaior());

        System.out.println("Maior Elemento Recursivo: " + pilha.getMaior_Recursivo());

        pilha.mostrar_insercao_recursivo();

    }
}