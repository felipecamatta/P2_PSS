package br.ufes.p2.service;

import br.ufes.p2.dao.interfaces.IUsuarioDAO;
import br.ufes.p2.model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioService {

    private IUsuarioDAO usuarioDAO;

    public UsuarioService(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public List<Usuario> getAll() {
        try {
            return usuarioDAO.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Usuario get(Long id) throws Exception {
        return usuarioDAO.get(id);
    }

    public void save(Usuario usuario) throws Exception {
        usuarioDAO.save(usuario);
    }

    public void update(Usuario usuario) throws Exception {
        usuarioDAO.update(usuario);
    }

    public void delete(Long id) throws Exception {
        usuarioDAO.delete(id);
    }

}
