import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

class Crud<T extends Registro> {
    private RandomAccessFile arq;
    private RandomAccessFile arq_s;
    private RandomAccessFile arq_g;
    private HashExtensivel indice_direto;
    private ArvoreBMais_String_Int indice_indireto;
    private HashExtensivel indice_direto_sugestoes;
    private HashExtensivel indice_direto_grupo;
    private AB_sugestions indice_sugestions;
    private AB_grupos indice_grupos;
    private int[][] associacao_vetorid;
    private int associacao_n;
    private int[][] associacao_vetorid_grupo;
    private int associacao_n_grupo;
    Constructor<T> construtor;

    public Crud(Constructor<T> construtor) {
        this.construtor = construtor;
        associacao_n = 500; // Mudar conforme necessidade
        associacao_vetorid = new int[associacao_n][2];

        associacao_n_grupo = 500; // Mudar conforme necessidade
        associacao_vetorid_grupo = new int[associacao_n_grupo][2];

        for (int i = 0; i < associacao_n; i++) {
            associacao_vetorid[i][0] = -1;
        }
        for (int i = 0; i < associacao_n; i++) {
            associacao_vetorid[i][1] = -1;
        }

        for (int i = 0; i < associacao_n_grupo; i++) {
            associacao_vetorid_grupo[i][0] = -1;
        }
        for (int i = 0; i < associacao_n; i++) {
            associacao_vetorid_grupo[i][1] = -1;
        }

        try {
            arq = new RandomAccessFile("dados/dados.db", "rw");
            arq_s = new RandomAccessFile("dados/dados_s.db", "rw");
            arq_g = new RandomAccessFile("dados/dados_g.db", "rw");

            // Verifico se o arquivo está vazio. Se estiver, trato de inicializar o
            // cabeçalho
            if (arq.length() == 0)
                arq.writeInt(0);
            if (arq_s.length() == 0)
                arq_s.writeInt(0);
            if (arq_g.length() == 0)
                arq_g.writeInt(0);

            indice_direto = new HashExtensivel(5, "dados/diretorio.db", "dados/bucket.db");
            indice_indireto = new ArvoreBMais_String_Int(5, "dados/AB.db");
            indice_sugestions = new AB_sugestions("dados/sugestions.db");
            indice_direto_sugestoes = new HashExtensivel(5, "dados/diretorio_sugestions.db",
                    "dados/bucket_sugestions.db");
            indice_grupos = new AB_grupos("dados/grupos.db");
            indice_direto_grupo = new HashExtensivel(5, "dados/diretorio_grupos.db", "dados/bucket_grupos.db");

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
            indice_direto_sugestoes.create(current_id, begin);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return current_id;
    }

    // Cria uma grupo no arquivo de dados
    public int create(int idU, String n, long ms, float v, long me, String le, String o, boolean s, boolean a) {
        int last_id = 0;
        int current_id = 0;
        long begin = 0;
        try {
            arq_g.seek(0);
            last_id = arq_g.readInt();
            current_id = last_id + 1;
            arq_g.seek(0);
            arq_g.writeInt(current_id);

            // Criando o objeto usuario
            Grupo grupo = new Grupo(current_id, idU, n, ms, v, me, le, o, s, a);

            // Movo o ponteiro pro fim do arquivo
            arq_g.seek(arq_g.length());
            begin = arq_g.getFilePointer();
            // System.out.println(begin);
            arq_g.writeByte('*');
            arq_g.seek(arq_g.length());
            arq_g.writeShort(grupo.toByteArray().size());
            arq_g.seek(arq_g.length());
            arq_g.write(grupo.toByteArray().toByteArray());

            indice_grupos.insere(idU, begin);
            indice_direto_grupo.create(current_id, begin);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return current_id;
    }

    public void organiza_vetor(int id) {
        ArrayList<Long> sugestoes = null;

        try {
            sugestoes = indice_sugestions.getSugestions(id);
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

                associacao_vetorid[i][0] = i + 1;
                associacao_vetorid[i][1] = idSugestao;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

                if (lapide == '*') {

                    System.out.println((i + 1) + ". " + produto);
                    System.out.println(loja);
                    System.out.println("R$ " + valor);
                    System.out.println(obs);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lista os grupos de um dado usuario
    public void list_grupo(int idU) {
        ArrayList<Long> grupos = null;
        String p = "";
        try {
            grupos = indice_grupos.getGrupos(idU);

            for (int i = 0; i < grupos.size(); i++) {
                // System.out.println(grupos.get(i));
                arq_g.seek(grupos.get(i));
                byte lapide = arq_g.readByte();
                short tamanho_reg = arq_g.readShort();
                int idGrupo = arq_g.readInt();
                int idUsuario = arq_g.readInt();
                String nome = arq_g.readUTF();
                long momentoSorteio = arq_g.readLong();
                float valor = arq_g.readFloat();
                long momentoEncontro = arq_g.readLong();
                String local = arq_g.readUTF();
                String obs = arq_g.readUTF();
                boolean sorteado = arq_g.readBoolean();
                boolean ativo = arq_g.readBoolean();

                if (ativo == true) {

                    System.out.println((i + 1) + ". " + nome);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Altera uma sugestão
    public void update_sugestao(Sugestao s) throws Exception {
        long endereco = indice_direto_sugestoes.read(s.getId());
        arq_s.seek(endereco + 1);
        long old_size = arq_s.readShort();
        if (old_size == s.toByteArray().size()) {
            arq_s.seek(endereco);
            arq_s.writeByte('*');
            arq_s.writeShort(s.toByteArray().size());
            arq_s.write(s.toByteArray().toByteArray());

        } else {
            arq_s.seek(endereco);
            arq_s.writeByte('$');
            arq_s.seek(arq_s.length());
            long begin = arq_s.getFilePointer();
            arq_s.writeByte('*');
            arq_s.writeShort(s.toByteArray().size());
            arq_s.write(s.toByteArray().toByteArray());

            indice_sugestions.insere(s.getIdUsuario(), begin);
            indice_direto_sugestoes.update(s.getId(), begin);

        }
    }

    public void delete_sugestao(int pseudo_id) {
        int idSugestao = 0;
        for (int i = 0; i < associacao_vetorid.length; i++) {
            // System.out.println(associacao_vetorid[i][1]);
            if (associacao_vetorid[i][0] == pseudo_id) {
                idSugestao = associacao_vetorid[i][1];
            }
        }
        try {
            long endereco = indice_direto_sugestoes.read(idSugestao);
            arq_s.seek(endereco);
            arq_s.writeByte('$');
            indice_direto_sugestoes.delete(idSugestao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Busca um usuario no arquivo de dados pela chave primaria id
    public Sugestao read_Sugestao(int pseudo_id) {
        Sugestao s = new Sugestao();
        int idSugestao = 0;
        for (int i = 0; i < associacao_vetorid.length; i++) {
            // System.out.println(associacao_vetorid[i][1]);
            if (associacao_vetorid[i][0] == pseudo_id) {
                idSugestao = associacao_vetorid[i][1];
            }
        }
        try {
            long endereco = indice_direto_sugestoes.read(idSugestao);
            arq_s.seek(endereco + 1);
            short reg_size = arq_s.readShort();
            // System.out.println(reg_size);
            arq_s.seek(endereco + 3);
            byte[] data = new byte[reg_size];
            arq_s.readFully(data);

            s.fromByteArray(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
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