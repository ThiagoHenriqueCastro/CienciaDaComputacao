import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Time {
    // Strings
    private String nome;
    private String apelido;
    private String estadio;
    private String tecnico;
    private String liga;
    private String nomeAquivo;

    // ints
    private int capacidade;
    private int fundacaoDia;
    private int fundacaoMes;
    private int fundacaoAno;

    // long
    private long tamanho;

     /**
    * 
    * @param nome nome do time
    * @param apelido apelido do time
    * @param estadio estadio do time
    * @param tecnico apelido do time
    * @param liga liga do time
    * @param nomeArquivo nome do arquivo do time
    * @param capacidade capacidade do estadio do time
    * @param fundacaoDia fundacaoDia do time
    * @param fundacaoAno fundacaoAno do time
    * @param fundacaoMes fundacaoMes do time
    * @param tamanho tamanho do arquivo do time
    * @return true se 'FIM' for encontrado
    */
    public Time(String nome, String apelido, String estadio, String tecnico, String liga, String nomeArquivo,
            int capacidade, int fundacaoDia, int fundacaoMes, int fundacaoAno, long tamanho) {

        setNome(nome);
        setApelido(apelido);
        setEstadio(estadio);
        setTecnico(tecnico);
        setLiga(liga);
        setnomeAquivo(nomeArquivo);
        setCapacidade(capacidade);
        setFundacaoDia(fundacaoDia);
        setFundacaoMes(fundacaoMes);
        setFundacaoAno(fundacaoAno);
        setTamanho(tamanho);

    }

    public Time() {
        setNome("");
        setApelido("");
        setEstadio("");
        setTecnico("");
        setLiga("");
        setnomeAquivo("");
        setCapacidade(0);
        setFundacaoDia(0);
        setFundacaoMes(0);
        setFundacaoAno(0);
        setTamanho(0);
    }

    // GETS
    public String getNome() {
        return this.nome;
    }

    public String getApelido() {
        return this.apelido;
    }

    public String getEstadio() {
        return this.estadio;
    }

    public String getTecnico() {
        return this.tecnico;
    }

    public String getLiga() {
        return this.liga;
    }

    public String getnomeAquivo() {
        return this.nomeAquivo;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public int getFundacaoDia() {
        return this.fundacaoDia;
    }

    public int getFundacaoMes() {
        return this.fundacaoMes;
    }

    public int getFundacaoAno() {
        return this.fundacaoAno;
    }

    public long getTamanho() {
        return this.tamanho;
    }

    // SETS
    public void setNome(String valor) {
        this.nome = valor;
    }

    public void setApelido(String valor) {
        this.apelido = valor;
    }

    public void setEstadio(String valor) {
        this.estadio = valor;
    }

    public void setTecnico(String valor) {
        this.tecnico = valor;
    }

    public void setLiga(String valor) {
        this.liga = valor;
    }

    public void setnomeAquivo(String valor) {
        this.nomeAquivo = valor;
    }

    public void setCapacidade(int valor) {
        this.capacidade = valor;
    }

    public void setFundacaoDia(int valor) {
        this.fundacaoDia = valor;
    }

    public void setFundacaoMes(int valor) {
        this.fundacaoMes = valor;
    }

    public void setFundacaoAno(int valor) {
        this.fundacaoAno = valor;
    }

    public void setTamanho(long valor) {
        this.tamanho = valor;
    }

    /**
     * imprime os atributos da classe
     */
    public void imprimir() {

        System.out.printf(getNome() + " ## ");
        System.out.printf(getApelido() + " ## ");
        if (getFundacaoDia() >= 0 && getFundacaoDia() <= 9) {

            if (getFundacaoMes() >= 0 && getFundacaoMes() <= 9) {
                MyIO.print("0" + getFundacaoDia() + "/" + "0" + getFundacaoMes() + "/" + getFundacaoAno() + " ## ");
            } else {
                MyIO.print("0" + getFundacaoDia() + "/" + getFundacaoMes() + "/" + getFundacaoAno() + " ## ");
            }

        }

        else {
            if (getFundacaoMes() >= 0 && getFundacaoMes() <= 9) {
                MyIO.print(getFundacaoDia() + "/" + "0" + getFundacaoMes() + "/" + getFundacaoAno() + " ## ");
                ;
            } else {
                MyIO.print(getFundacaoDia() + "/" + getFundacaoMes() + "/" + getFundacaoAno() + " ## ");
            }

        }
        MyIO.print(getEstadio() + " ## ");
        MyIO.print(getCapacidade() + " ## ");
        MyIO.print(getTecnico() + " ## ");
        MyIO.print(getLiga() + " ## ");
        MyIO.print(getnomeAquivo() + " ## ");
        MyIO.print(getTamanho() + " ## \n");
    }

}

public class Crawl {
    public static void main(String[] args) {
        String[] input = new String[1000];
        int inputIndex = 0;
        MyIO.setCharset("UTF-8");
        // preenche um array de nomes de arquivos
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        Time[] times = new Time[1000];
        String html = "";
        String table = "";
        StringBuilder sb = new StringBuilder();
        String[] splitted = new String[100];
        String withoutTags = "";
        String nome = "";
        String apelido = "";
        int dia = 0;
        int mes = 0;
        int ano = 0;
        String estadio = "";
        int capacidade = 0;
        String tecnico = "";
        String liga = "";
        long tamanho = 0;
        String nomeArquivo = "";
        String Proxlinha = "";
        String aux;
        int controller;

        // para cada aquivo, o abre e extrai os dados da classe do mesmo
        for (int i = 0; i < inputIndex; i++) {
            try {
                controller = 0;
                File arq = new File(input[i]);
                FileInputStream file = new FileInputStream(input[i]);
                InputStreamReader is = new InputStreamReader(file, "UTF-8");
                BufferedReader br = new BufferedReader(is);

                while ((html = br.readLine()) != null && controller != 1) {
                    if (html.contains("Full name")) {
                        table = html;
                        controller = 1;

                    }
                }

                splitted = table.split("Full name");
                withoutTags = removerTags(splitted[1]);

                nome = crawlNome(withoutTags);
                apelido = crawlApelido(withoutTags);
                dia = crawlDia(withoutTags);
                mes = crawlMes(withoutTags);
                ano = crawlAno(withoutTags);
                estadio = crawlEstadio(withoutTags);
                capacidade = crawlCapacidade(withoutTags);
                tecnico = crawlTecnico(withoutTags);
                liga = crawlLiga(withoutTags);
                tamanho = arq.length();
                nomeArquivo = input[i];

                times[i] = new Time(nome, apelido, estadio, tecnico, liga, nomeArquivo, capacidade, dia, mes, ano,
                        tamanho);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        for (int i = 0; i < inputIndex; i++) {
            times[i].imprimir();
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
     * @param line string que devera ser verificada
     * @return o nome do time
    */
    public static String crawlNome(String line) {
        //splita a string em referencias proximas e pega o conteudo do meio
        String nome = "";
        String[] splitted = line.split(" Nickname\\(s\\)");
        nome = splitted[0];
        // MyIO.println(nome);
        // remove strings inconvenientes que podem ter sobrado
        nome = nome.replace("&amp", "");
        nome = nome.replace("( ", "(");
        nome = nome.replace(" )", ")");
        nome = nome.replace(" &#91 1&#93", "");
        return nome;
    }
    /**
     * 
     * @param line string que devera ser verificada
     * @return o apelido do time
    */
    public static String crawlApelido(String line) {
        //splita a string em referencias proximas e pega o conteudo do meio

        String splitted[] = new String[1000];

        String value = "";
        // MyIO.println(line);
        splitted = line.split("Nickname\\(s\\) ");
        if (splitted[1].contains("Short name")) {
            splitted = splitted[1].split(" Short name");

        } else {
            splitted = splitted[1].split(" Founded");
        }

        value = splitted[0];
        // remove strings inconvenientes que podem ter sobrado
        value = value.replace(" ,", ",");
        value = value.replace("( ", "(");
        value = value.replace(" )", ")");
        value = value.replace("&amp", "");
        value = value.replace("&#91 note 1&#93", "");
        value = value.replace("&#91 1&#93", "");
        value = value.replace("\"", "");

        value = value.trim();

        return value;
    }
    /**
     * 
     * @param line string que devera ser verificada
     * @return o die de fundacao do time
    */
    public static int crawlDia(String line) {

        //splita a string em referencias proximas e pega o conteudo do meio

        String splitted[] = new String[1000];
        String value = "";
        int resp = 0;
        splitted = line.split("Founded ");
        splitted = splitted[1].split(" ");
        value = splitted[0];
        if (splitted[0].charAt(0) >= 'A' && splitted[0].charAt(0) <= 'Z') {
            splitted = splitted[1].split(",");
            value = splitted[0];
        }
        // MyIO.println(value);
        if (line.contains("January") || line.contains("February") || line.contains("March") || line.contains("April")
                || line.contains("May") || line.contains("June") || line.contains("July") || line.contains("September")
                || line.contains("August") || line.contains("October") || line.contains("November")
                || line.contains("July") || line.contains("December")) {
            resp = Integer.parseInt(value);
        } else {
            resp = 0;
        }

        return resp;
    }
    /**
     * 
     * @param line string que devera ser verificada
     * @return o mes de fundacao do time
    */
    public static int crawlMes(String line) {
        String splitted[] = new String[1000];
        int mes = 0;
        String value = "";

        splitted = line.split("Founded ");
        splitted = splitted[1].split(" ");
        value = splitted[1];
        // Converte s escrita do mes para o inteiro respectivo
        if (value.matches(".*\\d.*")) {
            value = splitted[0];
        }
        if (value.equals("January")) {
            mes = 1;
        } else if (value.equals("February")) {
            mes = 2;
        } else if (value.equals("March")) {
            mes = 3;
        } else if (value.equals("April")) {
            mes = 4;
        } else if (value.equals("May")) {
            mes = 5;
        } else if (value.equals("June")) {
            mes = 6;
        } else if (value.equals("July")) {
            mes = 7;
        } else if (value.equals("August")) {
            mes = 8;
        } else if (value.equals("September")) {
            mes = 9;
        } else if (value.equals("October")) {
            mes = 10;
        } else if (value.equals("November")) {
            mes = 11;
        } else if (value.equals("December")) {
            mes = 12;
        } else {
            mes = 0;
        }
        return mes;
    }
    /**
     * 
     * @param line string que devera ser verificada
     * @return o mes de fundacao do time
    */
    public static int crawlAno(String line) {
        // splita em referencias proximas e pega o conteudo entre as referencias
        String splitted[] = new String[1000];
        int resp = 0;
        String value = "";
        // splita conforme a proxima referencia, o mes
        if (line.contains("January")) {
            splitted = line.split("January ");
        } else if (line.contains("February")) {
            splitted = line.split("February ");
        } else if (line.contains("March")) {
            splitted = line.split("March ");
        } else if (line.contains("April")) {
            splitted = line.split("April ");
        } else if (line.contains("May")) {
            splitted = line.split("May ");
        } else if (line.contains("June")) {
            splitted = line.split("June ");
        } else if (line.contains("July")) {
            splitted = line.split("July ");
        } else if (line.contains("August")) {
            splitted = line.split("August ");
        } else if (line.contains("September")) {
            splitted = line.split("September ");
        } else if (line.contains("October")) {
            splitted = line.split("October ");
        } else if (line.contains("November")) {
            splitted = line.split("November ");
        } else if (line.contains("December")) {
            splitted = line.split("December ");
        } else {
            splitted = line.split("Founded ");
        }

        splitted = splitted[1].split(" ");
        value = splitted[0];
        if (splitted[0].contains(",")) {
            value = splitted[1];
        }
        // MyIO.println(value);
        resp = Integer.parseInt(value);
        return resp;
    }

    /**
     * 
     * @param line string que devera ser verificada
     * @return o nome do Estadio do time
    */
    public static String crawlEstadio(String line) {
        // splita em referencias e pega o conteudo
        String estadio = "";
        String[] splitted = line.split("Ground ");
        splitted = splitted[1].split("Capacity");

        estadio = splitted[0];
        // retira inconveniencias da string resultante
        estadio = estadio.replace(" ,", ",");

        estadio = estadio.replace(" .", ".");

        estadio = estadio.trim();

        return estadio;
    }

    /**
     * 
     * @param line string que devera ser verificada
     * @return a capacidade estadio do time
    */
    public static int crawlCapacidade(String line) {
        // split em referencias proximas 
        String capacidade = "";
        String[] splitted = line.split("Capacity ");
        splitted = splitted[1].split(" ");
        if (splitted[0].contains(",")) {
            splitted = splitted[0].split(",");
            capacidade = splitted[0] + splitted[1];
        } else {
            capacidade = splitted[0] + splitted[1];
        }
        // MyIO.println(capacidade);
        return Integer.parseInt(capacidade);
    }
    /**
     * 
     * @param line string que devera ser verificada
     * @return o tecnico do time
    */
    public static String crawlTecnico(String line) {
        // split em referencias proximas
        String tecnico = "";
        String[] splitted = line.contains("General manager") ? line.split("General manager")
                : line.contains("Manager") ? line.split("Manager ")
                        : !line.contains("Coach ") ? line.split("Head coach ") : line.split("Coach ");
        splitted = line.contains("General manager") ? splitted[1].split("Chairman") : splitted[1].split("League");
        tecnico = splitted[0];
        // retirando inconveniencias da string resultante
        tecnico = tecnico.replace("&#91 1&#93", "");
        tecnico = tecnico.replace("&#91 8&#93", "");

        tecnico = tecnico.trim();

        return tecnico;
    }

    /**
     * 
     * @param line string que devera ser verificada
     * @return a liga do time
    */
    public static String crawlLiga(String line) {
        // split em referencias proximas
        String splitted[] = new String[1000];
        String resp = "";
        if (line.contains("Viktor")) {
            splitted = line.split("Viktor");
            splitted = splitted[1].split("League ");
        } else {
            splitted = line.split("League ");
        }

        if (splitted[1].contains("2")) {
            splitted = splitted[1].split(" 2");
            resp = splitted[0];
        } else {
            resp = splitted[1] + "League";
            resp += splitted[2].contains("(East)") ? " (East)" : "";
            resp += splitted[2].contains("BH") ? " BH" : "";
        }

        return resp;
    }
    /**
     * 
     * @param html string que devera ser verificada
     * @return string sem tags
    */
    public static String removerTags(String html) {
        String resp = "";
        // remove tags por regex em um html
        resp = html.replaceAll("\\<(/?[^\\>]+)\\>", " ").replaceAll("\\s+", " ").trim();
        resp = resp.replaceAll(";", " ").trim();
        resp = resp.replaceAll("&#160", " ").trim();
        resp = resp.replaceAll("/", " ").replaceAll("\\s+", " ").trim();
        return resp;

    }
}
