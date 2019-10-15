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

    public Celula getTopo() {
        return this.topo;
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

class _Fila_sem_no {
    private Celula primeiro, ultimo;

    public _Fila_sem_no() {
        primeiro = ultimo = null;
    }

    /***
     * Na fila sem o no cabeça temos um if/else. Antes de proceder para a inserçao,
     * devemos verificar se o primeiro aponta para null. Caso aponte, devemos
     * somente criar esse primeiro elemento e apontar o ultimo para o mesmo. Isso é
     * similar ao criar o nó, porem este elemento é valido.
     * 
     * @param x
     */
    public void inserir(int x) {

        if (primeiro == null) {
            primeiro = new Celula(x);
            ultimo = primeiro;
        } else {
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
        }

    }

    public int remover() throws Exception {
        int elemento;
        if (primeiro == null)
            throw new Exception("Estrutura Vazia");
        else if (primeiro == ultimo) {
            elemento = primeiro.elemento;
            primeiro = ultimo = null;
        }
        // nesse else, sei que primeiro != ultimo
        else {
            elemento = primeiro.elemento;
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            tmp = tmp.prox = null;
        }

        return elemento;

    }

    public void mostrar() {
        System.out.print("Elementos da Fila sem Nó: [ ");
        for (Celula i = primeiro; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }
}

class _Fila {
    private Celula primeiro, ultimo;

    public _Fila() {
        primeiro = new Celula(-1);
        ultimo = primeiro;
    }

    public void inserir(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int remover() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Estrutura Vazia");

        Celula tmp = primeiro.prox;
        int elemento = tmp.elemento;
        if (primeiro.prox == ultimo)
            ultimo = primeiro;
        primeiro.prox = primeiro.prox.prox;

        tmp = tmp.prox = null;

        return elemento;
    }

    public void mostrar() {
        System.out.print("Elementos da Fila: [ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");
    }

    public int getMaior() {
        int maior = primeiro.prox.elemento;
        for (Celula i = primeiro.prox.prox; i != null; i = i.prox) {
            if (i.elemento > maior)
                maior = i.elemento;
        }

        return maior;
    }

    public void showTerceiro() throws Exception {
        if (primeiro.prox.prox.prox == null)
            throw new Exception("Terceiro Elemento não existe");
        System.out.println("Terceiro Elemento: " + primeiro.prox.prox.prox.elemento);
    }

    public int soma() {
        return soma(primeiro.prox);
    }

    public int soma(Celula i) {
        int soma = 0;
        if (i != null) {
            soma = i.elemento + soma(i.prox);
        }

        return soma;
    }

    /*
     * O funcionamento do metodo, apesar de parecer complicado, é bem simples
     * Guardamos no ponteiro fim o endereco da ultima posicao da fila Nosso laço vai
     * percorrer enquanto o nosso primeiro.prox, o primeiro elemento valido (apos o
     * no cabeca), for diferente do antigo ultimo. O ponteiro fim é fixo. Quando
     * forem iguais, quer dizer que nosso primeiro elemento agora é o ultimo, ou
     * seja, foi invertido. O interior do laço faz o seguinte: Cria-se uma nova
     * celula, com o conteudo atual da primeira posiçao. Nova.prox aponta para o
     * prox de fim, num primeiro momento esse valor será nulo, mas nas execucoes
     * seguintes não será. A chave do metodo esta justamente nas proximas execuçoes.
     * Simulando esse metodo em uma fila contendo elementos: [1, 2, 3] tem-se o
     * seguinte Fim aponta para 3; Celula 1 != Celula 3 - fim nova = Celula 1
     * nova.prox = fim.prox -- null neste momento fim.prox = nova -- Celula 1 neste
     * momento tmp = Celula 1 primeiro.prox = tmp.prox -- Celula 2 neste momento
     * novo tmp e tmp.prox nulos na proxima execucao... nova = Celula 2 novo.prox =
     * fim.prox -- sendo fixa, o fim esta no Celula 3 e seu prox no Celula 1
     * fim.prox = nova -- Celula 2 neste momento aqui temos um 3 que aponta para um
     * 2, que aponta para um 1 invertido! devemos somente lembrar de mover o
     * primeiro prox, para o primeiro.prox.prox agora temos nosso ponteiro
     * primeiro.prox em Fim saimos do while metodo finalizado
     * 
     * 
     */
    public void inverter() {
        Celula fim = ultimo;

        while (primeiro.prox != fim) {
            Celula nova = new Celula(primeiro.prox.elemento); // primeiro elemento, logo apos o no cabeça
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

    public int somar_m25() {
        return somar_m25(primeiro.prox);
    }

    public int somar_m25(Celula i) {
        int soma = 0;

        if (i != null) {
            if (i.elemento % 2 == 0 && i.elemento % 5 == 0)
                soma = i.elemento + somar_m25(i.prox);
        }

        return soma;
    }

    private static Celula newInsert(int x) {
        Celula i = new Celula(x);

        return i;
    }

    public static Celula toFila(Celula topo) {
        Celula primeiro = new Celula(-1);
        Celula ultimo = primeiro;

        for (Celula i = topo; i != null; i = i.prox) {
            ultimo.prox = newInsert(i.elemento);
            ultimo = ultimo.prox;
        }

        return primeiro;
    }
}

public class Fila {
    public static void main(String[] args) {
        _Fila fila = new _Fila();

        fila.inserir(0);
        try {
            fila.remover();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fila.inserir(1);
        fila.inserir(3);
        fila.inserir(5);
        fila.inserir(9);
        fila.inserir(7);
        fila.inserir(10);
        try {
            fila.remover();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fila.mostrar();

        System.out.println("Maior elemento: " + fila.getMaior());

        try {
            fila.showTerceiro();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Soma dos elementos: " + fila.soma());

        fila.inverter();

        fila.mostrar();

        System.out.println("Soma dos elementos pares multiplos de 5: " + fila.somar_m25());

        _Pilha pilha = new _Pilha();
        pilha.inserir(1);
        pilha.inserir(3);
        pilha.inserir(5);
        pilha.inserir(9);
        pilha.inserir(7);

        _Fila pilha_fila = new _Fila();

        Celula no_cabeca = pilha_fila.toFila(pilha.getTopo());

        System.out.print("Elementos da pilha em fila: [ ");
        for (Celula i = no_cabeca.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("]");

        _Fila_sem_no sem_no = new _Fila_sem_no();

        sem_no.inserir(0);
        try {
            sem_no.remover();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sem_no.inserir(1);
        sem_no.inserir(3);
        sem_no.inserir(5);
        sem_no.inserir(9);
        sem_no.inserir(7);
        sem_no.inserir(10);
        try {
            sem_no.remover();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sem_no.mostrar();

    }
}