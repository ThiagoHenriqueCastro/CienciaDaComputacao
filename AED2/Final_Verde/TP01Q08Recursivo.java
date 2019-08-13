/**
 * TP01Q08 ls
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Algoritmo que verifica se uma string é composta so por
 *          vogais, consoantes, se for numeral inteiro ou real.
 */

class TP01Q08Recursivo {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        MyIO.setCharset("UTF-8");
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {

            MyIO.print(onlyVowels(input[i], 0) == input[i].length() ? "SIM " : "NAO ");
            MyIO.print(onlyConsonants(input[i], 0) == input[i].length() ? "SIM " : "NAO ");
            MyIO.print(isInt(input[i]) ? "SIM " : "NAO ");
            MyIO.println(isDouble(input[i]) ? "SIM" : "NAO");
        }
    }

    /**
     * 
     * @param line string a ser verificada
     * @return true se a string for FIM
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
     * @return verifica se a string é composta apenas por letras, sejam maisuculas
     *         ou minusculas
     */
    public static Boolean onlyLetters(String line) {
        boolean resp = false;
        int cont = 0;
        for (int i = 0; i < line.length(); i++) {
            if ((line.charAt(i) >= 'a' && line.charAt(i) <= 'z') || (line.charAt(i) >= 'A' && line.charAt(i) <= 'Z'))
                cont++;
        }

        if (cont == line.length())
            resp = true;

        return resp;
    }

    /**
     * 
     * @param letter testa se a letra é uma vogal
     * @return true caso seja vogal
     */
    public static Boolean isVowel(char letter) {
        boolean resp = false;

        if (letter == 'a' || letter == 'A' || letter == 'e' || letter == 'e' || letter == 'E' || letter == 'i'
                || letter == 'I' || letter == 'o' || letter == 'O' || letter == 'u' || letter == 'U')
            resp = true;

        return resp;
    }

    /**
     * 
     * @param line verifica se a string é composta apenas por algarismos
     * @param i    controlador da recursão
     * @return a contagem do numero de algarismos para avaliação futura
     */
    public static Integer onlyNumbers(String line, int i) {
        int resp = 0;
        if (i == line.length())
            resp = 0;
        else if ((int) line.charAt(i) >= 48 && (int) line.charAt(i) <= 57)
            resp = 1 + onlyNumbers(line, (i + 1));
        else
            resp = 0 + onlyNumbers(line, (i + 1));

        return resp;
    }

    /**
     * testa se um char é numeral
     * 
     * @param letter char a ser verificado
     * @return true se for numeral
     */
    public static Boolean isNumber(char letter) {
        boolean resp = false;
        if ((int) letter >= 48 && (int) letter <= 57)
            resp = true;

        return resp;
    }

    /**
     * 
     * @param line string a ser verificada
     * @param i    controlador da recursao
     * @return aa contagem de vogais para uso futuro
     */
    public static Integer onlyVowels(String line, int i) {
        int result = 0;
        if (onlyLetters(line)) {
            if (i == line.length())
                result = 0;
            else if (isVowel(line.charAt(i)))
                result = 1 + onlyVowels(line, (i + 1));
            else
                result = 0 + onlyVowels(line, (i + 1));

        }

        return result;

    }

    /**
     * 
     * @param line string a ser verifcada
     * @param i    controlador da recusao
     * @return a contagem do numero de letras nao vogais da string
     */
    public static Integer onlyConsonants(String line, int i) {
        int result = 0;
        if (onlyLetters(line)) {
            if (i == line.length())
                result = 0;
            else if (!isVowel(line.charAt(i)))
                result = 1 + onlyConsonants(line, (i + 1));
            else
                result = 0 + onlyConsonants(line, (i + 1));

        }

        return result;

    }

    /**
     * 
     * @param line string a ser verificada
     * @return true caso todos os digitos da string forem numericos
     */
    public static Boolean isInt(String line) {
        boolean result = false;
        if (onlyNumbers(line, 0) == line.length())
            result = true;
        return result;

    }

    /**
     * 
     * @param line string a ser processada
     * @return true se for numeral com ate uma virgula ou ponto
     */
    public static Boolean isDouble(String line) {
        boolean result = false;
        int number = 0;
        int special_char = 0;

        for (int i = 0; i < line.length(); i++) {
            if (isNumber(line.charAt(i))) {
                number++;
            } else if (line.charAt(i) == ',' || line.charAt(i) == '.') {
                number++;
                special_char++;
            }
        }

        if (number == line.length() && special_char == 1)
            result = true;

        return result;

    }

}