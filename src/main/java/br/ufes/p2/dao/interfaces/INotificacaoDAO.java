package br.ufes.p2.dao.interfaces;

import br.ufes.p2.model.Notificacao;
import java.util.List;

public interface INotificacaoDAO {

    public List<Notificacao> getByIdUsuario(Long idUsuario) throws Exception;

    public void save(Notificacao notificacao) throws Exception;

    public void delete(Long idNotificacao) throws Exception;

    public void marcarComoLida(Long idNotificacao) throws Exception;

}
