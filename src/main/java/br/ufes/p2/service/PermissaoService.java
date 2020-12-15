package br.ufes.p2.service;

import br.ufes.p2.dao.interfaces.IPermissaoDAO;
import br.ufes.p2.model.Permissao;
import java.util.List;
import java.util.Optional;

public class PermissaoService {

    private IPermissaoDAO permissaoDAO;

    public PermissaoService(IPermissaoDAO permissaoDAO) {
        this.permissaoDAO = permissaoDAO;
    }

    public List<Permissao> getByIdUsuario(Long idUsuario) throws Exception {
        return permissaoDAO.getByIdUsuario(idUsuario);
    }

    public Optional<Permissao> get(Long idImagem, Long idUsuario) throws Exception {
        return permissaoDAO.get(idImagem, idUsuario);
    }

    public void save(Permissao permissao) throws Exception {
        Permissao permissaoP = permissaoDAO.get(permissao.getImagem().getId(), permissao.getUsuario().getId()).orElse(null);
        if (permissaoP != null) {
            if (permissao.getDescricao().equals("c")) {
                if (permissao.getDescricao().equals("v")) {
                    permissaoP.setDescricao("vc");
                } else if (permissao.getDescricao().equals("ve")) {
                    permissaoP.setDescricao("vce");
                }
            } else if (permissao.getDescricao().equals("e")) {
                if (permissao.getDescricao().equals("v")) {
                    permissaoP.setDescricao("ve");
                } else if (permissao.getDescricao().equals("vc")) {
                    permissaoP.setDescricao("vce");
                }
            }
        }
        permissaoDAO.save(permissao);
    }

    public void delete(Permissao permissao) throws Exception {
        permissaoDAO.delete(permissao.getImagem().getId(), permissao.getUsuario().getId());
    }

}
