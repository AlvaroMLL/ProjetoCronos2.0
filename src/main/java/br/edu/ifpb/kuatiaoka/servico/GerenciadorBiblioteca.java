package br.edu.ifpb.kuatiaoka.servico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.kuatiaoka.excecao.EmprestimoEmAtrasoException;
import br.edu.ifpb.kuatiaoka.excecao.EmprestimoFinalizadoException;
import br.edu.ifpb.kuatiaoka.excecao.EmprestimoNaoEncontradoExcepiton;
import br.edu.ifpb.kuatiaoka.excecao.ItemIndisponivelException;
import br.edu.ifpb.kuatiaoka.excecao.ItemNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.excecao.LimiteEmprestimosException;
import br.edu.ifpb.kuatiaoka.excecao.MultaInexistenteException;
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

    public ArrayList<Item> buscarItemPorEditora(String editoraBuscada) {
        ArrayList<Item> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item.getEditora().equalsIgnoreCase(editoraBuscada)) {
                resultado.add(item);
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

    public void registrarDevolucao(int idEmprestimo) {
        Emprestimo emprestimoBuscado = buscarEmprestimoPorId(idEmprestimo);
        if (emprestimoBuscado.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            throw new EmprestimoFinalizadoException("Erro: Emprestimo já finalizado");
        }
        emprestimoBuscado.setDataDevolucao(LocalDate.now());
        if (emprestimoBuscado.getDataPrevista().isBefore(emprestimoBuscado.getDataDevolucao())) {
            long diasAtraso = emprestimoBuscado.getDataDevolucao().toEpochDay()
                    - emprestimoBuscado.getDataPrevista().toEpochDay();
            double multaTotal = diasAtraso * emprestimoBuscado.getUsuario().getMultaDiaria();
            emprestimoBuscado.getUsuario().setRegularizado(false);
            emprestimoBuscado.getUsuario().setMultaPendente(multaTotal);
            System.out.println("Multa de R$ " + multaTotal + " aplicada por " + diasAtraso + " dias de atraso.");
        }
        emprestimoBuscado.setStatus(StatusEmprestimo.DEVOLVIDO);
        emprestimoBuscado.getItemEmprestado().setStatusItem(StatusItem.DISPONIVEL);
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

    public void pagarMulta(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario.getMultaPendente() <= 0) {
            throw new MultaInexistenteException("Erro: Multa inexistente");
        }
        usuario.setMultaPendente(0);
        usuario.setRegularizado(true);
        System.out.println("Pagamento realizado com sucesso.");
    }
}
