package br.edu.ifpb.kuatiaoka.modelo.Usuario;

public class Administrativo extends Usuario{
    // Pode pegar até 2 itens simultaneamente por ate 10 dias, multa de 1.50R$ por dia por item
    private static final int LIMITE_EMPRESTIMOS = 2;
    private static final double MULTA_DIARIA = 1.50;
    private static final int PRAZO_EMPRESTIMO = 10; 
}
