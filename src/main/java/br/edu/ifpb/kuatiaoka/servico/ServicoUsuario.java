package br.edu.ifpb.kuatiaoka.servico;

import java.util.ArrayList;

import br.edu.ifpb.kuatiaoka.excecao.MultaInexistenteException;
import br.edu.ifpb.kuatiaoka.excecao.UsuarioNaoEncontradoException;
import br.edu.ifpb.kuatiaoka.modelo.Usuario.Usuario;

public class ServicoUsuario {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private int proximoIdUsuario = 1;

    public void adicionarUsuario(Usuario usuario) {
        usuario.setId(proximoIdUsuario);
        proximoIdUsuario++;
        this.usuarios.add(usuario); 
    }

    public int getProximoIdUsuario() {
        return proximoIdUsuario;
    }

    public void setProximoIdUsuario(int proximoIdUsuario) {
        this.proximoIdUsuario = proximoIdUsuario;
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

    public void pagarMulta(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario.getMultaPendente() <= 0) {
            throw new MultaInexistenteException("Erro: Multa inexistente");
        }
        usuario.setMultaPendente(0);
        usuario.setRegularizado(true);
        System.out.println("Pagamento realizado com sucesso.");
    }

    public ArrayList<Usuario> listarUsuariosComMulta() {
        ArrayList<Usuario> lista = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getMultaPendente() > 0) {
                lista.add(usuario);
            }
        }
        return lista;
    }
}
