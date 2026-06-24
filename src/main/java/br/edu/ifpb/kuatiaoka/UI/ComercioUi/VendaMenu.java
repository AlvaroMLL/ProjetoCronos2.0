package br.edu.ifpb.kuatiaoka.UI.ComercioUi;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.servico.ServicoUsuario;
import br.edu.ifpb.kuatiaoka.servico.ServicoVenda;

public class VendaMenu {
    private Console console = new Console();
    private ServicoVenda servicoVenda;
    private ServicoUsuario servicoUsuario;

    public VendaMenu(ServicoVenda servicoVenda, ServicoUsuario servicoUsuario) {
        this.servicoVenda = servicoVenda;
        this.servicoUsuario = servicoUsuario;
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
                    System.out.println("=== REALIZANDO VENDA ===");
                    servicoVenda.listarJogos();
                    System.out.println("\nDigite o ID Do Jogo Que Sera Comprado: ");
                    int jogoId = console.nextInt();

                    servicoUsuario.listarUsuarios();
                    System.out.println("\nDigite o ID Do Comprador: ");
                    int idUsuario = console.nextInt();

                    servicoVenda.realizarVenda(idUsuario, jogoId);
                    console.pause();

                    break;
                case 2:
                    System.out.println("=== LISTANDO HISTORICO DE VENDAS ===");
                    servicoVenda.listarVendas();
                    console.pause();

                    break;
                case 0:
                    break;
                default:
                    console.mensagemErro("=== OPCAO INVALIDA ===");
                    console.pause();
            }
        } while (opcao != 0);
    }
}
