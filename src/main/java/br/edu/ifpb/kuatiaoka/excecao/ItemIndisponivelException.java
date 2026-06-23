package br.edu.ifpb.kuatiaoka.excecao;

public class ItemIndisponivelException extends RuntimeException{
    public ItemIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
