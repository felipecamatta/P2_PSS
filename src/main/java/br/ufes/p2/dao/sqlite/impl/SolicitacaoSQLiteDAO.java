package br.ufes.p2.dao.sqlite.impl;

import br.ufes.p2.dao.interfaces.ISolicitacaoDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.model.Solicitacao;
import br.ufes.p2.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoSQLiteDAO implements ISolicitacaoDAO {

    private SQLiteManager manager;

    public SolicitacaoSQLiteDAO(SQLiteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public List<Solicitacao> getAllPendentes() throws Exception {
        try {
            String SQL = "SELECT i.id, i.nome, i.diretorio, i.excluida, u.id, u.nome, u.tipo, s.id, s.descricao, s.aceita "
                    + "FROM Solicitacao s "
                    + "INNER JOIN Imagem i ON i.id = s.idImagem "
                    + "INNER JOIN Usuario u ON u.id = s.idUsuario "
                    + "WHERE s.aceita IS NULL";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            List<Solicitacao> solicitacoes = new ArrayList<>();
            while (rs.next()) {
                Imagem imagem = new Imagem();
                imagem.setId(rs.getLong(1));
                imagem.setNome(rs.getString(2));
                imagem.setDiretorio(rs.getString(3));
                imagem.setExcluida(rs.getBoolean(4));

                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong(5));
                usuario.setNome(rs.getString(6));
                usuario.setTipo(rs.getString(7));

                Solicitacao solicitacao = new Solicitacao();
                solicitacao.setId(rs.getLong(8));
                solicitacao.setImagem(imagem);
                solicitacao.setUsuario(usuario);
                solicitacao.setDescricao(rs.getString(9));
                solicitacao.setAceita(rs.getBoolean(10));
                solicitacoes.add(solicitacao);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return solicitacoes;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void save(Solicitacao solicitacao) throws Exception {
        try {
            String SQL = "INSERT INTO Solicitacao(idImagem, idUsuario, descricao) VALUES(?, ?, ?)";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, solicitacao.getImagem().getId());
            ps.setLong(2, solicitacao.getUsuario().getId());
            ps.setString(3, solicitacao.getDescricao());
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

    public void update(Solicitacao solicitacao) throws Exception {
        try {
            String SQL = "UPDATE Solicitacao SET aceita = ? WHERE id = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setBoolean(1, solicitacao.isAceita());
            ps.setLong(2, solicitacao.getId());
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

}
