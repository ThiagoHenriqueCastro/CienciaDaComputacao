import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * TP01Q10 Arquivo
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Le o arquivo e o imprime de traz para frente na saida
 *          padrao
 */

class TP01Q10Arquivo {
    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");
        int size = MyIO.readInt(); // tamanho do arquivo
        double num;
        // abre o arquivo para escrita e transcreve a entrada padrao para o mesmo
        try {
            RandomAccessFile file = new RandomAccessFile("file.in", "rwd");
            for (int i = 0; i < size; i++) {
                num = MyIO.readDouble();
                file.writeDouble(num);
            }
            // pega o ponteiro do arquivo menos o byte
            long position = file.getFilePointer() - 8;
            file.close();

            // reabre o arquivo para leitura
            file = new RandomAccessFile("file.in", "r");

            double n;
            // desce o ponteiro byte por byte transcrevendo na saida o arquivo ao contrario
            while (position >= 0) {
                file.seek(position);
                n = file.readDouble();
                if (n - (int) n == 0)
                    MyIO.println((int) n);
                else
                    MyIO.println(n);

                position -= 8;

            }

            file.close();

        } catch (IOException ex) {
            ex.printStackTrace(); // printa a exceçao
        }

    }

}