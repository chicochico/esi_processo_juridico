/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import advogado.UC_AdvogadoManager;
import cliente.UC_ClienteManager;
import forum.UC_ForumManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import processoJuridico.UC_ProcessoManager;
import processo_juridico.Advogado;
import processo_juridico.Cliente;
import processo_juridico.Forum;
import processo_juridico.ProcessoJuridico;
import processo_juridico.TipoProcesso;
import processo_juridico.Tramite;
import tramite.UC_TramiteManager;

/**
 *
 * @author Eduardo
 */
public final class VisualizarDetalhes extends javax.swing.JFrame {

    private ArrayList<Tramite> tramites;
    private ProcessoJuridico processo;
    private DefaultTableModel model;

    private ArrayList<Advogado> advogados;
    private ArrayList<Cliente> clientes;
    private ArrayList<Forum> foruns;
    private ArrayList<TipoProcesso> tipos;
    
    public VisualizarDetalhes(){
        initComponents();
    }
    
    public VisualizarDetalhes(ProcessoJuridico processo) throws Exception {
        initComponents();
        this.processo = processo;
        this.tramites = new ArrayList<>();
        this.loadAdvogado();
        this.loadCliente();
        this.loadForum();
        this.loadTipoProcesso();
        this.loadData();
        cbForum.setEnabled(false);
        cbAdvogado.setEnabled(false);
        cbCliente.setEnabled(false);
        cbSituacao.setEnabled(false);
        cbTipo.setEnabled(false);
        txtDescricao.setEditable(false);
        txtNro.setEditable(false);
        btGravar.setVisible(false);
        btAddTramite.setVisible(false);
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
    
    
    
    public void loadData() throws Exception {
        this.txtNro.setText(Integer.toString(processo.getNumero()));
        this.cbTipo.setSelectedIndex(processo.getTipoProcesso().getId()-1);
        this.txtDescricao.setText(processo.getDescricao());
        this.cbForum.setSelectedIndex(processo.getForum().getId());
        this.cbAdvogado.setSelectedIndex(processo.getResponsavel().getId());
        this.cbCliente.setSelectedItem(processo.getCliente().getPessoa().getNome());
        this.loadTramites();
        
    }
    
    public void loadTramites() throws Exception{
        UC_TramiteManager uct = new UC_TramiteManager();
        this.tramites = uct.recuperarTodosTramites(processo.getNumero());
        
        String[] column = new String[] {"Tipo do Trâmite", "Observações", "Data do Trâmite"};
        String[][] data = new String[0][3];
        this.model = new DefaultTableModel(data, column);

        this.tbTramite.setModel(this.model);
        
        for(int i=0; i<this.tramites.size(); i++){
            
            this.addTramite(i);
        
        }
    }
    
    public void addTramite(int posicao) {
        
        Tramite tramite = this.tramites.get(posicao);
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/YYYY");
        String[] row = new String[] {tramite.getTipo().getNome(), tramite.getObs(), date.format(tramite.getData())};
        this.model.addRow(row);
        this.tbTramite.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNro = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblForum = new javax.swing.JLabel();
        lblAdvogado = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblSituacao = new javax.swing.JLabel();
        lblTramites = new javax.swing.JLabel();
        cbSituacao = new javax.swing.JComboBox();
        cbCliente = new javax.swing.JComboBox();
        cbAdvogado = new javax.swing.JComboBox();
        cbForum = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTramite = new javax.swing.JTable();
        txtNro = new javax.swing.JTextField();
        cbTipo = new javax.swing.JComboBox();
        txtTitulo = new javax.swing.JLabel();
        btAlterar = new javax.swing.JButton();
        btGravar = new javax.swing.JButton();
        btAddTramite = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNro.setText("Nro. Do Processo:");

        lblTipo.setText("Tipo do Processo:");
        lblTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblDescricao.setText("Descrição do Processo:");

        lblForum.setText("Fórum:");

        lblAdvogado.setText("Advogado Responsável:");

        lblCliente.setText("Cliente:");

        lblSituacao.setText("Situação do Processo:");

        lblTramites.setText("Trâmites do Processo");

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

        tbTramite.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo do Trâmite", "Observações", "Data do Trâmite"
            }
        ));
        jScrollPane2.setViewportView(tbTramite);

        txtNro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroActionPerformed(evt);
            }
        });

        txtTitulo.setText("Visualização Detalhada");

        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        btGravar.setText("Gravar");

        btAddTramite.setText("Adicionar Trâmite");
        btAddTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddTramiteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescricao)
                                    .addComponent(lblForum)
                                    .addComponent(lblTipo)
                                    .addComponent(lblNro))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNro, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                            .addComponent(cbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAdvogado)
                                    .addComponent(lblCliente)
                                    .addComponent(lblSituacao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbAdvogado, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbForum, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtTitulo)
                .addGap(176, 176, 176))
            .addGroup(layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(lblTramites)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btGravar)
                        .addGap(30, 30, 30)
                        .addComponent(btAlterar))
                    .addComponent(btAddTramite, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitulo)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNro)
                    .addComponent(txtNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricao)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForum)
                    .addComponent(cbForum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdvogado)
                    .addComponent(cbAdvogado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSituacao)
                    .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblTramites)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btAddTramite)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAlterar)
                    .addComponent(btGravar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSituacaoActionPerformed

    private void cbClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbClienteMouseClicked

    }//GEN-LAST:event_cbClienteMouseClicked

    private void cbAdvogadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbAdvogadoMouseClicked

    }//GEN-LAST:event_cbAdvogadoMouseClicked

    private void cbAdvogadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAdvogadoActionPerformed

    }//GEN-LAST:event_cbAdvogadoActionPerformed

    private void cbForumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbForumMouseClicked

    }//GEN-LAST:event_cbForumMouseClicked

    private void txtNroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        // TODO add your handling code here:
        cbForum.setEnabled(true);
        cbAdvogado.setEnabled(true);
        cbCliente.setEnabled(true);
        cbSituacao.setEnabled(true);
        cbTipo.setEnabled(true);
        txtDescricao.setEditable(true);
        txtTitulo.setText("Alteraração de Dados");
        btGravar.setVisible(true);
        btAddTramite.setVisible(true);
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btAddTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddTramiteActionPerformed
        AddTramite tramiteFrame = new AddTramite(this.tramites, this);
        tramiteFrame.setVisible(true);
    }//GEN-LAST:event_btAddTramiteActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(VisualizarDetalhes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizarDetalhes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizarDetalhes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizarDetalhes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizarDetalhes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddTramite;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btGravar;
    private javax.swing.JComboBox cbAdvogado;
    private javax.swing.JComboBox cbCliente;
    private javax.swing.JComboBox cbForum;
    private javax.swing.JComboBox cbSituacao;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdvogado;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblForum;
    private javax.swing.JLabel lblNro;
    private javax.swing.JLabel lblSituacao;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTramites;
    private javax.swing.JTable tbTramite;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNro;
    private javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}
