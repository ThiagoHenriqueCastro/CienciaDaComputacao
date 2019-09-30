/**
 * TP01Q00 Aquecimento
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Este algoritmo conta o numero de letras maiusculas em uma
 *          string.
 */

class TP01Q00Aquecimento {
    public static void main(String[] args) {
        String[] input = new String[1000]; // array onde são armazenadas as strings
        int inputIndex = 0; // controlador dos index para iteraçao

        // PREENCHIMENTO DO ARRAY
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            MyIO.println(countUpperCase(input[i]));
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
     * @param line string a ser verificada
     * @return o numero de letras maisculas
     */
    public static int countUpperCase(String line) {
        int occurrencies = 0;
        // verifica, letra por letra se alguma letra é maiscula pelo metodo isUpperCase
        // abaixo
        for (int i = 0; i < line.length(); i++) {
            occurrencies += isUpperCase(line.charAt(i));
        }

        return occurrencies;
    }

    /**
     * 
     * @param letter letra a ser verificada
     * @return se for maisculo, retorna 1 para somar posteriormente
     */
    public static int isUpperCase(char letter) {
        int response = 0;
        if (letter >= 'A' && letter <= 'Z')
            response = 1;

        return response;
    }

}