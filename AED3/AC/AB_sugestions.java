import java.io.*;

public class AB_sugestions {
    private int ordem;
    private RandomAccessFile arquivo;
    private String nomeArquivo;

    private int C;
    private int FC;
    private long P;
    private boolean cresceu;
    private boolean diminuiu;

    private class _Pagina {
        protected int ordem;
        protected int n;
        protected int[] chaves;
        protected int[] foreign_chave;
        protected long[] filhos;
        private int TAMANHO_REGISTRO = 12;
        protected int TAMANHO_PAGINA;

        public _Pagina(int o) {
            n = 0;
            ordem = o;
            chaves = new int[ordem * 2];
            foreign_chave = new int[ordem * 2];
            filhos = new long[ordem * 2 + 1];
            for (int i = 0; i < ordem * 2; i++) {
                chaves[i] = -1;
                foreign_chave[i] = -1;
                filhos[i] = -1;

            }
            filhos[ordem * 2] = -1;
            TAMANHO_PAGINA = 4 + (ordem * 2) * 20 + 8;

        }

        protected byte[] getBytes() throws IOException {
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(ba);
            out.writeInt(n);
            int i = 0;
            while (i < n) {
                out.writeLong(filhos[i]);
                out.writeInt(chaves[i]);
                out.writeInt(foreign_chave[i]);
                i++;
            }
            out.writeLong(filhos[i]);
            byte[] registroVazio = new byte[TAMANHO_REGISTRO];
            while (i < ordem * 2) {
                out.write(registroVazio);
                out.writeLong(filhos[i + 1]);
                i++;
            }
            return ba.toByteArray();
        }

        public void setBytes(byte[] buffer) throws IOException {
            ByteArrayInputStream ba = new ByteArrayInputStream(buffer);
            DataInputStream in = new DataInputStream(ba);
            n = in.readInt();
            int i = 0;
            while (i < n) {
                filhos[i] = in.readLong();
                chaves[i] = in.readInt();
                foreign_chave[i] = in.readInt();
                i++;
            }
            filhos[i] = in.readLong();
        }

    }

    public AB_sugestions(int o, String na) throws IOException {
        ordem = o;
        nomeArquivo = na;
        arquivo = new RandomAccessFile(na, "rw");
        if (arquivo.length() < 8)
            arquivo.writeLong(-1);

    }

    public void inserir(int idS, int idU) throws IOException {
        arquivo.seek(0);
        long pagina;
        pagina = arquivo.readLong();

        C = idS;
        FC = idU;
        P = -1;
        cresceu = false;

        inserePag(pagina);
        if (cresceu) {
            _Pagina novaPagina = new _Pagina(ordem);
            novaPagina.n = 1;
            novaPagina.chaves[0] = C;
            novaPagina.foreign_chave[0] = FC;
            novaPagina.filhos[0] = pagina;
            novaPagina.filhos[1] = P;

            // Achar o espaço em disco....
            arquivo.seek(arquivo.length());
            long raiz = arquivo.getFilePointer();
            arquivo.write(novaPagina.getBytes());
            arquivo.seek(0);
            arquivo.writeLong(raiz);
        }
    }

    private void inserePag(long pagina) throws IOException {
        if (pagina == -1) {
            cresceu = true;
            P = -1;
            return;
        }

        arquivo.seek(pagina);
        _Pagina pa = new _Pagina(ordem);
        byte[] buffer = new byte[pa.TAMANHO_PAGINA];
        arquivo.read(buffer);
        pa.setBytes(buffer);

        int i = 0;
        while (i < pa.n && C > pa.chaves[i]) {
            i++;
        }
        if (i < pa.n) {
            if (C < pa.chaves[i])
                inserePag(pa.filhos[i]);
            else
                inserePag(pa.filhos[i + 1]);
        } else
            inserePag(pa.filhos[i]);

        if (!cresceu)
            return;

        if (pa.n < ordem * 2) {
            for (int j = pa.n; j > i; j--) {
                pa.chaves[j] = pa.chaves[j - 1];
                pa.foreign_chave[j] = pa.foreign_chave[j - 1];
                pa.filhos[j] = pa.filhos[j - 1];

            }
            pa.chaves[i] = C;
            pa.foreign_chave[i] = FC;
            pa.filhos[i + 1] = P;
            pa.n++;

            arquivo.seek(pagina);
            arquivo.write(pa.getBytes());
            cresceu = false;
            return;
        }

        _Pagina np = new _Pagina(ordem);
        for (int j = 0; j < ordem; j++) {
            np.chaves[j] = pa.chaves[j + ordem];
            np.foreign_chave[j] = pa.foreign_chave[j + ordem];
            np.filhos[j + 1] = pa.filhos[j + ordem + 1];
        }
        np.filhos[0] = pa.filhos[ordem];
        np.n = ordem;
        pa.n = ordem;

        // Testa o lado de inserção
        if (i <= ordem) { // novo registro deve ficar na página da esquerda
            if (i == ordem) { // Testa se é o próprio registro que será promovido
                np.filhos[0] = P;
            } else {
                int c_aux = pa.chaves[ordem - 1];
                int e_aux = pa.foreign_chave[ordem - 1];
                for (int j = ordem; j > 0 && j > i; j--) {
                    pa.chaves[j] = pa.chaves[j - 1];
                    pa.foreign_chave[j] = pa.foreign_chave[j - 1];
                    pa.filhos[j + 1] = pa.filhos[j];
                }
                pa.chaves[i] = C;
                pa.foreign_chave[i] = FC;
                pa.filhos[i + 1] = P;
                C = c_aux;
                FC = e_aux;
            }
        } else {
            int c_aux = np.chaves[0];
            int e_aux = np.foreign_chave[0];
            int j;
            for (j = 0; j < ordem - 1 && np.chaves[j + 1] < C; j++) {
                np.chaves[j] = np.chaves[j + 1];
                np.foreign_chave[j] = np.foreign_chave[j + 1];
                np.filhos[j] = np.filhos[j + 1];
            }
            np.filhos[j] = np.filhos[j + 1];
            np.chaves[j] = C;
            np.foreign_chave[j] = FC;
            np.filhos[j + 1] = P;
            C = c_aux;
            FC = e_aux;
        }

        arquivo.seek(pagina);
        arquivo.write(pa.getBytes());
        arquivo.seek(arquivo.length());
        P = arquivo.getFilePointer();
        arquivo.write(np.getBytes());
    }
}