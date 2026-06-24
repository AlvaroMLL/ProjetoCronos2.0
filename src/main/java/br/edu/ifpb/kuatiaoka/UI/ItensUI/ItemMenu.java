package br.edu.ifpb.kuatiaoka.UI.ItensUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import lombok.Data;

@Data
public class ItemMenu {
    private Console console = new Console();

    public void exibirMenuItem() {
        System.out.println("=== MENU ITENS ===");
        System.out.println("1 - Cadastrar Itens");
        System.out.println("2 - Buscar Itens");
        System.out.println("3 - Listar Itens Disponiveis");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;
        ItemBusca itemBusca = new ItemBusca();
        ItemCadastro itemCadastro = new ItemCadastro();

        do {
            exibirMenuItem();

            System.out.println("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();

            switch (opcao) {
                case 1:
                    itemCadastro.executar();
                    break;
                case 2:
                    itemBusca.executar();
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
