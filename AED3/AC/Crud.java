import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

class Crud<T extends Registro> {
    private RandomAccessFile arq;
    private RandomAccessFile arq_s;
    private HashExtensivel indice_direto;
    private ArvoreBMais_String_Int indice_indireto;
    private AB_sugestions indice_sugestions;
    Constructor<T> construtor;

    public Crud(Constructor<T> construtor) {
        this.construtor = construtor;
        try {
            arq = new RandomAccessFile("dados/dados.db", "rw");
            arq_s = new RandomAccessFile("dados/dados_s.db", "rw");

            // Verifico se o arquivo está vazio. Se estiver, trato de inicializar o
            // cabeçalho
            if (arq.length() == 0)
                arq.writeInt(0);
            if (arq_s.length() == 0)
                arq_s.writeInt(0);

            indice_direto = new HashExtensivel(5, "dados/diretorio.db", "dados/bucket.db");
            indice_indireto = new ArvoreBMais_String_Int(5, "dados/AB.db");
            indice_sugestions = new AB_sugestions("dados/sugestions.db");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cria um usuario no arquivo de dados
    public int create(String nome, String email, String senha) {
        int last_id = 0;
        int current_id = 0;
        try {
            arq.seek(0);
            last_id = arq.readInt();
            current_id = last_id + 1;
            arq.seek(0);
            arq.writeInt(current_id);

            // Criando o objeto usuario
            Usuario user = new Usuario(current_id, nome, email, senha);

            // Movo o ponteiro pro fim do arquivo
            arq.seek(arq.length());
            long begin = arq.getFilePointer();
            // System.out.println(begin);
            arq.writeByte('*');
            arq.seek(arq.length());
            arq.writeShort(user.toByteArray().size());
            arq.seek(arq.length());
            arq.write(user.toByteArray().toByteArray());

            // Indices
            indice_direto.create(user.getId(), begin);
            indice_indireto.create(email, user.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return current_id;
    }

    // Cria uma sugestao no arquivo de dados
    public int create(int id, String p, String l, float f, String o) {
        int last_id = 0;
        int current_id = 0;
        long begin = 0;
        try {
            arq_s.seek(0);
            last_id = arq_s.readInt();
            current_id = last_id + 1;
            arq_s.seek(0);
            arq_s.writeInt(current_id);

            // Criando o objeto usuario
            Sugestao sugestao = new Sugestao(current_id, id, p, l, f, o);

            // Movo o ponteiro pro fim do arquivo
            arq_s.seek(arq_s.length());
            begin = arq_s.getFilePointer();
            // System.out.println(begin);
            arq_s.writeByte('*');
            arq_s.seek(arq_s.length());
            arq_s.writeShort(sugestao.toByteArray().size());
            arq_s.seek(arq_s.length());
            arq_s.write(sugestao.toByteArray().toByteArray());

            indice_sugestions.insere(id, begin);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return current_id;
    }

    // Lista as sugestões de um dado usuario
    public void list(int idU) {
        ArrayList<Long> sugestoes = null;
        String p = "";
        try {
            sugestoes = indice_sugestions.getSugestions(idU);

            for (int i = 0; i < sugestoes.size(); i++) {
                arq_s.seek(sugestoes.get(i));
                byte lapide = arq_s.readByte();
                short tamanho_reg = arq_s.readShort();
                int idSugestao = arq_s.readInt();
                int idUsuario = arq_s.readInt();
                String produto = arq_s.readUTF();
                String loja = arq_s.readUTF();
                Float valor = arq_s.readFloat();
                String obs = arq_s.readUTF();

                System.out.println((i + 1) + ". " + produto);
                System.out.println(loja);
                System.out.println("R$ " + valor);
                System.out.println(obs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Busca um usuario no arquivo de dados pela chave primaria id
    public Usuario read(int id) {
        Usuario user = new Usuario();
        try {
            long endereco = indice_direto.read(id);
            arq.seek(endereco + 1);
            short reg_size = arq.readShort();
            // System.out.println(reg_size);
            arq.seek(endereco + 3);
            byte[] data = new byte[reg_size];
            arq.readFully(data);

            user.fromByteArray(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // Busca um usuario no arquivo de dados pela chave secundaria email
    public Usuario read(String email) {
        Usuario user = new Usuario();
        try {
            int id = indice_indireto.read(email);
            user = read(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public int emailExists(String email) throws Exception {
        return indice_indireto.read(email);
    }

}