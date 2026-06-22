package br.edu.ifpb.kuatiaoka.modelo;

public class Usuario {
    private String nome;
    private String categoria;
    private boolean regularizado = true;
    private String email;
    private int id;
    private double multaPendente = 0;

    public double getMultaPendente() {
        return multaPendente;
    }

    public void setMultaPendente(double multaPendente) {
        this.multaPendente = multaPendente;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public boolean isRegularizado() {
        return regularizado;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRegularizado(boolean regularizado) {
        this.regularizado = regularizado;
    }

    public Usuario(String nome, String email, int id, String categoria) {
        this.nome = nome;
        this.email = email;
        this.id = id;
        this.categoria = categoria;
    }

    public int calcularPrazo(Item item) {
        if (getCategoria().equalsIgnoreCase("aluno de graduação")) {
            int prazo = 7;
            return prazo;
        }
        if (getCategoria().equalsIgnoreCase("professor") || getCategoria().equalsIgnoreCase("aluno de pós-graduação")) {
            int prazo = 14;
            if (item.getTipo().equalsIgnoreCase("dvd") || item.getTipo().equalsIgnoreCase("cd")
                    || item.getTipo().equalsIgnoreCase("revista")) {
                prazo = prazo - 7;
            }
            return prazo;
        }
        if (getCategoria().equalsIgnoreCase("funcionário administrativo")) {
            int prazo = 10;
            return prazo;
        }
        return 7;
    }

    public int getLimiteEmprestimos() {
        if (categoria.equalsIgnoreCase("aluno de graduação")) {
            return 3;
        } else if (categoria.equalsIgnoreCase("professor") || categoria.equalsIgnoreCase("aluno de pós-graduação")) {
            return 5;
        } else if (categoria.equalsIgnoreCase("funcionário administrativo")) {
            return 2;
        }
        return 0;
    }
}
