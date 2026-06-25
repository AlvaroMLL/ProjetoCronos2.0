package br.edu.ifpb.kuatiaoka.UI;

import java.math.BigDecimal;

import br.edu.ifpb.kuatiaoka.UI.ComercioUi.EmprestimoMenu;
import br.edu.ifpb.kuatiaoka.UI.ComercioUi.VendaMenu;
import br.edu.ifpb.kuatiaoka.UI.EditoraUi.EditoraMenu;
import br.edu.ifpb.kuatiaoka.UI.ItensUI.ItemMenu;
import br.edu.ifpb.kuatiaoka.UI.UsuarioUI.UsuarioMenu;
import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.modelo.Editora.Editora;
import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import br.edu.ifpb.kuatiaoka.modelo.Enum.TipoJogo;
import br.edu.ifpb.kuatiaoka.modelo.Item.Cd;
import br.edu.ifpb.kuatiaoka.modelo.Item.Jogo;
import br.edu.ifpb.kuatiaoka.modelo.Item.Livro;
import br.edu.ifpb.kuatiaoka.modelo.Item.Revista;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Administrativo;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Aluno;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Professor;
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
        this.servicoVenda = new ServicoVenda(servicoUsuario);
        this.servicoEditora = new ServicoEditora();
        this.servicoEmprestimo = new ServicoEmprestimo(servicoUsuario, servicoItem, servicoVenda);

        carregarDadosIniciais();
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
        EmprestimoMenu emprestimoMenu = new EmprestimoMenu(servicoEmprestimo, servicoItem, servicoUsuario,
                servicoVenda);
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

    private void carregarDadosIniciais() {

        // ===== EDITORAS =====

        Editora editora1 = new Editora();
        editora1.setId(1);
        editora1.setNome("Saraiva");
        editora1.setCnpj("11.111.111/0001-11");

        Editora editora2 = new Editora();
        editora2.setId(2);
        editora2.setNome("Novatec");
        editora2.setCnpj("22.222.222/0001-22");

        Editora editora3 = new Editora();
        editora3.setId(3);
        editora3.setNome("Alta Books");
        editora3.setCnpj("33.333.333/0001-33");

        servicoEditora.adicionarEditora(editora1);
        servicoEditora.adicionarEditora(editora2);
        servicoEditora.adicionarEditora(editora3);

        // ===== USUÁRIOS =====

        Aluno aluno = new Aluno();
        aluno.setNome("João Silva");
        aluno.setEmail("joao@ifpb.edu.br");

        Professor professor = new Professor();
        professor.setNome("Maria Souza");
        professor.setEmail("maria@ifpb.edu.br");

        Administrativo administrativo = new Administrativo();
        administrativo.setNome("Carlos Lima");
        administrativo.setEmail("carlos@ifpb.edu.br");

        servicoUsuario.adicionarUsuario(aluno);
        servicoUsuario.adicionarUsuario(professor);
        servicoUsuario.adicionarUsuario(administrativo);

        Aluno alunoMultado = new Aluno();
        alunoMultado.setNome("Pedro Santos");
        alunoMultado.setEmail("pedro@ifpb.edu.br");
        alunoMultado.setRegularizado(false);
        alunoMultado.setMultaPendente(15.0);

        Professor professorMultado = new Professor();
        professorMultado.setNome("Ana Costa");
        professorMultado.setEmail("ana@ifpb.edu.br");
        professorMultado.setRegularizado(false);
        professorMultado.setMultaPendente(30.0);

        servicoUsuario.adicionarUsuario(alunoMultado);
        servicoUsuario.adicionarUsuario(professorMultado);

        // ===== LIVRO =====

        Livro livro = new Livro();
        livro.setTitulo("Clean Code");
        livro.setAutor("Robert Martin");
        livro.setIsbn("9780132350884");
        livro.setEditora(editora2);

        servicoItem.adicionarItem(livro);

        Livro livroEmprestado = new Livro();
        livroEmprestado.setTitulo("Java Efetivo");
        livroEmprestado.setAutor("Joshua Bloch");
        livroEmprestado.setIsbn("9780134685991");
        livroEmprestado.setEditora(editora2);
        livroEmprestado.setStatusItem(StatusItem.EMPRESTADO);

        servicoItem.adicionarItem(livroEmprestado);

        // ===== REVISTA =====

        Revista revista = new Revista();
        revista.setTitulo("Mundo Java");
        revista.setAutor("Equipe Java");
        revista.setIssn("1234-5678");
        revista.setEditora(editora1);

        servicoItem.adicionarItem(revista);

        Revista revistaEmprestada = new Revista();
        revistaEmprestada.setTitulo("Tech Monthly");
        revistaEmprestada.setAutor("Equipe Tech");
        revistaEmprestada.setIssn("9876-5432");
        revistaEmprestada.setEditora(editora3);
        revistaEmprestada.setStatusItem(StatusItem.EMPRESTADO);

        servicoItem.adicionarItem(revistaEmprestada);

        // ===== CD =====

        Cd cd = new Cd();
        cd.setTitulo("Greatest Hits");
        cd.setAutor("Queen");
        cd.setListaDeFaixas(new String[] {
                "Bohemian Rhapsody",
                "We Will Rock You",
                "Don't Stop Me Now"
        });

        servicoItem.adicionarItem(cd);

        // ===== JOGOS =====

        Jogo jogo1 = new Jogo();
        jogo1.setNome("Banco Imobiliário");
        jogo1.setQtdPecas(120);
        jogo1.setPreco(new BigDecimal("99.90"));
        jogo1.setTipoJogo(TipoJogo.TABULEIRO);
        jogo1.setStatusItem(StatusItem.DISPONIVEL);

        Jogo jogo2 = new Jogo();
        jogo2.setNome("Uno");
        jogo2.setQtdPecas(108);
        jogo2.setPreco(new BigDecimal("29.90"));
        jogo2.setTipoJogo(TipoJogo.CARTAS);
        jogo2.setStatusItem(StatusItem.DISPONIVEL);

        Jogo jogo3 = new Jogo();
        jogo3.setNome("War");
        jogo3.setQtdPecas(150);
        jogo3.setPreco(new BigDecimal("149.90"));
        jogo3.setTipoJogo(TipoJogo.TABULEIRO);
        jogo3.setStatusItem(StatusItem.DISPONIVEL);

        servicoVenda.cadastrarJogo(jogo1);
        servicoVenda.cadastrarJogo(jogo2);
        servicoVenda.cadastrarJogo(jogo3);
    }
}
