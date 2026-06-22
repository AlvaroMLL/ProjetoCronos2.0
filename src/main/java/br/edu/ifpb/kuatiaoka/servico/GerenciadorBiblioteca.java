package br.edu.ifpb.kuatiaoka.servico;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifpb.kuatiaoka.modelo.Item;
import br.edu.ifpb.kuatiaoka.modelo.Usuario;
import br.edu.ifpb.kuatiaoka.modelo.Emprestimo;

public class GerenciadorBiblioteca {
    private int proximoIdUsuario = 1;
    private int proximoIdItem = 1;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public ArrayList<Item> getItens() {
        return this.itens;
    }

    public int getProximoIdItem() {
        return proximoIdItem;
    }

    public void setProximoIdItem(int proximoIdItem) {
        this.proximoIdItem = proximoIdItem;
    }

    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public int getProximoIdUsuario() {
        return proximoIdUsuario;
    }

    public void setProximoIdUsuario(int proximoIdUsuario) {
        this.proximoIdUsuario = proximoIdUsuario;
    }

    public ArrayList<Item> buscarItemPorTitulo(String tituloBuscado) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getTitulo().equalsIgnoreCase(tituloBuscado)) {
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
        return null;
    }

    public Item buscarItemPorIsbn(String isbnBuscado) {
        for (Item item : itens) {
            if (item.getIsbn().equalsIgnoreCase(isbnBuscado)) {
                return item;
            }
        }
        return null;
    }

    public Item buscarItemPorIssn(String issnBuscado) {
        for (Item item : itens) {
            if (item.getIssn().equalsIgnoreCase(issnBuscado)) {
                return item;
            }
        }
        return null;
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
            if (item.getAutores() != null && item.getTipo().equalsIgnoreCase("livro")) {
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

    public ArrayList<Item> buscarItemPorStatus(String statusBuscado) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getStatus().equalsIgnoreCase(statusBuscado)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public ArrayList<Item> buscarItemPorEditora(String editoraBuscada) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getEditora().equalsIgnoreCase(editoraBuscada)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public ArrayList<Item> buscarItemPorGenero(String generoBuscado) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getGeneroLiterario() != null) {
                if (item.getGeneroLiterario().equalsIgnoreCase(generoBuscado)) {
                    resultado.add(item);
                }
            }
        }
        return resultado;
    }

    public ArrayList<Usuario> buscarUsuarioPorNome(String nomeBuscado) {
        ArrayList<Usuario> resultado = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().toLowerCase().contains(nomeBuscado.toLowerCase())) {
                resultado.add(usuario);
            }
        }
        return resultado;
    }

    public Usuario buscarUsuarioPorId(int idBuscado) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == idBuscado) {
                return usuario;
            }
        }
        return null;
    }

    public void realizarEmprestimo(int idUsuario, int idItem) {
        Usuario usuarioAchado = buscarUsuarioPorId(idUsuario);
        Item itemAchado = buscarItemPorId(idItem);

        if (usuarioAchado == null || itemAchado == null) {
            System.out.println("Erro: usuário ou item não encontrado.");
            return;
        }
        if (temEmprestimoEmAtraso(usuarioAchado)) {
            System.out.println("Erro: usuário possui empréstimos em atraso.");
            return;
        }

        int totalAtivos = contarEmprestimosAtivos(usuarioAchado);

        if (itemAchado.getStatus().equalsIgnoreCase("Disponível")
                && usuarioAchado.isRegularizado()) {

            if (totalAtivos < usuarioAchado.getLimiteEmprestimos()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setUsuario(usuarioAchado);
                emprestimo.setItemEmprestado(itemAchado);
                emprestimo.setIdDoEmprestimo(emprestimos.size() + 1);

                int dias = usuarioAchado.calcularPrazo(itemAchado);

                emprestimo.setDataDoEmprestimo(LocalDate.now());
                emprestimo.setDataPrevista(LocalDate.now().plusDays(dias));

                itemAchado.setStatus("Emprestado");
                emprestimos.add(emprestimo);

                System.out.println("Empréstimo realizado com sucesso!");
            } else {
                System.out.println("Erro: limite de empréstimos atingido.");
            }

        } else {
            System.out.println("Erro: item indisponível ou usuário irregular.");
        }
    }

    public int contarEmprestimosAtivos(Usuario usuario) {
        int cont = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (usuario.getId() == emprestimo.getUsuario().getId()
                    && emprestimo.getStatus().equalsIgnoreCase("EM_ABERTO")) {
                cont = cont + 1;
            }
        }
        return cont;
    }

    public void registrarDevolucao(int idItemDevolvido) {
        for (Emprestimo emprestimo : emprestimos) {
            if (idItemDevolvido == emprestimo.getItemEmprestado().getId()
                    && emprestimo.getStatus().equalsIgnoreCase("EM_ABERTO")) {
                emprestimo.setDataDevolucao(LocalDate.now());
                emprestimo.setStatus("FINALIZADO");
                emprestimo.getItemEmprestado().setStatus("Disponível");
                verificarMulta(emprestimo);
                System.out.println(
                        "Devolução do item " + emprestimo.getItemEmprestado().getTitulo() + " realizada com sucesso!");
                return;
            }
        }
        System.out.println("Erro: item não encontrado ou não está emprestado.");
    }

    // Nesse método eu transformei LocalDate em um long usando .toEpochDay()
    // (pesquisei LocalDate javadoc no google) para conseguir achar o numero de dias
    // de atraso e calcular a multa.
    public void verificarMulta(Emprestimo emprestimo) {
        if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataPrevista())) {
            long diasAtraso = emprestimo.getDataDevolucao().toEpochDay() - emprestimo.getDataPrevista().toEpochDay();
            double valorPorDia = 0;

            if (emprestimo.getUsuario().getCategoria().equalsIgnoreCase("aluno de graduação")) {
                valorPorDia = 2.00;
            } else if (emprestimo.getUsuario().getCategoria().equalsIgnoreCase("professor")
                    || emprestimo.getUsuario().getCategoria().equalsIgnoreCase("aluno de pós-graduação")) {
                valorPorDia = 1.00;
            } else {
                valorPorDia = 1.50;
            }
            double multaTotal = diasAtraso * valorPorDia;
            emprestimo.getUsuario().setMultaPendente(multaTotal);
            emprestimo.getUsuario().setRegularizado(false);
            System.out.println("Multa de R$ " + multaTotal + " aplicada por " + diasAtraso + " dias de atraso.");
        }
    }


    public boolean temEmprestimoEmAtraso(Usuario usuario) {
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getId() == usuario.getId()
                    && e.getStatus().equalsIgnoreCase("EM_ABERTO")
                    && e.getDataPrevista().isBefore(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Emprestimo> listarEmprestimosEmAberto() {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getStatus().equalsIgnoreCase("EM_ABERTO")) {
                lista.add(e);
            }
        }
        return lista;
    }

    public ArrayList<Emprestimo> listarEmprestimosAtrasados() {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getStatus().equalsIgnoreCase("EM_ABERTO")
                    && e.getDataPrevista().isBefore(LocalDate.now())) {
                lista.add(e);
            }
        }
        return lista;
    }

    public ArrayList<Emprestimo> listarPorUsuario(int idUsuario) {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getId() == idUsuario) {
                lista.add(e);
            }
        }
        return lista;
    }

    public void listarItensDisponiveis() {
        for (Item item : itens) {
            if (item.getStatus().equalsIgnoreCase("Disponível")) {
                System.out.println("ID: " + item.getId() + " | "
                        + item.getTipo() + ": " + item.getTitulo());
            }
        }
    }
}
