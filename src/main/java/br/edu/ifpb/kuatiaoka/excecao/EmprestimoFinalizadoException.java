package br.edu.ifpb.kuatiaoka.excecao;

public class EmprestimoFinalizadoException extends RuntimeException {
    public EmprestimoFinalizadoException(String mensagem) {
        super(mensagem);
    }
}
