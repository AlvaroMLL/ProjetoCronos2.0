package br.edu.ifpb.kuatiaoka.modelo.Usuario;

import lombok.Data;

@Data
public class Usuario {
    private String nome;
    private boolean regularizado = true;
    private String email;
    private int id;
    private double multaPendente = 0;
}
