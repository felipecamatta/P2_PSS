package br.ufes.p2.business.proxy;

import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;

public class ImagemProxy2 implements Imagem2 {

    private ImagemReal2 imagemReal;
    private final String nomePasta;

    public ImagemProxy2(String nomePasta) {
        this.nomePasta = nomePasta;
    }

    @Override
    public List<ImageIcon> getImagens() {
        if (imagemReal == null) {
            imagemReal = new ImagemReal2(nomePasta);
        }
        return imagemReal.getImagens();
    }

}
