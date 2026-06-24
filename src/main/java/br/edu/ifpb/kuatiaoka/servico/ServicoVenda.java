package br.edu.ifpb.kuatiaoka.servico;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifpb.kuatiaoka.excecao.ItemNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.modelo.Item.Jogo;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Usuario;
import br.edu.ifpb.kuatiaoka.modelo.Venda.Venda;

public class ServicoVenda {
    private ArrayList<Jogo> jogos = new ArrayList<>();
    private ArrayList<Venda> vendas = new ArrayList<>();
    private ServicoUsuario servicoUsuario;
    private int proximoIdJogo = 1;

    public int proximoIdJogo() {
        return proximoIdJogo;
    }

    public void cadastrarJogo(Jogo jogo) {
        jogo.setIdJogo(proximoIdJogo);
        this.jogos.add(jogo);
        proximoIdJogo++;
    }

    public void cadastrarVenda(Venda venda) {
        this.vendas.add(venda);
    }

    public Jogo buscarJogoPorId(int idBuscado) {
        for (Jogo jogo : jogos) {
            if (jogo.getIdJogo() == idBuscado) {
                return jogo;
            }
        }
        throw new ItemNaoEncontradoException("=== ERRO: JOGO NAO ENCONTRADO ===");
    }

    public void realizarVenda(int idUsuario, int idJogo) {
        Usuario usuarioAchado = servicoUsuario.buscarUsuarioPorId(idUsuario);
        Jogo jogoAchado = buscarJogoPorId(idJogo);

        Venda venda = new Venda();
        venda.setComprador(usuarioAchado);
        venda.setDataVenda(LocalDate.now());
        venda.setJogo(jogoAchado);
        venda.setValor(jogoAchado.getPreco());
        venda.setIdVenda(vendas.size() + 1);
        vendas.add(venda);

    }

    public void listarVendas() {
        for (Venda venda : vendas) {
            System.out.println("ID: " + venda.getIdVenda() +
                    "\nNome: " + venda.getJogo().getNome() +
                    "\nValor: R$ " + venda.getValor() +
                    "\nID do Comprador: " + venda.getComprador().getId());
        }
    }

    public void listarJogos() {
        for (Jogo jogo : jogos) {
            System.out.println("ID: " + jogo.getIdJogo() + " | "
                    + jogo.getNome() + ": " + jogo.getPreco());
        }
    }
}
