/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Controle.dao.ClienteDAO;
import Controle.dao.ControleDAO;
import Controle.dao.ControleProdDAO;
import Controle.dao.FuncionarioDAO;
import Controle.dao.ProdDAO;
import connection.ConnectionFactory;
import static connection.ConnectionFactory.con;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Cliente;
import model.bean.Controle;
import model.bean.ControleProd;
import model.bean.Funcionario;
import model.bean.ProdEstado;

/**
 *
 * @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira
 * Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso INFEM302 CEDUPHH
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        PainelCadastrar.setVisible(false);
        PainelEmprestimo.setVisible(false);

        DefaultTableModel modelo = (DefaultTableModel) TabelaProduto.getModel();
        TabelaProduto.setRowSorter(new TableRowSorter(modelo));
        readJTable();

        DefaultTableModel modelos = (DefaultTableModel) TabelaEmprestimo.getModel();
        TabelaEmprestimo.setRowSorter(new TableRowSorter(modelos));
        readEmprestimo();

        DefaultTableModel modelo1 = (DefaultTableModel) TabelaProduto2.getModel();
        TabelaProduto2.setRowSorter(new TableRowSorter(modelo1));
        readTabelaProduto();
    }

    public void limpar() {
        txtNome.setText("");
        txtDataEntrada.setText("");
        txtID.setText("");
        txtCliente.setText("");
        txtDataDevolucao.setText("");
        txtDataEmprestimo.setText("");
        txtIDEmprestimo.setText("");
        txtProduto.setText("");
        txtIDInvisivel.setText("");
        txtQuantidade.setText("");
    }

    public void readEmprestimo() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        DefaultTableModel modelos = (DefaultTableModel) TabelaEmprestimo.getModel();

        modelos.setNumRows(0);

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("Select C.ID_CONTROLE, CLI.NOME_CLIENTE, \n"
                    + "P.NOME_PRODUTO, P.TIPO_PRODUTO ,CP.QUANTIDADE_DE_PRODUTO, P.STATUS_PRODUTO, C.DATA_DE_DEVOLUCAO, C.DATA_DE_EMPRESTIMO \n"
                    + "FROM controle as C INNER JOIN cliente as CLI\n"
                    + "ON CLI.ID_CLIENTE = C.Cliente_ID_CLIENTE \n"
                    + "INNER JOIN controle_produto AS CP \n"
                    + "ON C.ID_CONTROLE = CP.Controle_ID_CONTROLE \n"
                    + "INNER JOIN PRODUTO AS P\n"
                    + " ON CP.Produto_ID_PRODUTO = P.ID_PRODUTO\n"
                    + " order by C.ID_CONTROLE DESC ");
            // JOptionPane.showMessageDialog(null, "FOI");

            rs = stmt.executeQuery();

            while (rs.next()) {

                modelos.addRow(new Object[]{
                    rs.getInt("ID_CONTROLE"),
                    rs.getString("NOME_CLIENTE"),
                    rs.getString("NOME_PRODUTO"),
                    rs.getString("TIPO_PRODUTO"),
                    rs.getInt("QUANTIDADE_DE_PRODUTO"),
                    rs.getString("STATUS_PRODUTO"),
                    rs.getString("DATA_DE_DEVOLUCAO"),
                    rs.getString("DATA_DE_EMPRESTIMO")
                });

            }
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public void readTabelaProduto() {

        DefaultTableModel modelo1 = (DefaultTableModel) TabelaProduto2.getModel();
        modelo1.setNumRows(0);
        ProdDAO pd = new ProdDAO();

        for (ProdEstado pe : pd.read()) {

            modelo1.addRow(new Object[]{
                pe.getIdProd(),
                pe.getNomeProd(),
                pe.getTipoProd(),
                pe.getOrigemProd(),
                pe.getQuantidade(),
                pe.getDataEntradaProd(),
                pe.getStatusProduto()
            });
        }

    }

    public void readJTable() {

        DefaultTableModel modelo = (DefaultTableModel) TabelaProduto.getModel();
        modelo.setNumRows(0);
        ProdDAO pd = new ProdDAO();

        for (ProdEstado pe : pd.read()) {

            modelo.addRow(new Object[]{
                pe.getIdProd(),
                pe.getNomeProd(),
                pe.getTipoProd(),
                pe.getOrigemProd(),
                pe.getQuantidade(),
                pe.getDataEntradaProd(),
                pe.getStatusProduto()
            });
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

        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jMenuItem2 = new javax.swing.JMenuItem();
        PainelCadastrar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaProduto = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbOrigem = new javax.swing.JComboBox<>();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDataEntrada = new javax.swing.JTextField();
        BotaoInserir = new javax.swing.JButton();
        txtQuantidade = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        PainelEmprestimo = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtIDEmprestimo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtProduto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDataEmprestimo = new javax.swing.JTextField();
        txtDataDevolucao = new javax.swing.JTextField();
        botaoemprestar = new javax.swing.JButton();
        botaodevolver = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaEmprestimo = new javax.swing.JTable();
        cbTipo1 = new javax.swing.JComboBox<>();
        txtIDInvisivel = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TabelaProduto2 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtQuantidadeDeEmprestimo = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuCadastrar = new javax.swing.JMenu();
        MenuEmprestimoDevolucao = new javax.swing.JMenu();
        MenuSobre = new javax.swing.JMenu();
        MenuSair = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenuItem3.setText("jMenuItem3");

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextField1.setText("jTextField1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");
        setSize(new java.awt.Dimension(600, 398));

        TabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome do Produto", "Tipo do Produto", "Origem", "Quantidade", "Data de Entrada", "Situação do Produto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaProdutoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelaProduto);

        jLabel2.setText("ID");

        txtID.setEditable(false);
        txtID.setToolTipText("Este campo não está diponível para edição devido que ele será incrementado automáticamente pelo sistema.");

        jLabel3.setText("Nome do produto");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo do produto");

        jLabel5.setText("Origem do produto");

        cbOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estado", "Cooperativa" }));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descartável", "Reutilizável" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });

        jLabel6.setText("Situaçao do Produto");

        txtStatus.setEditable(false);
        txtStatus.setText("Disponível");

        jLabel7.setText("Data de entrada");

        BotaoInserir.setText("Inserir");
        BotaoInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoInserirActionPerformed(evt);
            }
        });

        jLabel15.setText("Quantidade");

        javax.swing.GroupLayout PainelCadastrarLayout = new javax.swing.GroupLayout(PainelCadastrar);
        PainelCadastrar.setLayout(PainelCadastrarLayout);
        PainelCadastrarLayout.setHorizontalGroup(
            PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PainelCadastrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelCadastrarLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelCadastrarLayout.createSequentialGroup()
                        .addGroup(PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addGroup(PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PainelCadastrarLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbOrigem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(PainelCadastrarLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelCadastrarLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataEntrada))
                            .addGroup(PainelCadastrarLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PainelCadastrarLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(BotaoInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PainelCadastrarLayout.setVerticalGroup(
            PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelCadastrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelCadastrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(59, 59, 59)
                .addComponent(BotaoInserir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel8.setText("ID");

        txtIDEmprestimo.setEditable(false);

        jLabel9.setText("Nome do Cliente");

        jLabel10.setText("Produto");

        jLabel11.setText("Tipo do produto");

        jLabel12.setText("Data de Emprestimo");

        jLabel13.setText("Data de Devolução");

        botaoemprestar.setText("Emprestar");
        botaoemprestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoemprestarActionPerformed(evt);
            }
        });

        botaodevolver.setText("Devolver");
        botaodevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaodevolverActionPerformed(evt);
            }
        });

        TabelaEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome do Cliente", "Produto", "Tipo do produto", "Quantidade de produto", "Situação do produto", "Data de empréstimo", "Data de devolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelaEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaEmprestimoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TabelaEmprestimo);

        cbTipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descatável", "Reutilizável", " " }));

        txtIDInvisivel.setEditable(false);

        TabelaProduto2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome do Produto", "Tipo do Produto", "Origem", "Quantidade", "Data de Entrada", "Situação do Produto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelaProduto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaProduto2MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TabelaProduto2);
        if (TabelaProduto2.getColumnModel().getColumnCount() > 0) {
            TabelaProduto2.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel16.setText("Quantidade");

        txtQuantidadeDeEmprestimo.setToolTipText("Insira aqui a quantidade de produto que é desejado ser retirado do estoque");

        javax.swing.GroupLayout PainelEmprestimoLayout = new javax.swing.GroupLayout(PainelEmprestimo);
        PainelEmprestimo.setLayout(PainelEmprestimoLayout);
        PainelEmprestimoLayout.setHorizontalGroup(
            PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtIDEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIDInvisivel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(138, 138, 138)
                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtQuantidadeDeEmprestimo)))
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(botaoemprestar)
                .addGap(74, 74, 74)
                .addComponent(botaodevolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelEmprestimoLayout.setVerticalGroup(
            PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelEmprestimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIDEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cbTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDInvisivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(txtDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtQuantidadeDeEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoemprestar)
                    .addComponent(botaodevolver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MenuCadastrar.setText("Cadastrar");
        MenuCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuCadastrarMouseClicked(evt);
            }
        });
        jMenuBar1.add(MenuCadastrar);

        MenuEmprestimoDevolucao.setText("Emprestímo/Devolução");
        MenuEmprestimoDevolucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuEmprestimoDevolucaoMouseClicked(evt);
            }
        });
        jMenuBar1.add(MenuEmprestimoDevolucao);

        MenuSobre.setText("Sobre");
        MenuSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuSobreMouseClicked(evt);
            }
        });
        MenuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSobreActionPerformed(evt);
            }
        });
        jMenuBar1.add(MenuSobre);

        MenuSair.setText("Sair");
        MenuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuSairMouseClicked(evt);
            }
        });
        jMenuBar1.add(MenuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PainelCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(PainelCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(PainelEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSobreActionPerformed

    }//GEN-LAST:event_MenuSobreActionPerformed

    private void MenuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuSairMouseClicked
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_MenuSairMouseClicked

    private void MenuSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuSobreMouseClicked
        new Sobre().setVisible(true);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_MenuSobreMouseClicked

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void MenuCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuCadastrarMouseClicked
        PainelCadastrar.setVisible(true);
        PainelEmprestimo.setVisible(false);

// TODO add your handling code here:
    }//GEN-LAST:event_MenuCadastrarMouseClicked

    private void MenuEmprestimoDevolucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuEmprestimoDevolucaoMouseClicked
        PainelCadastrar.setVisible(false);
        PainelEmprestimo.setVisible(true);
        txtIDInvisivel.setVisible(false);
        readTabelaProduto();
//        frame1 f = new frame1();
//        f.setSize(700, 450);

    }//GEN-LAST:event_MenuEmprestimoDevolucaoMouseClicked

    private void BotaoInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoInserirActionPerformed

        PreparedStatement stmt = null;
        ProdDAO pd = new ProdDAO();
        //
        ControleDAO dao1 = new ControleDAO();
        ControleProdDAO dao2 = new ControleProdDAO();
//        Criando objetos para as classes localizados os atributos e os getters e setters
        ProdEstado pe = new ProdEstado();
        ControleProd controleprod = new ControleProd();
        Controle controle = new Controle();
//        Chamando a conexão do banco de dados
        Connection con = null;

        try {
            ConnectionFactory.getConnection();

            pe.setOrigemProd((String) cbOrigem.getSelectedItem());

            pe.setTipoProd(String.valueOf(cbTipo.getSelectedItem()));
            pe.setStatusProduto(txtStatus.getText());
            pe.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
//        Validação para se os campos estão sendo preechidos ou não(vale para todos os campos!!r)
            if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo nome inválido!");
            } else {

                if ((txtDataEntrada.getText() == null || txtDataEntrada.getText().trim().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Você deve escrever no formato de uma data!");
                } else {
                    pe.setNomeProd(txtNome.getText());
                    pe.setDataEntradaProd(ConnectionFactory.formatDate(txtDataEntrada.getText()));

                    if (pe.getDataEntradaProd() == null) {
                        JOptionPane.showMessageDialog(null, "A Data deve condizer com a realidade!!");
                    } else {
                        if ((txtQuantidade.getText() == null || txtQuantidade.getText().trim().equals(""))) {
                            JOptionPane.showMessageDialog(null, "Quantidade inválida, deve ser a cima ou igual a 1!");
                        } else {
                            // chamando os métodos     
                            pd.inserir(pe);
                            readJTable();
                            readEmprestimo();
                            limpar();
                        }
                    }

                }

            }

            //dao1.inserir(controle);
            //dao2.inserir(controleprod);
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoInserirActionPerformed

    private void TabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaProdutoMouseClicked
        if (TabelaProduto.getSelectedRow() != -1) {
            DefaultTableModel modelo = (DefaultTableModel) TabelaProduto.getModel();
            String v0, v1, v2, v3, v4, v5, v6;
            v0 = TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 0).toString();
            v1 = TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 1).toString();
            v2 = TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 2).toString();
            v3 = TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 3).toString();
            v6 = TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 4).toString();
            v4 = TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 5).toString();
            v5 = TabelaProduto.getValueAt(TabelaProduto.getSelectedRow(), 6).toString();
            txtID.setText(v0);
            txtNome.setText(v1);
            txtQuantidade.setText(v6);
            txtDataEntrada.setText(v4);
            txtStatus.setText(v5);

            if (v2.equals("Descartável")) {
                cbTipo.setSelectedIndex(0);
            } else {
                cbTipo.setSelectedIndex(1);
            }
            if (v3.equals("Estado")) {
                cbOrigem.setSelectedIndex(0);
            } else {
                cbOrigem.setSelectedIndex(1);
            }

            //         txtDataEntrada.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
            //           String bct = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
//            cbOrigem.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 3));           
//            txtDataEntrada.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
//            JOptionPane.showMessageDialog(null, jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
        } else {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um produto!");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaProdutoMouseClicked

    private void botaoemprestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoemprestarActionPerformed
        String codigodofuncionario = null;
        Funcionario f = new Funcionario();
        codigodofuncionario = JOptionPane.showInputDialog("Insira o código de funcionário respectivo ao seu log-in.");

        try {

            ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("Select * from Funcionario WHERE ID_FUNCIONARIO = ?");
            stmt.setString(1, codigodofuncionario);

            //capturar os resultados do select
            ResultSet rs = stmt.executeQuery();
            //ver se encontra no banco de dados
            while (rs.next()) {
                if (TabelaProduto2.getSelectedRow() != -1) {

                    ProdEstado pe = new ProdEstado();
                    ProdDAO pd = new ProdDAO();
                    Cliente cli = new Cliente();
                    ClienteDAO cd = new ClienteDAO();
                    Controle c = new Controle();
                    ControleDAO cond = new ControleDAO();

                    FuncionarioDAO fd = new FuncionarioDAO();
                    //settando o status para indísponivel
                    pe.setStatusProduto("Indisponível");
                    pe.setIdProd(Integer.parseInt(txtIDInvisivel.getText()));
                    f.getIdFunc();
                    //Settando o cliente

                    //Settando o Tipo do produto
                    pe.setTipoProd(cbTipo1.getSelectedItem().toString());
                    //Settando as datas com verificação{
                    if ((txtDataEmprestimo.getText() == null || txtDataEmprestimo.getText().trim().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Você deve escrever no formato de uma data!");
                    } else {
                        c.setDataEmprestimo(ConnectionFactory.formatDate(txtDataEmprestimo.getText()));
                    }
                    if (c.getDataEmprestimo() == null) {
                        JOptionPane.showMessageDialog(null, "A Data deve condizer com a realidade!!");
                    } else {
                        if ((txtDataDevolucao.getText() == null || txtDataDevolucao.getText().trim().equals(""))) {
                            JOptionPane.showMessageDialog(null, "Você deve escrever no formato de uma data!");
                        } else {
                            c.setDataDevolucao(ConnectionFactory.formatDate(txtDataDevolucao.getText()));
                        }
                        if (c.getDataDevolucao() == null) {
                            JOptionPane.showMessageDialog(null, "A Data deve condizer com a realidade!!");
                        } else {
                            JOptionPane.showMessageDialog(null, c.getCliente());
                            //Chamando os métodos
                            f.setIdFunc(Integer.parseInt(codigodofuncionario));
                            c.setFuncionario(f);
                            cli.setNomeCli(txtCliente.getText());
                            c.setCliente(cli);

                            cd.inserir(cli); // Método do inserir Cliente
                            JOptionPane.showMessageDialog(null, "esse aqui" + c.getCliente().getIdCli());
                            cd.readIDCli(cli.getNomeCli());

                            fd.readIDFunc(f.getIdFunc());
                            cond.inserir(c); //Método de inserir Controle

                            pd.AlterarStatus(pe); // Método de alterar o Status para indísponivel
                            readTabelaProduto(); // Método de Colocar na tabela os dados do mysql
                            readEmprestimo();// Método de Colocar na tabela os dados do mysql
                            limpar(); //Limpar os campos de texto
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "SELECIONE UMA LINHA");
                }
            }
            //fechar o prepareStatement
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_botaoemprestarActionPerformed

    private void TabelaEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaEmprestimoMouseClicked
        ProdEstado pe = new ProdEstado();
        if (TabelaEmprestimo.getSelectedRow() != -1) {
            DefaultTableModel modelos = (DefaultTableModel) TabelaEmprestimo.getModel();
            String v0, v1, v2;
            v0 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 0).toString();
            v1 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 1).toString();
            v2 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 2).toString();
            txtIDInvisivel.setText(v0);
            txtProduto.setText(v1);
            if (v2.equals("Descartável")) {
                cbTipo1.setSelectedIndex(0);
            } else {
                cbTipo1.setSelectedIndex(1);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um produto!");
        }   // TODO add your handling code here:
    }//GEN-LAST:event_TabelaEmprestimoMouseClicked

    private void botaodevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaodevolverActionPerformed
        if (TabelaEmprestimo.getSelectedRow() != -1) {
            ProdEstado pe = new ProdEstado();
            ProdDAO pd = new ProdDAO();
            pe.setStatusProduto("Disponível");
            pe.setIdProd(Integer.parseInt(txtIDInvisivel.getText()));
            JOptionPane.showMessageDialog(null, pe.getIdProd());
            pe.getStatusProduto();
            JOptionPane.showMessageDialog(null, pe.getStatusProduto());
            pd.AlterarStatus(pe);
            readJTable();
            readEmprestimo();
            limpar();
        } else {
            JOptionPane.showMessageDialog(null, "SELECIONE UMA LINHA");
        }

    }//GEN-LAST:event_botaodevolverActionPerformed

    private void TabelaProduto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaProduto2MouseClicked
        if (TabelaProduto2.getSelectedRow() != -1) {
            DefaultTableModel modelo1 = (DefaultTableModel) TabelaProduto2.getModel();
            String v0, v1, v2, v3, v4, v5, v6;
            v0 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 0).toString();
            v1 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 1).toString();
            v2 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 2).toString();
            v3 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 3).toString();
            v6 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 4).toString();
            v4 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 5).toString();
            v5 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 6).toString();
            txtIDInvisivel.setText(v0);
//            txtNome.setText(v1);
//            txtQuantidade.setText(v6);
//            txtDataEntrada.setText(v4);
            txtStatus.setText(v5);
            txtProduto.setText(v1);

            if (v2.equals("Descartável")) {
                cbTipo1.setSelectedIndex(0);
            } else {
                cbTipo1.setSelectedIndex(1);
            }
//            if (v3.equals("Estado")) {
//                cbOrigem.setSelectedIndex(0);
//            } else {
//                cbOrigem.setSelectedIndex(1);
//            }
        } else {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um produto!");
        }
    }//GEN-LAST:event_TabelaProduto2MouseClicked

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
    private javax.swing.JButton BotaoInserir;
    private javax.swing.JMenu MenuCadastrar;
    private javax.swing.JMenu MenuEmprestimoDevolucao;
    private javax.swing.JMenu MenuSair;
    private javax.swing.JMenu MenuSobre;
    private javax.swing.JPanel PainelCadastrar;
    private javax.swing.JPanel PainelEmprestimo;
    private javax.swing.JTable TabelaEmprestimo;
    private javax.swing.JTable TabelaProduto;
    private javax.swing.JTable TabelaProduto2;
    private javax.swing.JButton botaodevolver;
    private javax.swing.JButton botaoemprestar;
    private javax.swing.JComboBox<String> cbOrigem;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<String> cbTipo1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDataDevolucao;
    private javax.swing.JTextField txtDataEmprestimo;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDEmprestimo;
    private javax.swing.JTextField txtIDInvisivel;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtQuantidadeDeEmprestimo;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}
