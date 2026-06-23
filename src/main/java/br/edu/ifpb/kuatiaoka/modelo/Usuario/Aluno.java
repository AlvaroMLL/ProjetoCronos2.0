package br.edu.ifpb.kuatiaoka.modelo.Usuario;

import br.edu.ifpb.kuatiaoka.modelo.Item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno extends Usuario{
    // Pode pegar até 3 itens simultaneamente por ate 7 dias, multa de 2.00R$ por dia por item
    private static final int LIMITE_EMPRESTIMOS = 3;
    private static final double MULTA_DIARIA = 2.00;
    private static final int PRAZO_EMPRESTIMO = 7;

 @Override
    public int getLimiteEmprestimos() {
        return LIMITE_EMPRESTIMOS;
    }

    @Override
    public double getMultaDiaria() {
        return MULTA_DIARIA;
    }

    @Override
    public int calcularPrazo(Item item) {
        return PRAZO_EMPRESTIMO;
    }
}
