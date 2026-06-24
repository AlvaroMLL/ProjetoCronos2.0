package br.edu.ifpb.kuatiaoka.UI.ItensUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;

public class ItemCadastro {
    private Console console = new Console();

    public void exibirCadastroItens() {
        System.out.println("=== CADASTRO DE ITENS ===");
        System.out.println("1 - Cadastrar Um Livro Fisico");
        System.out.println("2 - Cadastrar Um Audio Livro");
        System.out.println("3 - Cadastrar Uma Revista");
        System.out.println("4 - Cadastrar Um Cd");
        System.out.println("5 - Cadastrar Um Jogo");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;

        do {
            exibirCadastroItens();

            System.out.println("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break;

                case 3:
                    break;

                case 0:
                    break;
                default:
                    System.out.println("Opcao Invalida.");

            }
        } while (opcao != 0);
    }
}
