package br.edu.ifpb.kuatiaoka.modelo.Item;

import br.edu.ifpb.kuatiaoka.modelo.Editora.Editora;
import br.edu.ifpb.kuatiaoka.modelo.Interface.Emprestavel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Revista extends Item implements Emprestavel{
    private int issn;
    private String volume;
    private int numero;
    private Editora editora;
    private String dataDePublicacaoString;
}
