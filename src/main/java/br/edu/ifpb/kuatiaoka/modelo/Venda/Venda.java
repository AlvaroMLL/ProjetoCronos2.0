package br.edu.ifpb.kuatiaoka.modelo.Venda;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.ifpb.kuatiaoka.modelo.Item.Jogo;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Usuario;
import lombok.Data;

@Data
public class Venda {
    private int idVenda;
    private Jogo jogo;
    private Usuario comprador;
    private LocalDate dataVenda;
    private BigDecimal valor;
}
