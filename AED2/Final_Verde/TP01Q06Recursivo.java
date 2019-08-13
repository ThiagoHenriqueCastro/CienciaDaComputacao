
/**
 * TP01Q03 ALTERAÇÃO
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Este algoritmo sorteia letras aleatorias, as atribuindo em uma string como um "replace"
 */

import java.util.Random;

class TP01Q06Recursivo {
    public static void main(String[] args) {
        String[] input = new String[1000];
        Random gerador = new Random();
        gerador.setSeed(4);
        char firstLetter = randomLetter(gerador);
        char secondLetter = randomLetter(gerador);
        int inputIndex = 0;
        MyIO.setCharset("WINDOWS-1252");
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            MyIO.println(encrypt(input[i], 0, firstLetter, secondLetter));
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
     * @param line    string a ser encriptada
     * @param i       parametro controlador da recursão, somando de um em uma até
     *                que toda a estring tenha sido contemplada
     * @param gerador Intancia da classe random
     * @return string modificada
     */
    public static String encrypt(String line, int i, char firstLetter, char secondLetter) {
        String output = "";

        // ao chegar ao fim da recursão, retorna a string vazia
        if (i == line.length())
            output = "";
        else if (line.charAt(i) == firstLetter)
            output = secondLetter + encrypt(line, (i + 1), firstLetter, secondLetter);
        else
            output = line.charAt(i) + encrypt(line, (i + 1), firstLetter, secondLetter);
        return output;
    }

    /**
     * @param gerador instancia da classe Random
     * @return um char pseudo-aleatorio
     */
    public static char randomLetter(Random gerador) {
        return (char) (Math.abs(gerador.nextInt()) % 26);
    }

}