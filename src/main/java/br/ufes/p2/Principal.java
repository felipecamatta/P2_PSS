package br.ufes.p2;

import br.ufes.p2.business.VerificacaoImagens;
import br.ufes.p2.presenter.LoginPresenter;

public class Principal {

    public static void main(String[] args) {
        new VerificacaoImagens();

        new LoginPresenter();
    }

}
