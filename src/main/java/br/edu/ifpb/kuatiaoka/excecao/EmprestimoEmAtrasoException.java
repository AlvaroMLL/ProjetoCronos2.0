package br.edu.ifpb.kuatiaoka.excecao;

public class EmprestimoEmAtrasoException extends RuntimeException{
    public EmprestimoEmAtrasoException(String mensagem) {
        super(mensagem);
    }
}