package telas;

import dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();

        conexao = ModuloConexao.conector();
    }

    private void consultar() {
        //verfica se os campos ID e USUARIOS estão preenchidos, caso esteja ele limpa para realizar uma nova consulta
        if (!txtUsuId.getText().isEmpty() && !txtUsuUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Limpe os campos para realizar uma nova pesquisa", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            txtUsuId.setText(""); // Limpa o campo IDUser
            txtUsuUsuario.setText(""); // Limpa o campo de usuário
            txtUsuLogin.setText(""); // Limpa o campo de login
            txtUsuSenha.setText(""); // Limpa o campo de senha
            cboUsuPerfil.setSelectedItem(null);

            return;
        } else {

            String sql = "SELECT * FROM tbusuarios WHERE iduser=? or usuario=? ";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                pst.setString(2, txtUsuUsuario.getText()); // Segundo parâmetro: usuario

                rs = pst.executeQuery();

                if (rs.next()) {
                    txtUsuId.setText(rs.getString(1));
                    txtUsuUsuario.setText(rs.getString(2));
                    txtUsuLogin.setText(rs.getString(3));
                    txtUsuSenha.setText(rs.getString(4));
                    cboUsuPerfil.setSelectedItem(rs.getString(5));

                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado, digite o Id ou usuário", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    // limpa os campos após o usuario não ser encontrado
                    //txtUsuId.setText(""); // Limpa o campo IDUser
                    txtUsuUsuario.setText(""); // Limpa o campo de usuário
                    txtUsuLogin.setText(""); // Limpa o campo de login
                    txtUsuSenha.setText(""); // Limpa o campo de senha

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // metodo para adicionar novos usuarios
    private void adicionar() {

        String sqlCheckId = "SELECT COUNT(*) FROM tbusuarios WHERE iduser = ?";
        String sqlCheckLogin = "SELECT COUNT(*) FROM tbusuarios WHERE login = ?";
        String sqlInsert = "INSERT INTO tbusuarios(iduser, usuario, login, senha, perfil) VALUES(?,?,?,?,?)";

        try {
            // Verifica se o ID já existe
            pst = conexao.prepareStatement(sqlCheckId);
            pst.setString(1, txtUsuId.getText());
            ResultSet rs = pst.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Se o ID já existe, exibe uma mensagem de aviso
                JOptionPane.showMessageDialog(null, "Id existente, por favor insira um Id diferente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Fecha o ResultSet e o PreparedStatement antes de reusá-los
                rs.close();
                pst.close();

                // Verifica se o login já existe
                pst = conexao.prepareStatement(sqlCheckLogin);
                pst.setString(1, txtUsuLogin.getText());
                rs = pst.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    // Se o login já existe, exibe uma mensagem de aviso
                    JOptionPane.showMessageDialog(null, "Nome de usuário existente, por favor insira um nome de usuário diferente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Fecha o ResultSet e o PreparedStatement antes de reusá-los
                    rs.close();
                    pst.close();

                    // Se o ID e o login não existem, continua com a inserção
                    pst = conexao.prepareStatement(sqlInsert);
                    pst.setString(1, txtUsuId.getText());
                    pst.setString(2, txtUsuUsuario.getText());
                    pst.setString(3, txtUsuLogin.getText());
                    pst.setString(4, txtUsuSenha.getText());
                    pst.setString(5, cboUsuPerfil.getSelectedItem().toString());

                    if (txtUsuId.getText().isEmpty() || txtUsuLogin.getText().isEmpty()
                            || txtUsuSenha.getText().isEmpty() || txtUsuUsuario.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Atualiza a tabela usuario com os dados do formulario
                        int adicionado = pst.executeUpdate();
                        if (adicionado > 0) {
                            JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            // Limpa os campos após a inserção bem-sucedida
                            txtUsuId.setText("");
                            txtUsuUsuario.setText("");
                            txtUsuLogin.setText("");
                            txtUsuSenha.setText("");
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    // criando metodo para alterar dados dos usuarios
    private void alterar() {

        String sql = "update tbusuarios set usuario=?, login=?, senha=?, perfil=? where iduser=?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtUsuUsuario.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(5, txtUsuId.getText());

            if (txtUsuId.getText().isEmpty() || txtUsuLogin.getText().isEmpty()
                    || txtUsuSenha.getText().isEmpty() || txtUsuUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Altera a tabela usuario com os dados do formulario
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterado com sucesso.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    // Limpa os campos após a alteração bem-sucedida
                    txtUsuId.setText("");
                    txtUsuUsuario.setText("");
                    txtUsuLogin.setText("");
                    txtUsuSenha.setText("");

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void deletar() {

        if (txtUsuId.getText().isEmpty() || txtUsuLogin.getText().isEmpty()
                || txtUsuSenha.getText().isEmpty() || txtUsuUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {

            //a linha abaixo confirma se o usuario deseja excluir o usuario 
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuario?,", "Atenção", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {
                String sql = "Delete from tbusuarios where iduser=?";

                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtUsuId.getText());

                    // Limpa os campos após a alteração bem-sucedida
                    txtUsuId.setText("");
                    txtUsuUsuario.setText("");
                    txtUsuLogin.setText("");
                    txtUsuSenha.setText("");
                    int apagado = pst.executeUpdate();

                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Usuario removido com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuSenha = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        btnUsuCreat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnUsuUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnUsuDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnUsuRead = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuUsuario = new javax.swing.JTextField();
        txtUsuId = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        txtUsuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuSenhaActionPerformed(evt);
            }
        });

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        cboUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuPerfilActionPerformed(evt);
            }
        });

        btnUsuCreat.setText("Adicionar");
        btnUsuCreat.setToolTipText("Adicionar");
        btnUsuCreat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreat.setPreferredSize(new java.awt.Dimension(75, 75));
        btnUsuCreat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreatActionPerformed(evt);
            }
        });

        jLabel1.setText("*Id");

        btnUsuUpdate.setText("Editar");
        btnUsuUpdate.setToolTipText("Editar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(75, 75));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        jLabel2.setText("*Usuário");

        btnUsuDelete.setText("Deletar");
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(75, 90));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        jLabel3.setText("*Login");

        btnUsuRead.setText("Pesquisar");
        btnUsuRead.setToolTipText("Pesquisar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(75, 75));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        jLabel4.setText("*Senha");

        jLabel5.setText("*Perfil");

        txtUsuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUsuCreat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(cboUsuPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(35, 35, 35)
                            .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(93, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuCreat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(33, 33, 33)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addContainerGap(200, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuSenhaActionPerformed

    private void cboUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsuPerfilActionPerformed

    private void btnUsuCreatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreatActionPerformed
        // Chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnUsuCreatActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // chama o metodo alterar
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // chamndo o metodo deletar
        deletar();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        //chamando o metodo consultar
        consultar();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void txtUsuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreat;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuSenha;
    private javax.swing.JTextField txtUsuUsuario;
    // End of variables declaration//GEN-END:variables
}
