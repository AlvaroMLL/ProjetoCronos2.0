package br.edu.ifpb.kuatiaoka.modelo.Item;

import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import lombok.Data;

@Data
public abstract class Item {
    private String titulo;
    private String autor;
    private int id;
    private StatusItem statusItem = StatusItem.DISPONIVEL;

    @Override
    public String toString() {
        return "====================================\n" +
                "Tipo: " + getClass().getSimpleName() +
                "\nID: " + id +
                "\nTitulo: " + titulo +
                "\nAutor: " + autor +
                "\nStatus: " + statusItem;
    }
}