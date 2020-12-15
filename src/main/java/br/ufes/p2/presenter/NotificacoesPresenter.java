package br.ufes.p2.presenter;

import br.ufes.p2.dao.sqlite.impl.NotificacaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Notificacao;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.imagem.ManterImagemPresenter;
import br.ufes.p2.service.NotificacaoService;
import br.ufes.p2.view.NotificacoesView;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class NotificacoesPresenter {

    private NotificacoesView view;
    private NotificacaoService notificacaoService;
    private DefaultTableModel tmNotificacoes;
    private List<Notificacao> notificacoes;
    private Usuario usuario;

    public NotificacoesPresenter(Usuario usuario) throws Exception {
        this.view = new NotificacoesView();
        this.notificacaoService = new NotificacaoService(new NotificacaoSQLiteDAO(new SQLiteManager()));
        this.tmNotificacoes = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mensagem"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.notificacoes = notificacaoService.getByIdUsuario(usuario.getId());
        this.usuario = usuario;

        preencheTabela(notificacoes.listIterator());

        this.view.getTblNotificacoes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.view.getBtnLida().addActionListener((e) -> {
            try {
                marcarComoLida();
            } catch (Exception ex) {
                Logger.getLogger(NotificacoesPresenter.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao marcar como lida", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.getBtnAcessar().addActionListener((e) -> {
            Notificacao notificacao = notificacoes.get(this.view.getTblNotificacoes().getSelectedRow());
            if (!notificacao.getImagemReferencia().isExcluida()) {
                new ManterImagemPresenter(notificacao.getImagemReferencia(), usuario, null);
            } else {
                JOptionPane.showMessageDialog(null, "Essa imagem não está mais acessível", "Erro", JOptionPane.ERROR_MESSAGE);
            }            
        });

        this.view.setVisible(true);
    }

    private void marcarComoLida() throws Exception {
        Notificacao notificacao = notificacoes.get(this.view.getTblNotificacoes().getSelectedRow());
        notificacaoService.marcarComoLida(notificacao.getId());
        notificacoes = notificacaoService.getByIdUsuario(usuario.getId());
        preencheTabela(notificacoes.listIterator());
    }

    private void preencheTabela(ListIterator<Notificacao> notificacoes) {
        tmNotificacoes.setNumRows(0);

        while (notificacoes.hasNext()) {
            Notificacao notificacao = notificacoes.next();
            tmNotificacoes.addRow(new Object[]{notificacao.getMensagem()});
        }

        this.view.getTblNotificacoes().setModel(tmNotificacoes);
    }

}
