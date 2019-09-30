
/**
 * TP01Q06 ALTERACAO
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Altera Aleatoreamente letras de uma string
 */

import java.util.Random;

class TP01Q06Alteracao {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        Random gerador = new Random();
        gerador.setSeed(4);
        MyIO.setCharset("WINDOWS-1252");
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            MyIO.println(encrypt(input[i], gerador));
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
     * @param line string a ser analizada
     * @return string modificada
     */
    public static String encrypt(String line, Random gerador) {
        char firstLetter = randomLetter(gerador);
        char secondLetter = randomLetter(gerador);
        String output = "";

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == firstLetter) {
                output += secondLetter;
            } else {
                output += line.charAt(i);
            }
        }
        return output;
    }

    /**
     * 
     * @return uma letra aleatoria
     */
    public static char randomLetter(Random gerador) {
        return (char) ('a' + Math.abs(gerador.nextInt()) % 26);
    }

}