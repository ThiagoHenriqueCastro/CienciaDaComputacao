class TP01Q03Palindromo {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        MyIO.setCharset("UTF-8");
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            if (isPalindromo(input[i]) == true) {
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

    public static boolean isPalindromo(String line) {
        int j = line.length() - 1;
        boolean result = true;
        for (int i = 0; i < (line.length() / 2); i++) {
            if (line.charAt(i) != line.charAt(j)) {
                result = false;
            }
            j--;
        }
        return result;

    }
}