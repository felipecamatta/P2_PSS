package br.ufes.p2.presenter;

import br.ufes.p2.dao.sqlite.impl.NotificacaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.PermissaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.UsuarioSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.service.CompartilharImagemService;
import br.ufes.p2.service.UsuarioService;
import br.ufes.p2.view.CompartilharImagemView;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CompartilharImagemPresenter {

    private CompartilharImagemView view;
    private CompartilharImagemService compartilharImagemService;
    private UsuarioService usuarioService;
    private DefaultTableModel tmUsuarios;
    private List<Usuario> usuarios;
    private Imagem imagem;

    public CompartilharImagemPresenter(Imagem imagem) {
        this.view = new CompartilharImagemView();
        this.compartilharImagemService = new CompartilharImagemService(
                new NotificacaoSQLiteDAO(new SQLiteManager()),
                new PermissaoSQLiteDAO(new SQLiteManager()));
        this.usuarioService = new UsuarioService(new UsuarioSQLiteDAO(new SQLiteManager()));
        this.tmUsuarios = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.usuarios = usuarioService.getAll();
        this.imagem = imagem;

        preencheTabela(usuarios.listIterator());

        //this.view.getTblUsuarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.view.getBtnConfirmar().setEnabled(false);

        this.view.getTblUsuarios().getSelectionModel().addListSelectionListener((e) -> {
            this.view.getBtnConfirmar().setEnabled(true);
        });

        this.view.getBtnConfirmar().addActionListener((e) -> {
            try {
                compartilharParaTodos();
                JOptionPane.showMessageDialog(null, "Imagem compartilhada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.view.dispose();
            } catch (Exception ex) {
                Logger.getLogger(ListaSolicitacaoPresenter.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao compartilhar imagem", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.setVisible(true);
    }

    private void compartilhar() throws Exception {
        compartilharImagemService.compartilharImagem(imagem, getUsuarioSelected());
    }

    private void compartilharParaTodos() throws Exception {
        List<Usuario> usuariosSelecionados = getUsuariosSelecionados();
        for (Usuario usuario : usuariosSelecionados) {
            compartilharImagemService.compartilharImagem(this.imagem, usuario);
        }
    }

    private Usuario getUsuarioSelected() {
        int posicao = view.getTblUsuarios().getSelectedRow();
        return usuarios.get(posicao);
    }

    private List<Usuario> getUsuariosSelecionados() {
        int posicoes[] = view.getTblUsuarios().getSelectedRows();

        List<Usuario> usuariosSelecionados = new ArrayList<>();
        for (int i = 0; i < posicoes.length; i++) {
            usuariosSelecionados.add(this.usuarios.get(posicoes[i]));
        }

        return usuariosSelecionados;
    }

    private void preencheTabela(ListIterator<Usuario> usuarios) {
        tmUsuarios.setNumRows(0);

        while (usuarios.hasNext()) {
            Usuario usuario = usuarios.next();
            tmUsuarios.addRow(new Object[]{usuario.getNome()});
        }

        this.view.getTblUsuarios().setModel(tmUsuarios);
    }

}
