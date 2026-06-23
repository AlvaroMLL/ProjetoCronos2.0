package br.edu.ifpb.kuatiaoka.servico;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifpb.kuatiaoka.excecao.EmprestimoEmAtrasoException;
import br.edu.ifpb.kuatiaoka.excecao.EmprestimoNaoEncontradoExcepiton;
import br.edu.ifpb.kuatiaoka.excecao.ItemIndisponivelException;
import br.edu.ifpb.kuatiaoka.excecao.ItemNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.excecao.LimiteEmprestimosException;
import br.edu.ifpb.kuatiaoka.excecao.UsuarioNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.excecao.UsuarioNaoRegularizadoException;
import br.edu.ifpb.kuatiaoka.modelo.Emprestimo.Emprestimo;
import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusEmprestimo;
import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import br.edu.ifpb.kuatiaoka.modelo.Item.Item;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Usuario;

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
        throw new ItemNaoEncontradoException("Erro: Item não encontrado");
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
        throw new UsuarioNaoEncontradoException("Erro: Usuario não encontrado");
    }

    public void realizarEmprestimo(int idUsuario, int idItem) {
        Usuario usuarioAchado = buscarUsuarioPorId(idUsuario);
        Item itemAchado = buscarItemPorId(idItem);

        if (itemAchado.getStatusItem() != StatusItem.DISPONIVEL) {
            throw new ItemIndisponivelException("Erro: Item indisponível");
        }
        if (temEmprestimoEmAtraso(usuarioAchado)) {
            throw new EmprestimoEmAtrasoException("Erro: Emprestimo atrasado");
        }

        int totalAtivos = contarEmprestimosAtivos(usuarioAchado);

        if (!usuarioAchado.isRegularizado()) {
            throw new UsuarioNaoRegularizadoException("Erro: Usuario não regularizado");
        }
        if (totalAtivos >= usuarioAchado.getLimiteEmprestimos()) {
            throw new LimiteEmprestimosException("Erro: Limite de emprestimos atingido");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuarioAchado);
        emprestimo.setItemEmprestado(itemAchado);
        emprestimo.setIdDoEmprestimo(emprestimos.size() + 1);

        int dias = usuarioAchado.calcularPrazo(itemAchado);

        emprestimo.setDataDoEmprestimo(LocalDate.now());
        emprestimo.setDataPrevista(LocalDate.now().plusDays(dias));

        itemAchado.setStatusItem(StatusItem.EMPRESTADO);
        emprestimos.add(emprestimo);

        System.out.println("Empréstimo realizado com sucesso!");
    }

    public int contarEmprestimosAtivos(Usuario usuario) {
        int cont = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (usuario.getId() == emprestimo.getUsuario().getId()
                    && emprestimo.getStatus() != StatusEmprestimo.ATRASADO
                    && emprestimo.getStatus() != StatusEmprestimo.MULTA_PEDENTE) {
                cont = cont + 1;
            }
        }
        return cont;
    }

    public void registrarDevolucao(int) {
        if () {
            
        }
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
                    && e.getStatus() != StatusEmprestimo.EM_ABERTO && e.getStatus() != StatusEmprestimo.DEVOLVIDO
                    && e.getDataPrevista().isBefore(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Emprestimo> listarEmprestimosEmAberto() {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getStatus() == StatusEmprestimo.EM_ABERTO) {
                lista.add(e);
            }
        }
        return lista;
    }

    public ArrayList<Emprestimo> listarEmprestimosAtrasados() {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getStatus() == StatusEmprestimo.ATRASADO) {
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
            if (item.getStatusItem() == StatusItem.DISPONIVEL) {
                System.out.println("ID: " + item.getId() + " | "
                        + item.getTipo() + ": " + item.getTitulo());
            }
        }
    }

    public Emprestimo buscarEmprestimoPorId(int idBuscado) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getIdDoEmprestimo() == idBuscado) {
                return emprestimo;
            }
        }
        throw new EmprestimoNaoEncontradoExcepiton("Erro: Emprestimo não encontrado");
    }
}
