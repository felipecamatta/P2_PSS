package br.ufes.p2.business;

import br.ufes.p2.dao.sqlite.impl.ImagemSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.service.ImagemService;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerificacaoImagens {

    private ImagemService imagemService;

    public VerificacaoImagens() {
        this.imagemService = new ImagemService(new ImagemSQLiteDAO(new SQLiteManager()));

        List<Imagem> imagens = new ArrayList<>();
        try {
            imagens = imagemService.getAllDisponiveis();
        } catch (Exception ex) {
            Logger.getLogger(VerificacaoImagens.class.getName()).log(Level.SEVERE, null, ex);
        }

        CompararLocalComBanco comparacao = new CompararLocalComBanco(imagens, carregarDoDisco("C:\\Users\\Felipe\\Documents\\NetBeansProjects\\p2\\src\\main\\java\\br\\ufes\\p2\\imagens"));

        comparacao.getArquivosNovos().forEach(f -> {
            Imagem imagem = new Imagem();
            imagem.setNome(f.getName());
            imagem.setDiretorio(f.getPath());
            try {
                imagemService.save(imagem);
            } catch (Exception ex) {
                Logger.getLogger(VerificacaoImagens.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        comparacao.getImagensRemovidas().forEach(i -> {
            try {
                imagemService.delete(i.getId());
            } catch (Exception ex) {
                Logger.getLogger(VerificacaoImagens.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private List<File> carregarDoDisco(String nomePasta) {
        File pasta = new File(nomePasta);

        List<File> arquivos = Arrays.asList(pasta.listFiles());

        for (int i = 0; i < arquivos.size(); i++) {
            String name = arquivos.get(i).toString();
            if (!(name.endsWith("png") || name.endsWith("jpg") || name.endsWith("jpeg"))) {
                arquivos.remove(i);
            }
        }

        return arquivos;
    }

}
