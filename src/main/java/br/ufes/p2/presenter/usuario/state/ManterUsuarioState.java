package br.ufes.p2.presenter.usuario.state;

import br.ufes.p2.dao.sqlite.impl.LoginSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.PrivilegioSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.UsuarioSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Login;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.usuario.ManterUsuarioPresenter;
import br.ufes.p2.service.LoginService;
import br.ufes.p2.service.UsuarioService;
import br.ufes.p2.view.ManterUsuarioView;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public abstract class ManterUsuarioState {

    protected ManterUsuarioPresenter presenter;
    protected ManterUsuarioView view;
    protected Usuario usuario;
    protected UsuarioService usuarioService;
    protected LoginService loginService;

    public ManterUsuarioState(ManterUsuarioPresenter presenter) {
        this.presenter = presenter;
        this.view = presenter.getView();
        this.usuario = new Usuario();
        this.usuarioService = new UsuarioService(new UsuarioSQLiteDAO(new SQLiteManager()));
        this.loginService = new LoginService(
                new LoginSQLiteDAO(new SQLiteManager()),
                new UsuarioSQLiteDAO(new SQLiteManager()),
                new PrivilegioSQLiteDAO(new SQLiteManager())
        );

        removeActionListeners(this.view.getBtnExcluir());
        removeActionListeners(this.view.getBtnSalvar());
    }

    protected void salvar() throws Exception {

    }

    protected void excluir() throws Exception {

    }

    protected Usuario getUsuarioForm() {
        this.usuario.setNome(this.view.getTxtNome().getText());
        if (this.view.getRbAdministrador().isSelected()) {
            this.usuario.setTipo("Administrador");
        } else {
            this.usuario.setTipo("Usuario");
        }
        return this.usuario;
    }

    protected Login getLoginForm() {
        Login login = new Login();
        login.setUsuario(this.view.getTxtUsuario().getText());
        login.setSenha(new String(this.view.getTxtSenha().getPassword()));
        return login;
    }

    protected void setUsuarioForm() {
        this.view.getTxtNome().setText(this.usuario.getNome());
        if (this.usuario.getTipo().equals("Administrador")) {
            this.view.getBgTipo().setSelected(this.view.getRbAdministrador().getModel(), true);
        } else if (this.usuario.getTipo().equals("Usuario")) {
            this.view.getBgTipo().setSelected(this.view.getRbUsuario().getModel(), true);
        }
    }

    protected void setLoginForm(Login login) {
        this.view.getTxtUsuario().setText(login.getUsuario());
        this.view.getTxtSenha().setText(login.getSenha());
    }

    protected void disableFields() {
        this.view.getTxtNome().setEditable(false);
        this.view.getRbAdministrador().setEnabled(false);
        this.view.getRbUsuario().setEnabled(false);
        this.view.getTxtUsuario().setEditable(false);
        this.view.getTxtSenha().setEditable(false);
    }

    protected void enableFields() {
        this.view.getTxtNome().setEditable(true);
        this.view.getRbAdministrador().setEnabled(true);
        this.view.getRbUsuario().setEnabled(true);
        this.view.getTxtUsuario().setEditable(true);
        this.view.getTxtSenha().setEditable(true);
    }

    private void removeActionListeners(JButton button) {
        for (ActionListener action : button.getActionListeners()) {
            button.removeActionListener(action);
        }
    }

}
