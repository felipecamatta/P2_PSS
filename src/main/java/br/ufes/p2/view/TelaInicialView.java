package br.ufes.p2.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TelaInicialView extends javax.swing.JFrame {

    public TelaInicialView() {
        initComponents();
    }

    public JButton getBtnNotificacoes() {
        return btnNotificacoes;
    }

    public JLabel getLblTipoUsuario() {
        return lblTipoUsuario;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public JList<String> getListImagens() {
        return listImagens;
    }

    public JMenu getMenuImagem() {
        return menuImagem;
    }

    public JMenu getMenuSair() {
        return menuSair;
    }

    public JMenu getMenuSolicitacoes() {
        return menuSolicitacoes;
    }

    public JMenu getMenuUsuarios() {
        return menuUsuarios;
    }

    public JMenuItem getMiListarSolicitacoes() {
        return miListarSolicitacoes;
    }

    public JMenuItem getMiListarUsuarios() {
        return miListarUsuarios;
    }

    public JMenuItem getMiRestaurarImagem() {
        return miRestaurarImagem;
    }

    public JMenuItem getMiSair() {
        return miSair;
    }

    public JPanel getPnListaImagens() {
        return pnListaImagens;
    }

    public JScrollPane getScrlImagens() {
        return scrlImagens;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnListaImagens = new javax.swing.JPanel();
        rodape = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTipoUsuario = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        scrlImagens = new javax.swing.JScrollPane();
        listImagens = new javax.swing.JList<>();
        menuBar = new javax.swing.JMenuBar();
        menuUsuarios = new javax.swing.JMenu();
        miListarUsuarios = new javax.swing.JMenuItem();
        menuSolicitacoes = new javax.swing.JMenu();
        miListarSolicitacoes = new javax.swing.JMenuItem();
        menuImagem = new javax.swing.JMenu();
        miRestaurarImagem = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();
        miSair = new javax.swing.JMenuItem();

        javax.swing.GroupLayout pnListaImagensLayout = new javax.swing.GroupLayout(pnListaImagens);
        pnListaImagens.setLayout(pnListaImagensLayout);
        pnListaImagensLayout.setHorizontalGroup(
            pnListaImagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        pnListaImagensLayout.setVerticalGroup(
            pnListaImagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio");

        rodape.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setText("Usuário:");

        jLabel2.setText("Tipo:");

        btnNotificacoes.setText("Notificações");

        javax.swing.GroupLayout rodapeLayout = new javax.swing.GroupLayout(rodape);
        rodape.setLayout(rodapeLayout);
        rodapeLayout.setHorizontalGroup(
            rodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rodapeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUsuario)
                .addGap(154, 154, 154)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(lblTipoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 446, Short.MAX_VALUE)
                .addComponent(btnNotificacoes)
                .addContainerGap())
        );
        rodapeLayout.setVerticalGroup(
            rodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rodapeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(rodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblUsuario)
                    .addComponent(jLabel2)
                    .addComponent(lblTipoUsuario)
                    .addComponent(btnNotificacoes))
                .addContainerGap())
        );

        listImagens.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrlImagens.setViewportView(listImagens);

        menuUsuarios.setText("Usuários");

        miListarUsuarios.setText("Listar usuários");
        menuUsuarios.add(miListarUsuarios);

        menuBar.add(menuUsuarios);

        menuSolicitacoes.setText("Solicitações");

        miListarSolicitacoes.setText("Listar solicitações");
        menuSolicitacoes.add(miListarSolicitacoes);

        menuBar.add(menuSolicitacoes);

        menuImagem.setText("Imagem");

        miRestaurarImagem.setText("Restaurar imagem");
        menuImagem.add(miRestaurarImagem);

        menuBar.add(menuImagem);

        menuSair.setText("Sair");

        miSair.setText("Sair do sistema");
        menuSair.add(miSair);

        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rodape, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrlImagens)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrlImagens, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rodape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JList<String> listImagens;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuImagem;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuSolicitacoes;
    private javax.swing.JMenu menuUsuarios;
    private javax.swing.JMenuItem miListarSolicitacoes;
    private javax.swing.JMenuItem miListarUsuarios;
    private javax.swing.JMenuItem miRestaurarImagem;
    private javax.swing.JMenuItem miSair;
    private javax.swing.JPanel pnListaImagens;
    private javax.swing.JPanel rodape;
    private javax.swing.JScrollPane scrlImagens;
    // End of variables declaration//GEN-END:variables
}
