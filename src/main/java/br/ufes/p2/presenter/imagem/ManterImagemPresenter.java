package br.ufes.p2.presenter.imagem;

import br.ufes.p2.business.memento.ZeladorImagemMemento;
import br.ufes.p2.business.observer.Observado;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.imagem.state.ManterImagemState;
import br.ufes.p2.presenter.imagem.state.VisualizarImagemPresenter;
import br.ufes.p2.view.ManterImagemView;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

public class ManterImagemPresenter extends Observado {

    private ManterImagemView view;
    private ManterImagemState state;

    public ManterImagemPresenter(Imagem imagem, Usuario usuario, ZeladorImagemMemento zeladorImagemMemento) {
        this.view = new ManterImagemView();
        this.state = new VisualizarImagemPresenter(this, imagem, usuario, zeladorImagemMemento);

        this.view.setVisible(true);
    }

    public ManterImagemView getView() {
        return view;
    }

    public void setImagem(File file) {
        ImageIcon ii = new ImageIcon(file.getPath());
        this.view.getLblImagem().setIcon(
                new ImageIcon(ii.getImage().getScaledInstance(
                        this.view.getLblImagem().getWidth(),
                        this.view.getLblImagem().getHeight(),
                        Image.SCALE_DEFAULT)));
    }

}
