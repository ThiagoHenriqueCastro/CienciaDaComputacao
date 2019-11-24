class Celula {
    public int elemento;
    public Celula prox;

    public Celula(int x) {
        this.elemento = x;
        this.prox = null;
    }
}

class _Lista {
    public Celula primeiro; // no cabeca
    public Celula ultimo;
    public int n; // contador de elementos

    public _Lista() {
        this.n = 0;
        this.primeiro = new Celula(-1);

        this.ultimo = primeiro;
    }

    public void inserir(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;

        n++;
    }

    public void mostrar() {
        Celula aux = primeiro.prox;
        System.out.println("Elementos da area de reserva (Lista dinamica simples): ");
        for (int i = 0; i < n; i++, aux = aux.prox)
            System.out.println(aux.elemento);

        aux = null;
    }
}

class No {
    public int elemento;
    public No esq;
    public No dir;

    public No(int x) {
        this.elemento = x;
        this.esq = this.dir = null;
    }
}

class _AB {
    public No raiz;

    public _AB() {
        this.raiz = null;
    }

    public void inserir(int x) {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) {
        if (i == null)
            i = new No(x);
        else if (i.elemento > x)
            i.esq = inserir(x, i.esq);
        else if (i.elemento < x)
            i.dir = inserir(x, i.dir);
        else
            System.out.println("Elemento ja inserido!");

        return i;
    }

    public void mostrar() {
        System.out.println("Elementos da area de reserva (Arvore Binaria): ");
        mostrar(raiz);
    }

    private void mostrar(No i) {
        if (i != null) {
            mostrar(i.esq);
            System.out.println(i.elemento);
            mostrar(i.dir);
        }

    }
}

class _Hash_Rehash {
    public int tabela[];
    public int m;
    public int NULO = -1;
    public _AB reserva;

    public _Hash_Rehash(int m) {
        this.m = m;
        this.tabela = new int[this.m];
        reserva = new _AB();
        // inicializar todas as posicoes com NULO
        for (int i = 0; i < m; i++) {
            tabela[i] = NULO;
        }
    }

    private int hash(int x) {
        return x % m;
    }

    private int rehash(int x) {
        return ++x % m;
    }

    public void inserir(int x) {
        int pos = hash(x);
        if (x != NULO) {
            // testo se a posicao esta livre insercao
            if (tabela[pos] == NULO)
                tabela[pos] = x;
            else {
                pos = rehash(x);
                if (tabela[pos] == NULO) {
                    tabela[pos] = x;
                }
                // se nao consigo inserir por rehash, insiro na arvore binaria
                else {
                    reserva.inserir(x);
                }
            }
        }

    }

    public void mostrar() {
        System.out.println(
                "Elementos da area de reserva 1 (Hash com rehash e area de reserva arvore binaria). Elementos da area principal: ");
        for (int i = 0; i < m; i++) {

            System.out.println(tabela[i]);
        }

        reserva.mostrar();

    }
}

class _Hash_Doidona {
    public int tabela[];
    public int m;
    public int NULO;
    public _Hash_Rehash r1;
    public _Lista r2;
    public _AB r3;

    public _Hash_Doidona(int m) {
        this.NULO = -1;
        this.m = m;
        tabela = new int[this.m];
        r1 = new _Hash_Rehash(m);
        r2 = new _Lista();
        r3 = new _AB();

        for (int i = 0; i < m; i++)
            tabela[i] = NULO;
    }

    public int hash(int x) {
        return x % m;
    }

    public int rehash(int x) {
        return x % 3;
    }

    public void inserir(int x) {
        int pos = hash(x);

        if (tabela[pos] == NULO)
            tabela[pos] = x;
        else {
            pos = rehash(x);
            if (pos == 0)
                r1.inserir(x);
            else if (pos == 1)
                r2.inserir(x);
            else {
                r3.inserir(x);
            }
        }
    }

    public void mostrar() {
        System.out.println("Elementos da hash principal: ");
        for (int i = 0; i < m; i++) {

            System.out.println(tabela[i]);
        }

        r1.mostrar();
        r2.mostrar();
        r3.mostrar();
    }
}

class Hash {
    public static void main(String[] args) {
        _Hash_Doidona hash = new _Hash_Doidona(10);
        hash.inserir(0);
        hash.inserir(1);
        hash.inserir(2);
        hash.inserir(3);
        hash.inserir(4);
        hash.inserir(5);
        hash.inserir(6);
        hash.inserir(7);
        hash.inserir(8);
        hash.inserir(9);
        hash.inserir(10);
        hash.inserir(11);
        hash.inserir(12);
        hash.inserir(13);
        hash.inserir(14);
        hash.inserir(15);
        hash.inserir(16);
        hash.inserir(17);
        hash.inserir(18);
        hash.inserir(19);
        hash.inserir(20);
        hash.inserir(21);
        hash.inserir(22);
        hash.inserir(23);
        hash.inserir(24);
        hash.inserir(25);
        hash.inserir(26);
        hash.inserir(27);
        hash.inserir(28);
        hash.inserir(29);
        hash.inserir(30);
        hash.inserir(31);
        hash.inserir(32);
        hash.inserir(33);
        hash.inserir(34);
        hash.inserir(35);
        hash.inserir(36);
        hash.inserir(37);
        hash.inserir(38);
        hash.inserir(39);
        hash.inserir(40);
        hash.inserir(41);
        hash.inserir(42);
        hash.inserir(43);
        hash.inserir(44);
        hash.inserir(45);
        hash.inserir(46);
        hash.inserir(47);
        hash.inserir(48);
        hash.inserir(49);

        hash.mostrar();
    }
}