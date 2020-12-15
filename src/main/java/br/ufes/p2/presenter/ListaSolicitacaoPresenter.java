package br.ufes.p2.presenter;

import br.ufes.p2.dao.sqlite.impl.NotificacaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.PermissaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.SolicitacaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Solicitacao;
import br.ufes.p2.service.SolicitacaoService;
import br.ufes.p2.view.ListaSolicitacaoView;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ListaSolicitacaoPresenter {

    private ListaSolicitacaoView view;
    private SolicitacaoService solicitacaoService;
    private DefaultTableModel tmSolicitacoes;
    private List<Solicitacao> solicitacoes;

    public ListaSolicitacaoPresenter() {
        this.view = new ListaSolicitacaoView();
        this.solicitacaoService = new SolicitacaoService(
                new NotificacaoSQLiteDAO(new SQLiteManager()),
                new PermissaoSQLiteDAO(new SQLiteManager()),
                new SolicitacaoSQLiteDAO(new SQLiteManager()));
        this.tmSolicitacoes = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Usuário", "Imagem", "Descrição"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.solicitacoes = solicitacaoService.getAllPendentes();

        preencheTabela(solicitacoes.listIterator());

        this.view.getTblSolicitacoes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.view.getBtnAceitar().setEnabled(false);
        this.view.getBtnRecusar().setEnabled(false);

        this.view.getTblSolicitacoes().getSelectionModel().addListSelectionListener((e) -> {
            this.view.getBtnAceitar().setEnabled(true);
            this.view.getBtnRecusar().setEnabled(true);
        });

        this.view.getBtnRecusar().addActionListener((e) -> {
            try {
                recusar();
                JOptionPane.showMessageDialog(null, "Solicitacao recusada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarTabela();
            } catch (Exception ex) {
                Logger.getLogger(ListaSolicitacaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao recusar solicitacao", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.getBtnAceitar().addActionListener((e) -> {
            try {
                aceitar();
                JOptionPane.showMessageDialog(null, "Solicitacao aceita", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarTabela();
            } catch (Exception ex) {
                Logger.getLogger(ListaSolicitacaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao aceitar solicitacao", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.setVisible(true);
    }

    public void recusar() throws Exception {
        Solicitacao solicitacao = getSolicitacaoSelected();
        solicitacaoService.rejeitarSolicitacao(solicitacao);
    }

    public void aceitar() throws Exception {
        Solicitacao solicitacao = getSolicitacaoSelected();
        solicitacaoService.aceitarSolicitacao(solicitacao);
    }

    private Solicitacao getSolicitacaoSelected() {
        int posicao = view.getTblSolicitacoes().getSelectedRow();
        return solicitacoes.get(posicao);
    }

    private void preencheTabela(ListIterator<Solicitacao> notificacoes) {
        tmSolicitacoes.setNumRows(0);

        while (notificacoes.hasNext()) {
            Solicitacao solicitacao = notificacoes.next();
            tmSolicitacoes.addRow(new Object[]{solicitacao.getUsuario().getNome(), solicitacao.getImagem().getNome(), solicitacao.getDescricao()});
        }

        this.view.getTblSolicitacoes().setModel(tmSolicitacoes);
    }

    private void atualizarTabela() {
        solicitacoes = solicitacaoService.getAllPendentes();
        preencheTabela(solicitacoes.listIterator());
    }

}
