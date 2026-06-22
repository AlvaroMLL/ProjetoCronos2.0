package br.edu.ifpb.kuatiaoka.modelo;

public class Item {
    private String isbn;
    private String titulo;
    private String[] autores;
    private String editora;
    private int ano;
    private String edicao;
    private String generoLiterario;
    private int numeroDePaginas;
    private String sinopse;
    private String tipo;
    private String issn;
    private String numero;
    private String dataDePublicacao;
    private int id;
    private String status = "Disponível";
    private String diretor;
    private String duracao;
    private String classificacaoIndicativa;
    private String artista;
    private String[] listaDeFaixas;
    private String volume;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAno() {
        return ano;
    }

    public String[] getAutores() {
        return autores;
    }

    public String getEdicao() {
        return edicao;
    }

    public String getEditora() {
        return editora;
    }

    public String getGeneroLiterario() {
        return generoLiterario;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getDataDePublicacao() {
        return dataDePublicacao;
    }

    public String getIssn() {
        return issn;
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getArtista() {
        return artista;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public String getDiretor() {
        return diretor;
    }

    public String getDuracao() {
        return duracao;
    }

    public String[] getListaDeFaixas() {
        return listaDeFaixas;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setAutor(String[] autores) {
        this.autores = autores;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setGeneroLiterario(String generoLiterario) {
        this.generoLiterario = generoLiterario;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDataDePublicacao(String dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setListaDeFaixas(String[] listaDeFaixas) {
        this.listaDeFaixas = listaDeFaixas;
    }

    public Item(String isbn, int id, String titulo, String tipo, String[] autores, String editora, int ano,
            String edicao, String generoLiterario, int numeroDePaginas, String sinopse,
            String issn, String volume, String numero, String dataDePublicacao, String diretor,
            String duracao, String classificacaoIndicativa, String artista, String[] listaDeFaixas) {
        this.isbn = isbn;
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.autores = autores;
        this.editora = editora;
        this.ano = ano;
        this.edicao = edicao;
        this.generoLiterario = generoLiterario;
        this.numeroDePaginas = numeroDePaginas;
        this.sinopse = sinopse;
        this.issn = issn;
        this.volume = volume;
        this.numero = numero;
        this.dataDePublicacao = dataDePublicacao;
        this.diretor = diretor;
        this.duracao = duracao;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.artista = artista;
        this.listaDeFaixas = listaDeFaixas;
    }

    public void exibirFichaTecnica() {
        System.out.println("\nID: " + this.id + " | [" + this.tipo + "] " + this.titulo);
        System.out.println("Status: " + (this.status));

        if (this.tipo.equalsIgnoreCase("Livro") && this.autores != null) {
            System.out.println("Autores: " + String.join(", ", this.autores));
            System.out.println("Editora: " + this.editora + " | Gênero: " + this.generoLiterario);
        } else if (this.tipo.equalsIgnoreCase("Revista")) {
            System.out.println("ISSN: " + this.issn + " | Volume: " + this.volume + " | Edição: " + this.edicao);
        } else if (this.tipo.equalsIgnoreCase("CD")) {
            System.out.println("Artista: " + this.artista);
            if (this.listaDeFaixas != null) {
                System.out.println("Faixas: " + String.join(" / ", this.listaDeFaixas));
            }
        } else if (this.tipo.equalsIgnoreCase("DVD")) {
            System.out.println("Diretor: " + this.diretor + " | Duração: " + this.duracao + " min");
            System.out.println("Classificação: " + this.classificacaoIndicativa);
        }
    }
}
