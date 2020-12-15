package br.ufes.p2.business.proxy;

import java.io.File;

public class ImagemReal implements Imagem {

    private String nomeArquivo;
    private File imagemArquivo;

    public ImagemReal(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        carregarDoDisco(nomeArquivo);
    }

    @Override
    public File getImagem() {
        return this.imagemArquivo;
    }

    private void carregarDoDisco(String nomeArquivo) {
        this.imagemArquivo = new File(nomeArquivo);
    }

}
