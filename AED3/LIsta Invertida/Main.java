import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        try {
            Lista_Invertida lista_invertida = new Lista_Invertida("termos.db", "lista_id.db", "dados.db");

            byte flag = 0;
            do {
                System.out.println("Trabalho AED III - Lista Invertida\n");
                System.out.println("1) Inserir Nome");
                System.out.println("2) Buscar Chave");
                System.out.println("0) Sair");

                System.out.print("\n\nOpção: ");

                flag = reader.nextByte();
                reader.nextLine();

                if (flag == 1) {
                    System.out.print("\n\nNome a ser incluido: ");
                    String n = reader.nextLine();

                    lista_invertida.create(n);
                } else if (flag == 2) {
                    System.out.print("\n\nNome a ser buscado: ");
                    String n = reader.nextLine();

                    long endereco = lista_invertida.buscar(n);

                    System.out
                            .println("Endereço: " + endereco + " | Nome: " + lista_invertida.busca_endereco(endereco));
                    System.out.println("\n");
                }
            } while (flag != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}