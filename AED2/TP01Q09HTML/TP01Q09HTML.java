import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;

/**
 * TP01Q09 HTML
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 08/2019 Algoritmo que verifica ocorrencias de strings em paginas
 *          html.
 */

class TP01Q09HTML {
    public static void main(String[] args) {
        String[] name_url = new String[1000];
        String[] page_code = new String[1000];
        int inputIndex = 0;
        int page_index = 0;
        MyIO.setCharset("UTF-8");
        do {
            name_url[inputIndex] = MyIO.readLine();
        } while (isFim(name_url[inputIndex++]) == false);
        inputIndex--;

        for (int i = 1; i < inputIndex; i += 2) {
            try {
                page_code[page_index] = getCode(name_url[i]);
                page_index++;
            } catch (MalformedURLException exception) {
            } catch (IOException exception_2) {
            }

        }

        int name_index = 0;
        for (int i = 0; i < page_index; i++) {
            MyIO.print("a(" + cont_common_a(page_code[i]) + ") ");
            MyIO.print("e(" + cont_common_e(page_code[i]) + ") ");
            MyIO.print("i(" + cont_common_i(page_code[i]) + ") ");
            MyIO.print("o(" + cont_common_o(page_code[i]) + ") ");
            MyIO.print("u(" + cont_common_u(page_code[i]) + ") ");
            MyIO.print("á(" + cont_acute_a(page_code[i]) + ") ");
            MyIO.print("é(" + cont_acute_e(page_code[i]) + ") ");
            MyIO.print("í(" + cont_acute_i(page_code[i]) + ") ");
            MyIO.print("ó(" + cont_acute_o(page_code[i]) + ") ");
            MyIO.print("ú(" + cont_acute_u(page_code[i]) + ") ");
            MyIO.print("à(" + cont_grave_a(page_code[i]) + ") ");
            MyIO.print("è(" + cont_grave_e(page_code[i]) + ") ");
            MyIO.print("ì(" + cont_grave_i(page_code[i]) + ") ");
            MyIO.print("ò(" + cont_grave_o(page_code[i]) + ") ");
            MyIO.print("ù(" + cont_grave_u(page_code[i]) + ") ");
            MyIO.print("ã(" + cont_tilde_a(page_code[i]) + ") ");
            MyIO.print("õ(" + cont_tilde_o(page_code[i]) + ") ");
            MyIO.print("â(" + cont_circ_a(page_code[i]) + ") ");
            MyIO.print("ê(" + cont_circ_e(page_code[i]) + ") ");
            MyIO.print("î(" + cont_circ_i(page_code[i]) + ") ");
            MyIO.print("ô(" + cont_circ_o(page_code[i]) + ") ");
            MyIO.print("û(" + cont_circ_u(page_code[i]) + ") ");
            MyIO.print("consoante(" + cont_consonants(page_code[i]) + ") ");
            MyIO.print("<br>(" + cont_br(page_code[i]) + ") ");
            MyIO.print("<table>(" + cont_table(page_code[i]) + ") ");
            MyIO.println(name_url[name_index]);
            name_index += 2;
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
     * @param url url da pagina web a ser extraida
     * @return o codigo fonte da pagina web em uma string
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getCode(String url) throws MalformedURLException, IOException {
        BufferedReader in = null;
        StringBuilder content = null;
        try {
            URL page = new URL(url);
            URLConnection urlConnection = page.openConnection();

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String line;
            content = new StringBuilder();

            while ((line = in.readLine()) != null)
                content.append(line);

        } finally {
            in.close();
        }
        return content.toString();
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return o numero de ocorrencias
     */
    public static Integer cont_consonants(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if ((code.charAt(i) >= 'a' && code.charAt(i) <= 'z') || (code.charAt(i) >= 'A' && code.charAt(i) <= 'Z')) {
                if (code.charAt(i) != 'a' && code.charAt(i) != 'A' && code.charAt(i) != 'á' && code.charAt(i) != 'Á'
                        && code.charAt(i) != 'ã' && code.charAt(i) != 'Ã' && code.charAt(i) != 'à'
                        && code.charAt(i) != 'À' && code.charAt(i) != 'â' && code.charAt(i) != 'Â'
                        && code.charAt(i) != 'e' && code.charAt(i) != 'E' && code.charAt(i) != 'é'
                        && code.charAt(i) != 'É' && code.charAt(i) != 'è' && code.charAt(i) != 'È'
                        && code.charAt(i) != 'ê' && code.charAt(i) != 'Ê' && code.charAt(i) != 'i'
                        && code.charAt(i) != 'I' && code.charAt(i) != 'í' && code.charAt(i) != 'Í'
                        && code.charAt(i) != 'ì' && code.charAt(i) != 'Ì' && code.charAt(i) != 'î'
                        && code.charAt(i) != 'Î' && code.charAt(i) != 'o' && code.charAt(i) != 'O'
                        && code.charAt(i) != 'ó' && code.charAt(i) != 'Ó' && code.charAt(i) != 'õ'
                        && code.charAt(i) != 'Õ' && code.charAt(i) != 'ò' && code.charAt(i) != 'Ò'
                        && code.charAt(i) != 'ô' && code.charAt(i) != 'Ô' && code.charAt(i) != 'u'
                        && code.charAt(i) != 'U' && code.charAt(i) != 'ú' && code.charAt(i) != 'Ú'
                        && code.charAt(i) != 'ù' && code.charAt(i) != 'Ù' && code.charAt(i) != 'û'
                        && code.charAt(i) != 'Û') {
                    result++;
                }
            }

        }

        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return numero de ocorrencias da tag br
     */
    public static Integer cont_br(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '<') {
                if (code.charAt((i + 1)) == 'b' && code.charAt((i + 2)) == 'r' && code.charAt((i + 3)) == '>')
                    result++;
            }
        }

        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return numero de ocorrencias da tag table
     */
    public static Integer cont_table(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '<') {
                if (code.charAt((i + 1)) == 't' && code.charAt((i + 2)) == 'a' && code.charAt((i + 3)) == 'b'
                        && code.charAt((i + 4)) == 'l' && code.charAt((i + 5)) == 'e' && code.charAt((i + 6)) == '>')
                    result++;
            }
        }

        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra a comum
     */
    public static Integer cont_common_a(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'a')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra e comum
     */
    public static Integer cont_common_e(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'e')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra i comum
     */
    public static Integer cont_common_i(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'i')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra 0 comum
     */
    public static Integer cont_common_o(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'o')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra u comum
     */
    public static Integer cont_common_u(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'u')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra a aguda
     */
    public static Integer cont_acute_a(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'á')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra e aguda
     */
    public static Integer cont_acute_e(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'é')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra i aguda
     */
    public static Integer cont_acute_i(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'í')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra o aguda
     */
    public static Integer cont_acute_o(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'ó')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra u aguda
     */
    public static Integer cont_acute_u(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'ú')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra a grave
     */
    public static Integer cont_grave_a(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'à')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra e grave
     */
    public static Integer cont_grave_e(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'è')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra i grave
     */
    public static Integer cont_grave_i(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'ì')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra o grave
     */
    public static Integer cont_grave_o(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'ò')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra u grave
     */
    public static Integer cont_grave_u(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'ù')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra a til
     */
    public static Integer cont_tilde_a(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'ã')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra o til
     */
    public static Integer cont_tilde_o(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'õ')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra a circunflexa
     */
    public static Integer cont_circ_a(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'â')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra e circunflexa
     */
    public static Integer cont_circ_e(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'ê')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra i circunflexa
     */
    public static Integer cont_circ_i(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'î')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra o circunflexa
     */
    public static Integer cont_circ_o(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'ô')
                result++;
        }
        return result;
    }

    /**
     * 
     * @param code codigo da pagina web
     * @return ocorrencias da letra circunflexa
     */
    public static Integer cont_circ_u(String code) {
        int result = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'û')
                result++;
        }
        return result;
    }

}