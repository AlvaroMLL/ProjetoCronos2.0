package br.edu.ifpb.kuatiaoka.UI.ItensUI;

import java.math.BigDecimal;

import br.edu.ifpb.kuatiaoka.UI.Util.Console;
import br.edu.ifpb.kuatiaoka.modelo.Enum.GeneroLiterario;
import br.edu.ifpb.kuatiaoka.modelo.Enum.TipoJogo;
import br.edu.ifpb.kuatiaoka.modelo.Item.AudioLivro;
import br.edu.ifpb.kuatiaoka.modelo.Item.Cd;
import br.edu.ifpb.kuatiaoka.modelo.Item.Jogo;
import br.edu.ifpb.kuatiaoka.modelo.Item.LivroFisico;
import br.edu.ifpb.kuatiaoka.modelo.Item.Revista;
import br.edu.ifpb.kuatiaoka.servico.ServicoItem;
import br.edu.ifpb.kuatiaoka.servico.ServicoVenda;

public class ItemCadastro {
    private Console console = new Console();
    private ServicoItem servicoItem;
    private ServicoVenda servicoVenda;

    public ItemCadastro(ServicoItem servicoItem, ServicoVenda servicoVenda) {
        this.servicoItem = servicoItem;
        this.servicoVenda = servicoVenda;
    }

    public void exibirCadastroItens() {
        System.out.println("=== CADASTRO DE ITENS ===");
        System.out.println("1 - Cadastrar Um Livro Fisico");
        System.out.println("2 - Cadastrar Um Audio Livro");
        System.out.println("3 - Cadastrar Uma Revista");
        System.out.println("4 - Cadastrar Um Cd");
        System.out.println("5 - Cadastrar Um Jogo");
        System.out.println("0 - Voltar");
    }

    public void executar() {
        int opcao = -1;

        do {
            exibirCadastroItens();

            System.out.println("\nEscolha Uma Opcao: ");
            opcao = console.nextInt();
            switch (opcao) {
                case 1:
                    LivroFisico livroFisico = new LivroFisico();

                    System.out.println("=== CADASTRO DE LIVRO FISICO ===");
                    System.out.println("\nDigite o Titulo Do Livro Fisico: ");
                    String tituloLivro = console.nextLine();

                    System.out.println("\nDigite o Autor Do Livro Fisico: ");
                    String autorLivro = console.nextLine();

                    System.out.println("\nDigite o ISBN Do Livro Fisico: ");
                    String isbnLivro = console.nextLine();

                    // System.out.println("\nDigite a Editora Do Livro Fisico: ");
                    // String editoraLivro = console.nextLine(); REFAZER ISSO APOS AQUILO

                    System.out.println("\nDigite o Ano De Publicacao Do Livro Fisico: ");
                    int anoDePublicacaoLivro = console.nextInt();

                    System.out.println("\nDigite a Edicao Do Livro Fisico: ");
                    String edicaoLivro = console.nextLine();

                    System.out.println("Escolha o Genero Literario Do Livro: ");
                    System.out.println("1 - Ficcao Cientifica | 2 - Romance | 3 - Terror | 4 - Biografia | 5 - Outro");
                    int opcaoGenero = console.nextInt();
                    switch (opcaoGenero) {
                        case 1:
                            livroFisico.setGeneroLiterario(GeneroLiterario.FICCAO_CIENTIFICA);
                            break;
                        case 2:
                            livroFisico.setGeneroLiterario(GeneroLiterario.ROMANCE);
                            break;
                        case 3:
                            livroFisico.setGeneroLiterario(GeneroLiterario.TERROR);
                            break;
                        case 4:
                            livroFisico.setGeneroLiterario(GeneroLiterario.BIOGRAFIA);
                            break;
                        case 5:
                            livroFisico.setGeneroLiterario(GeneroLiterario.OUTRO);
                            break;
                        default:
                            console.mensagemErro("Numero Invalido!");
                            break;
                    }

                    System.out.println("\nDigite a Sinopse do Livro Fisico: ");
                    String sinopseLivro = console.nextLine();

                    System.out.println("\nDigite o Numero De Paginas Do Livro Fisico: ");
                    int numeroDePaginas = console.nextInt();

                    livroFisico.setTitulo(tituloLivro);
                    livroFisico.setAutor(autorLivro);
                    livroFisico.setIsbn(isbnLivro);
                    livroFisico.setEditora();
                    livroFisico.setAnoDePublicacao(anoDePublicacaoLivro);
                    livroFisico.setEdicao(edicaoLivro);
                    livroFisico.setSinopse(sinopseLivro);
                    livroFisico.setNumeroDePaginas(numeroDePaginas);
                    servicoItem.adicionarItem(livroFisico);
                    console.pause();

                    break;
                case 2:
                    AudioLivro audiolivro = new AudioLivro();

                    System.out.println("=== CADASTRO DE AUDIOLIVRO ===");
                    System.out.println("\nDigite o Titulo Do Audiolivro: ");
                    String tituloAudiolivro = console.nextLine();

                    System.out.println("\nDigite o Autor Do Audiolivro: ");
                    String autorAudiolivro = console.nextLine();

                    System.out.println("\nDigite o ISBN Do Audiolivro: ");
                    String isbnAudiolivro = console.nextLine();

                    // System.out.println("\nDigite a Editora Do Audiolivro: ");
                    // String editoraAudiolivro = console.nextLine(); REFAZER ISSO APOS AQUILO

                    System.out.println("\nDigite o Ano De Publicacao Do Audiolivro: ");
                    int anoDePublicacaoAudiolivro = console.nextInt();

                    System.out.println("\nDigite a Edicao Do Audiolivro: ");
                    String edicaoAudiolivro = console.nextLine();

                    System.out.println("1 - Ficcao Cientifica | 2 - Romance | 3 - Terror | 4 - Biografia | 5 - Outro");
                    System.out.println("\nEscolha o Genero Literario Do Livro: ");
                    int opcaoGeneroAudiolivro = console.nextInt();
                    switch (opcaoGeneroAudiolivro) {
                        case 1:
                            audiolivro.setGeneroLiterario(GeneroLiterario.FICCAO_CIENTIFICA);
                            break;
                        case 2:
                            audiolivro.setGeneroLiterario(GeneroLiterario.ROMANCE);
                            break;
                        case 3:
                            audiolivro.setGeneroLiterario(GeneroLiterario.TERROR);
                            break;
                        case 4:
                            audiolivro.setGeneroLiterario(GeneroLiterario.BIOGRAFIA);
                            break;
                        case 5:
                            audiolivro.setGeneroLiterario(GeneroLiterario.OUTRO);
                            break;
                        default:
                            console.mensagemErro("=== NUMERO INVALIDO ===");
                            console.pause();
                            break;
                    }

                    System.out.println("\nDigite a Sinopse do Livro Fisico: ");
                    String sinopseAudiolivro = console.nextLine();

                    System.out.println("\nDigite a Duracao Em Minutos Do Audiolivro: ");
                    int duracaoAudiolivro = console.nextInt();

                    audiolivro.setTitulo(tituloAudiolivro);
                    audiolivro.setAutor(autorAudiolivro);
                    audiolivro.setIsbn(isbnAudiolivro);
                    audiolivro.setEditora();
                    audiolivro.setAnoDePublicacao(anoDePublicacaoAudiolivro);
                    audiolivro.setEdicao(edicaoAudiolivro);
                    audiolivro.setSinopse(sinopseAudiolivro);
                    audiolivro.setDuracaoMinutos(duracaoAudiolivro);
                    servicoItem.adicionarItem(audiolivro);
                    console.pause();

                    break;
                case 3:
                    System.out.println("=== CADASTRO DE REVISTA ===");
                    System.out.println("\nDigite o Titulo Da Revista: ");
                    String tituloRevista = console.nextLine();

                    System.out.println("\nDigite o Autor Da Revista: ");
                    String autorRevista = console.nextLine();

                    System.out.println("\nDigite ISSN Da Revista: ");
                    String issnRevista = console.nextLine();

                    System.out.println("\nDigite o Volume Da Revista: ");
                    String volumeRevista = console.nextLine();

                    System.out.println("\nDigite o Numero Da Revista: ");
                    int numeroRevista = console.nextInt();

                    System.out.println("\nDigite a Editora Da Revista");
                    String editoraRevista = console.nextLine();

                    System.out.println("\nDigite a Data Da Publicacao Da Revista No Formato dd/MM/aaaa: ");
                    String dataPubliRevista = console.nextLine();

                    Revista revista = new Revista();

                    revista.setTitulo(tituloRevista);
                    revista.setAutor(autorRevista);
                    revista.setIssn(issnRevista);
                    revista.setVolume(volumeRevista);
                    revista.setNumero(numeroRevista);
                    revista.setEditora(editoraRevista);
                    revista.setDataDePublicacaoString(dataPubliRevista);
                    servicoItem.adicionarItem(revista);
                    console.pause();

                    break;
                case 4:
                    System.out.println("=== CADASTRO DE CD ===");
                    System.out.println("\nDigite o Titulo Do Cd: ");
                    String tituloCd = console.nextLine();

                    System.out.println("\nDigite o Artista Do Cd: ");
                    String artistaCd = console.nextLine();

                    System.out.println("\nDigite a Quantidade De Faixas Do Cd: ");
                    int qtdFaixas = Integer.parseInt(console.nextLine());
                    String[] faixaInformadas = new String[qtdFaixas];

                    for (int i = 0; i < qtdFaixas; i++) {
                        System.out.print("\nDigite o Nome Da Faixa " + (i + 1) + ": ");
                        faixaInformadas[i] = console.nextLine();
                    }

                    Cd cd = new Cd();

                    cd.setTitulo(tituloCd);
                    cd.setAutor(artistaCd);
                    cd.setListaDeFaixas(faixaInformadas);
                    console.pause();

                    break;
                case 5:
                    Jogo jogo = new Jogo();

                    System.out.println("=== CADASTRO DE JOGO ===");
                    System.out.println("\nDigite o Nome Do Jogo: ");
                    String NomeJogo = console.nextLine();

                    System.out.println("\nDigite a Quantidade De Pecas Do Jogo: ");
                    int qtdPecas = console.nextInt();

                    System.out.println("\nDigite o Preco Do Jogo (So os numeros): ");
                    String precoString = console.nextLine();
                    BigDecimal preco = new BigDecimal(precoString);

                    System.out.println("1 - Tabuleiro | 2 - Cartas");
                    System.out.println("\nEscolha Qual o Tipo Do Jogo: ");
                    int tipoJogo = console.nextInt();

                    switch (tipoJogo) {
                        case 1:
                            jogo.setTipoJogo(TipoJogo.TABULEIRO);

                            break;
                        case 2:
                            jogo.setTipoJogo(TipoJogo.CARTAS);

                            break;
                        default:
                            console.mensagemErro("=== NUMERO INVALIDO ===");
                            console.pause();
                            break;
                    }

                    jogo.setNome(NomeJogo);
                    jogo.setQtdPecas(qtdPecas);
                    jogo.setPreco(preco);
                    servicoVenda.cadastrarJogo(jogo);
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
