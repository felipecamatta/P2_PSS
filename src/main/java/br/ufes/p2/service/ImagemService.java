package br.ufes.p2.service;

import br.ufes.p2.dao.interfaces.IImagemDAO;
import br.ufes.p2.dao.interfaces.IPermissaoDAO;
import br.ufes.p2.model.Imagem;
import java.util.List;

public class ImagemService {

    private IImagemDAO imagemDAO;

    public ImagemService(IImagemDAO imagemDAO) {
        this.imagemDAO = imagemDAO;
    }

    public List<Imagem> getAllDisponiveis() throws Exception {
        return imagemDAO.getAllDisponiveis();
    }

    public void save(Imagem imagem) throws Exception {
        imagem.setExcluida(false);
        imagemDAO.save(imagem);
    }

    public void delete(Long id) throws Exception {
        imagemDAO.delete(id);
    }

    public void recuperar(Long id) throws Exception {
        imagemDAO.recuperar(id);
    }

}
