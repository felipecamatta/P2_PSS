package br.ufes.p2.presenter.usuario;

import br.ufes.p2.business.observer.Observado;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.usuario.state.CadastrarUsuarioPresenter;
import br.ufes.p2.presenter.usuario.state.ManterUsuarioState;
import br.ufes.p2.presenter.usuario.state.VisualizarUsuarioPresenter;
import br.ufes.p2.view.ManterUsuarioView;

public class ManterUsuarioPresenter extends Observado {

    private ManterUsuarioView view;
    private ManterUsuarioState state;

    public ManterUsuarioPresenter() {
        this.view = new ManterUsuarioView();
        this.state = new CadastrarUsuarioPresenter(this, null);

        this.view.setVisible(true);
    }

    public ManterUsuarioPresenter(Usuario usuario) {
        this.view = new ManterUsuarioView();
        this.state = new VisualizarUsuarioPresenter(this, usuario);

        this.view.setVisible(true);
    }

    public ManterUsuarioView getView() {
        return view;
    }

}
