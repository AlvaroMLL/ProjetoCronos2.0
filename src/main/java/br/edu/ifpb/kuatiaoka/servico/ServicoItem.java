package br.edu.ifpb.kuatiaoka.servico;

import java.util.ArrayList;

import br.edu.ifpb.kuatiaoka.excecao.ItemNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import br.edu.ifpb.kuatiaoka.modelo.Item.Item;
import br.edu.ifpb.kuatiaoka.modelo.Item.Livro;
import br.edu.ifpb.kuatiaoka.modelo.Item.Revista;

public class ServicoItem {
    private int proximoIdItem = 1;
    private ArrayList<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        item.setId(proximoIdItem);
        this.itens.add(item);
        proximoIdItem++;
    }

    public int getProximoIdItem() {
        return proximoIdItem;
    }

    public void setProximoIdItem(int proximoIdItem) {
        this.proximoIdItem = proximoIdItem;
    }

    public ArrayList<Item> getItens() {
        return this.itens;
    }

    public ArrayList<Item> buscarItemPorTitulo(String tituloBuscado) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getTitulo().equalsIgnoreCase(tituloBuscado)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public Item buscarItemPorId(int idBuscado) {
        for (Item item : itens) {
            if (item.getId() == idBuscado) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException("=== ERRO: ITEM NAO ENCONTRADO ===");
    }

    public ArrayList<Item> buscarItemPorAutor(String autorBuscado) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getAutor() == autorBuscado) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public ArrayList<Livro> buscarLivroPorEditora(String editoraBuscada) {
        ArrayList<Livro> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item instanceof Livro livro) {
                if (livro.getEditora().getNome().equalsIgnoreCase(editoraBuscada)) {
                    resultado.add(livro);
                }
            }
        }
        return resultado;
    }

    public Livro buscarLivroPorIsbn(String isbnBuscado) {
        for (Item item : itens) {
            if (item instanceof Livro livro) {
                if (livro.getIsbn().equalsIgnoreCase(isbnBuscado)) {
                    return livro;
                }
            }
        }
        throw new ItemNaoEncontradoException("=== ERRO: LIVRO NAO ENCONTRADO ===");
    }

    public Revista buscarRevistaPorIssn(String issnBuscado) {
        for (Item item : itens) {
            if (item instanceof Revista revista) {
                if (revista.getIssn().equalsIgnoreCase(issnBuscado)) {
                    return revista;
                }
            }
        }
        throw new ItemNaoEncontradoException("=== ERRO: REVISTA NAO ENCONTRADA ===");
    }

    public void listarItensDisponiveis() {
        for (Item item : itens) {
            if (item.getStatusItem() == StatusItem.DISPONIVEL) {
                System.out.println("ID: " + item.getId() + " | "
                        + item.getClass().getSimpleName() + ": " + item.getTitulo());
            }
        }
    }

    public void listarItens() {
        for (Item item : itens) {
            System.out.println("ID: " + item.getId() + " | "
                    + item.getClass().getSimpleName() + ": " + item.getTitulo());
        }
    }
}
