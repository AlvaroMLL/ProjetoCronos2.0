package br.edu.ifpb.kuatiaoka.excecao;

public class MultaInexistenteException extends RuntimeException{
    public MultaInexistenteException(String mensagem) {
        super(mensagem);
    }
}
