package br.edu.ifpb.kuatiaoka.UI.UsuarioUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;

public class UsuarioCadastro {
    private Console console = new Console();

    public void exibirCadastroUsuario() {
        System.out.println("=== CADASTRO DE USUÁRIOS ===");
        System.out.println("1 - Cadastrar Aluno");
        System.out.println("2 - Cadastrar Professor");
        System.out.println("3 - Cadastrar Administrativo");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;

        do {
            exibirCadastroUsuario();

            System.out.println("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();
            ;

            switch (opcao) {
                case 1:
                    
                    break;
                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 0:
                    break;
                default:
                    System.out.println("Opcao Invalida.");
            }
        } while (opcao != 0);
    }
}
