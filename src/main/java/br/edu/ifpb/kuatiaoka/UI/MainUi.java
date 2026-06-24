package br.edu.ifpb.kuatiaoka.UI;

import br.edu.ifpb.kuatiaoka.UI.ComercioUi.EmprestimoMenu;
import br.edu.ifpb.kuatiaoka.UI.ComercioUi.VendaMenu;
import br.edu.ifpb.kuatiaoka.UI.EditoraUi.EditoraMenu;
import br.edu.ifpb.kuatiaoka.UI.ItensUI.ItemMenu;
import br.edu.ifpb.kuatiaoka.UI.UsuarioUI.UsuarioMenu;
import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.servico.ServicoEditora;
import br.edu.ifpb.kuatiaoka.servico.ServicoEmprestimo;
import br.edu.ifpb.kuatiaoka.servico.ServicoItem;
import br.edu.ifpb.kuatiaoka.servico.ServicoUsuario;
import br.edu.ifpb.kuatiaoka.servico.ServicoVenda;

public class MainUi {
    private ServicoUsuario servicoUsuario;
    private ServicoItem servicoItem;
    private ServicoEmprestimo servicoEmprestimo;
    private ServicoVenda servicoVenda;
    private ServicoEditora servicoEditora;
    private Console console = new Console();

    public MainUi() {
        this.servicoUsuario = new ServicoUsuario();
        this.servicoItem = new ServicoItem();
        this.servicoEmprestimo = new ServicoEmprestimo();
        this.servicoVenda = new ServicoVenda();
        this.servicoEditora = new ServicoEditora();
    }

    public void ebixirMenu() {
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1 - Ir Para o Menu De Usuarios");
        System.out.println("2 - Ir Para o Menu De Itens");
        System.out.println("3 - Ir Para o Menu De Emprestimos");
        System.out.println("4 - Ir Para o Menu De Vendas");
        System.out.println("5 - Ir Para o Menu De Editoras");
        System.out.println("0 - Encerrar Programa");
    }

    public void executar() {
        int opcao = -1;
        UsuarioMenu usuarioMenu = new UsuarioMenu(servicoUsuario);
        ItemMenu itemMenu = new ItemMenu(servicoItem, servicoVenda, servicoEditora);
        EmprestimoMenu emprestimoMenu = new EmprestimoMenu(servicoEmprestimo, servicoItem, servicoUsuario);
        VendaMenu vendaMenu = new VendaMenu(servicoVenda, servicoUsuario);
        EditoraMenu editoraMenu = new EditoraMenu(servicoEditora);

        do {
            ebixirMenu();

            System.out.print("\nEscolha Uma Opcao: ");
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
                case 5:
                    editoraMenu.executar();
                    break;
                case 0:
                    System.out.println("Encerrando Programa...");
                    break;
                default:
                    console.mensagemErro("=== OPCAO INVALIDA ===");
                    console.pause();
            }
        } while (opcao != 0);
    }
}
