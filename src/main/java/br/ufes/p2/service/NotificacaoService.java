package br.ufes.p2.service;

import br.ufes.p2.dao.interfaces.INotificacaoDAO;
import br.ufes.p2.model.Notificacao;
import java.util.List;

public class NotificacaoService {

    private INotificacaoDAO notificacaoDAO;

    public NotificacaoService(INotificacaoDAO notificacaoDAO) {
        this.notificacaoDAO = notificacaoDAO;
    }

    public List<Notificacao> getByIdUsuario(Long idUsuario) throws Exception {
        return notificacaoDAO.getByIdUsuario(idUsuario);
    }

    public void delete(Long id) throws Exception {
        notificacaoDAO.delete(id);
    }

    public void marcarComoLida(Long id) throws Exception {
        notificacaoDAO.marcarComoLida(id);
    }

}
