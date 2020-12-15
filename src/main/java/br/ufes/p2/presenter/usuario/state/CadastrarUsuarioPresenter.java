package br.ufes.p2.presenter.usuario.state;

import br.ufes.p2.model.Login;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.usuario.ManterUsuarioPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CadastrarUsuarioPresenter extends ManterUsuarioState {

    public CadastrarUsuarioPresenter(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter);

        this.usuario = new Usuario();

        enableFields();

        this.view.getBtnExcluir().setVisible(false);
        this.view.getBtnSalvar().addActionListener((e) -> {
            try {
                verificarUsuarioExiste();
                try {
                    salvar();
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    this.presenter.notificarObservadores();
                    this.view.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(CadastrarUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Falha ao salvar usuário", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Esse usuário não é permitido", "Usuário já existe", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.setVisible(true);
    }

    private void verificarUsuarioExiste() throws Exception {
        Login login = getLoginForm();
        if (this.loginService.usuarioLoginExists(login.getUsuario())) {
            throw new Exception();
        }
    }

    protected void salvar() throws Exception {
        Usuario usuario = getUsuarioForm();
        Login login = getLoginForm();
        this.loginService.save(login, usuario);
    }

}
