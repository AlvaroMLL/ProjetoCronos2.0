package br.edu.ifpb.kuatiaoka.UI.UsuarioUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import lombok.Data;

@Data
public class UsuarioMenu {
    private Console console = new Console();

    public void exibirMenuUsuario() {

        System.out.println("=== MENU USUÁRIOS ===");
        System.out.println("1 - Cadastrar Usuario");
        System.out.println("2 - Buscar Usuario");
        System.out.println("3 - Listar Usuarios Com Multa");
        System.out.println("4 - Pagar Multa De Um Usuario");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;

        do {
            exibirMenuUsuario();

            System.out.println("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();
            UsuarioCadastro usuarioCadastro = new UsuarioCadastro();
            UsuarioBusca usuarioBusca = new UsuarioBusca();

            switch (opcao) {
                case 1:
                    usuarioCadastro.executar();
                    break;
                case 2:
                    usuarioBusca.executar();
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
