package br.edu.ifpb.kuatiaoka.UI;

import br.edu.ifpb.kuatiaoka.UI.ComercioUi.EmprestimoMenu;
import br.edu.ifpb.kuatiaoka.UI.ComercioUi.VendaMenu;
import br.edu.ifpb.kuatiaoka.UI.ItensUI.ItemMenu;
import br.edu.ifpb.kuatiaoka.UI.UsuarioUI.UsuarioMenu;
import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.servico.ServicoEmprestimo;
import br.edu.ifpb.kuatiaoka.servico.ServicoItem;
import br.edu.ifpb.kuatiaoka.servico.ServicoUsuario;
import br.edu.ifpb.kuatiaoka.servico.ServicoVenda;

public class MainUi {
    private ServicoUsuario servicoUsuario;
    private ServicoItem servicoItem;
    private ServicoEmprestimo servicoEmprestimo;
    private ServicoVenda servicoVenda;
    private Console console = new Console();

    public MainUi() {
        this.servicoUsuario = new ServicoUsuario();
        this.servicoItem = new ServicoItem();
        this.servicoEmprestimo = new ServicoEmprestimo();
        this.servicoVenda = new ServicoVenda();
    }

    public void ebixirMenu() {
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1 - Ir Para o Menu De Usuarios");
        System.out.println("2 - Ir Para o Menu De Itens");
        System.out.println("3 - Ir Para o Menu De Emprestimos");
        System.out.println("4 - Ir Para o Menu De Vendas");
        System.out.println("0 - Encerrar Programa");
    }

    public void executar() {
        int opcao = -1;
        UsuarioMenu usuarioMenu = new UsuarioMenu(servicoUsuario);
        ItemMenu itemMenu = new ItemMenu(servicoItem);
        EmprestimoMenu emprestimoMenu = new EmprestimoMenu(servicoEmprestimo);
        VendaMenu vendaMenu = new VendaMenu(servicoVenda);

        do {
            ebixirMenu();

            System.out.println("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();

            switch (opcao) {
                case 1:
                    usuarioMenu.executar();
                    break;
                case 2:
                    itemMenu.executar();
                    break;

                case 3:
                    emprestimoMenu.executar();
                    break;

                case 4:
                    vendaMenu.executar();
                    break;

                case 0:
                    System.out.println("Encerrando Programa...");
                    break;
                default:
                    System.out.println("Opcao Invalida.");

            }
        } while (opcao != 0);
    }
}
