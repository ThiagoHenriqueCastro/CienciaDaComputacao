import java.io.*;

public class Main {
    public static void main(String[] args) {

        // TESTES DE IMPLEMENTAÇÃO APENAS!

        Usuario user1 = new Usuario(1, "Thiago Henrique", "titico@titico.com", "abc");
        Usuario user2 = new Usuario(2, "Lucas Spartacus", "spartacus@spartacus.com", "def");

        RandomAccessFile arq;

        try {
            arq = new RandomAccessFile("dados/dados.db", "rw");

            // uso o write para escrever no arquivo e uso o metodo toByteArray para me
            // devolver o fluxo de bytes da classe
            arq.write(user1.toByteArray());
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}