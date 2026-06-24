package br.edu.ifpb.kuatiaoka.modelo.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cd extends Item {
    private String[] listaDeFaixas;
    // private String artista; Vou usar "autores" como artista
}
