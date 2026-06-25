package br.edu.ifpb.kuatiaoka.UI.ComercioUi;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.excecao.EmprestimoEmAtrasoException;
import br.edu.ifpb.kuatiaoka.excecao.EmprestimoFinalizadoException;
import br.edu.ifpb.kuatiaoka.excecao.ItemIndisponivelException;
import br.edu.ifpb.kuatiaoka.excecao.ItemNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.excecao.LimiteEmprestimosException;
import br.edu.ifpb.kuatiaoka.excecao.UsuarioNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.excecao.UsuarioNaoRegularizadoException;
import br.edu.ifpb.kuatiaoka.servico.ServicoEmprestimo;
import br.edu.ifpb.kuatiaoka.servico.ServicoItem;
import br.edu.ifpb.kuatiaoka.servico.ServicoUsuario;
import br.edu.ifpb.kuatiaoka.servico.ServicoVenda;

public class EmprestimoMenu {
    private Console console = new Console();
    private ServicoEmprestimo servicoEmprestimo;
    private ServicoItem servicoItem;
    private ServicoUsuario servicoUsuario;
    private ServicoVenda servicoVenda;

    public EmprestimoMenu(ServicoEmprestimo servicoEmprestimo, ServicoItem servicoItem, ServicoUsuario servicoUsuario,
            ServicoVenda servicoVenda) {
        this.servicoEmprestimo = servicoEmprestimo;
        this.servicoItem = servicoItem;
        this.servicoUsuario = servicoUsuario;
        this.servicoVenda = servicoVenda;
    }

    public void exibirMenuEmprestimo() {
        System.out.println("=== MENU EMPRESTIMO ===");
        System.out.println("1 - Realizar Um Emprestimo De Itens");
        System.out.println("2 - Realizar Um Emprestimo De Jogos");
        System.out.println("3 - Registrar Devolucao");
        System.out.println("4 - Exibir o Historico De Emprestimos De Um Usuario");
        System.out.println("5 - Exibir o Historico De Emprestimos De Um Item");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;
        do {
            exibirMenuEmprestimo();

            System.out.print("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("=== REALIZANDO UM EMPRESTIMO DE ITEM ===");
                    servicoItem.listarItensDisponiveis();
                    System.out.print("\nDigite o ID Do Item Que Será Emprestado: ");
                    int idItem = console.nextInt();

                    servicoUsuario.listarUsuarios();
                    System.out.print("\nDigite o ID Do Usuario Que Vai Pegar o Item: ");
                    int idUsuario = console.nextInt();

                    try {
                        servicoEmprestimo.realizarEmprestimo(idUsuario, idItem);
                        console.mensagemSucesso("=== EMPRESTIMO REALIZADO COM SUCESSO ===");
                        console.pause();
                    } catch (ItemIndisponivelException e) {
                        console.mensagemErro(e.getMessage());
                        console.pause();
                    } catch (EmprestimoEmAtrasoException ex) {
                        console.mensagemErro(ex.getMessage());
                        console.pause();
                    } catch (UsuarioNaoRegularizadoException exc) {
                        console.mensagemErro(exc.getMessage());
                        console.pause();
                    } catch (LimiteEmprestimosException exce) {
                        console.mensagemErro(exce.getMessage());
                        console.pause();
                    } catch (UsuarioNaoEncontradoException excep) {
                        console.mensagemErro(excep.getMessage());
                        console.pause();
                    } catch (ItemNaoEncontradoException except) {
                        console.mensagemErro(except.getMessage());
                        console.pause();
                    }

                    break;

                case 2:
                    System.out.println("=== REALIZANDO UM EMPRESTIMO DE JOGO ===");
                    servicoVenda.listarJogosDisponiveis();
                    System.out.print("\nDigite o ID Do Jogo Que Será Emprestado: ");
                    int idJogo = console.nextInt();

                    servicoUsuario.listarUsuarios();
                    System.out.print("\nDigite o ID Do Usuario Que Vai Pegar o Item: ");
                    int idUsuarioJogo = console.nextInt();

                    try {
                        servicoEmprestimo.realizarEmprestimoJogo(idUsuarioJogo, idJogo);
                        console.mensagemSucesso("=== EMPRESTIMO REALIZADO COM SUCESSO ===");
                        console.pause();
                    } catch (ItemIndisponivelException e) {
                        console.mensagemErro(e.getMessage());
                        console.pause();
                    } catch (EmprestimoEmAtrasoException ex) {
                        console.mensagemErro(ex.getMessage());
                        console.pause();
                    } catch (UsuarioNaoRegularizadoException exc) {
                        console.mensagemErro(exc.getMessage());
                        console.pause();
                    } catch (LimiteEmprestimosException exce) {
                        console.mensagemErro(exce.getMessage());
                        console.pause();
                    } catch (UsuarioNaoEncontradoException excep) {
                        console.mensagemErro(excep.getMessage());
                        console.pause();
                    } catch (ItemNaoEncontradoException except) {
                        console.mensagemErro(except.getMessage());
                        console.pause();
                    }

                    break;
                case 3:
                    System.out.println("=== REGISTRANDO UMA DEVOLUCAO ===");
                    servicoUsuario.listarUsuarios();
                    System.out.print("\nDigite o Id Do Usuario Que Fara a Devolucao: ");
                    int idUsuarioDevolucao = console.nextInt();

                    System.out.println(servicoEmprestimo.listarEmprestimosPorUsuario(idUsuarioDevolucao));

                    System.out.print("\nDigite o ID Do Emprestimo a Ser Devolvido: ");
                    int idEmprestimo = console.nextInt();
                    try {
                        servicoEmprestimo.registrarDevolucao(idEmprestimo);
                        console.mensagemSucesso("=== DEVOLUCAO REGISTRADA COM SUCESSO ===");
                        console.pause();
                    } catch (EmprestimoFinalizadoException e) {
                        console.mensagemErro(e.getMessage());
                        console.pause();
                    }

                    break;
                case 4:
                    System.out.println("=== HISTORICO DE EMPRESTIMO DO USUARIO ===");
                    servicoUsuario.listarUsuarios();
                    System.out.print("\nDigite o Id Do Usuario Que Tera Os Emprestimos Listados: ");
                    int idUsuarioEmprestimo = console.nextInt();
                    System.out.println(servicoEmprestimo.listarEmprestimosPorUsuario(idUsuarioEmprestimo));
                    console.pause();

                    break;
                case 5:
                    System.out.println("=== HISTORICO DE EMPRESTIMOS DO ITEM ===");
                    servicoItem.listarItens();
                    System.out.print("\nDigite o Id Do Item Que Tera Os Emprestimos Listados: ");
                    int idItemEmprestimo = console.nextInt();
                    System.out.println(servicoEmprestimo.listarHistoricoPorItem(idItemEmprestimo));
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