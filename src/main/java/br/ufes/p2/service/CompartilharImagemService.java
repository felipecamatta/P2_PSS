package br.ufes.p2.service;

import br.ufes.p2.dao.interfaces.IPermissaoDAO;
import br.ufes.p2.dao.interfaces.INotificacaoDAO;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.model.Notificacao;
import br.ufes.p2.model.Permissao;
import br.ufes.p2.model.Usuario;

public class CompartilharImagemService {

    private INotificacaoDAO notificacaoDAO;
    private IPermissaoDAO permissaoDAO;

    public CompartilharImagemService(INotificacaoDAO notificacaoDAO, IPermissaoDAO permissaoDAO) {
        this.notificacaoDAO = notificacaoDAO;
        this.permissaoDAO = permissaoDAO;
    }

    public void compartilharImagem(Imagem imagem, Usuario usuario) throws Exception {
        Permissao permissao = permissaoDAO.get(imagem.getId(), usuario.getId()).orElse(null);
        if (permissao == null) {
            permissao = new Permissao();
            permissao.setImagem(imagem);
            permissao.setUsuario(usuario);
            permissao.setDescricao("v");
            permissaoDAO.save(permissao);
        }

        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem("A imagem " + imagem.getNome() + " foi compartilhada com você");
        notificacao.setImagemReferencia(imagem);
        notificacao.setUsuarioDestino(usuario);
        notificacaoDAO.save(notificacao);
    }

}
