import java.io.*;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;

public class Lista_Invertida {
    String nomeArqTermos;
    String nomeArqLista;
    String nomeArqDados;
    RandomAccessFile arqTermos;
    RandomAccessFile arqLista;
    RandomAccessFile arqDados;

    public Lista_Invertida(String nT, String nL, String nD) throws Exception {
        nomeArqLista = nL;
        nomeArqTermos = nT;
        nomeArqDados = nD;

        arqLista = new RandomAccessFile("dados/" + nL, "rw");
        arqTermos = new RandomAccessFile("dados/" + nT, "rw");
        arqDados = new RandomAccessFile("dados/" + nD, "rw");

        if (arqDados.length() == 0)
            arqDados.writeInt(0);

        if (arqTermos.length() == 0)
            arqTermos.writeInt(0);

    }

    void create(String n) throws Exception {
        int last_id = 0;
        int current_id = 0;

        // Insere no arquivo de dados principal
        arqDados.seek(0);
        last_id = arqDados.readInt();
        current_id = last_id + 1;
        arqDados.seek(0);
        arqDados.writeInt(current_id);

        arqDados.seek(arqDados.length());
        arqDados.writeInt(current_id);
        arqDados.writeUTF(n);

        // Prepara para inserçao na lista de termos e de ids

        // remove acentos e torna lower case
        String handled_string = Normalizer.normalize(n, Form.NFD);
        handled_string = handled_string.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        handled_string = handled_string.toLowerCase();

        String[] broken_string = handled_string.split(" ");

        for (String termo : broken_string) {
            /*
             * Para cada palavra quebrada do meu dado, preciso verificar se ele ja existe na
             * lista de termos
             */

            // Percorre Sequencialmente a lista de termos, verificando se o termo ja existe
            if (buscaTermo(termo) == -1) {
                long endereco = insereTermo(termo, arqTermos.length());
                criaBloco(endereco, 1, current_id, -1);
            } else {
                insereBloco(current_id, buscaTermo(termo), termo);
            }

        }

    }

    ArrayList<Long> buscar(String termo) throws Exception {
        // remove acentos e torna lower case
        String handled_string = Normalizer.normalize(termo, Form.NFD);
        handled_string = handled_string.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        handled_string = handled_string.toLowerCase();
        String[] broken_string = handled_string.split(" ");

        ArrayList<Integer> out = new ArrayList<Integer>();
        ArrayList<Integer> aux = new ArrayList<Integer>();

        long e = buscaTermo(broken_string[0]);
        arqLista.seek(e);
        int n = arqLista.readInt();
        for (int i = 0; i < n; i++) {
            out.add(arqLista.readInt());
        }

        for (int i = 1; i < broken_string.length; i++) {
            aux.clear();
            e = buscaTermo(broken_string[i]);
            arqLista.seek(e);
            n = arqLista.readInt();
            for (int j = 0; j < n; j++) {
                aux.add(arqLista.readInt());
            }

            out.retainAll(aux);
        }

        ArrayList<Long> out_endereco = new ArrayList<Long>();
        for (int i = 0; i < out.size(); i++) {
            out_endereco.add(busca_id(out.get(i)));
        }

        return out_endereco;

    }

    long insereTermo(String termo, long endereco) throws Exception {
        arqTermos.seek(0);
        int n = arqTermos.readInt();

        long current_offset = (n + 1) * 50;

        arqTermos.seek(0);
        arqTermos.writeInt(n + 1);

        arqTermos.seek(arqTermos.length());
        arqTermos.writeUTF(termo);
        arqTermos.writeLong(current_offset);

        return current_offset;

    }

    long buscaTermo(String termo) throws Exception {
        arqTermos.seek(0);
        int n = arqTermos.readInt();
        String t = "";
        long e = 0;

        long out = -1;

        if (n != 0) {
            for (int i = 0; i < n; i++) {
                t = arqTermos.readUTF();
                e = arqTermos.readLong();
                if (t.equals(termo)) {
                    out = e;
                    break;
                }

            }
        }

        return out;
    }

    long criaBloco(long e, int n, int id, long prox) throws Exception {
        arqLista.seek(e);
        arqLista.writeInt(n);
        arqLista.writeInt(id);
        for (int i = 0; i < 9; i++) {
            arqLista.writeInt(-1);
        }
        arqLista.writeLong(prox);

        return e;
    }

    void insereBloco(int id, long e, String termo) throws Exception {
        arqLista.seek(e);
        int n = arqLista.readInt();
        if (n <= 10) {
            arqLista.seek(e);
            arqLista.writeInt(n + 1);

            int offset = n * 4;

            arqLista.seek(e + 4 + offset);
            arqLista.writeInt(id);
        } else {
            long new_bloco = criaBloco(e * 10, 1, id, -1);
            arqLista.seek(e + 4 + n * 4);
            arqLista.writeLong(new_bloco);
        }
    }

    long busca_id(int chave) throws Exception {
        arqDados.seek(0);
        int n = arqDados.readInt();
        int id = 0;
        String nome = "";

        long out = -1;

        for (int i = 0; i < n; i++) {
            long pointer = arqDados.getFilePointer();
            id = arqDados.readInt();
            nome = arqDados.readUTF();
            if (id == chave) {
                out = pointer;
                break;
            }
        }

        return out;
    }

    String busca_endereco(long e) throws Exception {
        arqDados.seek(e);
        arqDados.readInt();
        String nome = arqDados.readUTF();

        return nome;
    }
}