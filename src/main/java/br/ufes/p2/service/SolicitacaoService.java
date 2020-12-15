package br.ufes.p2.service;

import br.ufes.p2.dao.interfaces.INotificacaoDAO;
import br.ufes.p2.dao.interfaces.IPermissaoDAO;
import br.ufes.p2.dao.interfaces.ISolicitacaoDAO;
import br.ufes.p2.model.Notificacao;
import br.ufes.p2.model.Permissao;
import br.ufes.p2.model.Solicitacao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolicitacaoService {

    private INotificacaoDAO notificacaoDAO;
    private IPermissaoDAO permissaoDAO;
    private ISolicitacaoDAO solicitacaoDAO;

    public SolicitacaoService(INotificacaoDAO notificacaoDAO, IPermissaoDAO permissaoDAO, ISolicitacaoDAO solicitacaoDAO) {
        this.notificacaoDAO = notificacaoDAO;
        this.permissaoDAO = permissaoDAO;
        this.solicitacaoDAO = solicitacaoDAO;
    }

    public List<Solicitacao> getAllPendentes() {
        try {
            return solicitacaoDAO.getAllPendentes();
        } catch (Exception ex) {
            Logger.getLogger(SolicitacaoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void save(Solicitacao solicitacao) throws Exception {
        solicitacaoDAO.save(solicitacao);
    }

    public void aceitarSolicitacao(Solicitacao solicitacao) throws Exception {
        Permissao permissao = permissaoDAO.get(solicitacao.getImagem().getId(), solicitacao.getUsuario().getId()).orElse(null);
        if (permissao == null) {
            permissao = new Permissao();
            permissao.setImagem(solicitacao.getImagem());
            permissao.setUsuario(solicitacao.getUsuario());
            permissao.setDescricao("v");
            permissaoDAO.save(permissao);
        } else {
            permissao.setDescricao(setDescricaoPermissao(permissao.getDescricao(), solicitacao.getDescricao()));
            permissaoDAO.update(permissao);
        }

        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem("Sua solicitação de " + solicitacao.getDescricao() + " à imagem " + solicitacao.getImagem().getNome() + " foi aprovada");
        notificacao.setUsuarioDestino(solicitacao.getUsuario());
        notificacao.setImagemReferencia(solicitacao.getImagem());
        notificacaoDAO.save(notificacao);

        solicitacao.setAceita(true);
        solicitacaoDAO.update(solicitacao);
    }

    public void rejeitarSolicitacao(Solicitacao solicitacao) throws Exception {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem("Sua solicitação de " + solicitacao.getDescricao() + " à imagem " + solicitacao.getImagem().getNome() + " foi negada");
        notificacao.setUsuarioDestino(solicitacao.getUsuario());
        notificacao.setImagemReferencia(solicitacao.getImagem());
        notificacaoDAO.save(notificacao);

        solicitacao.setAceita(false);
        solicitacaoDAO.update(solicitacao);
    }

    private String setDescricaoPermissao(String descricaoPermissao, String descricaoSolicitacao) {
        if (descricaoSolicitacao.equals("Visualizar")) {
            return "v";
        } else if (descricaoSolicitacao.equals("Compartilhar")) {
            if (descricaoPermissao.equals("v")) {
                return "vc";
            }
            return "vce";
        } else {
            if (descricaoPermissao.equals("v")) {
                return "ve";
            } else {
                return "vce";
            }
        }
    }

}
