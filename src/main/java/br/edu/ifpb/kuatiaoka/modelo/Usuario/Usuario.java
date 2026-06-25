package br.edu.ifpb.kuatiaoka.modelo.Usuario;

import br.edu.ifpb.kuatiaoka.modelo.Interface.Emprestavel;
import lombok.Data;

@Data
public abstract class Usuario {
    private String nome;
    private boolean regularizado = true;
    private String email;
    private int id;
    private double multaPendente = 0;

    public abstract int getLimiteEmprestimos();

    public abstract double getMultaDiaria();

    public abstract int calcularPrazo(Emprestavel item);

    @Override
    public String toString() {
        return "====================================\n" +
                "ID: " + id +
                "\nNome: " + nome +
                "\nEmail: " + email +
                "\nRegularizado: " + regularizado +
                "\nMulta Pendente: R$ " + multaPendente;
    }
}
