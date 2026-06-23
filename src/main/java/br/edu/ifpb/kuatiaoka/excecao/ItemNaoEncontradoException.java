package br.edu.ifpb.kuatiaoka.excecao;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}