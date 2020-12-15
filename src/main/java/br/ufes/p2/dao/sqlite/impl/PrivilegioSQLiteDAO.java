package br.ufes.p2.dao.sqlite.impl;

import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import br.ufes.p2.dao.interfaces.IPrivilegioDAO;
import br.ufes.p2.model.Privilegio;

public class PrivilegioSQLiteDAO implements IPrivilegioDAO {

    private SQLiteManager manager;

    public PrivilegioSQLiteDAO(SQLiteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public void save(Privilegio privilegio) throws Exception {
        try {
            String SQL = "INSERT INTO Privilegio(idUsuario, visualizarTodas, excluirTodas, compartilharTodas) VALUES(?, ?, ?, ?)";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, privilegio.getIdUsuario());
            ps.setBoolean(2, privilegio.isVisualizarTodas());
            ps.setBoolean(3, privilegio.isExcluirTodas());
            ps.setBoolean(4, privilegio.isCompartilharTodas());
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
    public void update(Privilegio privilegio) throws Exception {
        try {
            String SQL = "UPDATE Privilegio SET visualizarTodas = ?, excluirTodas = ?, compartilharTodas = ? "
                    + "WHERE idUsuario = ?";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setBoolean(1, privilegio.isVisualizarTodas());
            ps.setBoolean(2, privilegio.isExcluirTodas());
            ps.setBoolean(3, privilegio.isCompartilharTodas());
            ps.setLong(4, privilegio.getIdUsuario());
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
