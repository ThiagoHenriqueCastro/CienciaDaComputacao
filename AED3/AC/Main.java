import java.io.*;
import java.util.Scanner;

public class Main {
    public static int active_id = 0;

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        Crud<Usuario> crud = null;

        try {
            crud = new Crud<>(Usuario.class.getConstructor());
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte flag = 0;
        String email = "";
        String nome = "";
        String senha = "";

        do {
            System.out.println("AMIGO OCULTO 1.0");
            System.out.println("================");
            System.out.println("ACESSO");
            System.out.println("1) Acesso ao sistema");
            System.out.println("2) Novo usuario (primeiro acesso)");
            System.out.println("0) Sair");

            flag = reader.nextByte();
            reader.nextLine();

            // Acesso de usuario ja existente
            if (flag == 1) {
                System.out.println("ACESSO AO SISTEMA");
                System.out.print("Email: ");
                email = reader.nextLine();
                int isRegistered = 0;
                try {
                    isRegistered = crud.emailExists(email);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isRegistered == -1) {
                    System.out.println("NÃO EXISTE NENHUMA CONTA ASSOCIADA A ESSE EMAIL\n");
                    press_toContinue();
                } else {
                    System.out.print("Senha: ");
                    senha = reader.nextLine();
                    Usuario user = new Usuario();
                    user = crud.read(email);
                    if (user.getSenha().equals(senha)) {
                        active_id = user.getId();
                        byte flag1 = 0;
                        byte flag2 = 0;
                        System.out.println("AMIGO OCULTO 1.0");
                        System.out.println("================");
                        System.out.println("INICIO");
                        System.out.println("1) Sugestões de presentes");
                        System.out.println("2) Grupos");
                        System.out.println("3) Novos convites: 0");
                        System.out.println("0) Sair");

                        flag1 = reader.nextByte();
                        reader.nextLine();

                        if (flag == 1) {
                            System.out.println("AMIGO OCULTO 1.0");
                            System.out.println("================\n");
                            System.out.println("INICIO > SUGESTÕES\n\n");
                            System.out.println("1) Listar");
                            System.out.println("2) Incluir");
                            System.out.println("3) Alterar");
                            System.out.println("3) Excluir");
                            System.out.println("0) Retornar ao menu anterior");

                            flag2 = reader.nextByte();
                            reader.nextLine();
                        }
                    } else {
                        System.out.println("SENHA INCORRETA\n");
                        press_toContinue();
                    }
                }
            }
            // Novo usuario
            if (flag == 2) {
                System.out.println("NOVO USUARIO");
                System.out.print("Email: ");
                email = reader.nextLine();
                // Verificar se o email existe
                int isRegistered = 0;
                try {
                    isRegistered = crud.emailExists(email);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isRegistered == -1) {
                    System.out.print("Nome: ");
                    nome = reader.nextLine();
                    System.out.print("Senha: ");
                    senha = reader.nextLine();

                    String confirm = "";
                    System.out.println("DESEJA CRIAR A CONTA COM ESSAS INFORMAÇÕES?(S/N)");
                    confirm = reader.nextLine();
                    if (confirm.equals("s") || confirm.equals("S")) {
                        crud.create(nome, email, senha);
                        System.out.println("Cadastro realizado com sucesso!\n");
                        press_toContinue();
                    }

                } else {
                    System.out.println("Email ja cadastrado\n");
                    press_toContinue();
                }

            }

        } while (flag != 0);
        // TESTES DE IMPLEMENTAÇÃO APENAS!
        /*
         * Usuario user1 = new Usuario(1, "Thiago Henrique", "titico@titico.com",
         * "abc"); Usuario user2 = new Usuario(2, "Lucas Spartacus",
         * "spartacus@spartacus.com", "def");
         * 
         * RandomAccessFile arq;
         * 
         * try { arq = new RandomAccessFile("dados/dados.db", "rw");
         * 
         * // uso o write para escrever no arquivo e uso o metodo toByteArray para me //
         * devolver o fluxo de bytes da classe arq.write(user1.toByteArray()); }
         * 
         * catch (IOException e) { e.printStackTrace(); }
         */
    }

    static void press_toContinue() {
        try {
            System.out.println("Pressione uma tecla para continuar...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}