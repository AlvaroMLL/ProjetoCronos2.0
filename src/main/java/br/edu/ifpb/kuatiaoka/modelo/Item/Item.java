package br.edu.ifpb.kuatiaoka.modelo.Item;

import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import lombok.Data;

@Data
public class Item {
    private String titulo;
    private String[] autores;
    private int id;
    private StatusItem statusItem;
}