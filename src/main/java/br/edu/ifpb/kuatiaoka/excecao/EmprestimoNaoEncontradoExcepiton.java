package br.edu.ifpb.kuatiaoka.excecao;

public class EmprestimoNaoEncontradoExcepiton extends RuntimeException {
    public EmprestimoNaoEncontradoExcepiton(String mensagem) {
        super(mensagem);
    }
}