package br.edu.ifpb.kuatiaoka.modelo.Interface;

import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;

public interface Emprestavel {
    int getId();

    void setStatusItem(StatusItem status);

    StatusItem getStatusItem();

    String getTitulo();
}
