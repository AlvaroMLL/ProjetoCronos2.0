package br.edu.ifpb.kuatiaoka.UI.EditoraUi;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.modelo.Editora.Editora;
import br.edu.ifpb.kuatiaoka.servico.ServicoEditora;

public class EditoraMenu {
    private Console console = new Console();
    private ServicoEditora servicoEditora;

    public EditoraMenu(ServicoEditora servicoEditora) {
        this.servicoEditora = servicoEditora;
    }

    public void exibirMenuEditora() {
        System.out.println("=== MENU EDITORAS ===");
        System.out.println("1 - Cadastrar Editora");
        System.out.println("2 - Listar Editoras");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;

        do {
            exibirMenuEditora();

            System.out.print("\nEscolha uma Opcao: ");
            opcao = console.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("=== CADASTRANDO EDITORA ===");
                    System.out.print("\nDigite o Nome Da Editora: ");
                    String nome = console.nextLine();

                    System.out.print("\nDigite o Cnpj Da Editora: ");
                    String cnpj = console.nextLine();

                    Editora editora = new Editora();

                    editora.setNome(nome);
                    editora.setCnpj(cnpj);

                    servicoEditora.adicionarEditora(editora);
                    break;
                case 2:
                    System.out.println("=== LISTA DE EDITORAS ===");
                    servicoEditora.listarEditoras();
                    console.pause();
                    break;
                case 0:
                    break;
                default:
                    console.mensagemErro("=== OPCAO INVALIDA ===");
                    console.pause();
                    break;
            }
        } while (opcao != 0);
    }
}
