package br.ufes.p2.dao.sqlite.impl;

import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Notificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import br.ufes.p2.dao.interfaces.INotificacaoDAO;
import br.ufes.p2.model.Imagem;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoSQLiteDAO implements INotificacaoDAO {

    private SQLiteManager manager;

    public NotificacaoSQLiteDAO(SQLiteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public List<Notificacao> getByIdUsuario(Long idUsuario) throws Exception {
        try {
            String SQL = "SELECT n.id, n.mensagem, n.lida, n.idImagem, "
                    + "i.id, i.nome, i.diretorio, i.excluida FROM Notificacao n "
                    + "INNER JOIN Imagem i ON i.id = n.idImagem "
                    + "WHERE idusuario = ? AND lida = 0";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            List<Notificacao> notificacoes = new ArrayList<>();
            while (rs.next()) {
                Notificacao notificacao = new Notificacao();
                notificacao.setId(rs.getLong(1));
                notificacao.setMensagem(rs.getString(2));
                notificacao.setLida(rs.getBoolean(3));

                Imagem imagem = new Imagem();
                imagem.setId(rs.getLong(5));
                imagem.setNome(rs.getString(6));
                imagem.setDiretorio(rs.getString(7));
                imagem.setExcluida(rs.getBoolean(8));

                notificacao.setImagemReferencia(imagem);

                notificacoes.add(notificacao);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return notificacoes;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void save(Notificacao notificacao) throws Exception {
        try {
            String SQL = "INSERT INTO Notificacao(mensagem, lida, idImagem, idUsuario) VALUES(?, 0, ?, ?)";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, notificacao.getMensagem());
            ps.setLong(2, notificacao.getImagemReferencia().getId());
            ps.setLong(3, notificacao.getUsuarioDestino().getId());
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
    public void delete(Long idNotificacao) throws Exception {
        try {
            String SQL = "DELETE FROM Notificacao WHERE id = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idNotificacao);
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
    public void marcarComoLida(Long idNotificacao) throws Exception {
        try {
            String SQL = "UPDATE Notificacao SET lida = 1 WHERE id = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idNotificacao);
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
