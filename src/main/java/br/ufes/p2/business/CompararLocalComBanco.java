package br.ufes.p2.business;

import br.ufes.p2.model.Imagem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CompararLocalComBanco {

    private List<Imagem> imagensRemovidas;
    private List<File> arquivosNovos;

    public CompararLocalComBanco(List<Imagem> imagens, List<File> arquivosImagem) {
        imagensRemovidas = new ArrayList(imagens);
        arquivosNovos = new ArrayList(arquivosImagem);

        for (File arquivo : arquivosImagem) {
            for (Imagem imagem : imagens) {
                if (arquivo.getName().equals(imagem.getNome())) {
                    imagensRemovidas.remove(imagem);
                    arquivosNovos.remove(arquivo);
                    break;
                }
            }
        }
    }

    public List<Imagem> getImagensRemovidas() {
        return imagensRemovidas;
    }

    public List<File> getArquivosNovos() {
        return arquivosNovos;
    }

}
