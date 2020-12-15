package br.ufes.p2.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ManterImagemView extends javax.swing.JFrame {

    public ManterImagemView() {
        initComponents();
    }

    public JButton getBtnCompartilhar() {
        return btnCompartilhar;
    }

    public JButton getBtnEscolherImagem() {
        return btnEscolherImagem;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JLabel getLblImagem() {
        return lblImagem;
    }

    public JTextField getTxtDiretorio() {
        return txtDiretorio;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtDiretorio = new javax.swing.JTextField();
        lblImagem = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnEscolherImagem = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCompartilhar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manter Imagem");

        jLabel1.setText("Nome:");

        jLabel2.setText("Diretório:");

        txtNome.setEditable(false);

        txtDiretorio.setEditable(false);

        lblImagem.setMinimumSize(new java.awt.Dimension(800, 600));
        lblImagem.setPreferredSize(new java.awt.Dimension(800, 800));

        btnSalvar.setText("Salvar");

        btnEscolherImagem.setText("Escolher imagem");

        btnExcluir.setText("Excluir");

        btnCompartilhar.setText("Compartilhar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNome)
                    .addComponent(txtDiretorio)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnCompartilhar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(btnEscolherImagem))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEscolherImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)
                    .addComponent(btnCompartilhar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompartilhar;
    private javax.swing.JButton btnEscolherImagem;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JTextField txtDiretorio;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
