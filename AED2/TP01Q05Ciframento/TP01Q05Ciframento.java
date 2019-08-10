class TP01Q05Ciframento {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        MyIO.setCharset("UTF-8");
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        for (int i = 0; i < inputIndex; i++) {
            MyIO.println(encrypt(input[i]));
        }
    }

    public static boolean isFim(String line) {
        boolean boolValue = false;
        if (line.length() == 3 && line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M') {
            boolValue = true;
        }
        return boolValue;
    }

    public static String encrypt(String line) {
        String encrypted = "";
        int toASCII = 0;
        for (int i = 0; i < line.length(); i++) {
            toASCII = (int) line.charAt(i) + 3;
            encrypted += (char) toASCII;
        }

        return encrypted;
    }

}