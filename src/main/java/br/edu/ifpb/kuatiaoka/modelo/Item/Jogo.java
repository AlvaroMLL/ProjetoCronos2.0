package br.edu.ifpb.kuatiaoka.modelo.Item;

import java.math.BigDecimal;

import br.edu.ifpb.kuatiaoka.modelo.Enum.TipoJogo;
import lombok.Data;

@Data
public class Jogo {
    private String nome;
    private int qtdPecas;
    private BigDecimal preco;
    private TipoJogo tipoJogo;
}
