import java.util.Random;

class TP01Q06Alteracao {
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
        char firstLetter = randomLetter();
        char secondLetter = randomLetter();
        String output = "";

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == firstLetter) {
                output += secondLetter;
            } else {
                output += line.charAt(i);
            }
        }
        return output;
    }

    public static char randomLetter() {
        Random gerador = new Random();
        gerador.setSeed(4);
        return (char) (Math.abs(gerador.nextInt()) % 26);
    }

}