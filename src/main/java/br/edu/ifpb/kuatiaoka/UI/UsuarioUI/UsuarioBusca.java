package br.edu.ifpb.kuatiaoka.UI.UsuarioUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.excecao.UsuarioNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.servico.ServicoUsuario;

public class UsuarioBusca {
    private Console console = new Console();
    private ServicoUsuario servicoUsuario;

    public UsuarioBusca(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

    public void exibirBuscaUsuario() {
        System.out.println("=== BUSCA DE USUÁRIOS ===");
        System.out.println("1 - Busca Usuario por Nome");
        System.out.println("2 - Busca Usuario por ID");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;

        do {
            exibirBuscaUsuario();

            System.out.print("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("=== BUSCA DE USUARIO POR NOME ===");
                    System.out.print("\nDigite o Nome Do Usuario Buscado: ");
                    String nome = console.nextLine();

                    System.out.println(servicoUsuario.buscarUsuarioPorNome(nome));
                    console.pause();

                    break;
                case 2:
                    System.out.println("=== BUSCA DE USUARIO POR ID ===");
                    System.out.print("\nDigite o ID Do Usuario: ");
                    int idBuscado = console.nextInt();

                    try {
                        servicoUsuario.buscarUsuarioPorId(idBuscado);
                        console.pause();
                    } catch (UsuarioNaoEncontradoException e) {
                        console.mensagemErro(e.getMessage());
                        console.pause();
                    }

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
