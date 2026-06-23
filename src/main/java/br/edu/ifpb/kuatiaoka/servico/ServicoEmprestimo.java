package br.edu.ifpb.kuatiaoka.servico;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifpb.kuatiaoka.excecao.EmprestimoEmAtrasoException;
import br.edu.ifpb.kuatiaoka.excecao.EmprestimoFinalizadoException;
import br.edu.ifpb.kuatiaoka.excecao.EmprestimoNaoEncontradoExcepiton;
import br.edu.ifpb.kuatiaoka.excecao.ItemIndisponivelException;
import br.edu.ifpb.kuatiaoka.excecao.LimiteEmprestimosException;
import br.edu.ifpb.kuatiaoka.excecao.UsuarioNaoRegularizadoException;
import br.edu.ifpb.kuatiaoka.modelo.Emprestimo.Emprestimo;
import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusEmprestimo;
import br.edu.ifpb.kuatiaoka.modelo.Enum.StatusItem;
import br.edu.ifpb.kuatiaoka.modelo.Item.Item;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Usuario;

public class ServicoEmprestimo {

    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

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

    public ArrayList<Emprestimo> listarEmprestimosPorUsuario(int idUsuario) {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getId() == idUsuario) {
                lista.add(e);
            }
        }
        return lista;
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
