package br.edu.ifpb.kuatiaoka.servico;

import java.util.ArrayList;

import br.edu.ifpb.kuatiaoka.excecao.ItemNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import br.edu.ifpb.kuatiaoka.modelo.Item.Item;

public class ServicoItem {
    private int proximoIdItem = 1;
    private ArrayList<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        this.itens.add(item);
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
            if (item.getTitulo().equalsIgnoreCase(tituloBuscado)){
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
        throw new ItemNaoEncontradoException("Erro: Item não encontrado");
    }

    public ArrayList<Item> buscarItemPorTipo(String tipoBuscado) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getTipo().equalsIgnoreCase(tipoBuscado)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public ArrayList<Item> buscarItemPorAutor(String autorBuscado) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getAutores() != null) {
                for (String autor : item.getAutores()) {
                    if (autor.equalsIgnoreCase(autorBuscado)) {
                        resultado.add(item);
                        break;
                    }
                }
            }
        }
        return resultado;
    }

    public ArrayList<Item> buscarLivroPorEditora(String editoraBuscada) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (().equalsIgnoreCase(editoraBuscada)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public void listarItensDisponiveis() {
        for (Item item : itens) {
            if (item.getStatusItem() == StatusItem.DISPONIVEL) {
                System.out.println("ID: " + item.getId() + " | "
                        + item.getTipo() + ": " + item.getTitulo());
            }
        }
    }

    public ArrayList<Item> buscaritemPorStatus(String statusBuscado) {

    }
}
