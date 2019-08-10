class TP01Q03Recursivo {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        MyIO.setCharset("UTF-8");
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            if (isPalindromo(input[i], 0, (input[i].length() - 1)) >= (input[i].length() / 2)) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NAO");
            }
        }
    }

    public static boolean isFim(String line) {
        boolean boolValue = false;
        if (line.length() == 3 && line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M') {
            boolValue = true;
        }
        return boolValue;
    }

    public static int isPalindromo(String line, int i, int j) {
        int result;
        if (i >= j)
            result = 0;
        else if (line.charAt(i) == line.charAt(j))
            result = 1 + isPalindromo(line, (i + 1), (j - 1));
        else
            result = 0 + isPalindromo(line, (i + 1), (j - 1));
        return result;

    }
}