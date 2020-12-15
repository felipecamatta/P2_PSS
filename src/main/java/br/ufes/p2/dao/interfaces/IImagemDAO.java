package br.ufes.p2.dao.interfaces;

import br.ufes.p2.model.Imagem;
import java.util.List;

public interface IImagemDAO {

    public List<Imagem> getAllDisponiveis() throws Exception;

    public void save(Imagem imagem) throws Exception;

    public void delete(Long id) throws Exception;

    public void recuperar(Long id) throws Exception;

}
