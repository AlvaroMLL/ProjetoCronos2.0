package br.edu.ifpb.kuatiaoka.excecao;

public class UsuarioNaoRegularizadoException extends RuntimeException {
        public UsuarioNaoRegularizadoException(String mensagem) {
        super(mensagem);
    }
}

