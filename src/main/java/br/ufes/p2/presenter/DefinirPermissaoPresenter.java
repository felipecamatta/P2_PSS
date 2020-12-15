package br.ufes.p2.presenter;

import br.ufes.p2.dao.sqlite.impl.PrivilegioSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Privilegio;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.service.PrivilegioService;
import br.ufes.p2.view.DefinirPermissaoView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DefinirPermissaoPresenter {

    private DefinirPermissaoView view;
    private PrivilegioService privilegioService;
    private Usuario usuario;
    private Privilegio privilegio;

    public DefinirPermissaoPresenter(Usuario usuario) {
        this.view = new DefinirPermissaoView();
        this.privilegioService = new PrivilegioService(new PrivilegioSQLiteDAO(new SQLiteManager()));
        this.usuario = usuario;
        this.privilegio = usuario.getPrivilegio();

        this.setForm();

        this.view.getBtnSalvar().addActionListener((e) -> {
            try {
                salvar();
                JOptionPane.showMessageDialog(null, "Permissão alterada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.view.dispose();
            } catch (Exception ex) {
                Logger.getLogger(DefinirPermissaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao alterar permissão", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.setVisible(true);
    }

    private void salvar() throws Exception {
        this.getPrivilegioForm();
        this.privilegioService.update(this.privilegio);
    }

    public void getPrivilegioForm() {
        this.privilegio.setVisualizarTodas(this.view.getCbVisualizar().isSelected());
        this.privilegio.setExcluirTodas(this.view.getCbExcluir().isSelected());
        this.privilegio.setCompartilharTodas(this.view.getCbCompartilhar().isSelected());
    }

    public void setForm() {
        this.view.getLblNome().setText(this.usuario.getNome());
        this.view.getCbVisualizar().setSelected(this.privilegio.isVisualizarTodas());
        this.view.getCbExcluir().setSelected(this.privilegio.isExcluirTodas());
        this.view.getCbCompartilhar().setSelected(this.privilegio.isCompartilharTodas());
    }

}
