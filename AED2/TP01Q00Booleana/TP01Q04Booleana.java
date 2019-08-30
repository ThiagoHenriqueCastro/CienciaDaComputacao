/**
 * TP01Q04 - Álgebra Booleana
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019
 */

class TP01Q04Booleana {
    static String[] in = { "not(0)", "not(1)", "or(0,0)", "or(0,1)", "or(1,0)", "or(1,1)", "and(0,0)", "and(0,1)",
            "and(1,0)", "and(1,1)", "or(0,0,0)", "or(0,0,1)", "or(0,1,0)", "or(0,1,1)", "or(1,0,0)", "or(1,0,1)",
            "or(1,1,0)", "or(1,1,1)", "and(0,0,0)", "and(0,0,1)", "and(0,1,0)", "and(0,1,1)", "and(1,0,0)",
            "and(1,0,1)", "and(1,1,0)", "and(1,1,1)", "or(0,0,0,0)", "or(0,0,0,1)", "or(0,0,1,0)", "or(0,0,1,1)",
            "or(0,1,0,0)", "or(0,1,0,1)", "or(0,1,1,0)", "or(0,1,1,1)", "or(1,0,0,0)", "or(1,0,0,1)", "or(1,0,1,0)",
            "or(1,0,1,1)", "or(1,1,0,0)", "or(1,1,0,1)", "or(1,1,1,0)", "or(1,1,1,1)", "and(0,0,0,0)", "and(0,0,0,1)",
            "and(0,0,1,0)", "and(0,0,1,1)", "and(0,1,0,0)", "and(0,1,0,1)", "and(0,1,1,0)", "and(0,1,1,1)",
            "and(1,0,0,0)", "and(1,0,0,1)", "and(1,0,1,0)", "and(1,0,1,1)", "and(1,1,0,0)", "and(1,1,0,1)",
            "and(1,1,1,0)", "and(1,1,1,1)" };

    static String[] out = { "1", "0", "0", "1", "1", "1", "0", "0", "0", "1", "0", "1", "1", "1", "1", "1", "1", "1",
            "0", "0", "0", "0", "0", "0", "0", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "1" };

    /**
     * Método que verifica se dois conjuntos de caracteres são idênticos
     */
    public static boolean equal(String first, String second) {
        boolean resp = true;
        if (first.length() != second.length()) {
            resp = false;
        } else {
            int i = 0;
            while (i < first.length() && resp) {
                if (first.charAt(i) != second.charAt(i)) {
                    resp = false;
                }
                i++;
            }
        }
        return resp;
    }

    /**
     * Retorna os valores que devem ser colocados nas expressões
     */
    public static int[] getValores(String entry) {
        int num = Character.getNumericValue(entry.charAt(0));
        int[] in = new int[num];
        String phrase = valores(entry, num);

        for (int i = 0; i < phrase.length(); i++) {
            int value = Character.getNumericValue(phrase.charAt(i));
            if (value == 0 || value == 1) {
                in[i] = value;
            }
        }
        return in;
    }

    public static String getFrase(String entry) {
        String expression = "";
        for (int i = 0; i < entry.length(); i++) {
            if (!(entry.charAt(i) >= '0' && entry.charAt(i) <= '9')) {
                expression += entry.charAt(i);
            }
        }
        return expression;
    }

    /**
     * Separa os espaços da entrada
     */
    public static String split(String entry) {
        String expression = "";
        for (int i = 0; i < entry.length(); i++) {
            if (entry.charAt(i) != ' ') {
                expression += entry.charAt(i);
            }
        }
        return expression;
    }

    /**
     * Retorna os valores da entrada
     */
    public static String valores(String entry, int num) {
        String expression = "";
        for (int i = 1; i <= num; i++) {
            expression += entry.charAt(i);
        }
        return expression;
    }

    /**
     * Tratamento da erxpressão para sua devida validação e execução
     */
    public static String validar(String expression, int[] values) {
        expression = setvalores(expression, values);
        expression = executar(expression);
        return expression;
    }

    /**
     * Atribui os valores na expressão
     */
    public static String setvalores(String expression, int[] values) {
        String frase = "";
        for (int i = 0; i < expression.length(); i++) {
            int j = 0, value = -1;
            while (j < values.length) {
                if (expression.charAt(i) == ('A' + j)) {
                    value = values[j];
                }
                j++;
            }
            if (value != -1) {
                frase += value;
            } else {
                frase += expression.charAt(i);
            }
        }
        return frase;
    }

    /**
     * Substitui a expressão pelo respectivo valor definido no padrão
     */
    public static String trocar(String expression, int index, int std) {
        String new_string = "";

        for (int i = 0; i < expression.length(); i++) {
            if (i == index) {
                new_string += out[std];
                i = (index + in[std].length() - 1);
            } else {
                new_string += expression.charAt(i);
            }
        }
        return new_string;
    }

    /**
     * Simplifica a expressão após o término do tratamento
     */
    public static String executar(String expression) {
        int index, std = 0;
        while (!equal(expression, "0") && !equal(expression, "1")) {
            index = index(expression, in[std]);
            if (index != -1) {
                expression = trocar(expression, index, std);
            }
            if (std < in.length - 1) {
                std++;
            } else {
                std = 0;
            }
        }
        return expression;
    }

    /**
     * Retorna o padrão booleano
     */
    public static int index(String expression, String std) {
        int resp = -1;
        if (std.length() <= expression.length()) {
            int i = 0;
            while (resp == -1 && i < expression.length()) {
                if (expression.charAt(i) == std.charAt(0)) {
                    if (standard(expression, std, i)) {
                        resp = i;
                    }
                }
                i++;
            }
        }
        return resp;
    }

    /**
     * Verifica se há o padrão
     */
    public static boolean standard(String expression, String std, int i) {
        int j = 0;
        boolean resp = true;

        while (resp && i < expression.length() && j < std.length()) {
            if (expression.charAt(i) != std.charAt(j)) {
                resp = false;
            }
            i++;
            j++;
        }
        return resp;
    }

    public static void main(String[] args) {
        String[] input = new String[1000];
        String linha;
        int inputIndex = 0;

        do {
            input[inputIndex] = MyIO.readLine();
        } while (equal(input[inputIndex++], "0") == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            String expressao = split(input[i]);
            int[] valores = getValores(expressao);
            String nova = getFrase(expressao);
            MyIO.println(validar(nova, valores));
        }
    }
}