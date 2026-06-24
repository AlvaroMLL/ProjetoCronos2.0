package br.edu.ifpb.kuatiaoka.UI.ItensUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.servico.ServicoItem;
import br.edu.ifpb.kuatiaoka.servico.ServicoVenda;
import lombok.Data;

@Data
public class ItemMenu {
    private Console console = new Console();
    private ServicoItem servicoItem;
    private ServicoVenda servicoVenda;

    public ItemMenu(ServicoItem servicoItem, ServicoVenda servicoVenda) {
        this.servicoItem = servicoItem;
        this.servicoVenda = servicoVenda;
    }

    public void exibirMenuItem() {
        System.out.println("=== MENU ITENS ===");
        System.out.println("1 - Cadastrar Itens");
        System.out.println("2 - Buscar Itens");
        System.out.println("3 - Listar Itens Disponiveis");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;
        ItemBusca itemBusca = new ItemBusca(servicoItem);
        ItemCadastro itemCadastro = new ItemCadastro(servicoItem, servicoVenda);

        do {
            exibirMenuItem();

            System.out.print("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();

            switch (opcao) {
                case 1:
                    itemCadastro.executar();

                    break;
                case 2:
                    itemBusca.executar();

                    break;
                case 3:
                    System.out.println("=== LISTANDO ITENS DISPONIVEIS ===");
                    servicoItem.listarItensDisponiveis();
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
