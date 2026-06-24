package br.edu.ifpb.kuatiaoka.modelo.Item;

import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import lombok.Data;

@Data
public abstract class Item {
    private String titulo;
    private String autor;
    private int id;
    private StatusItem statusItem = StatusItem.DISPONIVEL;
}