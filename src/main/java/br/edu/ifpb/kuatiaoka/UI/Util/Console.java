package br.edu.ifpb.kuatiaoka.UI.Util;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public void pause() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public int nextInt() {
        while (true) {
            try {
                System.out.print("\nDigite Uma Opcao: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                mensagemErro("=== ERRO: DIGITE APENAS NUMEROS ===");
            }
        }
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void mensagemErro(String mensagem) {
        System.out.println(Cores.VERMELHO + mensagem + Cores.RESET);
    }

    public void mensagemSucesso(String mensagem) {
        System.out.println(Cores.VERDE + mensagem + Cores.RESET);
    }
}
