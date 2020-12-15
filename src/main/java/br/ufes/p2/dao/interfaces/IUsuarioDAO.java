package br.ufes.p2.dao.interfaces;

import br.ufes.p2.model.Usuario;
import java.util.List;

public interface IUsuarioDAO {

    public List<Usuario> getAll() throws Exception;

    public Usuario get(Long id) throws Exception;

    public void save(Usuario usuario) throws Exception;

    public void update(Usuario usuario) throws Exception;

    public void delete(Long id) throws Exception;

    public Long getMaxId() throws Exception;

}
