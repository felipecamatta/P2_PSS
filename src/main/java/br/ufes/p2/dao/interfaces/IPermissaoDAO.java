package br.ufes.p2.dao.interfaces;

import br.ufes.p2.model.Permissao;
import java.util.List;
import java.util.Optional;

public interface IPermissaoDAO {

    public List<Permissao> getByIdUsuario(Long idUsuario) throws Exception;

    public Optional<Permissao> get(Long idImagem, Long idUsuario) throws Exception;

    public void save(Permissao permissao) throws Exception;

    public void update(Permissao permissao) throws Exception;

    public void delete(Long idImagem, Long idUsuario) throws Exception;

}
