import java.io.*;

class Crud {
    private RandomAccessFile arq;
    private HashExtensivel indice_direto;
    private ArvoreBMais_String_Int indice_indireto;

    public Crud() {

        try {
            arq = new RandomAccessFile("dados/dados.db", "rw");

            // Verifico se o arquivo está vazio. Se estiver, trato de inicializar o
            // cabeçalho
            if (arq.length() == 0)
                arq.writeInt(0);

            indice_direto = new HashExtensivel(5, "dados/diretorio.db", "dados/bucket.db");
            indice_indireto = new ArvoreBMais_String_Int(5, "dados/AB.db");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            indice_indireto.create(user.getEmail(), user.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return current_id;
    }

}