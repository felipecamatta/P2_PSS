package br.ufes.p2.dao.sqlite.impl;

import br.ufes.p2.dao.interfaces.IPermissaoDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.model.Permissao;
import br.ufes.p2.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PermissaoSQLiteDAO implements IPermissaoDAO {

    private SQLiteManager manager;

    public PermissaoSQLiteDAO(SQLiteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public List<Permissao> getByIdUsuario(Long idUsuario) throws Exception {
        try {
            StringBuilder SQL = new StringBuilder();
            SQL.append("SELECT i.id, i.nome, i.diretorio, i.excluida, u.id, u.nome, p.descricao ");
            SQL.append("FROM Permissao p ");
            SQL.append("INNER JOIN Usuario u ON p.idUsuario = u.id ");
            SQL.append("INNER JOIN Imagem i ON p.idImagem = i.id ");
            SQL.append("WHERE u.id = ?");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL.toString());
            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            List<Permissao> permissoes = new ArrayList<>();
            while (rs.next()) {
                Imagem imagem = new Imagem();
                imagem.setId(rs.getLong(1));
                imagem.setNome(rs.getString(2));
                imagem.setDiretorio(rs.getString(3));
                imagem.setExcluida(rs.getBoolean(4));

                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong(5));
                usuario.setNome(rs.getString(6));

                Permissao permissao = new Permissao();
                permissao.setImagem(imagem);
                permissao.setUsuario(usuario);
                permissao.setDescricao(rs.getString(7));
                permissoes.add(permissao);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return permissoes;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public Optional<Permissao> get(Long idImagem, Long idUsuario) throws Exception {
        try {
            StringBuilder SQL = new StringBuilder();
            SQL.append("SELECT i.id, i.nome, i.diretorio, i.excluida, u.id, u.nome, p.descricao ");
            SQL.append("FROM Permissao p ");
            SQL.append("INNER JOIN Usuario u ON p.idUsuario = u.id ");
            SQL.append("INNER JOIN Imagem i ON p.idImagem = i.id ");
            SQL.append("WHERE i.id = ? AND u.id = ?");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL.toString());
            ps.setLong(1, idImagem);
            ps.setLong(2, idUsuario);
            ResultSet rs = ps.executeQuery();

            Permissao permissao = null;

            while (rs.next()) {
                Imagem imagem = new Imagem();
                imagem.setId(rs.getLong(1));
                imagem.setNome(rs.getString(2));
                imagem.setDiretorio(rs.getString(3));
                imagem.setExcluida(rs.getBoolean(4));

                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong(5));
                usuario.setNome(rs.getString(6));

                permissao = new Permissao();
                permissao.setImagem(imagem);
                permissao.setUsuario(usuario);
                permissao.setDescricao(rs.getString(7));
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return Optional.ofNullable(permissao);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void save(Permissao permissao) throws Exception {
        try {
            String SQL = "INSERT INTO Permissao(idImagem, idUsuario, descricao) VALUES(?, ?, ?);";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, permissao.getImagem().getId());
            ps.setLong(2, permissao.getUsuario().getId());
            ps.setString(3, permissao.getDescricao());
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
    public void update(Permissao permissao) throws Exception {
        try {
            String SQL = "UPDATE Permissao SET descricao = ? WHERE idImagem = ? AND idUsuario = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, permissao.getDescricao());
            ps.setLong(2, permissao.getImagem().getId());
            ps.setLong(3, permissao.getUsuario().getId());
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
    public void delete(Long idImagem, Long idUsuario) throws Exception {
        try {
            String SQL = "DELETE Permissao WHERE idImagem = ? AND idUsuario = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idImagem);
            ps.setLong(2, idUsuario);
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

}
