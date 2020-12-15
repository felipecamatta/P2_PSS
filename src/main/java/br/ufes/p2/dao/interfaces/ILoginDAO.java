package br.ufes.p2.dao.interfaces;

import br.ufes.p2.model.Login;
import java.util.Optional;

public interface ILoginDAO {

    public Optional<Login> get(String usuario, String senha) throws Exception;

    public Login getByIdUsuario(Long idUsuario) throws Exception;

    public void save(Login login) throws Exception;

    public void delete(Long id) throws Exception;

    public boolean usuarioLoginExists(String usuario) throws Exception;

    public boolean existeUsuario() throws Exception;

}
