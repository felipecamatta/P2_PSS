package br.ufes.p2.business;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class UtilitarioImagem {

    private static UtilitarioImagem instancia = null;

    private UtilitarioImagem() {
    }

    public static UtilitarioImagem getInstancia() {
        if (instancia == null) {
            instancia = new UtilitarioImagem();
        }
        return instancia;
    }

    public ImageIcon gerarMiniatura(File image, int new_w, int new_h) throws IOException {
        try {
            BufferedImage imagem = ImageIO.read(image);
            BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, new_w, new_h, null);
            g.dispose();

            return new ImageIcon(new_img);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
