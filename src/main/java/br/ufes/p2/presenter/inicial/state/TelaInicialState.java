package br.ufes.p2.presenter.inicial.state;

import br.ufes.p2.presenter.inicial.TelaInicialPresenter;
import br.ufes.p2.view.TelaInicialView;

public abstract class TelaInicialState {

    protected TelaInicialPresenter presenter;
    protected TelaInicialView view;

    public TelaInicialState(TelaInicialPresenter presenter) {
        this.presenter = presenter;
        this.view = presenter.getView();
    }

}
