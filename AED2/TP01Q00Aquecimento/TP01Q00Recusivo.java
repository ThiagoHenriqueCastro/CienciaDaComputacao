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

    public static boolean isFim(String line) {
        boolean boolValue = false;
        if (line.length() == 3 && line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M') {
            boolValue = true;
        }
        return boolValue;
    }

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

    public static boolean isUpperCase(char letter) {
        boolean response = false;
        if (letter >= 'A' && letter <= 'Z')
            response = true;

        return response;
    }

}