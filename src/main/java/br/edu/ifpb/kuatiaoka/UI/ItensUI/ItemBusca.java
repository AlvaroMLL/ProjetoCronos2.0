package br.edu.ifpb.kuatiaoka.UI.ItensUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.servico.ServicoItem;

public class ItemBusca {
    private Console console = new Console();
    private ServicoItem servicoItem;
    
    public ItemBusca(ServicoItem servicoItem) {
        this.servicoItem = servicoItem;
    }

    public void exibirBuscaItem() {
        System.out.println("=== BUSCA DE ITENS ===");
        System.out.println("1 - Buscar Item Por Titulo");
        System.out.println("2 - Buscar Item Por ID");
        System.out.println("3 - Buscar Item Por Editora");
        System.out.println("4 - Buscar Item Por Autor");
        System.out.println("5 - Buscar Item Por ISBN");
        System.out.println("6 - Buscar Item Por ISSN");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;

        do {
            exibirBuscaItem();

            System.out.println("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao Invalida.");

            }
        } while (opcao != 0);
    }
}
