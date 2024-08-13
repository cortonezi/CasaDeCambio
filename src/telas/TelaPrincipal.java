package telas;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author rafae
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();

       // Definir o tamanho fixo da janela para 550x450 pixels
        setSize(670, 550);  // Define as dimensões fixas da janela

        // Desabilitar o redimensionamento da janela
        setResizable(false);  // Isso garante que a janela não possa ser redimensionada

        // Configurar o layout para que o JDesktopPane ocupe todo o espaço
        // Substitua o layout padrão pelo BorderLayout
        getContentPane().setLayout(new java.awt.BorderLayout());

        // Adicionando o jDesktopPane ao centro do BorderLayout
        getContentPane().add(desktop, java.awt.BorderLayout.CENTER);

        // Se desejar manter o jPanel1 no topo da janela
        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        desktop = new javax.swing.JDesktopPane();
        menu = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuMoedas = new javax.swing.JMenuItem();
        menuOperacoes = new javax.swing.JMenuItem();
        menuHistorico = new javax.swing.JMenu();
        menuConfiguracao = new javax.swing.JMenu();
        menuConfigMoedas = new javax.swing.JMenuItem();
        menuConfigUsuarios = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu1.setText("jMenu1");

        jMenuItem2.setText("jMenuItem2");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema para gestão cambial");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(550, 450));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        lblUsuario.setText("Usuário");

        lblData.setText("Data");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblData)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(lblData))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        desktop.setPreferredSize(new java.awt.Dimension(510, 410));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        menuCadastro.setText("Operações");

        menuMoedas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuMoedas.setText("Moedas");
        menuMoedas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMoedasActionPerformed(evt);
            }
        });
        menuCadastro.add(menuMoedas);
        menuMoedas.getAccessibleContext().setAccessibleDescription("");

        menuOperacoes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOperacoes.setText("Operação");
        menuOperacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOperacoesActionPerformed(evt);
            }
        });
        menuCadastro.add(menuOperacoes);

        menu.add(menuCadastro);

        menuHistorico.setText("Histórico");
        menu.add(menuHistorico);

        menuConfiguracao.setText("Configuração");

        menuConfigMoedas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuConfigMoedas.setText("Moedas");
        menuConfigMoedas.setEnabled(false);
        menuConfigMoedas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConfigMoedasActionPerformed(evt);
            }
        });
        menuConfiguracao.add(menuConfigMoedas);

        menuConfigUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuConfigUsuarios.setText("Usuários");
        menuConfigUsuarios.setEnabled(false);
        menuConfigUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuConfigUsuariosMouseClicked(evt);
            }
        });
        menuConfigUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConfigUsuariosActionPerformed(evt);
            }
        });
        menuConfiguracao.add(menuConfigUsuarios);

        menu.add(menuConfiguracao);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(716, 412));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuOperacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOperacoesActionPerformed
        // A LINHA ABAIXO ABRE O FOMRULARIO TELAUSUARIOS DENTRO DO DESKTOP DA TELA PRINCIPAL

        TelaOperacoes operacao = new TelaOperacoes();
        operacao.setVisible(true);
        desktop.add(operacao);
    }//GEN-LAST:event_menuOperacoesActionPerformed

    private void menuConfigMoedasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfigMoedasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuConfigMoedasActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // AS LINHAS ABAIXOS SUBSTITUI AS LABELs USUARIOS E DATA
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);

        lblData.setText(formatador.format(data));

    }//GEN-LAST:event_formWindowActivated

    private void menuConfigUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuConfigUsuariosMouseClicked

    }//GEN-LAST:event_menuConfigUsuariosMouseClicked

    private void menuConfigUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfigUsuariosActionPerformed
        // A LINHA ABAIXO ABRE O FOMRULARIO TELAUSUARIOS DENTRO DO DESKTOP DA TELA PRINCIPAL

        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);
    }//GEN-LAST:event_menuConfigUsuariosActionPerformed

    private void menuMoedasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMoedasActionPerformed
        // TODO add your handling code here:
        TelaMoedas moedas = new TelaMoedas();
        moedas.setVisible(true);
        desktop.add(moedas);
        
    }//GEN-LAST:event_menuMoedasActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuCadastro;
    public static javax.swing.JMenuItem menuConfigMoedas;
    public static javax.swing.JMenuItem menuConfigUsuarios;
    private javax.swing.JMenu menuConfiguracao;
    public static javax.swing.JMenu menuHistorico;
    public static javax.swing.JMenuItem menuMoedas;
    public static javax.swing.JMenuItem menuOperacoes;
    // End of variables declaration//GEN-END:variables
}
