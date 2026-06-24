package br.edu.ifpb.kuatiaoka.excecao;

public class EditoraNaoEncontradaException extends RuntimeException {
    public EditoraNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
