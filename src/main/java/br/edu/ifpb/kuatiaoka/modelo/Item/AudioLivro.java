package br.edu.ifpb.kuatiaoka.modelo.Item;

import br.edu.ifpb.kuatiaoka.modelo.Interface.Emprestavel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AudioLivro extends Livro {
    private int duracaoMinutos;
}
