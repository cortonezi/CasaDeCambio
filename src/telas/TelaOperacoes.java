package telas;

import dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class TelaOperacoes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Formatação de valores
    DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Creates new form TelaOperacoes
     */
    public TelaOperacoes() {
        initComponents();

        conexao = ModuloConexao.conector();
    }

    private void limparCampos() {
        txtMoeda.setText("");
        txtTaxaVendida.setText("");
        txtMontante.setText("");
        txtTaxaComercial.setText("");
        txtIof.setText("");
        txtCusto.setText("");
        txtLucroLiquidoOperacao.setText("");
        txtSpreadLiquido.setText("");
        txtValorTotal.setText("");
    }

    private void calcularOperacoes() {
        try {
            // Converte os campos para double, substituindo vírgulas por pontos
            double taxaComercial = Double.parseDouble(txtTaxaComercial.getText().replace(",", "."));
            double taxaVendida = Double.parseDouble(txtTaxaVendida.getText().replace(",", "."));
            double montante = Double.parseDouble(txtMontante.getText().replace(",", "."));
            double iof = Double.parseDouble(txtIof.getText().replace(",", ".")) / 100;
            double custo = Double.parseDouble(txtCusto.getText().replace(",", "."));

            // Calcular Taxa Sem IOF
            double taxaSemIof = taxaComercial + (taxaComercial * custo);

            // Calcular Taxa Com IOF
            double taxaComIof = taxaSemIof + (taxaSemIof * iof);

            // Calcular Valor Total
            double valorTotal = taxaVendida * montante;

            // Calcular Valor Sem Lucro
            double valorSemLucro = taxaComIof * montante;

            // Calcular Spread Líquido
            double spreadLiquido = (taxaVendida - taxaComIof) / taxaVendida;

            // Calcular Lucro Líquido Operação
            double lucroLiquidoOperacao = valorTotal - valorSemLucro;

            // Formatação para o padrão de moeda brasileira
            NumberFormat formatBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            // Definir valores formatados nos campos de texto
            txtValorTotal.setText(formatBR.format(valorTotal));
            txtSpreadLiquido.setText(df.format(spreadLiquido)); // Spread é uma taxa, pode não ser formatado como moeda
            txtLucroLiquidoOperacao.setText(formatBR.format(lucroLiquidoOperacao));
            txtTaxaComIof.setText(formatBR.format(taxaComIof));
            txtTaxaSemIof.setText(formatBR.format(taxaSemIof));
            txtValorSemLucro.setText(formatBR.format(valorSemLucro));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular os valores: " + e.getMessage());
        }
    }

    private void operacoes() {
        // Verifica se os campos de pesquisa estão preenchidos
        if (txtMoeda.getText().isEmpty() || txtTaxaVendida.getText().isEmpty() || txtMontante.getText().isEmpty() || txtTaxaComercial.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String moeda = txtMoeda.getText();
        String operacao = (String) cbxCompraVenda.getSelectedItem();
        String coluna = operacao.equals("Compra") ? "compra" : "venda";

        // Definir IOF de acordo com a operação
        double iof = operacao.equals("Compra") ? 0.38 : 1.10;
        txtIof.setText(df.format(iof));

        // Buscar o valor do custo da moeda
        String sql = "SELECT " + coluna + " FROM moedas WHERE moeda=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, moeda);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtCusto.setText(rs.getString(coluna));
            } else {
                JOptionPane.showMessageDialog(null, "Moeda não encontrada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                return;
            }

            // Calcular as operações
            calcularOperacoes();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
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

        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbxCompraVenda = new javax.swing.JComboBox<>();
        txtMoeda = new javax.swing.JTextField();
        txtTaxaComercial = new javax.swing.JTextField();
        txtTaxaVendida = new javax.swing.JTextField();
        txtSpreadLiquido = new javax.swing.JTextField();
        txtLucroLiquidoOperacao = new javax.swing.JTextField();
        txtCusto = new javax.swing.JTextField();
        txtIof = new javax.swing.JTextField();
        btnOperacao = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtMontante = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtValorSemLucro = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTaxaComIof = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTaxaSemIof = new javax.swing.JTextField();

        jTextField6.setText("jTextField6");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tela de Operações");

        jLabel1.setText("Operação:");

        jLabel2.setText("Moeda:");

        jLabel3.setText("Taxa Comercial:");

        jLabel8.setText("Taxa Vendida:");

        jLabel9.setText("Montante:");

        jLabel10.setText("IOF:");

        jLabel11.setText("Spread Líquido:");

        jLabel12.setText("Lucro Líquido Operação:");

        cbxCompraVenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venda" }));
        cbxCompraVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCompraVendaActionPerformed(evt);
            }
        });

        txtMoeda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoedaActionPerformed(evt);
            }
        });

        txtTaxaComercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaxaComercialActionPerformed(evt);
            }
        });

        txtTaxaVendida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaxaVendidaActionPerformed(evt);
            }
        });

        txtSpreadLiquido.setEditable(false);

        txtLucroLiquidoOperacao.setEditable(false);
        txtLucroLiquidoOperacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLucroLiquidoOperacaoActionPerformed(evt);
            }
        });

        txtCusto.setEditable(false);
        txtCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustoActionPerformed(evt);
            }
        });

        txtIof.setEditable(false);

        btnOperacao.setText("Operação");
        btnOperacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperacaoActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        jLabel13.setText("Custo:");

        txtMontante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontanteActionPerformed(evt);
            }
        });

        jLabel14.setText("Valor Total:");

        txtValorTotal.setEditable(false);

        jLabel15.setText("Valor sem lucro:");

        txtValorSemLucro.setEditable(false);

        jLabel16.setText("Taxa com IOF:");

        txtTaxaComIof.setEditable(false);

        jLabel17.setText("Taxa sem IOF:");

        txtTaxaSemIof.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxCompraVenda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMoeda)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTaxaComercial)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOperacao)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpar)
                                .addGap(0, 11, Short.MAX_VALUE))
                            .addComponent(txtMontante, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTaxaVendida, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTaxaSemIof, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtTaxaComIof, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtValorSemLucro, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtSpreadLiquido, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtIof, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtLucroLiquidoOperacao, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtCusto))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxCompraVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(txtMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLucroLiquidoOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTaxaVendida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtSpreadLiquido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtIof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtMontante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtTaxaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtValorSemLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtTaxaComIof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOperacao)
                            .addComponent(btnLimpar))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtTaxaSemIof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxCompraVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCompraVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCompraVendaActionPerformed

    private void txtLucroLiquidoOperacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLucroLiquidoOperacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLucroLiquidoOperacaoActionPerformed

    private void txtTaxaVendidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaxaVendidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaxaVendidaActionPerformed

    private void txtCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustoActionPerformed

    private void txtMoedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoedaActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // CHAMANDO O METODO LIMPAR CAMPOS
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnOperacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperacaoActionPerformed
        // CHAMANDO O METODO OPERAÇAO
        operacoes();
    }//GEN-LAST:event_btnOperacaoActionPerformed

    private void txtMontanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontanteActionPerformed

    private void txtTaxaComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaxaComercialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaxaComercialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnOperacao;
    private javax.swing.JComboBox<String> cbxCompraVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField txtCusto;
    private javax.swing.JTextField txtIof;
    private javax.swing.JTextField txtLucroLiquidoOperacao;
    private javax.swing.JTextField txtMoeda;
    private javax.swing.JTextField txtMontante;
    private javax.swing.JTextField txtSpreadLiquido;
    private javax.swing.JTextField txtTaxaComIof;
    private javax.swing.JTextField txtTaxaComercial;
    private javax.swing.JTextField txtTaxaSemIof;
    private javax.swing.JTextField txtTaxaVendida;
    private javax.swing.JTextField txtValorSemLucro;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
