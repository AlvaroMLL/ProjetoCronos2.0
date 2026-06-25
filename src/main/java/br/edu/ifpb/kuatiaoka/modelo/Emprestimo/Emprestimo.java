package br.edu.ifpb.kuatiaoka.modelo.Emprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusEmprestimo;
import br.edu.ifpb.kuatiaoka.modelo.Interface.Emprestavel;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Usuario;
import lombok.Data;

@Data
public class Emprestimo {
    private LocalDate dataDoEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    private int idDoEmprestimo;
    private Emprestavel itemEmprestado;
    private Usuario usuario;
    private BigDecimal valorMulta;
    private StatusEmprestimo status = StatusEmprestimo.EM_ABERTO;

    @Override
    public String toString() {
        return "====================================\n" +
                "ID Do Emprestimo: " + idDoEmprestimo +
                "\nUsuario: " + usuario.getNome() +
                "\nItem: " + itemEmprestado.getTitulo() +
                "\nData Do Emprestimo: " + dataDoEmprestimo +
                "\nData Prevista: " + dataPrevista +
                "\nData Devolucao: " +
                (dataDevolucao == null ? "Nao Devolvido" : dataDevolucao) +
                "\nStatus: " + status +
                "\nValor Da Multa: R$ " +
                (valorMulta == null ? "0.00" : valorMulta);
    }
}