package br.ufes.p2.presenter.usuario.state;

import br.ufes.p2.model.Login;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.usuario.ManterUsuarioPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VisualizarUsuarioPresenter extends ManterUsuarioState {

    public VisualizarUsuarioPresenter(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter);
        this.usuario = usuario;

        setUsuarioForm();

        try {
            Login login = this.loginService.getByIdUsuario(this.usuario.getId());
            setLoginForm(login);
        } catch (Exception ex) {
            Logger.getLogger(VisualizarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

        disableFields();

        this.view.getBtnSalvar().setVisible(false);
        this.view.getBtnExcluir().addActionListener((e) -> {
            try {
                excluir();
                JOptionPane.showMessageDialog(null, "Usuário removido", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.presenter.notificarObservadores();
                this.view.dispose();
            } catch (Exception ex) {
                Logger.getLogger(VisualizarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao remover usuário", "Falha", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    protected void excluir() throws Exception {
        this.usuarioService.delete(this.usuario.getId());
    }

}
