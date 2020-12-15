package br.ufes.p2.dao.sqlite.impl;

import br.ufes.p2.dao.interfaces.IImagemDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Imagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImagemSQLiteDAO implements IImagemDAO {

    private SQLiteManager manager;

    public ImagemSQLiteDAO(SQLiteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public List<Imagem> getAllDisponiveis() throws Exception {
        try {
            String SQL = "SELECT i.id, i.nome, i.diretorio, i.excluida FROM Imagem i WHERE i.excluida = false";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            List<Imagem> imagens = new ArrayList<>();
            while (rs.next()) {
                Imagem imagem = new Imagem();
                imagem.setId(rs.getLong(1));
                imagem.setNome(rs.getString(2));
                imagem.setDiretorio(rs.getString(3));
                imagem.setExcluida(rs.getBoolean(4));
                imagens.add(imagem);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return imagens;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void save(Imagem imagem) throws Exception {
        try {
            String SQL = "INSERT INTO Imagem(nome, diretorio, excluida) VALUES(?, ?, ?);";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, imagem.getNome());
            ps.setString(2, imagem.getDiretorio());
            ps.setBoolean(3, imagem.isExcluida());
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
            String SQL = "UPDATE Imagem SET excluida = true WHERE id = ?";

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
            throw new Exception("Erro ao excluir");
        }
    }

    @Override
    public void recuperar(Long id) throws Exception {
        try {
            String SQL = "UPDATE Imagem SET excluida = false WHERE id = ?";

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
            throw new Exception("Erro ao recuperar");
        }
    }

}
