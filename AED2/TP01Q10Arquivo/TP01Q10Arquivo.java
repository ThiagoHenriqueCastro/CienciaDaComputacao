import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * TP01Q09 HTML
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Algoritmo que verifica ocorrencias de strings em paginas
 *          html.
 */

class TP01Q10Arquivo {
    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");
        int size = MyIO.readInt();
        double num;
        try {
            RandomAccessFile file = new RandomAccessFile("file.in", "rwd");
            for (int i = 0; i < size; i++) {
                num = MyIO.readDouble();
                file.writeDouble(num);
            }
            long position = file.getFilePointer() - 8;
            file.close();

            file = new RandomAccessFile("file.in", "r");

            double n;
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
            ex.printStackTrace();
        }

    }

}