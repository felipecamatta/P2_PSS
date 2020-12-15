package br.ufes.p2.presenter.imagem.state;

import br.ufes.p2.dao.sqlite.impl.ImagemSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.presenter.imagem.ManterImagemPresenter;
import br.ufes.p2.service.ImagemService;
import br.ufes.p2.view.ManterImagemView;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;

public abstract class ManterImagemState {

    protected ManterImagemPresenter presenter;
    protected ManterImagemView view;
    protected Imagem imagem;
    protected ImagemService imagemService;

    public ManterImagemState(ManterImagemPresenter presenter, Imagem imagem) {
        this.presenter = presenter;
        this.view = presenter.getView();
        this.imagem = imagem;
        this.imagemService = new ImagemService(new ImagemSQLiteDAO(new SQLiteManager()));

        removeActionListeners(this.view.getBtnExcluir());
        removeActionListeners(this.view.getBtnSalvar());
    }

    protected Imagem getImagemForm() {
        this.imagem.setNome(this.view.getTxtNome().getText().toString());
        this.imagem.setDiretorio(this.view.getTxtDiretorio().getText().toString());
        return this.imagem;
    }

    protected void setImagemForm() {
        this.view.getTxtNome().setText(this.imagem.getNome());
        this.view.getTxtDiretorio().setText(this.imagem.getDiretorio());

        File file = new File(this.imagem.getDiretorio());

        this.presenter.setImagem(file);
    }

    protected void disableFields() {
        this.view.getBtnEscolherImagem().setEnabled(false);
    }

    protected void enableFields() {
        this.view.getBtnEscolherImagem().setEnabled(true);
    }

    private void removeActionListeners(JButton button) {
        for (ActionListener action : button.getActionListeners()) {
            button.removeActionListener(action);
        }
    }

}
