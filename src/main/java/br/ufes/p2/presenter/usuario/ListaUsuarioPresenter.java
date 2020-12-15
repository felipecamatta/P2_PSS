package br.ufes.p2.presenter.usuario;

import br.ufes.p2.presenter.usuario.ManterUsuarioPresenter;
import br.ufes.p2.business.observer.Observador;
import br.ufes.p2.dao.sqlite.impl.UsuarioSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.DefinirPermissaoPresenter;
import br.ufes.p2.service.UsuarioService;
import br.ufes.p2.view.ListaUsuarioView;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ListaUsuarioPresenter implements Observador {

    private ListaUsuarioView view;
    private UsuarioService usuarioService;
    private DefaultTableModel tmUsuarios;
    private List<Usuario> usuarios;

    public ListaUsuarioPresenter() {
        this.view = new ListaUsuarioView();
        this.usuarioService = new UsuarioService(new UsuarioSQLiteDAO(new SQLiteManager()));
        this.tmUsuarios = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Tipo"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.usuarios = new ArrayList<>();

        update();

        preencheTabela(usuarios.listIterator());

        this.view.getTblUsuarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.view.getBtnVisualizar().setEnabled(false);

        this.view.getTblUsuarios().getSelectionModel().addListSelectionListener((e) -> {
            this.view.getBtnVisualizar().setEnabled(true);
        });

        this.view.getBtnVisualizar().addActionListener((e) -> {
            Usuario usuario = this.usuarios.get(this.view.getTblUsuarios().getSelectedRow());
            ManterUsuarioPresenter manterUsuarioPresenter = new ManterUsuarioPresenter(usuario);
            manterUsuarioPresenter.addObservador(this);
        });

        this.view.getBtnNovo().addActionListener((e) -> {
            ManterUsuarioPresenter manterUsuarioPresenter = new ManterUsuarioPresenter();
            manterUsuarioPresenter.addObservador(this);
        });

        this.view.getBtnDefinirPermissoes().addActionListener((e) -> {
            new DefinirPermissaoPresenter(this.usuarios.get(this.view.getTblUsuarios().getSelectedRow()));
        });

        this.view.setVisible(true);
    }

    private void preencheTabela(ListIterator<Usuario> usuarios) {
        tmUsuarios.setNumRows(0);

        while (usuarios.hasNext()) {
            Usuario usuario = usuarios.next();
            tmUsuarios.addRow(new Object[]{usuario.getNome(), usuario.getTipo()});
        }

        this.view.getTblUsuarios().setModel(tmUsuarios);
    }

    @Override
    public void update() {
        try {
            this.usuarios = usuarioService.getAll();
        } catch (Exception ex) {
            Logger.getLogger(ListaUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

        preencheTabela(this.usuarios.listIterator());
    }

}
