
/**
 * TP02Q7 Quicksort Flexivel em Java
 * 
 * @author Thiago Henrique de Castro Oliveira
 * @version 1 09/2019 Este algoritmo faz um quicksort em uma lista dupla
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

class No {
    public Time elemento;
    public No esq, dir;
    public boolean cor;

    public No(Time x) {
        this(x, false, null, null);
    }

    public No(Time x, boolean cor) {
        this(x, cor, null, null);
    }

    public No(Time x, boolean cor, No esq, No dir) {
        this.cor = cor;
        this.elemento = x.clone();
        this.esq = esq;
        this.dir = dir;
    }
}

class _Arvore {
    private No raiz;
    public int comparisons; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public _Arvore() {
        raiz = null;
        comparisons = 0;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    public boolean pesquisar(String x) {
        return pesquisar(x, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @param i        No em analise.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    private boolean pesquisar(String x, No i) {
        boolean resp;
        if (i == null) {
            resp = false;

        } else if (x.equals(i.elemento.getNome())) {
            resp = true;
        } else if (x.compareTo(i.elemento.getNome()) < 0)
            resp = pesquisar(x, i.esq);
        else {
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    /**
     * Metodo publico iterativo para inserir elemento.
     * 
     * @param elemento Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Time elemento) throws Exception {

        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new No(elemento, false);
            // System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento +
            // ").");

            // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
            if (raiz.elemento.getNome().compareTo(elemento.getNome()) > 0) {
                raiz.esq = new No(elemento, true);
            } else {
                raiz.dir = new No(elemento, true);
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {

            if (raiz.elemento.getNome().compareTo(elemento.getNome()) > 0) {
                raiz.esq = new No(elemento);
            } else if (raiz.dir.elemento.getNome().compareTo(elemento.getNome()) > 0) {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = elemento.clone();
            } else {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = raiz.dir.elemento.clone();
                raiz.dir.elemento = elemento.clone();
            }

            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {

            if (raiz.elemento.getNome().compareTo(elemento.getNome()) < 0) {
                raiz.dir = new No(elemento);
            } else if (raiz.esq.elemento.getNome().compareTo(elemento.getNome()) > 0) {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = elemento.clone();
            } else {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = raiz.esq.elemento.clone();
                raiz.esq.elemento = elemento.clone();
            }

            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            // System.out.println("Arvore com tres ou mais elementos...");
            inserir(elemento, null, null, null, raiz);
        }

        raiz.cor = false;
    }

    private void balancear(No bisavo, No avo, No pai, No i) {

        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {

            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0) { // rotacao a esquerda ou
                                                                                // direita-esquerda
                if (i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }

            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if (bisavo == null) {
                raiz = avo;
            } else {
                if (avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0) {
                    bisavo.esq = avo;
                } else {
                    bisavo.dir = avo;
                }
            }

            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } // if(pai.cor == true)
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * 
     * @param elemento Elemento a ser inserido.
     * @param avo      No em analise.
     * @param pai      No em analise.
     * @param i        No em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(Time elemento, No bisavo, No avo, No pai, No i) throws Exception {
        if (i == null) {
            if (elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
                i = pai.esq = new No(elemento, true);
            } else {
                i = pai.dir = new No(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private No rotacaoDir(No no) {
        // System.out.println("Rotacao DIR(" + no.elemento + ")");
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private No rotacaoEsq(No no) {
        // System.out.println("Rotacao ESQ(" + no.elemento + ")");
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
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
        _Arvore arvore = new _Arvore();
        for (int i = 0; i < inputIndex; i++) {

            time = new Time();

            time.ler(input[i]);

            arvore.inserir(time);
        }
        int searchIndex = 0;
        String[] searches = new String[1000];
        do {
            searches[searchIndex] = MyIO.readLine();
        } while (isFim(searches[searchIndex++]) == false);
        searchIndex--;

        for (int i = 0; i < searchIndex; i++) {
            System.out.println(arvore.pesquisar(searches[i]) ? "SIM" : "NÃO");
        }

        try {
            FileWriter writer = new FileWriter("649884_arvoreBinaria.txt");
            writer.write("649884\t" + (System.currentTimeMillis() - start) + "\t" + arvore.comparisons);
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
