package br.ufes.p2.business.proxy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagemReal2 implements Imagem2 {

    private String nomePasta;
    private List<ImageIcon> imagens;

    public ImagemReal2(String nomePasta) {
        this.nomePasta = nomePasta;
        this.imagens = new ArrayList<>();
        carregarDoDisco(nomePasta);
    }

    @Override
    public List<ImageIcon> getImagens() {
        return this.imagens;
    }

    private void carregarDoDisco(String nomePasta) {
        File pasta = new File(nomePasta);

        File[] arquivos = pasta.listFiles();

        for (int i = 0; i < arquivos.length; i++) {
            String name = arquivos[i].toString();
            if (name.endsWith("png") || name.endsWith("jpg") || name.endsWith("jpeg")) {
                try {
                    ImageIcon ii = new ImageIcon(ImageIO.read(arquivos[i]));
                    this.imagens.add(ii);
                } catch (IOException ex) {
                    Logger.getLogger(ImagemReal2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
