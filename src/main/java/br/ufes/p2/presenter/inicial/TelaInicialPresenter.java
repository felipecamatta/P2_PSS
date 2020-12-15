package br.ufes.p2.presenter.inicial;

import br.ufes.p2.business.UtilitarioImagem;
import br.ufes.p2.business.memento.ImagemMemento;
import br.ufes.p2.business.memento.ZeladorImagemMemento;
import br.ufes.p2.business.observer.Observador;
import br.ufes.p2.dao.sqlite.impl.ImagemSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.NotificacaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.PermissaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.impl.SolicitacaoSQLiteDAO;
import br.ufes.p2.dao.sqlite.manager.SQLiteManager;
import br.ufes.p2.model.Imagem;
import br.ufes.p2.model.Permissao;
import br.ufes.p2.model.Solicitacao;
import br.ufes.p2.model.Usuario;
import br.ufes.p2.presenter.ListaSolicitacaoPresenter;
import br.ufes.p2.presenter.usuario.ListaUsuarioPresenter;
import br.ufes.p2.presenter.LoginPresenter;
import br.ufes.p2.presenter.imagem.ManterImagemPresenter;
import br.ufes.p2.presenter.NotificacoesPresenter;
import br.ufes.p2.presenter.inicial.state.TelaInicialAdministradorPresenter;
import br.ufes.p2.presenter.inicial.state.TelaInicialState;
import br.ufes.p2.presenter.inicial.state.TelaInicialUsuarioPresenter;
import br.ufes.p2.service.ImagemService;
import br.ufes.p2.service.NotificacaoService;
import br.ufes.p2.service.PermissaoService;
import br.ufes.p2.service.SolicitacaoService;
import br.ufes.p2.view.TelaInicialView;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TelaInicialPresenter implements Observador {

    private TelaInicialView view;
    private TelaInicialState state;
    private List<Imagem> imagens;
    private ImagemService imagemService;
    private PermissaoService permissaoService;
    private SolicitacaoService solicitacaoService;
    private NotificacaoService notificacaoService;
    private ZeladorImagemMemento zeladorImagemMemento;
    private Usuario usuario;

    public TelaInicialPresenter(Usuario usuario) throws IOException, Exception {
        this.view = new TelaInicialView();
        this.usuario = usuario;

        this.view.getLblUsuario().setText(usuario.getNome());
        this.view.getLblTipoUsuario().setText(usuario.getTipo());

        if (usuario.getTipo().equals("Administrador")) {
            this.state = new TelaInicialAdministradorPresenter(this);
        } else {
            this.state = new TelaInicialUsuarioPresenter(this);
        }

        this.imagemService = new ImagemService(new ImagemSQLiteDAO(new SQLiteManager()));
        this.permissaoService = new PermissaoService(new PermissaoSQLiteDAO(new SQLiteManager()));
        this.solicitacaoService = new SolicitacaoService(
                new NotificacaoSQLiteDAO(new SQLiteManager()),
                new PermissaoSQLiteDAO(new SQLiteManager()),
                new SolicitacaoSQLiteDAO(new SQLiteManager()));
        this.notificacaoService = new NotificacaoService(new NotificacaoSQLiteDAO(new SQLiteManager()));
        this.zeladorImagemMemento = new ZeladorImagemMemento();

        this.imagens = imagemService.getAllDisponiveis();

        setListaImagens();

        setNumeroNotificacoes();

        this.view.getListImagens().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Imagem imagem = getImagemSelecionada();
                try {
                    if (usuario.getPrivilegio().isVisualizarTodas() || possuiPermissao(imagem, usuario) || usuario.getTipo().equals("Administrador")) {
                        ManterImagemPresenter manterImagemPresenter = new ManterImagemPresenter(getImagemSelecionada(), usuario, zeladorImagemMemento);
                        manterImagemPresenter.addObservador(returnThis());
                    } else {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Você não tem permissão para acessar essa imagem. Gostaria de solicitar a permissão?", "Permissão negada", JOptionPane.YES_NO_OPTION);
                        if (dialogResult == 0) {
                            try {
                                solicitarAcesso(imagem, usuario, "Visualizar");
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
            }
        });

        this.view.getMiListarUsuarios().addActionListener((e) -> {
            new ListaUsuarioPresenter();
        });

        this.view.getMiListarSolicitacoes().addActionListener((e) -> {
            new ListaSolicitacaoPresenter();
        });

        this.view.getMiRestaurarImagem().addActionListener((e) -> {
            try {
                Imagem imagem = new Imagem();
                ImagemMemento imagemMemento = zeladorImagemMemento.getUltimo();
                if (imagemMemento != null) {
                    imagem.setMemento(imagemMemento);
                    this.imagemService.recuperar(imagem.getId());
                    JOptionPane.showMessageDialog(null, "Imagem recuperada", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    this.update();
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma imagem para ser recuperada", "Atenção", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(TelaInicialPresenter.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao recuperar imagem", "Falha", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.getBtnNotificacoes().addActionListener((e) -> {
            try {
                new NotificacoesPresenter(usuario);
            } catch (Exception ex) {
                Logger.getLogger(TelaInicialPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.view.getMiSair().addActionListener((e) -> {
            sair();
        });

        this.view.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                setNumeroNotificacoes();
            }
        });

        this.view.setVisible(true);
    }

    public TelaInicialPresenter returnThis() {
        return this;
    }

    private void setNumeroNotificacoes() {
        try {
            int quantidadeNotificacoes = this.notificacaoService.getByIdUsuario(Long.parseLong(System.getProperty("idUsuarioLogado"))).size();
            this.view.getBtnNotificacoes().setText("Notificações (" + quantidadeNotificacoes + ")");
        } catch (Exception ex) {
            Logger.getLogger(TelaInicialPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sair() {
        this.view.dispose();
        System.setProperty("idUsuarioLogado", "");
        new LoginPresenter();
    }

    private boolean possuiPermissao(Imagem imagem, Usuario usuario) throws Exception {
        Optional<Permissao> permissao = permissaoService.get(imagem.getId(), usuario.getId());
        return permissao.isPresent();
    }

    private void solicitarAcesso(Imagem imagem, Usuario usuario, String descricao) throws Exception {
        Solicitacao solicitacao = new Solicitacao(imagem, usuario, descricao);
        solicitacaoService.save(solicitacao);
    }

    private Imagem getImagemSelecionada() {
        int posicao = this.getView().getListImagens().getSelectedIndex();
        return this.imagens.get(posicao);
    }

    @Override
    public void update() {
        try {
            this.imagens = imagemService.getAllDisponiveis();
            setListaImagens();
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialPresenter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TelaInicialPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setListaImagens() throws IOException {
        List<File> files = new ArrayList<>();

        for (Imagem imagem : this.imagens) {
            File file = new File(imagem.getDiretorio());
            files.add(file);
        }

        DefaultListModel listModel = new DefaultListModel();

        int count = 0;

        for (File file : files) {
            String name = file.toString();
            if (name.endsWith("png") || name.endsWith("jpg") || name.endsWith("jpeg")) {
                ImageIcon ii = UtilitarioImagem.getInstancia().gerarMiniatura(file, 200, 200);
                listModel.add(count++, ii);
            }
        }

        this.view.getListImagens().setModel(listModel);
        this.view.getListImagens().setVisibleRowCount(1);
    }

    public TelaInicialView getView() {
        return view;
    }

}
