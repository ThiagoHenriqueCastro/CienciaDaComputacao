import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class learq {
    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");

        String nome;
        int i = 0;
        Time time[] = new Time[1000];
        long tamanho = 0;

        String name = "";
        String estadio = "";
        String apelido = "";
        String tecnico = "";
        String liga = "";
        int capacidade = 0;
        int mes = 0;
        int dia = 0;
        int ano = 0;
        nome = MyIO.readLine();

        while (isFim(nome) == false) {

            try {

                File file = new File(nome);
                FileReader arq = new FileReader(file);
                BufferedReader lerArq = new BufferedReader(arq);
                String linha = lerArq.readLine();
                tamanho = file.length();
                while (linha != null) {

                    if (linha.contains("Full name")) {
                        if (linha.contains("Capital</a>")) {

                            name = Nome(linha);

                            String Proxlinha = lerArq.readLine();
                            String Valor = linha + Proxlinha;
                            apelido = Apelido(Valor);
                            tecnico = Tecnico(Valor);
                            liga = Liga(Valor);
                            estadio = Estadio(Valor);
                            capacidade = Capacidade(Valor);
                            mes = Mes(Valor);
                            dia = Dia(Valor);
                            ano = Ano(Valor);
                        } else {
                            name = Nome(linha);
                            apelido = Apelido(linha);
                            tecnico = Tecnico(linha);
                            liga = Liga(linha);
                            estadio = Estadio(linha);
                            capacidade = Capacidade(linha);
                            mes = Mes(linha);
                            dia = Dia(linha);
                            ano = Ano(linha);
                        }

                    }
                    linha = lerArq.readLine();
                }

                arq.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            time[i] = new Time(name, apelido, estadio, tecnico, liga, nome, capacidade, dia, mes, ano, tamanho);

            nome = "";
            nome = MyIO.readLine();
            i++;
        }
        for (int j = 0; j < i; j++) {
            time[j].imprimir();

        }
    }

    public static boolean isFim(String nome) {
        boolean resposta = false;
        if (nome.length() == 3 && nome.charAt(0) == 'F' && nome.charAt(1) == 'I' && nome.charAt(2) == 'M') {
            resposta = true;
        }
        return resposta;

    }

    public static String removerTags(String linha) {
        String resposta = "";

        resposta = linha.replaceAll("\\<(/?[^\\>]+)\\>", " ").replaceAll("\\s+", " ").trim();
        resposta = resposta.replaceAll(";", " ").trim();
        resposta = resposta.replaceAll("&#160", " ").trim();
        resposta = resposta.replaceAll("/", " ").replaceAll("\\s+", " ").trim();
        return resposta;
    }

    public static String Nome(String linha) {
        String nomesplit[] = new String[1000];
        String name = "";
        String aux = "";
        aux = removerTags(linha);
        nomesplit = aux.split("Full name ");
        nomesplit = nomesplit[1].split(" Nickname");
        name = nomesplit[0];

        return name;
    }

    public static String Apelido(String linha) {
        String nomesplit[] = new String[1000];

        String name = "";
        String aux = "";

        aux = removerTags(linha);
        nomesplit = aux.split("Nickname\\(s\\) ");
        if (nomesplit[1].contains("Short name")) {
            nomesplit = nomesplit[1].split(" Short name");

        } else {
            nomesplit = nomesplit[1].split(" Founded");
        }

        name = nomesplit[0];
        MyIO.print(name);
        return name;
    }

    public static String Tecnico(String linha) {
        String nomesplit[] = new String[1000];
        String name = "";
        String aux = "";
        aux = removerTags(linha);
        nomesplit = aux.split("Manager ");
        if (aux.contains("Manager ")) {
            nomesplit = aux.split("Manager ");

        } else if (aux.contains("Head coach ")) {
            nomesplit = aux.split("Head coach ");
        } else if (aux.contains("Head Coach ")) {
            nomesplit = aux.split("Head Coach ");
        }
        nomesplit = nomesplit[1].split(" League");
        name = nomesplit[0];

        return name;
    }

    public static int Dia(String linha) {
        String nomesplit[] = new String[1000];
        String name = "";
        int resposta = 0;
        String aux = "";
        aux = removerTags(linha);
        nomesplit = aux.split("Founded ");
        nomesplit = nomesplit[1].split(" ");
        name = nomesplit[0];
        if (nomesplit[0].charAt(0) >= 'A' && nomesplit[0].charAt(0) <= 'Z') {
            nomesplit = nomesplit[1].split(",");
            name = nomesplit[0];
        }
        if (aux.contains("January") || aux.contains("February") || aux.contains("March") || aux.contains("April")
                || aux.contains("May") || aux.contains("June") || aux.contains("July") || aux.contains("September")
                || aux.contains("August") || aux.contains("October") || aux.contains("November") || aux.contains("July")
                || aux.contains("December")) {
            resposta = Integer.parseInt(name);
        } else {
            resposta = 0;
        }

        return resposta;
    }

    public static String Liga(String linha) {
        String nomesplit[] = new String[1000];
        String name = "";
        String aux = "";
        aux = removerTags(linha);

        nomesplit = aux.split("League ");
        nomesplit = nomesplit[1].split(" 2");
        name = nomesplit[0];

        return name;
    }

    public static int Ano(String linha) {
        String nomesplit[] = new String[1000];
        int resposta = 0;
        String name = "";
        String aux = "";
        aux = removerTags(linha);
        if (aux.contains("January")) {
            nomesplit = aux.split("January ");
        } else if (aux.contains("February")) {
            nomesplit = aux.split("February ");
        } else if (aux.contains("March")) {
            nomesplit = aux.split("March ");
        } else if (aux.contains("April")) {
            nomesplit = aux.split("April ");
        } else if (aux.contains("May")) {
            nomesplit = aux.split("May ");
        } else if (aux.contains("June")) {
            nomesplit = aux.split("June ");
        } else if (aux.contains("July")) {
            nomesplit = aux.split("July ");
        } else if (aux.contains("August")) {
            nomesplit = aux.split("August ");
        } else if (aux.contains("September")) {
            nomesplit = aux.split("September ");
        } else if (aux.contains("October")) {
            nomesplit = aux.split("October ");
        } else if (aux.contains("November")) {
            nomesplit = aux.split("November ");
        } else if (aux.contains("December")) {
            nomesplit = aux.split("December ");
        } else {
            nomesplit = aux.split("Founded ");
        }

        nomesplit = nomesplit[1].split(" ");
        name = nomesplit[0];
        if (nomesplit[0].contains(",")) {
            name = nomesplit[1];
        }
        resposta = Integer.parseInt(name);
        return resposta;
    }

    public static String Estadio(String linha) {
        String nomesplit[] = new String[1000];
        String name = "";
        String aux = "";
        aux = removerTags(linha);
        nomesplit = aux.split("Ground ");

        nomesplit = nomesplit[1].split(" Capacity");

        name = nomesplit[0];

        return name;
    }

    public static int Mes(String linha) {
        String nomesplit[] = new String[1000];
        int mes = 0;
        String name = "";
        String aux = "";
        aux = removerTags(linha);
        nomesplit = aux.split("Founded ");
        nomesplit = nomesplit[1].split(" ");
        name = nomesplit[1];
        if (name.equals("January")) {
            mes = 1;
        } else if (name.equals("February")) {
            mes = 2;
        } else if (name.equals("March")) {
            mes = 3;
        } else if (name.equals("April")) {
            mes = 4;
        } else if (name.equals("May")) {
            mes = 5;
        } else if (name.equals("June")) {
            mes = 6;
        } else if (name.equals("July")) {
            mes = 7;
        } else if (name.equals("August")) {
            mes = 8;
        } else if (name.equals("September")) {
            mes = 9;
        } else if (name.equals("October")) {
            mes = 10;
        } else if (name.equals("November")) {
            mes = 11;
        } else if (name.equals("December")) {
            mes = 12;
        } else {
            mes = 0;
        }
        return mes;
    }

    public static int Capacidade(String linha) {

        String nomesplit[] = new String[1000];
        String name = "";
        int resposta = 0;
        String aux = "";
        aux = removerTags(linha);
        nomesplit = aux.split("Capacity ");

        nomesplit = nomesplit[1].split(" ");

        if (nomesplit[0].contains(",")) {
            nomesplit = nomesplit[0].split(",");
            name = nomesplit[0] + nomesplit[1];
        } else {
            name = nomesplit[0] + nomesplit[1];
        }

        resposta = Integer.parseInt(name);

        return resposta;
    }

    class Lista {
        private String[] nome = new String[1000];
        private int n;

        public void inserirInicio(String x) throws Exception {

            if (n >= nome.length) {
                throw new Exception("Erro ao inserir!");
            }

            for (int i = n; i > 0; i--) {
                nome[i] = nome[i - 1];
            }

            nome[0] = x;
            n++;
        }

        public void inserirFim(String x) throws Exception {

            if (n >= nome.length) {
                throw new Exception("Erro ao inserir!");
            }

            nome[n] = x;
            n++;
        }

        public void inserir(String x, int pos) throws Exception {

            if (n >= nome.length || pos < 0 || pos > n) {
                throw new Exception("Erro ao inserir!");
            }

            for (int i = n; i > pos; i--) {
                nome[i] = nome[i - 1];
            }

            nome[pos] = x;
            n++;
        }

        public String removerInicio() throws Exception {

            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }

            String resp = nome[0];
            n--;

            for (int i = 0; i < n; i++) {
                nome[i] = nome[i + 1];
            }

            return resp;
        }

        public String removerFim() throws Exception {

            if (n == 0) {
                throw new Exception("Erro ao remover!");
            }

            return nome[--n];
        }

        public String remover(int pos) throws Exception {

            if (n == 0 || pos < 0 || pos >= n) {
                throw new Exception("Erro ao remover!");
            }

            String resp = nome[pos];
            n--;

            for (int i = pos; i < n; i++) {
                nome[i] = nome[i + 1];
            }

            return resp;
        }

        public void mostrar() {
            System.out.print("[ ");
            for (int i = 0; i < n; i++) {
                System.out.print(nome[i] + " ");
            }
            System.out.println("]");
        }
    }
}

class Time {
    private String nome, apelido, estadio, tecnico, liga, nomeArquivo;
    private int capacidade, fundacaoDia, fundacaoMes, fundacaoAno;
    private long paginaTam;

    public Time(String nome, String apelido, String estadio, String tecnico, String liga, String nomeArquivo,
            int capacidade, int fundacaoDia, int fundacaoMes, int fundacaoAno, long paginaTam) {

        setnome(nome);
        setapelido(apelido);
        setestadio(estadio);
        settecnico(tecnico);
        setliga(liga);
        setnomeArquivo(nomeArquivo);
        setcapacidade(capacidade);
        setfundacaoDia(fundacaoDia);
        setfundacaoAno(fundacaoAno);
        setfundacaoMes(fundacaoMes);
        setpaginaTam(paginaTam);

    }

    public Time() {
        setnome("");
        setapelido("");
        setestadio("");
        settecnico("");
        setliga("");
        setnomeArquivo("");
        setcapacidade(0);
        setfundacaoDia(0);
        setfundacaoDia(0);
        setfundacaoAno(0);
        setpaginaTam(0);

    }

    public String getnome() {
        return this.nome;
    }

    public String getapelido() {
        return this.apelido;
    }

    public String getestadio() {
        return this.estadio;
    }

    public String gettecnico() {
        return this.tecnico;
    }

    public String getliga() {
        return this.liga;
    }

    public String getnomeArquivo() {
        return this.nomeArquivo;
    }

    public int getcapacidade() {
        return this.capacidade;
    }

    public int getfundacaoDia() {
        return this.fundacaoDia;
    }

    public int getfundacaoMes() {
        return this.fundacaoMes;
    }

    public int getfundacaoAno() {
        return this.fundacaoAno;
    }

    public long getpaginaTam() {
        return this.paginaTam;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public void setapelido(String apelido) {
        this.apelido = apelido;
    }

    public void setestadio(String estadio) {
        this.estadio = estadio;
    }

    public void settecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public void setliga(String liga) {
        this.liga = liga;
    }

    public void setnomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void setcapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setfundacaoDia(int fundacaoDia) {
        this.fundacaoDia = fundacaoDia;
    }

    public void setfundacaoMes(int fundacaoMes) {
        this.fundacaoMes = fundacaoMes;
    }

    public void setfundacaoAno(int fundacaoAno) {
        this.fundacaoAno = fundacaoAno;
    }

    public void setpaginaTam(long paginaTam) {
        this.paginaTam = paginaTam;
    }

    public void imprimir() {

        System.out.printf(getnome() + " ## ");
        System.out.printf(getapelido() + " ## ");
        if (getfundacaoDia() >= 0 && getfundacaoDia() <= 9) {

            if (getfundacaoMes() >= 0 && getfundacaoMes() <= 9) {
                MyIO.print("0" + getfundacaoDia() + "/" + "0" + getfundacaoMes() + "/" + getfundacaoAno() + " ## ");
            } else {
                MyIO.print("0" + getfundacaoDia() + "/" + getfundacaoMes() + "/" + getfundacaoAno() + " ## ");
            }

        }

        else {
            if (getfundacaoMes() >= 0 && getfundacaoMes() <= 9) {
                MyIO.print(getfundacaoDia() + "/" + "0" + getfundacaoMes() + "/" + getfundacaoAno() + " ## ");
                ;
            } else {
                MyIO.print(getfundacaoDia() + "/" + getfundacaoMes() + "/" + getfundacaoAno() + " ## ");
            }

        }
        MyIO.print(getestadio() + " ## ");
        MyIO.print(getcapacidade() + " ## ");
        MyIO.print(gettecnico() + " ## ");
        MyIO.print(getliga() + " ## ");
        MyIO.print(getnomeArquivo() + " ## ");
        MyIO.print(getpaginaTam() + " ## \n");
    }

}