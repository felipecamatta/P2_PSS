package br.ufes.p2.presenter.inicial.state;

import br.ufes.p2.presenter.inicial.TelaInicialPresenter;

public class TelaInicialUsuarioPresenter extends TelaInicialState {

    public TelaInicialUsuarioPresenter(TelaInicialPresenter presenter) {
        super(presenter);

        this.view.getMenuSolicitacoes().setVisible(false);
        this.view.getMenuUsuarios().setVisible(false);
    }

}
