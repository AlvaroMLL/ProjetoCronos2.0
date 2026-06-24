package br.edu.ifpb.kuatiaoka.UI.Util;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public void pause() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public int nextInt() {
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    public String nextLine() {
        return scanner.nextLine();
    }

}
