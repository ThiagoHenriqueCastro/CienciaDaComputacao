public class Bloco {

    long endereco;
    int n;
    int[] id;
    long prox;

    public Bloco() {
        this.endereco = 0;
        this.n = 0;
        this.id = new int[10];
        for (int i = 0; i < 10; i++) {
            this.id[i] = 0;
        }
        this.prox = 0;
    }

}