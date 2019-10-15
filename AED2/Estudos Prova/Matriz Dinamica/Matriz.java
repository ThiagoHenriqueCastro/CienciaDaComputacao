class Celula {
    public int elemento;
    public Celula esq, dir, sup, inf;

    public Celula(int x) {
        this.elemento = x;
        this.esq = this.dir = this.sup = this.inf = null;
    }
}

class _Matriz {
    private Celula inicio;
    private int linha, coluna;

    public _Matriz(int linha, int coluna) {
        this.inicio = new Celula(-1);
        this.linha = linha;
        this.coluna = coluna;
        alocarMatriz(this.linha, this.coluna);
    }

    public void alocarMatriz(int linha, int coluna) {
        Celula celulaX = null;
        Celula celulaY = null;

        int x = 0;
        int y = 0;

        for (Celula i = inicio; x < linha; x++, i = celulaX) {
            y = 0;
            for (Celula j = i; y < coluna - 1; y++, j = celulaY) {
                celulaY = new Celula(-1);
                j.dir = celulaY;
                celulaY.esq = j;
                if (celulaY.esq != null && celulaY.esq.sup != null) {
                    celulaY.sup = celulaY.esq.sup.dir;
                    celulaY.esq.sup.dir.inf = celulaY;
                }
            }
            if (x < linha - 1) {
                celulaX = new Celula(-1);
                i.inf = celulaX;
                celulaX.sup = i;
            }
        }
    }

    // Aqui percorro linhas e colunas
    // A cada linha, percorro suas colunas
    // A cada linha, leio a linha da entrada
    // Separo os espacos e coloco em cada coluna da linha seu valor
    public void ler() {
        String[] entry = new String[this.linha];

        for (Celula i = this.inicio; i != null; i = i.inf) {
            int x = 0;
            String line = MyIO.readLine();
            entry = line.split(" ");
            for (Celula j = i; j != null; j = j.dir, x++) {
                j.elemento = Integer.parseInt(entry[x]);
            }
        }
    }

    public void mostrar() {
        System.out.println("Matriz: [");
        for (Celula i = this.inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                System.out.print(j.elemento + " ");
            }

            System.out.println("");
        }
        System.out.println("]");
    }

    public boolean isQuadrada() {
        boolean resp = false;

        if (this.linha == this.coluna)
            resp = true;

        return resp;
    }

    public void showDiagonalPrincipal() {
        if (isQuadrada()) {
            System.out.println("Diagonal Principal: ");
            Celula i = this.inicio;

            while (i != null) {
                System.out.print(i.elemento + " ");
                if (i.inf != null) {
                    i = i.inf.dir;
                } else {
                    i = null;
                }
            }

            System.out.println("");

        }
    }

    public void showDiagonalSecundaria() {
        if (isQuadrada()) {
            System.out.println("Diagonal Secundaria: ");
            Celula i;
            for (i = inicio; i.dir != null; i = i.dir)
                ;

            while (i != null) {
                System.out.print(i.elemento + " ");
                if (i.inf != null) {
                    i = i.inf.esq;
                } else
                    i = null;
            }

            System.out.println("");
        }
    }

    public boolean sameType(_Matriz b) {
        return (this.linha == b.linha && this.coluna == b.coluna);
    }

    public _Matriz soma(_Matriz matriz) {
        System.out.print("Soma ");
        _Matriz soma = null;
        if (sameType(matriz)) {
            soma = new _Matriz(this.linha, this.coluna);
            for (Celula i = soma.inicio, a = this.inicio, b = matriz.inicio; i != null; i = i.inf, a = a.inf, b = b.inf) {
                for (Celula j = i, auxA = a, auxB = b; j != null; j = j.dir, auxA = auxA.dir, auxB = auxB.dir)
                    j.elemento = auxA.elemento + auxB.elemento;
            }
        }
        return soma;
    }

    /**
     * Codigo composto por 3 laços O primeiro itera entre as linhas da minha matriz
     * de retorno O segundo itera entre as colunas Sempre que mundo de linha, minha
     * primeira matriz tambem muda Sempre que mudo de coluna, minha segunda matriz
     * tambem muda O terceiro for itera entre as matrizes multiplicada, para cada
     * coluna da primeira uma linha da segunda Esses valores sao somados na posicao
     * da matriz de resposta
     * 
     * @param matriz
     * @return
     */
    public _Matriz multiplicacao(_Matriz matriz) {
        System.out.print("Multiplicacao ");
        _Matriz multiplicacao = null;
        if (this.coluna == matriz.linha) {
            multiplicacao = new _Matriz(this.linha, matriz.coluna);
            for (Celula i = multiplicacao.inicio, a = this.inicio, b = matriz.inicio; i != null; i = i.inf, a = a.inf, b = matriz.inicio) {
                for (Celula j = i; j != null; j = j.dir, b = b.dir) {
                    for (Celula auxA = a, auxB = b; auxA != null; auxA = auxA.dir, auxB = auxB.inf) {
                        j.elemento += auxA.elemento * auxB.elemento;
                    }
                    j.elemento++;
                }
            }
        }
        return multiplicacao;
    }
}

public class Matriz {
    public static void main(String[] args) {
        int testes = MyIO.readInt();
        _Matriz a, b, soma, multiplicacao;

        int linha, coluna;
        for (int i = 0; i < testes; i++) {
            // Primeira Matriz
            linha = MyIO.readInt();
            coluna = MyIO.readInt();

            a = new _Matriz(linha, coluna);
            a.ler();
            a.mostrar();
            a.showDiagonalPrincipal();
            a.showDiagonalSecundaria();

            // Segunda Matriz
            linha = MyIO.readInt();
            coluna = MyIO.readInt();

            b = new _Matriz(linha, coluna);
            b.ler();

            soma = a.soma(b);
            soma.mostrar();

            multiplicacao = a.multiplicacao(b);
            multiplicacao.mostrar();

        }
    }
}