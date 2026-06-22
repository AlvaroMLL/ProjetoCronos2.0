package br.edu.ifpb.kuatiaoka.modelo;

import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataDoEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    private int idDoEmprestimo;
    private Item itemEmprestado;
    private Usuario usuario;
    private String status = "EM_ABERTO";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public Item getItemEmprestado() {
        return itemEmprestado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataDoEmprestimo() {
        return dataDoEmprestimo;
    }

    public int getIdDoEmprestimo() {
        return idDoEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDoEmprestimo(LocalDate dataDoEmprestimo) {
        this.dataDoEmprestimo = dataDoEmprestimo;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setIdDoEmprestimo(int idDoEmprestimo) {
        this.idDoEmprestimo = idDoEmprestimo;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setItemEmprestado(Item itemEmprestado) {
        this.itemEmprestado = itemEmprestado;
    }

}
