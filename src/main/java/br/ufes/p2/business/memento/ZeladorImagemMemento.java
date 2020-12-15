package br.ufes.p2.business.memento;

import java.util.ArrayList;
import java.util.List;

public class ZeladorImagemMemento {

    List<ImagemMemento> historico;

    public ZeladorImagemMemento() {
        this.historico = new ArrayList<>();
    }

    public ImagemMemento getUltimo() {
        if (historico.isEmpty()) {
            return null;
        }

        return historico.remove(historico.size() - 1);
    }

    public void add(ImagemMemento imagemMemento) {
        historico.add(imagemMemento);
    }

}
