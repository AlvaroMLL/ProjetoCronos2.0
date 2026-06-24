package br.edu.ifpb.kuatiaoka.UI.ItensUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.excecao.ItemNaoEncontradoException;
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
        System.out.println("5 - Buscar Livro Por ISBN");
        System.out.println("6 - Buscar Revista Por ISSN");
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
                    System.out.println("=== BUSCANDO ITEM PELO TITULO ===");
                    System.out.println("\nDigite o Titulo Do Item: ");
                    String titulo = console.nextLine();

                    servicoItem.buscarItemPorTitulo(titulo);
                    console.pause();

                    break;
                case 2:
                    System.out.println("=== BUSCANDO ITEM POR ID ===");
                    System.out.println("\nDigite o ID Do Item: ");
                    int id = console.nextInt();

                    try {
                        servicoItem.buscarItemPorId(id);
                        console.pause();
                    } catch (ItemNaoEncontradoException e) {
                        console.mensagemErro(e.getMessage());
                        console.pause();
                    }

                    break;
                case 3:
                    System.out.println("=== BUSCANDO ITEM PELA EDITORA ===");
                    System.out.println("\nDigite o Editora Do Item: ");
                    String editora = console.nextLine();

                    servicoItem.buscarLivroPorEditora(editora);
                    console.pause();

                    break;
                case 4:
                    System.out.println("=== BUSCANDO ITEM PELO AUTOR ===");
                    System.out.println("\nDigite o Autor Do Item: ");
                    String autor = console.nextLine();

                    servicoItem.buscarItemPorAutor(autor);
                    console.pause();

                    break;
                case 5:
                    System.out.println("=== BUSCANDO LIVRO PELO ISBN ===");
                    System.out.println("\nDigite o ISBN Do Livro: ");
                    String isbn = console.nextLine();

                    try {
                        servicoItem.buscarLivroPorIsbn(isbn);
                        console.pause();
                    } catch (ItemNaoEncontradoException e) {
                        console.mensagemErro(e.getMessage());
                        console.pause();
                    }

                    break;
                case 6:
                    System.out.println("=== BUSCANDO REVISTA PELO ISSN ===");
                    System.out.println("\nDigite o ISSN Da Revista: ");
                    String issn = console.nextLine();

                    try {
                        servicoItem.buscarRevistaPorIssn(issn);
                        console.pause();
                    } catch (ItemNaoEncontradoException e) {
                        console.mensagemErro(e.getMessage());
                        console.pause();
                    }

                    break;
                case 0:
                    break;
                default:
                    console.mensagemErro("=== OPCAO INVALIDA ===");

            }
        } while (opcao != 0);
    }
}
