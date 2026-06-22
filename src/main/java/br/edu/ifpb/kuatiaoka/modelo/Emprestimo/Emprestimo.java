package br.edu.ifpb.kuatiaoka.modelo.Emprestimo;

import java.time.LocalDate;

import br.edu.ifpb.kuatiaoka.modelo.Item.Item;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Usuario;
import lombok.Data;

@Data
public class Emprestimo {
    private LocalDate dataDoEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    private int idDoEmprestimo;
    private Item itemEmprestado;
    private Usuario usuario;
    private String status = "EM_ABERTO";
}