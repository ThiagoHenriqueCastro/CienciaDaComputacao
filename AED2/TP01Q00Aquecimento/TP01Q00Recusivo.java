/**
 * TP01Q00 Aquecimento Recursivo
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Este algoritmo conta o numero de letras maiusculas em uma
 *          string.
 */

class TP01Q00Recursivo {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            MyIO.println(countUpperCase(input[i], 0));
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
    public static int countUpperCase(String line, int i) {
        int occurrencies = 0;
        if (i == line.length()) {
            occurrencies = 0;
        } else if (isUpperCase(line.charAt(i))) {
            occurrencies = 1 + countUpperCase(line, i + 1);
        } else {
            occurrencies = 0 + countUpperCase(line, i + 1);
        }

        return occurrencies;
    }

    /**
     * 
     * @param letter letra a ser verificada
     * @return se for maisculo, retorna 1 para somar posteriormente
     */
    public static boolean isUpperCase(char letter) {
        boolean response = false;
        if (letter >= 'A' && letter <= 'Z')
            response = true;

        return response;
    }

}