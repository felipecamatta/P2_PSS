package br.ufes.p2.presenter.imagem.state;

import br.ufes.p2.business.memento.ZeladorImagemMemento;
import br.ufes.p2.dao.sqlite.impl.NotificacaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.PermissaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.SolicitacaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.model.Permissao;
import br.ufes.p2.model.Solicitacao;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.CompartilharImagemPresenter;
import br.ufes.p2.presenter.imagem.ManterImagemPresenter;
import br.ufes.p2.presenter.inicial.TelaInicialPresenter;
import br.ufes.p2.service.PermissaoService;
import br.ufes.p2.service.SolicitacaoService;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VisualizarImagemPresenter extends ManterImagemState {

    private Usuario usuario;
    private PermissaoService permissaoService;
    private SolicitacaoService solicitacaoService;
    private ZeladorImagemMemento zeladorImagemMemento;

    public VisualizarImagemPresenter(ManterImagemPresenter presenter, Imagem imagem, Usuario usuario, ZeladorImagemMemento zeladorImagemMemento) {
        super(presenter, imagem);
        this.imagem = imagem;
        this.usuario = usuario;
        this.permissaoService = new PermissaoService(new PermissaoSQLiteDAO(new SQLiteManager()));
        this.solicitacaoService = new SolicitacaoService(
                new NotificacaoSQLiteDAO(new SQLiteManager()),
                new PermissaoSQLiteDAO(new SQLiteManager()),
                new SolicitacaoSQLiteDAO(new SQLiteManager()));
        this.zeladorImagemMemento = zeladorImagemMemento;

        setImagemForm();

        disableFields();

        this.view.getBtnEscolherImagem().setVisible(false);

        this.view.getBtnExcluir().setVisible(true);
        this.view.getBtnSalvar().setVisible(false);

        this.view.getBtnExcluir().addActionListener((e) -> {
            try {
                if (usuario.getPrivilegio().isExcluirTodas() || usuario.getTipo().equals("Administrador") || possuiPermissao(imagem, usuario, "e")) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja excluir a imagem?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == 0) {
                        try {
                            delete();
                            this.zeladorImagemMemento.add(imagem.criar());
                            JOptionPane.showMessageDialog(view, "Imagem excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            this.presenter.notificarObservadores();
                            this.view.dispose();
                        } catch (Exception ex) {
                            Logger.getLogger(VisualizarImagemPresenter.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(view, "Falha ao excluir imagem", "Falha", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Você não tem permissão para excluir essa imagem. Gostaria de solicitar a permissão?", "Permissão negada", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == 0) {
                        try {
                            solicitarAcesso(imagem, usuario, "Excluir");
                            JOptionPane.showMessageDialog(null, "Acesso solicitado", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            Logger.getLogger(TelaInicialPresenter.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Falha ao solicitar acesso", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(VisualizarImagemPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.view.getBtnCompartilhar().addActionListener((e) -> {
            try {
                if (usuario.getPrivilegio().isCompartilharTodas() || usuario.getTipo().equals("Administrador") || possuiPermissao(imagem, usuario, "c")) {
                    new CompartilharImagemPresenter(imagem);
                } else {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Você não tem permissão para compartilhar essa imagem. Gostaria de solicitar a permissão?", "Permissão negada", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == 0) {
                        try {
                            solicitarAcesso(imagem, usuario, "Compartilhar");
                            JOptionPane.showMessageDialog(null, "Acesso solicitado", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            Logger.getLogger(TelaInicialPresenter.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Falha ao solicitar acesso", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(TelaInicialPresenter.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao buscar permissão", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void solicitarAcesso(Imagem imagem, Usuario usuario, String descricao) throws Exception {
        Solicitacao solicitacao = new Solicitacao(imagem, usuario, descricao);
        solicitacaoService.save(solicitacao);
    }

    private boolean possuiPermissao(Imagem imagem, Usuario usuario, String descricao) throws Exception {
        Optional<Permissao> permissao = permissaoService.get(imagem.getId(), usuario.getId());
        if (permissao.isPresent()) {
            return permissao.get().getDescricao().contains(descricao);
        } else {
            return false;
        }
    }

    private void delete() throws Exception {
        this.imagemService.delete(imagem.getId());
    }

}
