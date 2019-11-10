import java.io.*;
import java.nio.charset.*;

class MyIO {

    private static BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
    private static String charset = "ISO-8859-1";

    public static void setCharset(String charset_) {
        charset = charset_;
        in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
    }

    public static void print() {
    }

    public static void print(int x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void print(double x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void print(String x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void print(boolean x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void print(char x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void println() {
    }

    public static void println(int x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void println(double x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void println(String x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void println(boolean x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void println(char x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static void printf(String formato, double x) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.printf(formato, x);// "%.2f"
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
    }

    public static double readDouble() {
        double d = -1;
        try {
            d = Double.parseDouble(readString().trim().replace(",", "."));
        } catch (Exception e) {
        }
        return d;
    }

    public static double readDouble(String str) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
        return readDouble();
    }

    public static float readFloat() {
        return (float) readDouble();
    }

    public static float readFloat(String str) {
        return (float) readDouble(str);
    }

    public static int readInt() {
        int i = -1;
        try {
            i = Integer.parseInt(readString().trim());
        } catch (Exception e) {
        }
        return i;
    }

    public static int readInt(String str) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
        return readInt();
    }

    public static String readString() {
        String s = "";
        char tmp;
        try {
            do {
                tmp = (char) in.read();
                if (tmp != '\n' && tmp != ' ' && tmp != 13) {
                    s += tmp;
                }
            } while (tmp != '\n' && tmp != ' ');
        } catch (IOException ioe) {
            System.out.println("lerPalavra: " + ioe.getMessage());
        }
        return s;
    }

    public static String readString(String str) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
        return readString();
    }

    public static String readLine() {
        String s = "";
        char tmp;
        try {
            do {
                tmp = (char) in.read();
                if (tmp != '\n' && tmp != 13) {
                    s += tmp;
                }
            } while (tmp != '\n');
        } catch (IOException ioe) {
            System.out.println("lerPalavra: " + ioe.getMessage());
        }
        return s;
    }

    public static String readLine(String str) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
        return readLine();
    }

    public static char readChar() {
        char resp = ' ';
        try {
            resp = (char) in.read();
        } catch (Exception e) {
        }
        return resp;
    }

    public static char readChar(String str) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
        return readChar();
    }

    public static boolean readBoolean() {
        boolean resp = false;
        String str = "";

        try {
            str = readString();
        } catch (Exception e) {
        }

        if (str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") || str.equals("verdadeiro")
                || str.equals("VERDADEIRO") || str.equals("V")) {
            resp = true;
        }

        return resp;
    }

    public static boolean readBoolean(String str) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
        return readBoolean();
    }

    public static void pause() {
        try {
            in.read();
        } catch (Exception e) {
        }
    }

    public static void pause(String str) {
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro: charset invalido");
        }
        pause();
    }
}

class Escadinha {
    public static void main(String[] args) {
        String[] inputs = new String[1000];
        int index = 0;

        do {
            inputs[index] = MyIO.readLine();
        } while (isFim(inputs[index++]) == false);
        index--;

        for (int i = 0; i < index; i++) {
            System.out.println(escadinha(inputs[i], inputs[++i]));
        }

    }

    /**
     * verifica se é o fim do arquivo
     */
    public static boolean isFim(String line) {
        boolean boolValue = false;
        if (line.length() == 3 && line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M') {
            boolValue = true;
        }
        return boolValue;
    }

    public static int escadinha(String tamanho, String sequencia) {
        String[] numeros = sequencia.split(" ");
        int cont = 0;
        if (Integer.parseInt(tamanho) != 1) {
            for (int i = Integer.parseInt(tamanho) - 1; i >= 0; i--) {
                // System.out.println(i);
                if (i >= 2) {
                    int aux = Integer.parseInt(numeros[i]) - Integer.parseInt(numeros[i - 1]);
                    int j = i - 1;
                    int sub;
                    int cont_backup = cont;
                    do {
                        if (j >= 1) {
                            sub = Integer.parseInt(numeros[j]) - Integer.parseInt(numeros[j - 1]);
                            if (sub == aux) {
                                if (cont_backup == cont) {
                                    cont++;
                                }
                            }

                            j--;

                        } else {
                            sub = -10000;
                        }

                    } while (sub == aux);

                    i = j + 2;

                }
            }
        } else
            cont = 1;
        return cont;
    }

}
