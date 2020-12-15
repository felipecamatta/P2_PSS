package br.ufes.p2.business.proxy;

import java.io.File;

public class ImagemProxy implements Imagem {

    private ImagemReal imagemReal;
    private final String nomeAquivo;

    public ImagemProxy(String nomeAquivo) {
        this.nomeAquivo = nomeAquivo;
    }

    @Override
    public File getImagem() {
        if (imagemReal == null) {
            imagemReal = new ImagemReal(nomeAquivo);
        }
        return imagemReal.getImagem();
    }

}
