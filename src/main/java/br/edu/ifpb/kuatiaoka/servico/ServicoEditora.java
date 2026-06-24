package br.edu.ifpb.kuatiaoka.servico;

import java.util.ArrayList;

import br.edu.ifpb.kuatiaoka.excecao.EditoraNaoEncontradaException;
import br.edu.ifpb.kuatiaoka.modelo.Editora.Editora;
import br.edu.ifpb.kuatiaoka.modelo.Emprestimo.Emprestimo;

public class ServicoEditora {

    private ArrayList<Editora> editoras = new ArrayList<>();
    private int proximoId = 1;

    public void adicionarEditora(Editora editora) {
        editora.setId(proximoId++);
        editoras.add(editora);
    }

    public Editora buscarPorId(int id) {
        for (Editora editora : editoras) {
            if (editora.getId() == id) {
                return editora;
            }
        }
        throw new EditoraNaoEncontradaException("=== ERRO: EDITORA NAO ENCONTRADA ===");
    }

    public void listarEditoras() {
        for (Editora editora : editoras) {
            System.out.println("ID: " + editora.getId() +
                    "\nNome Da Editora: " + editora.getNome() +
                    "\nCnpj Da Editora: " + editora.getCnpj());
        }
    }
}
