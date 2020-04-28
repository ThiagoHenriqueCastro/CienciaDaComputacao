import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                        byte flag3 = 0;
                        byte flag4 = 0;
                        byte flag5 = 0;
                        byte flag6 = 0;
                        String produto = "";
                        String loja = "";
                        float valor = 0;
                        String obs = "";
                        do {
                            System.out.println("AMIGO OCULTO 1.0");
                            System.out.println("================");
                            System.out.println("INICIO");
                            System.out.println("1) Sugestões de presentes");
                            System.out.println("2) Grupos");
                            System.out.println("3) Novos convites: 0");
                            System.out.println("0) Sair");

                            flag1 = reader.nextByte();
                            reader.nextLine();

                            if (flag1 == 1) {
                                System.out.println("AMIGO OCULTO 1.0");
                                System.out.println("================\n");
                                System.out.println("INICIO > SUGESTÕES\n\n");
                                System.out.println("1) Listar");
                                System.out.println("2) Incluir");
                                System.out.println("3) Alterar");
                                System.out.println("4) Excluir");
                                System.out.println("0) Retornar ao menu anterior");

                                flag2 = reader.nextByte();
                                reader.nextLine();

                                if (flag2 == 1) {
                                    System.out.println("MINHAS SUGESTÕES");

                                    crud.list(active_id);
                                    System.out.println("");
                                    press_toContinue();

                                } else if (flag2 == 2) {
                                    System.out.print("Digite o nome do produto: ");
                                    produto = reader.nextLine();

                                    if (!produto.equals("")) {
                                        System.out.print("Digite o nome da loja: ");
                                        loja = reader.nextLine();
                                        System.out.print("Digite o valor do produto: ");
                                        valor = reader.nextFloat();
                                        reader.nextLine();
                                        System.out.print("Digite suas observações sobre o produto: ");
                                        obs = reader.nextLine();

                                        String confirm = "";
                                        System.out.println("DESEJA CRIAR A SUGESTÃO COM ESSAS INFORMAÇÕES?(S/N)");
                                        confirm = reader.nextLine();
                                        if (confirm.equals("s") || confirm.equals("S")) {
                                            crud.create(active_id, produto, loja, valor, obs);
                                            System.out.println("Cadastro de sugestão realizado com sucesso!\n");
                                            press_toContinue();
                                        }
                                    }
                                } else if (flag2 == 3) {
                                    System.out.println("ALTERAR SUGESTÃO");

                                    crud.list(active_id);
                                    System.out.println("");
                                    System.out.print("Digite o numero de qual sugestão deseja alterar: ");
                                    int update_id = reader.nextInt();
                                    reader.nextLine();
                                    if (update_id == 0) {
                                        System.out.println("Retornando ao menu...");
                                        press_toContinue();
                                    } else {
                                        crud.organiza_vetor(active_id);
                                        Sugestao s = crud.read_Sugestao(update_id);

                                        System.out.println("SUGESTÃO " + update_id);
                                        System.out.println("");
                                        System.out.println(s.getProduto());
                                        System.out.println(s.getLoja());
                                        System.out.println("R$ " + s.getValor());
                                        System.out.println(s.getObservacoes());
                                        System.out.println("");
                                        System.out.print("Digite o novo produto: ");
                                        String new_produto = reader.nextLine();
                                        if (new_produto.equals(""))
                                            new_produto = s.getProduto();
                                        System.out.print("Digite a nova loja: ");
                                        String new_loja = reader.nextLine();
                                        if (new_loja.equals(""))
                                            new_loja = s.getLoja();
                                        System.out.print("Digite o novo valor: ");
                                        String new_valor = reader.nextLine();
                                        float new_valor_float;
                                        if (new_valor.equals("")) {
                                            new_valor_float = s.getValor();
                                        } else {
                                            new_valor_float = Float.parseFloat(new_valor);
                                        }
                                        System.out.print("Digite a nova observação: ");
                                        String new_obs = reader.nextLine();
                                        if (new_obs.equals(""))
                                            new_obs = s.getObservacoes();

                                        Sugestao updated_sugestao = new Sugestao(s.getId(), s.getIdUsuario(),
                                                new_produto, new_loja, new_valor_float, new_obs);

                                        try {
                                            crud.update_sugestao(updated_sugestao);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        System.out.println("Sugestão alterada com sucesso!");
                                        press_toContinue();

                                    }
                                } else if (flag2 == 4) {
                                    System.out.println("EXCLUIR SUGESTÃO");
                                    crud.list(active_id);
                                    System.out.println("");
                                    System.out.print("Digite o numero de qual sugestão deseja apagar: ");
                                    int delete_id = reader.nextInt();
                                    reader.nextLine();

                                    if (delete_id == 0) {
                                        System.out.println("Retornando ao menu...");
                                        press_toContinue();
                                    } else {
                                        crud.organiza_vetor(active_id);
                                        Sugestao s = crud.read_Sugestao(delete_id);

                                        System.out.println("SUGESTÃO " + delete_id);
                                        System.out.println("");
                                        System.out.println(s.getProduto());
                                        System.out.println(s.getLoja());
                                        System.out.println("R$ " + s.getValor());
                                        System.out.println(s.getObservacoes());
                                        System.out.println("");
                                        String confirm = "";
                                        System.out.println("DESEJA APAGAR A SUGESTÃO COM ESSAS INFORMAÇÕES?(S/N)");
                                        confirm = reader.nextLine();
                                        if (confirm.equals("s") || confirm.equals("S")) {
                                            crud.delete_sugestao(delete_id);
                                            System.out.println("Sugestão apagada com sucesso!");
                                            press_toContinue();
                                        }
                                    }
                                }
                            } else if (flag1 == 2) {
                                System.out.println("AMIGO OCULTO 1.0");
                                System.out.println("================\n");
                                System.out.println("INICIO > GRUPOS\n\n");
                                System.out.println("1) Criação e gerenciamento de grupos");
                                System.out.println("2) Participação nos grupos");
                                System.out.println("0) Retornar ao menu anterior");

                                flag3 = reader.nextByte();
                                reader.nextLine();

                                if (flag3 == 1) {
                                    System.out.println("AMIGO OCULTO 1.0");
                                    System.out.println("================\n");
                                    System.out.println("INICIO > GRUPOS > GERENCIAMENTO DE GRUPOS\n\n");
                                    System.out.println("1) Grupos");
                                    System.out.println("2) Convites");
                                    System.out.println("3) Participantes");
                                    System.out.println("4) Sorteio");
                                    System.out.println("0) Retornar ao menu anterior");

                                    flag4 = reader.nextByte();
                                    reader.nextLine();

                                    if (flag4 == 1) {
                                        System.out.println("AMIGO OCULTO 1.0");
                                        System.out.println("================\n");
                                        System.out.println("INICIO > GRUPOS > GERENCIAMENTO DE GRUPOS > GRUPOS\n\n");
                                        System.out.println("1) Listar");
                                        System.out.println("2) Incluir");
                                        System.out.println("3) Alterar");
                                        System.out.println("4) Desativar");
                                        System.out.println("0) Retornar ao menu anterior");

                                        flag5 = reader.nextByte();
                                        reader.nextLine();

                                        if (flag5 == 1) {
                                            System.out.println("MEUS GRUPOS");

                                            crud.list_grupo(active_id);
                                            System.out.println("");
                                            press_toContinue();
                                        } else if (flag5 == 2) {
                                            Date now = new Date();
                                            System.out.println("INCLUSÃO DE GRUPO\n");
                                            System.out.print("Digite o nome do grupo: ");
                                            String nomeGrupo = reader.nextLine();
                                            System.out.print("Digite a data do sorteio(dia-mes-ano hora:minutos): ");
                                            String data = reader.nextLine();
                                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                                            Date date = null;
                                            try {
                                                date = format.parse(data);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            long momentoSorteio = date.getTime();
                                            if (momentoSorteio < now.getTime()) {
                                                System.out.println("Data invalida!");
                                            }
                                            System.out.print("Digite o valor medio dos presentes: ");
                                            float medio_valor = reader.nextFloat();
                                            reader.nextLine();
                                            System.out.print("Digite a data do encontro(dia-mes-ano hora:minutos): ");
                                            String data_encontro = reader.nextLine();
                                            Date date_encontro = null;
                                            try {
                                                date_encontro = format.parse(data_encontro);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            long momentoEncontro = date_encontro.getTime();
                                            if (momentoEncontro < momentoSorteio) {
                                                System.out.println("Data invalida!");
                                            }
                                            System.out.print("Digite o local do encontro: ");
                                            String localEncontro = reader.nextLine();
                                            System.out.print("Digite as observações: ");
                                            String observacoes = reader.nextLine();

                                            String confirm = "";
                                            System.out.println("DESEJA CRIAR UM GRUPO COM ESSAS INFORMAÇÕES?(S/N)");
                                            confirm = reader.nextLine();
                                            if (confirm.equals("s") || confirm.equals("S")) {
                                                crud.create(active_id, nomeGrupo, momentoSorteio, medio_valor,
                                                        momentoEncontro, localEncontro, observacoes, false, true);
                                                System.out.println("Grupo criado com sucesso!");
                                                press_toContinue();
                                            }

                                        } else if (flag5 == 3) {
                                            System.out.println("ALTERAR GRUPO");

                                            crud.list_grupo(active_id);
                                            System.out.println("");
                                            System.out.print("Digite o numero de qual grupo deseja alterar: ");
                                            int update_id = reader.nextInt();
                                            reader.nextLine();
                                            if (update_id == 0) {
                                                System.out.println("Retornando ao menu...");
                                                press_toContinue();
                                            } else {
                                                crud.organiza_vetor_grupos(active_id);
                                                Grupo g = crud.read_grupo(update_id);
                                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                                                Date ms = new Date(g.getMomentoSorteio());
                                                Date me = new Date(g.getMomentoEncontro());

                                                System.out.println("GRUPO " + update_id);
                                                System.out.println("");
                                                System.out.println(g.getNome());
                                                System.out.println("Data do sorteio: " + format.format(ms));
                                                System.out.println("Preço medio: R$ " + g.getValor());
                                                System.out.println("Data do encontro: " + format.format(me));
                                                System.out.println(g.getLocalEncontro());
                                                System.out.println(g.getObservacoes());
                                                System.out.println("");
                                                System.out.print("Digite o novo nome do grupo: ");
                                                String new_nome = reader.nextLine();
                                                if (new_nome.equals(""))
                                                    new_nome = g.getNome();
                                                System.out.print("Digite a nova data do sorteio: ");
                                                String data_sorteio = reader.nextLine();
                                                Date date_sorteio = null;
                                                long new_momento_sorteio = 0;
                                                if (!data_sorteio.equals("")) {
                                                    try {
                                                        date_sorteio = format.parse(data_sorteio);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    new_momento_sorteio = date_sorteio.getTime();
                                                } else {
                                                    new_momento_sorteio = g.getMomentoSorteio();
                                                }
                                                System.out.print("Digite o novo valor medio: ");
                                                String new_valor_medio = reader.nextLine();
                                                float new_valor_medio_float;
                                                if (new_valor_medio.equals("")) {
                                                    new_valor_medio_float = g.getValor();
                                                } else {
                                                    new_valor_medio_float = Float.parseFloat(new_valor_medio);
                                                }
                                                System.out.print("Digite a nova data do encontro: ");
                                                String data_encontro = reader.nextLine();
                                                Date date_encontro = null;
                                                long new_momento_encontro = 0;
                                                if (!data_encontro.equals("")) {
                                                    try {
                                                        date_encontro = format.parse(data_encontro);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    new_momento_encontro = date_encontro.getTime();
                                                } else {
                                                    new_momento_encontro = g.getMomentoEncontro();
                                                }
                                                System.out.print("Digite o novo local do encontro do grupo: ");
                                                String new_local = reader.nextLine();
                                                if (new_local.equals(""))
                                                    new_local = g.getLocalEncontro();

                                                System.out.print("Digite a nova observação: ");
                                                String new_observacao = reader.nextLine();
                                                if (new_observacao.equals(""))
                                                    new_observacao = g.getLocalEncontro();

                                                Grupo updated_grupo = new Grupo(g.getId(), g.getIdUsuario(), new_nome,
                                                        new_momento_sorteio, new_valor_medio_float,
                                                        new_momento_encontro, new_local, new_observacao,
                                                        g.getSorteado(), g.getAtivo());

                                                try {
                                                    crud.update_grupo(updated_grupo);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                                System.out.println("Grupo alterado com sucesso!");
                                                press_toContinue();

                                            }
                                        } else if (flag5 == 4) {
                                            System.out.println("DESATIVAR GRUPO");

                                            crud.list_grupo(active_id);
                                            System.out.println("");
                                            System.out.print("Digite o numero de qual grupo deseja desativar: ");
                                            int desativar_id = reader.nextInt();
                                            reader.nextLine();
                                            if (desativar_id == 0) {
                                                System.out.println("Retornando ao menu...");
                                                press_toContinue();
                                            } else {
                                                crud.organiza_vetor_grupos(active_id);
                                                Grupo g = crud.read_grupo(desativar_id);
                                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                                                Date ms = new Date(g.getMomentoSorteio());
                                                Date me = new Date(g.getMomentoEncontro());

                                                System.out.println("GRUPO " + desativar_id);
                                                System.out.println("");
                                                System.out.println(g.getNome());
                                                System.out.println("Data do sorteio: " + format.format(ms));
                                                System.out.println("Preço medio: R$ " + g.getValor());
                                                System.out.println("Data do encontro: " + format.format(me));
                                                System.out.println(g.getLocalEncontro());
                                                System.out.println(g.getObservacoes());
                                                System.out.println("");
                                                String confirm = "";
                                                System.out.println("DESEJA DESATIVAR ESTE GRUPO?(S/N)");
                                                confirm = reader.nextLine();
                                                if (confirm.equals("s") || confirm.equals("S")) {
                                                    g.setAtivo(false);
                                                    try {
                                                        crud.update_grupo(g);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    System.out.println("Grupo Alterado com sucesso!");
                                                    press_toContinue();
                                                }
                                            }
                                        }
                                    } else if (flag4 == 2) {
                                        System.out.println("AMIGO OCULTO 1.0");
                                        System.out.println("================\n");
                                        System.out.println("INICIO > GRUPOS > GERENCIAMENTO DE GRUPOS > CONVITES\n\n");
                                        System.out.println("1) Listagem dos convites");
                                        System.out.println("2) Emissao de convites");
                                        System.out.println("3) Cancelamento de convites");
                                        System.out.println("0) Retornar ao menu anterior");

                                        flag6 = reader.nextByte();
                                        reader.nextLine();

                                        if (flag6 == 1) {
                                            System.out.println("LISTAR CONVITES");

                                            crud.list_grupo(active_id);
                                            System.out.println("");
                                            System.out.print(
                                                    "Digite o numero de qual grupo deseja visualizar os convites: ");
                                            int list_id = reader.nextInt();
                                            reader.nextLine();
                                            if (list_id == 0) {
                                                System.out.println("Retornando ao menu...");
                                                press_toContinue();
                                            } else {
                                                crud.organiza_vetor_grupos(active_id);

                                                Grupo g = crud.read_grupo(list_id);
                                                crud.organiza_vetor_convites(g.getId());

                                                System.out.println("\n\nCONVITES DO GRUPO " + g.getNome());
                                                System.out.println("\n");
                                                crud.list_convites(g.idGrupo, true);
                                                press_toContinue();
                                            }

                                        } else if (flag6 == 2) {
                                            System.out.println("EMISSAO DE CONVITE PARA GRUPOS");

                                            crud.list_grupo(active_id, true);
                                            System.out.println("");
                                            System.out.print(
                                                    "Digite o numero para qual grupo deseja emitir um convite: ");
                                            int emissao_id = reader.nextInt();
                                            reader.nextLine();
                                            if (emissao_id == 0) {
                                                System.out.println("Retornando ao menu...");
                                                press_toContinue();
                                            } else {
                                                crud.organiza_vetor_grupos(active_id);
                                                Grupo g = crud.read_grupo(emissao_id);

                                                System.out.println("GRUPO " + emissao_id);
                                                System.out.println("");
                                                System.out.println(g.getNome());
                                                System.out.println("\n\n");
                                                System.out.print("Digite o email do destinatario do convite: ");
                                                String emissao_email = reader.nextLine();
                                                if (emissao_email.equals("")) {
                                                    System.out.println("Retornando ao menu anterior...");
                                                    press_toContinue();
                                                } else {
                                                    Convite c = crud.convite_Exists(emissao_email, g.getId());
                                                    if (c != null) {
                                                        // Vejo se status do convite aceito ou pendente
                                                        if (c.getEstado() == 0 || c.getEstado() == 1) {
                                                            System.out.println("Convite ja emitido!");
                                                            press_toContinue();
                                                        } else {
                                                            System.out
                                                                    .println("Convite ja emitido, mas nao confirmado!");
                                                            String confirm = "";
                                                            System.out.println("DESEJA REENVIAR O CONVITE?(S/N)");
                                                            if (confirm.equals("s") || confirm.equals("S")) {
                                                                Date now = new Date();
                                                                byte estado = 0;
                                                                crud.create(g.getId(), emissao_email, now.getTime(),
                                                                        estado);
                                                                // ESTADO 0 É UM CONVITE ABERTO
                                                                System.out.println("Convite reemitido com sucesso!\n");
                                                                press_toContinue();
                                                            }
                                                        }
                                                    } else {
                                                        String confirm = "";
                                                        System.out.println(
                                                                "DESEJA EMITIR O CONVITE PARA ESSE EMAIL?(S/N)");
                                                        confirm = reader.nextLine();
                                                        if (confirm.equals("s") || confirm.equals("S")) {
                                                            Date now = new Date();
                                                            byte estado = 0;
                                                            crud.create(g.getId(), emissao_email, now.getTime(),
                                                                    estado);
                                                            // ESTADO 0 É UM CONVITE ABERTO
                                                            System.out.println("Convite emitido com sucesso!\n");
                                                            press_toContinue();
                                                        }
                                                    }
                                                }

                                            }
                                        } else if (flag6 == 3) {
                                            System.out.println("CANCELAMENTO DE CONVITE");
                                            crud.list_grupo(active_id, true);
                                            System.out.println("");
                                            System.out.print(
                                                    "Digite o numero para qual grupo deseja cancelar um convite: ");
                                            int cancel_id = reader.nextInt();
                                            reader.nextLine();
                                            if (cancel_id == 0) {
                                                System.out.println("Retornando ao menu...");
                                                press_toContinue();
                                            } else {
                                                crud.organiza_vetor_grupos(active_id);

                                                Grupo g = crud.read_grupo(cancel_id);
                                                crud.organiza_vetor_convites(g.getId());

                                                System.out.println("\n\nCONVITES DO GRUPO " + g.getNome());
                                                System.out.println("\n");
                                                ArrayList<Convite> convites = crud.list_convites(g.idGrupo, false);
                                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                                                Date mc = null;
                                                for (int i = 0; i < convites.size(); i++) {
                                                    mc = new Date(convites.get(i).getMomentoConvite());
                                                    System.out.println((i + 1) + ". " + convites.get(i).getEmail()
                                                            + " (" + df.format(mc) + " - "
                                                            + (convites.get(i).getEstado() == 0 ? "pendente"
                                                                    : convites.get(i).getEstado() == 1 ? "aceito"
                                                                            : convites.get(i).getEstado() == 2
                                                                                    ? "recusado"
                                                                                    : "cancelado")
                                                            + ")");
                                                }
                                                System.out.print("Escolha qual convite deseja cancelar: ");
                                                int cancel = reader.nextInt();
                                                reader.nextLine();
                                                if (cancel == 0) {
                                                    System.out.println("Retornando ao menu anterior...");
                                                    press_toContinue();
                                                } else {
                                                    String confirm = "";
                                                    System.out.println("DESEJA CANCELAR ESSE CONVITE?(S/N)");
                                                    confirm = reader.nextLine();
                                                    if (confirm.equals("s") || confirm.equals("S")) {
                                                        Convite new_convite = convites.get(cancel - 1);
                                                        byte new_e = 3;
                                                        new_convite.setEstado(new_e);
                                                        try {
                                                            crud.update_convite(new_convite);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                        System.out.println("Convite cancelado com sucesso!\n");
                                                        press_toContinue();
                                                    }

                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        } while (flag1 != 0);
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