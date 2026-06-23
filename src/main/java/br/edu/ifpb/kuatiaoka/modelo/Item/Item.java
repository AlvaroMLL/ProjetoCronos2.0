package br.edu.ifpb.kuatiaoka.modelo.Item;

import java.util.List;

import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import lombok.Data;

@Data
public abstract class Item {
    private String titulo;
    private List<String> autores;
    private int id;
    private StatusItem statusItem = StatusItem.DISPONIVEL;

}