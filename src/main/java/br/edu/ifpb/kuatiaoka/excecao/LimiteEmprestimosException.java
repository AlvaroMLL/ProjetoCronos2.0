package br.edu.ifpb.kuatiaoka.excecao;

public class LimiteEmprestimosException extends RuntimeException {
    public LimiteEmprestimosException(String mensagem) {
        super(mensagem);
    }
}
