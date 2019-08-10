/**
 * TP01Q08 ls
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Algoritmo que verifica se uma string é composta so por
 *          vogais, consoantes, se for numeral inteiro ou real.
 */

class TP01Q08ls {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        MyIO.setCharset("UTF-8");
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {

            MyIO.print(onlyVowels(input[i]) ? "SIM " : "NAO ");
            MyIO.print(onlyConsonants(input[i]) ? "SIM " : "NAO ");
            MyIO.print(isInt(input[i]) ? "SIM " : "NAO ");
            MyIO.println(isDouble(input[i]) ? "SIM" : "NAO");
        }
    }

    public static boolean isFim(String line) {
        boolean boolValue = false;
        if (line.length() == 3 && line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M') {
            boolValue = true;
        }
        return boolValue;
    }

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

    public static Boolean isVowel(char letter) {
        boolean resp = false;

        if (letter == 'a' || letter == 'A' || letter == 'e' || letter == 'e' || letter == 'E' || letter == 'i'
                || letter == 'I' || letter == 'o' || letter == 'O' || letter == 'u' || letter == 'U')
            resp = true;

        return resp;
    }

    public static Boolean onlyNumbers(String line) {
        boolean resp = false;
        int cont = 0;
        for (int i = 0; i < line.length(); i++) {
            if ((int) line.charAt(i) >= 48 && (int) line.charAt(i) <= 57)
                cont++;
        }

        if (cont == line.length())
            resp = true;

        return resp;
    }

    public static Boolean isNumber(char letter) {
        boolean resp = false;
        if ((int) letter >= 48 && (int) letter <= 57)
            resp = true;

        return resp;
    }

    public static Boolean onlyVowels(String line) {
        boolean result = false;
        if (onlyLetters(line)) {
            for (int i = 0; i < line.length(); i++) {
                if (isVowel(line.charAt(i))) {
                    result = true;
                } else {
                    result = false;
                    i = line.length();
                }
            }
        }

        return result;

    }

    public static Boolean onlyConsonants(String line) {
        boolean result = false;
        if (onlyLetters(line)) {
            for (int i = 0; i < line.length(); i++) {
                if (!isVowel(line.charAt(i))) {
                    result = true;
                } else {
                    result = false;
                    i = line.length();
                }
            }
        }

        return result;

    }

    public static Boolean isInt(String line) {
        boolean result = false;
        if (onlyNumbers(line))
            result = true;
        return result;

    }

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