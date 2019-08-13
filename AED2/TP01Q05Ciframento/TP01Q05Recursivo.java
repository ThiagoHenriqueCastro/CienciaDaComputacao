/**
 * TP01Q03 CIFRAMENTO
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Este algoritmo criptografa uma string, trocando cada letra
 *          por tres a frente
 */

class TP01Q05Recursivo {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        MyIO.setCharset("WINDOWS-1252");
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            MyIO.println(encrypt(input[i], 0));
        }
    }

    /**
     * 
     * @param line string que devera ser verificada
     * @return true se 'FIM' for encontrado
     */
    public static boolean isFim(String line) {
        boolean boolValue = false;
        if (line.length() == 3 && line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M') {
            boolValue = true;
        }
        return boolValue;
    }

    /**
     * 
     * @param line string a ser criptografada
     * @param i    controlador da recursao, somando de um em um
     * @return a nova string ja criptografada
     */
    public static String encrypt(String line, int i) {
        String encrypted = "";
        int toASCII = 0;
        if (i == line.length()) {
            encrypted = "";
        } else {
            toASCII = (int) line.charAt(i) + 3;
            encrypted = (char) toASCII + encrypt(line, (i + 1));
        }

        return encrypted;
    }

}