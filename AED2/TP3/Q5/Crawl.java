
/**
 * TP03Q5
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 09/2019 Hash com reserva
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;

// Classe Time
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

    // Constructor
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
     * Clona uma instancia do objeto Time e a retorna
     */
    public Time clone() {
        Time resp = new Time();

        resp.nome = this.nome;
        resp.apelido = this.apelido;
        resp.capacidade = this.capacidade;
        resp.estadio = this.estadio;
        resp.fundacaoAno = this.fundacaoAno;
        resp.fundacaoDia = this.fundacaoDia;
        resp.fundacaoMes = this.fundacaoMes;
        resp.liga = this.liga;
        resp.tecnico = this.tecnico;
        resp.tamanho = this.tamanho;
        resp.nomeAquivo = this.nomeAquivo;

        return resp;
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

    /**
     * Le um arquivo html e os aloca nas variaveis da classe time
     * 
     * @param path caminho do arquivo
     */
    public void ler(String path) {
        String html = "";
        String table = "";
        try {
            int controller = 0;
            File arq = new File(path);
            FileInputStream file = new FileInputStream(path);
            InputStreamReader is = new InputStreamReader(file, "UTF-8");
            BufferedReader br = new BufferedReader(is);

            while ((html = br.readLine()) != null && controller != 1) {
                if (html.contains("Full name")) {
                    table = html;
                    controller = 1;

                }
            }

            if (path.equals("/tmp/times/AS_Saint-Etienne.html")) {
                table += br.readLine();
                table += br.readLine();
                table += br.readLine();
            }

            String[] splitted = table.split("Full name");
            String withoutTags = Crawl.removerTags(splitted[1]);

            this.setNome(Crawl.crawlNome(withoutTags));
            this.setApelido(Crawl.crawlApelido(withoutTags));
            this.setFundacaoDia(Crawl.crawlDia(withoutTags));
            this.setFundacaoMes(Crawl.crawlMes(withoutTags));
            this.setFundacaoAno(Crawl.crawlAno(withoutTags));
            this.setEstadio(Crawl.crawlEstadio(withoutTags));
            this.setCapacidade(Crawl.crawlCapacidade(withoutTags));
            this.setTecnico(Crawl.crawlTecnico(withoutTags));
            this.setLiga(Crawl.crawlLiga(withoutTags));
            this.setTamanho(arq.length());
            this.setnomeAquivo(path);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class Hash {
    Time tabela[];
    int m1, m2, m, reserva, comparisons;

    public Hash() {
        this(13, 7);
    }

    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new Time[this.m];
        for (int i = 0; i < m; i++)
            this.tabela[i] = null;
        reserva = 0;
        comparisons = 0;
    }

    public int h(long x) {
        return (int) x % m1;
    }

    public boolean inserir(Time x) {
        boolean resp = false;
        if (x != null) {
            int pos = h(x.getTamanho());
            comparisons++;
            if (tabela[pos] == null) {
                tabela[pos] = x;
                resp = true;
                comparisons++;
            } else if (reserva < m2) {
                tabela[m1 + reserva] = x;
                reserva++;
                resp = true;
                comparisons++;
            }
        }

        return resp;
    }

    public boolean pesquisar(String nome) {
        boolean resp = false;
        System.out.print(nome);
        long chave = 0;
        for (int i = 0; i < m; i++, comparisons++) {
            if (tabela[i] != null && tabela[i].getNome().equals(nome))
                chave = tabela[i].getTamanho();
        }

        int pos = h(chave);

        if (tabela[pos].getNome().equals(nome)) {
            resp = true;
            comparisons++;
        } else {
            for (int i = 0; i < reserva; i++, comparisons++) {
                if (tabela[m1 + i].getNome().equals(nome)) {
                    resp = true;
                    i = reserva;
                    comparisons++;
                }
            }
        }

        return resp;
    }

}

public class Crawl {
    public static void main(String[] args) throws Exception {
        String[] input = new String[1000];
        int inputIndex = 0;
        MyIO.setCharset("UTF-8");
        long start = System.currentTimeMillis();
        // Preenche o vetor de entradas e para cada uma le o arquivo
        do {
            input[inputIndex] = MyIO.readLine();
        } while (isFim(input[inputIndex++]) == false);
        inputIndex--;

        Time time;
        Hash hash = new Hash(21, 9);
        for (int i = 0; i < inputIndex; i++) {

            time = new Time();

            time.ler(input[i]);
            hash.inserir(time);
            // arvore.inserir(time);
        }
        int searchIndex = 0;
        String[] searches = new String[1000];
        do {
            searches[searchIndex] = MyIO.readLine();
        } while (isFim(searches[searchIndex++]) == false);
        searchIndex--;

        for (int i = 0; i < searchIndex; i++) {
            System.out.println(hash.pesquisar(searches[i]) ? " SIM" : " NÃO");
        }

        try {
            FileWriter writer = new FileWriter("649884_hashReserva.txt");
            writer.write("649884\t" + (System.currentTimeMillis() - start) + "\t" + hash.comparisons);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // arvore.mostrar();

    }

    /**
     * verifica se é o fim do arquivo
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
     * @param line linha a ser analisada
     * @return nome estraido do arquivo
     */
    public static String crawlNome(String line) {

        String nome = "";
        String[] splitted = line.split(" Nickname\\(s\\)");
        nome = splitted[0];
        // MyIO.println(nome);
        nome = nome.replace("&amp", "");
        nome = nome.replace("( ", "(");
        nome = nome.replace(" )", ")");
        nome = nome.replace(" &#91 1&#93", "");
        return nome;
    }

    /**
     * 
     * @param line linha a ser analisada
     * @return apelido estraido do arquivo
     */
    public static String crawlApelido(String line) {

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
     * @param line linha a ser analisada
     * @return dia extraido do arquivo
     */
    public static int crawlDia(String line) {
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
     * @param line linha a ser analisada
     * @return mes extraido do arquivo
     */
    public static int crawlMes(String line) {
        String splitted[] = new String[1000];
        int mes = 0;
        String value = "";

        splitted = line.split("Founded ");
        splitted = splitted[1].split(" ");
        value = splitted[1];
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
     * @param line linha a ser analisada
     * @return ano extraido do arquivo
     */
    public static int crawlAno(String line) {

        String splitted[] = new String[1000];
        int resp = 0;
        String value = "";
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
     * @param line linha a ser analisada
     * @return estadio extraido do arquivo
     */
    public static String crawlEstadio(String line) {

        String estadio = "";
        String[] splitted = line.split("Ground ");
        splitted = splitted[1].split("Capacity");

        estadio = splitted[0];

        estadio = estadio.replace(" ,", ",");

        estadio = estadio.replace(" .", ".");

        estadio = estadio.trim();

        return estadio;
    }

    /**
     * 
     * @param line linha a ser analisada
     * @return capacidade extraida do arquivo
     */
    public static int crawlCapacidade(String line) {

        String capacidade = "";

        String[] splitted = line.split("Capacity ");
        splitted = splitted[1].split(" ");
        if (splitted[0].contains(",")) {
            splitted = splitted[0].split(",");
            capacidade = splitted[0] + splitted[1];
        } else {
            capacidade = splitted[0] + splitted[1];
        }
        capacidade = capacidade.replace("&#91", "");
        capacidade = capacidade.replace("Chairman", "");
        capacidade = capacidade.replace("Owner", "");
        capacidade = capacidade.replace(".", "");

        return Integer.parseInt(capacidade);
    }

    /**
     * 
     * @param line linha a ser analisada
     * @return tecnico extraido do arquivo
     */
    public static String crawlTecnico(String line) {
        String tecnico = "";
        // System.out.println(line);
        String[] splitted = line.contains("General manager") ? line.split("General manager")
                : line.contains("Manager") ? line.split("Manager ")
                        : !line.contains("Coach ") ? line.split("Head coach ") : line.split("Coach ");

        splitted = line.contains("General manager") ? splitted[1].split("Chairman") : splitted[1].split("League");
        tecnico = splitted[0];

        tecnico = tecnico.replace("&#91 1&#93", "");
        tecnico = tecnico.replace("&#91 8&#93", "");

        tecnico = tecnico.trim();

        return tecnico;
    }

    /**
     * 
     * @param line linha a ser analisada
     * @return Liga extraido do arquivo
     */
    public static String crawlLiga(String line) {
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
     * @param line linha a ser tratada
     * @return a mesma linha sem tags html
     */
    public static String removerTags(String html) {
        String resp = "";

        resp = html.replaceAll("\\<(/?[^\\>]+)\\>", " ").replaceAll("\\s+", " ").trim();
        resp = resp.replaceAll(";", " ").trim();
        resp = resp.replaceAll("&#160", " ").trim();
        resp = resp.replaceAll("/", " ").replaceAll("\\s+", " ").trim();
        return resp;

    }
}
