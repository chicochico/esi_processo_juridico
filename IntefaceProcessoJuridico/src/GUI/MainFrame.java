/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import advogado.UC_AdvogadoManager;
import cliente.UC_ClienteManager;
import forum.UC_ForumManager;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import processoJuridico.*;
import processo_juridico.*;

/**
 *
 * @author Eduardo
 */
public final class MainFrame extends javax.swing.JFrame {

    private ArrayList<Advogado> advogados;
    private ArrayList<Cliente> clientes;
    private ArrayList<Forum> foruns;
    private ArrayList<TipoProcesso> tipos;
    private ArrayList<ProcessoJuridico> processos;
    private DefaultTableModel model;
    
    public MainFrame() throws Exception {
        initComponents();
        this.loadAdvogado();
        this.loadCliente();
        this.loadForum();
        this.loadTipoProcesso();
        this.loadVisualizar();
        this.cbSituacao.setEnabled(false);
    }
    
    public void loadTipoProcesso() {
        this.cbTipo.removeAll();
        UC_ProcessoManager ucp = new UC_ProcessoManager();
        try {
            tipos = ucp.recuperarTodosTipoProcesso();
            for (int i=0; i < tipos.size(); i++){
                TipoProcesso tipo = tipos.get(i);
                cbTipo.addItem(tipo.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void loadAdvogado() {
        UC_AdvogadoManager uca = new UC_AdvogadoManager();
        try {
            advogados = uca.recuperarTodosAdvogados();
            for (int i=0; i < advogados.size(); i++){
                Advogado advogado = advogados.get(i);
                cbAdvogado.addItem(advogado.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void loadForum() {
        UC_ForumManager ucf = new UC_ForumManager();
        try {
            foruns = ucf.recuperarTodosForuns();
            for (int i=0; i < foruns.size(); i++){
                Forum forum = foruns.get(i);
                cbForum.addItem(forum.getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void loadCliente() {
        UC_ClienteManager ucc = new UC_ClienteManager();
        try {
            clientes = ucc.recuperarTodosClientes();
            for (int i=0; i < clientes.size(); i++){
                Cliente cliente = clientes.get(i);
                cbCliente.addItem(cliente.getPessoa().getNome());
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void loadVisualizar() throws Exception{
        UC_ProcessoManager prc = new UC_ProcessoManager();
        this.processos = prc.recuperarTodosProcessos();
        ProcessoJuridico p = new ProcessoJuridico();
        
        String[] columnVisualizar = new String[] {"Nro. do Processo", "Tipo do Processo", "Advogado", "Cliente", "Situação do Processo"};
        String[][] dataVizualizar = new String[0][5];
        this.model = new DefaultTableModel(dataVizualizar, columnVisualizar);
        String[] row = null;
        
        for (int i=0; i<processos.size(); i++){
        
            p = processos.get(i);
            row = new String[] {Integer.toString(p.getNumero()), p.getTipoProcesso().getNome(),
                p.getResponsavel().getNome(), p.getCliente().getPessoa().getNome(), 
                this.selectSituacao(p.getSituacao().getId())};
            this.model.addRow(row);
        
        }
        
        this.tbVisualizar.setModel(this.model);
    
    }
    
    public String selectSituacao(int situacao) {
        switch (situacao) {
            case 0:
                return "Aberto";
            case 1:
                return "Em Julgamento";
            case 2:
                return "Julgado";
            case 3:
                return "Cancelado";
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        cadastroProcesso = new javax.swing.JTabbedPane();
        panelCadastro = new javax.swing.JPanel();
        lblNro = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblForum = new javax.swing.JLabel();
        lblAdvogado = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblSituacao = new javax.swing.JLabel();
        cbSituacao = new javax.swing.JComboBox();
        cbCliente = new javax.swing.JComboBox();
        cbAdvogado = new javax.swing.JComboBox();
        cbForum = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        txtNro = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        cbTipo = new javax.swing.JComboBox();
        btnNovoTipo = new javax.swing.JButton();
        visualizarProcesso = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbVisualizar = new javax.swing.JTable();
        btVisualizar = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cadastroProcesso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cadastroProcessoFocusGained(evt);
            }
        });

        lblNro.setText("Nro. Do Processo:");

        lblTipo.setText("Tipo do Processo:");

        lblDescricao.setText("Descrição do Processo:");

        lblForum.setText("Fórum:");

        lblAdvogado.setText("Advogado Responsável:");

        lblCliente.setText("Cliente:");

        lblSituacao.setText("Situação do Processo:");

        cbSituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aberto", "Em Julgamento", "Julgado", "Cancelado" }));
        cbSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSituacaoActionPerformed(evt);
            }
        });

        cbCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbClienteMouseClicked(evt);
            }
        });

        cbAdvogado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cbAdvogado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbAdvogadoMouseClicked(evt);
            }
        });
        cbAdvogado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdvogadoActionPerformed(evt);
            }
        });

        cbForum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cbForum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbForumMouseClicked(evt);
            }
        });

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        txtNro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroActionPerformed(evt);
            }
        });

        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        btnNovoTipo.setText("Novo Tipo");
        btnNovoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCadastroLayout = new javax.swing.GroupLayout(panelCadastro);
        panelCadastro.setLayout(panelCadastroLayout);
        panelCadastroLayout.setHorizontalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadastroLayout.createSequentialGroup()
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescricao)
                            .addComponent(lblForum)
                            .addComponent(lblTipo)
                            .addComponent(lblNro))
                        .addGap(18, 18, 18)
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(panelCadastroLayout.createSequentialGroup()
                                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTipo, 0, 279, Short.MAX_VALUE)
                                    .addComponent(txtNro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNovoTipo)
                                .addGap(41, 41, 41))))
                    .addGroup(panelCadastroLayout.createSequentialGroup()
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAdvogado)
                            .addComponent(lblCliente)
                            .addComponent(lblSituacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbForum, 0, 285, Short.MAX_VALUE)
                            .addComponent(cbAdvogado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSituacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadastroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCadastrar)
                .addGap(47, 47, 47))
        );
        panelCadastroLayout.setVerticalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNro)
                    .addComponent(txtNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovoTipo))
                .addGap(20, 20, 20)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricao)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForum)
                    .addComponent(cbForum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdvogado)
                    .addComponent(cbAdvogado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSituacao)
                    .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btCadastrar)
                .addContainerGap())
        );

        cadastroProcesso.addTab("Cadastro de Processos Jurídicos", panelCadastro);

        tbVisualizar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro. do Processo", "Tipo do Processo", "Advogado", "Cliente", "Situação do Processo"
            }
        ));
        jScrollPane3.setViewportView(tbVisualizar);

        btVisualizar.setText("Visualizar Detalhes");
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout visualizarProcessoLayout = new javax.swing.GroupLayout(visualizarProcesso);
        visualizarProcesso.setLayout(visualizarProcessoLayout);
        visualizarProcessoLayout.setHorizontalGroup(
            visualizarProcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visualizarProcessoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btVisualizar)
                .addContainerGap())
        );
        visualizarProcessoLayout.setVerticalGroup(
            visualizarProcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarProcessoLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btVisualizar)
                .addContainerGap())
        );

        cadastroProcesso.addTab("Visualizar Processos Jurídicos", visualizarProcesso);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cadastroProcesso, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cadastroProcesso)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
            int row = tbVisualizar.getSelectedRow();
            VisualizarDetalhes tipoFrame;
        try {
            tipoFrame = new VisualizarDetalhes(this.processos.get(row));
            tipoFrame.setVisible(true); 
            tipoFrame.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btVisualizarActionPerformed

    private void cadastroProcessoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cadastroProcessoFocusGained

    }//GEN-LAST:event_cadastroProcessoFocusGained

    private void btnNovoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoTipoActionPerformed
        AddTipoProcesso tipoFrame = new AddTipoProcesso(this.tipos, this);
        tipoFrame.setEnabled(true);
        tipoFrame.setVisible(true);
    }//GEN-LAST:event_btnNovoTipoActionPerformed

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        ProcessoJuridico processo = new ProcessoJuridico();
        processo.setAbertura(new Date());
        processo.setNumero(Integer.parseInt(this.txtNro.getText()));
        System.out.println(processo.getNumero());
        processo.setCliente(this.clientes.get(this.cbCliente.getSelectedIndex()-1));
        processo.setForum(this.foruns.get(this.cbForum.getSelectedIndex()-1));
        processo.setResponsavel(this.advogados.get(this.cbAdvogado.getSelectedIndex()-1));
        processo.setDescricao(this.txtDescricao.getText());
        Situacao situacao = new Situacao(this.cbSituacao.getSelectedIndex(), this.cbSituacao.getSelectedItem().toString());
        processo.setSituacao(situacao);
        TipoProcesso tipo = this.tipos.get(this.cbTipo.getSelectedIndex());
        processo.setTipoProcesso(tipo);

        UC_ProcessoManager ucp = new UC_ProcessoManager();
        try {
            processo = ucp.cadastrarProcessoJuridico(processo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        JOptionPane.showMessageDialog(null, "Processo cadastrado com sucesso!");
        try {
            this.loadVisualizar();
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void txtNroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroActionPerformed

    private void cbForumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbForumMouseClicked

    }//GEN-LAST:event_cbForumMouseClicked

    private void cbAdvogadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAdvogadoActionPerformed

    }//GEN-LAST:event_cbAdvogadoActionPerformed

    private void cbAdvogadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbAdvogadoMouseClicked

    }//GEN-LAST:event_cbAdvogadoMouseClicked

    private void cbClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbClienteMouseClicked

    }//GEN-LAST:event_cbClienteMouseClicked

    private void cbSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSituacaoActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainFrame().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btVisualizar;
    private javax.swing.JButton btnNovoTipo;
    private javax.swing.JTabbedPane cadastroProcesso;
    private javax.swing.JComboBox cbAdvogado;
    private javax.swing.JComboBox cbCliente;
    private javax.swing.JComboBox cbForum;
    private javax.swing.JComboBox cbSituacao;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAdvogado;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblForum;
    private javax.swing.JLabel lblNro;
    private javax.swing.JLabel lblSituacao;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel panelCadastro;
    private javax.swing.JTable tbVisualizar;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNro;
    private javax.swing.JPanel visualizarProcesso;
    // End of variables declaration//GEN-END:variables

}
