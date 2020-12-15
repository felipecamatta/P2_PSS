package br.ufes.p2.dao.sqlite.impl;

import br.ufes.p2.dao.interfaces.IUsuarioDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Privilegio;
import br.ufes.p2.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSQLiteDAO implements IUsuarioDAO {

    private SQLiteManager manager;

    public UsuarioSQLiteDAO(SQLiteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        try {
            String SQL = "SELECT u.id, u.nome, u.tipo, p.idUsuario, p.visualizarTodas, p.excluirTodas, "
                    + "p.compartilharTodas FROM Usuario u "
                    + "INNER JOIN Privilegio p ON p.idUsuario = u.id";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong(1));
                usuario.setNome(rs.getString(2));
                usuario.setTipo(rs.getString(3));

                Privilegio privilegio = new Privilegio();
                privilegio.setIdUsuario(rs.getLong(4));
                privilegio.setVisualizarTodas(rs.getBoolean(5));
                privilegio.setExcluirTodas(rs.getBoolean(6));
                privilegio.setCompartilharTodas(rs.getBoolean(7));

                usuario.setPrivilegio(privilegio);

                usuarios.add(usuario);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return usuarios;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public Usuario get(Long id) throws Exception {
        try {
            String SQL = "SELECT u.id, u.nome, u.tipo, p.idUsuario, p.visualizarTodas, p.excluirTodas, "
                    + "p.compartilharTodas FROM Usuario u "
                    + "INNER JOIN Privilegio p ON p.idUsuario = u.idUsuario "
                    + "WHERE u.id = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Usuario usuario = new Usuario();
            while (rs.next()) {
                usuario.setId(rs.getLong(1));
                usuario.setNome(rs.getString(2));
                usuario.setTipo(rs.getString(3));

                Privilegio privilegio = new Privilegio();
                privilegio.setIdUsuario(rs.getLong(4));
                privilegio.setVisualizarTodas(rs.getBoolean(5));
                privilegio.setExcluirTodas(rs.getBoolean(6));
                privilegio.setCompartilharTodas(rs.getBoolean(7));

                usuario.setPrivilegio(privilegio);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return usuario;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar usuario");
        }
    }

    @Override
    public void save(Usuario usuario) throws Exception {
        try {
            String SQL = "INSERT INTO Usuario(nome, tipo) VALUES(?, ?);";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getTipo());
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        try {
            String SQL = "UPDATE Usuario SET nome = ?, tipo = ? WHERE id = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getTipo());
            ps.setLong(3, usuario.getId());
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao atualizar");
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            String SQL = "DELETE FROM Usuario WHERE id = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, id);
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao excluir");
        }
    }

    @Override
    public Long getMaxId() throws Exception {
        try {
            String SQL = "SELECT MAX(id) from Usuario";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            Long id = rs.getLong(1);

            this.manager.fechaTransacao();
            this.manager.close();

            return id;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao bsucar");
        }
    }

}
