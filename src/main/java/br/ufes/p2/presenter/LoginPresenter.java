package br.ufes.p2.presenter;

import br.ufes.p2.presenter.inicial.TelaInicialPresenter;
import br.ufes.p2.dao.sqlite.impl.LoginSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.PrivilegioSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.UsuarioSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Login;
import br.ufes.p2.presenter.usuario.ManterUsuarioPresenter;
import br.ufes.p2.service.LoginService;
import br.ufes.p2.view.LoginView;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginPresenter {

    private LoginView view;
    private LoginService loginService;

    public LoginPresenter() {
        this.view = new LoginView();
        this.loginService = new LoginService(
                new LoginSQLiteDAO(new SQLiteManager()),
                new UsuarioSQLiteDAO(new SQLiteManager()),
                new PrivilegioSQLiteDAO(new SQLiteManager())
        );

        this.view.setVisible(true);

        try {
            if (!this.loginService.existeUsuario()) {
                new ManterUsuarioPresenter();
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.view.getBtnLogin().addActionListener((e) -> {
            logar();
        });
    }

    private void logar() {
        String usuario = view.getTxtLogin().getText();
        String senha = new String(view.getTxtSenha().getPassword());

        try {
            Optional<Login> login = loginService.realizarLogin(usuario, senha);
            if (login.isPresent()) {
                System.setProperty("idUsuarioLogado", login.get().getUsuarioObj().getId().toString());
                System.setProperty("tipoUsuarioLogado", login.get().getUsuarioObj().getTipo());
                new TelaInicialPresenter(login.get().getUsuarioObj());
                view.setVisible(false);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login Inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

}
