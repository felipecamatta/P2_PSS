package br.ufes.p2.service;

import br.ufes.p2.dao.interfaces.ILoginDAO;
import br.ufes.p2.dao.interfaces.IPrivilegioDAO;
import br.ufes.p2.dao.interfaces.IUsuarioDAO;
import br.ufes.p2.model.Login;
import br.ufes.p2.model.Privilegio;
import br.ufes.p2.model.Usuario;
import java.util.Optional;

public class LoginService {

    private ILoginDAO loginDAO;
    private IUsuarioDAO usuarioDAO;
    private IPrivilegioDAO privilegioDAO;

    public LoginService(ILoginDAO loginDAO, IUsuarioDAO usuarioDAO, IPrivilegioDAO privilegioDAO) {
        this.loginDAO = loginDAO;
        this.usuarioDAO = usuarioDAO;
        this.privilegioDAO = privilegioDAO;
    }

    public boolean existeUsuario() throws Exception {
        return loginDAO.existeUsuario();
    }

    public boolean usuarioLoginExists(String usuario) throws Exception {
        return loginDAO.usuarioLoginExists(usuario);
    }

    public Optional<Login> realizarLogin(String usuario, String senha) throws Exception {
        return loginDAO.get(usuario, senha);
    }

    public Login getByIdUsuario(Long idUsuario) throws Exception {
        return loginDAO.getByIdUsuario(idUsuario);
    }

    public void save(Login login, Usuario usuario) throws Exception {
        usuarioDAO.save(usuario);

        login.setUsuarioObj(usuario);

        Long id = usuarioDAO.getMaxId();
        login.getUsuarioObj().setId(id);

        loginDAO.save(login);

        Privilegio privilegio = new Privilegio(id, false, false, false);
        privilegioDAO.save(privilegio);
    }

}
