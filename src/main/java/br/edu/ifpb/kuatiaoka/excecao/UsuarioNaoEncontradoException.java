package br.edu.ifpb.kuatiaoka.excecao;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

