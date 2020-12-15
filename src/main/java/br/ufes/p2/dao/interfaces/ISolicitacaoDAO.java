package br.ufes.p2.dao.interfaces;

import br.ufes.p2.model.Solicitacao;
import java.util.List;

public interface ISolicitacaoDAO {

    public List<Solicitacao> getAllPendentes() throws Exception;

    public void save(Solicitacao solicitacao) throws Exception;

    public void update(Solicitacao solicitacao) throws Exception;

}
