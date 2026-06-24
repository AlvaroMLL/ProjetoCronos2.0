package br.edu.ifpb.kuatiaoka.UI.ComercioUi;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.servico.ServicoVenda;

public class VendaMenu {
    private Console console = new Console();
    private ServicoVenda servicoVenda;

    public VendaMenu(ServicoVenda servicoVenda) {
        this.servicoVenda = servicoVenda;
    }

    public void exibirMenuVenda() {
        System.out.println("=== VENDA DE JOGOS ===");
        System.out.println("1 - Realizar Venda");
        System.out.println("2 - Listar Historico De Vendas");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;

        do {
            exibirMenuVenda();

            System.out.println("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();

            switch (opcao) {
                case 1:

                    break;
                case 2:

                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao Invalida.");

            }
        } while (opcao != 0);
    }
}
