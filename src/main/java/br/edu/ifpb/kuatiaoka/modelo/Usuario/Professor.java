package br.edu.ifpb.kuatiaoka.modelo.Usuario;

import br.edu.ifpb.kuatiaoka.modelo.Interface.Emprestavel;
import br.edu.ifpb.kuatiaoka.modelo.Item.Livro;

public class Professor extends Usuario {
    // Pode pegar até 5 itens simultaneamente por ate 7 dias (outros) ou 14 dias
    // (livros), multa de 1.00R$ por dia por item
    private static final int LIMITE_EMPRESTIMOS = 5;
    private static final double MULTA_DIARIA = 1.00;
    private static final int PRAZO_EMPRESTIMO_LIVROS = 14;
    private static final int PRAZO_EMPRESTIMO_OUTROS = 7;

    @Override
    public int getLimiteEmprestimos() {
        return LIMITE_EMPRESTIMOS;
    }

    @Override
    public double getMultaDiaria() {
        return MULTA_DIARIA;
    }

    @Override
    public int calcularPrazo(Emprestavel item) {
        if (item instanceof Livro) {
            return PRAZO_EMPRESTIMO_LIVROS;
        }

        return PRAZO_EMPRESTIMO_OUTROS;
    }
}
