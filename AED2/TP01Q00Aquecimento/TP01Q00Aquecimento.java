class TP01Q00Aquecimento {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            MyIO.println(countUpperCase(input[i]));
        }
    }

    public static boolean isFim(String line) {
        boolean boolValue = false;
        if (line.length() == 3 && line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M') {
            boolValue = true;
        }
        return boolValue;
    }

    public static int countUpperCase(String line) {
        int occurrencies = 0;
        for (int i = 0; i < line.length(); i++) {
            occurrencies += isUpperCase(line.charAt(i));
        }

        return occurrencies;
    }

    public static int isUpperCase(char letter) {
        int response = 0;
        if (letter >= 'A' && letter <= 'Z')
            response = 1;

        return response;
    }

}