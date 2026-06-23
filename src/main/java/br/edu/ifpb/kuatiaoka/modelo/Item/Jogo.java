package br.edu.ifpb.kuatiaoka.modelo.Item;

import java.math.BigDecimal;

import br.edu.ifpb.kuatiaoka.modelo.Enum.TipoJogo;
import br.edu.ifpb.kuatiaoka.modelo.Interface.Emprestavel;
import br.edu.ifpb.kuatiaoka.modelo.Interface.Vendivel;
import lombok.Data;

@Data
public class Jogo implements Emprestavel, Vendivel{
    private String nome;
    private int qtdPecas;
    private BigDecimal preco;
    private TipoJogo tipoJogo;
    private int idJogo;
}
