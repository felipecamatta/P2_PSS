package br.ufes.p2.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class DefinirPermissaoView extends javax.swing.JFrame {

    public DefinirPermissaoView() {
        initComponents();
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JCheckBox getCbCompartilhar() {
        return cbCompartilhar;
    }

    public JCheckBox getCbExcluir() {
        return cbExcluir;
    }

    public JCheckBox getCbVisualizar() {
        return cbVisualizar;
    }

    public JLabel getLblNome() {
        return lblNome;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbVisualizar = new javax.swing.JCheckBox();
        cbExcluir = new javax.swing.JCheckBox();
        cbCompartilhar = new javax.swing.JCheckBox();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Permissões");

        jLabel1.setText("Nome:");

        jLabel3.setText("Permissões:");

        cbVisualizar.setText("Visualizar todas imagens");

        cbExcluir.setText("Excluir todas imagens");

        cbCompartilhar.setText("Compartilhar todas imagens");

        btnSalvar.setText("Salvar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCompartilhar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblNome))
                    .addComponent(jLabel3)
                    .addComponent(cbVisualizar)
                    .addComponent(cbExcluir))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVisualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCompartilhar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox cbCompartilhar;
    private javax.swing.JCheckBox cbExcluir;
    private javax.swing.JCheckBox cbVisualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblNome;
    // End of variables declaration//GEN-END:variables
}
