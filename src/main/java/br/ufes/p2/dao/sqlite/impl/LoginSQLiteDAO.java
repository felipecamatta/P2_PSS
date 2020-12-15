package br.ufes.p2.dao.sqlite.impl;

import br.ufes.p2.dao.interfaces.ILoginDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Login;
import br.ufes.p2.model.Privilegio;
import br.ufes.p2.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class LoginSQLiteDAO implements ILoginDAO {

    private SQLiteManager manager;

    public LoginSQLiteDAO(SQLiteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public void save(Login login) throws Exception {
        try {
            String SQL = "INSERT INTO Login(usuario, senha, idUsuario) VALUES(?, ?, ?)";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, login.getUsuario());
            ps.setString(2, login.getSenha());
            ps.setLong(3, login.getUsuarioObj().getId());
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
    public void delete(Long id) throws Exception {
        try {
            String SQL = "DELETE FROM Login WHERE id = ?";

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
    public Optional<Login> get(String usuario, String senha) throws Exception {
        try {
            String SQL = "SELECT l.id, l.usuario, l.senha, u.id, u.nome, u.tipo, "
                    + "p.idUsuario, p.visualizarTodas, p.excluirTodas, p.compartilharTodas "
                    + "FROM Login l INNER JOIN Usuario u ON l.idUsuario = u.id "
                    + "INNER JOIN Privilegio p ON p.idUsuario = l.idUsuario "
                    + "WHERE usuario = ? AND senha = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            Login login = null;

            while (rs.next()) {
                login = new Login();
                login.setId(rs.getLong(1));
                login.setUsuario(rs.getString(2));
                login.setSenha(rs.getString(3));

                Usuario usuarioObj = new Usuario();
                usuarioObj.setId(rs.getLong(4));
                usuarioObj.setNome(rs.getString(5));
                usuarioObj.setTipo(rs.getString(6));

                login.setUsuarioObj(usuarioObj);

                Privilegio privilegio = new Privilegio();
                privilegio.setIdUsuario(rs.getLong(7));
                privilegio.setVisualizarTodas(rs.getBoolean(8));
                privilegio.setExcluirTodas(rs.getBoolean(9));
                privilegio.setCompartilharTodas(rs.getBoolean(10));

                usuarioObj.setPrivilegio(privilegio);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return Optional.ofNullable(login);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public Login getByIdUsuario(Long idUsuario) throws Exception {
        try {
            String SQL = "SELECT l.id, l.usuario, l.senha, u.id, u.nome, u.tipo "
                    + "FROM Login l INNER JOIN Usuario u ON l.idUsuario = u.id "
                    + "WHERE l.idUsuario = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            Login login = null;

            while (rs.next()) {
                login = new Login();
                login.setId(rs.getLong(1));
                login.setUsuario(rs.getString(2));
                login.setSenha(rs.getString(3));

                Usuario usuarioObj = new Usuario();
                usuarioObj.setId(rs.getLong(4));
                usuarioObj.setNome(rs.getString(5));
                usuarioObj.setTipo(rs.getString(6));

                login.setUsuarioObj(usuarioObj);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return login;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public boolean usuarioLoginExists(String usuario) throws Exception {
        try {
            String SQL = "SELECT * FROM Login WHERE usuario = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            this.manager.fechaTransacao();
            this.manager.close();

            return rs.next();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public boolean existeUsuario() throws Exception {
        try {
            String SQL = "SELECT * FROM Login";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            this.manager.fechaTransacao();
            this.manager.close();

            return rs.next();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

}
