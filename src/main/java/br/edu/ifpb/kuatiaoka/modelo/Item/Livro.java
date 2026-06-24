package br.edu.ifpb.kuatiaoka.modelo.Item;

import br.edu.ifpb.kuatiaoka.modelo.Editora.Editora;
import br.edu.ifpb.kuatiaoka.modelo.Enum.GeneroLiterario;
import br.edu.ifpb.kuatiaoka.modelo.Interface.Emprestavel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Livro extends Item implements Emprestavel {
    private String isbn;
    private Editora editora;
    private int anoDePublicacao;
    private String edicao;
    private GeneroLiterario generoLiterario;
    private String sinopse;
}
