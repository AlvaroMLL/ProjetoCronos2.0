package br.edu.ifpb.kuatiaoka.UI.UsuarioUI;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.UI.Util.Cores;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Administrativo;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Aluno;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Professor;
import br.edu.ifpb.kuatiaoka.servico.ServicoUsuario;

public class UsuarioCadastro {
    private Console console = new Console();
    private ServicoUsuario servicoUsuario;

    public UsuarioCadastro(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

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

            switch (opcao) {
                case 1:
                    System.out.println("=== CADASTRANDO UM ALUNO ===");

                    System.out.println("\nDigite o Nome Do Aluno: ");
                    String nomeAluno = console.nextLine();

                    System.out.println("\nDigite o E-mail Do Aluno: ");
                    String emailAluno = console.nextLine();

                    Aluno aluno = new Aluno();
                    aluno.setNome(nomeAluno);
                    aluno.setEmail(emailAluno);
                    servicoUsuario.adicionarUsuario(aluno);
                    console.pause();

                    break;

                case 2:
                    System.out.println("=== CADASTRANDO UM PROFESSOR ===");

                    System.out.println("\nDigite o Nome Do Professor: ");
                    String nomeProfessor = console.nextLine();

                    System.out.println("\nDigite o E-mail Do Professor: ");
                    String emailProfessor = console.nextLine();

                    Professor professor = new Professor();
                    professor.setNome(nomeProfessor);
                    professor.setEmail(emailProfessor);
                    servicoUsuario.adicionarUsuario(professor);
                    console.pause();

                    break;

                case 3:
                    System.out.println("=== CADASTRANDO UM ADMINISTRATIVO ===");

                    System.out.println("\nDigite o Nome Do Administrativo: ");
                    String nomeAdmin = console.nextLine();

                    System.out.println("\nDigite o E-mail Do Administrativo: ");
                    String emailAdmin = console.nextLine();

                    Administrativo admin = new Administrativo();
                    admin.setNome(nomeAdmin);
                    admin.setEmail(emailAdmin);
                    servicoUsuario.adicionarUsuario(admin);
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
