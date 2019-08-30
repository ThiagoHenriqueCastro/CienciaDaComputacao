/**
 * TP01Q05 CIFRAMENTO
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Criptgrafa um string
 */

class TP01Q05Ciframento {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
<<<<<<< HEAD
        MyIO.setCharset("ISO-8859-7");
=======
        MyIO.setCharset("WINDOWS-1252");
>>>>>>> 6aa113d863187424cf64a8246ccac44f10208186
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            MyIO.print(encrypt(input[i]));
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
     * @return string criptografada
     */
    public static String encrypt(String line) {
        String encrypted = "";
        int toASCII = 0;
        // para cada letra, converte o char para int, soma 3, e devolve esse char
        // adulterado para a string de retorno
        for (int i = 0; i < line.length(); i++) {
            toASCII = (int) line.charAt(i) + 3;
            encrypted += (char) toASCII;
        }

        return encrypted;
    }

}