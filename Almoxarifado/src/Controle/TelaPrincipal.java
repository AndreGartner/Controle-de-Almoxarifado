/**
 * @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira
 * Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso INFEM302 CEDUPHH
 */
package Controle;

import Controle.dao.ClienteDAO;
import Controle.dao.ControleDAO;
import Controle.dao.ControleProdDAO;
import Controle.dao.FuncionarioDAO;
import Controle.dao.ProdDAO;
import connection.ConnectionFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Cliente;
import model.bean.Controle;
import model.bean.ControleProd;
import model.bean.Funcionario;
import model.bean.ProdEstado;

public class TelaPrincipal extends javax.swing.JFrame {
    Cliente cliente                 = new Cliente();
    ProdDAO prodDAO                 = new ProdDAO();
    Controle controle               = new Controle();
    ClienteDAO clienteDAO           = new ClienteDAO();
    Funcionario funcionario         = new Funcionario();
    ControleDAO controleDAO         = new ControleDAO();
    ProdEstado produtoEstado        = new ProdEstado();
    ControleProd controleProduto    = new ControleProd();
    FuncionarioDAO funcionarioDAO   = new FuncionarioDAO();
    ControleProdDAO controleProdDAO = new ControleProdDAO();

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        //Determinando a Vizibilidade e Interação com os Componentes da ToolPalets
        PainelPrincipal.setVisible(true);
        btnAtualizarProd.setEnabled(false);
        BotaoInserir.setEnabled(false);
        
        botaocadastrarcliente.setEnabled(false);
        botaoconsultarcliente.setEnabled(false);

        botaoemprestar.setEnabled(false);
        botaodevolver.setEnabled(false);

        jScrollPane4.setEnabled(true);
        TabelaCliente.setVisible(true);

        jScrollPane3.setEnabled(false);
        TabelaEmprestimo.setEnabled(false);
        TabelaEmprestimo.setVisible(false);
        TabelaProduto2.setVisible(false);

        //Determinando o ID do cliente como Invisível.
        txtIDInvisivelCLI.setVisible(false);

        this.setLocationRelativeTo(null);
        productPanel.setVisible(false);
        PainelEmprestimo.setVisible(false);

        // Chamando os métodos de atualizações das tabelas do Banco de dados no Jtable
        DefaultTableModel modelo = (DefaultTableModel) TabelaProduto.getModel();
        TabelaProduto.setRowSorter(new TableRowSorter(modelo));
        readJTable();

        DefaultTableModel modelos = (DefaultTableModel) TabelaEmprestimo.getModel();
        TabelaEmprestimo.setRowSorter(new TableRowSorter(modelos));
        readEmprestimo();

        DefaultTableModel modelo1 = (DefaultTableModel) TabelaProduto2.getModel();
        TabelaProduto2.setRowSorter(new TableRowSorter(modelo1));
        readTabelaProduto();

        DefaultTableModel model = (DefaultTableModel) TabelaCliente.getModel();
        TabelaCliente.setRowSorter(new TableRowSorter(model));
        readJTableCli();
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
        txtQuantidadeDeEmprestimo.setText("");
        
        readEmprestimo();
        readJTable();
        readJTableCli();
        readTabelaProduto();
    }
    
    private String verifyUser() throws SQLException 
    {
        String codigoFuncionario;
        
        // Pegando o código digitado pelo usuário(para Segurança)
        codigoFuncionario = JOptionPane.showInputDialog("Insira o código de funcionário respectivo ao seu log-in.");
        
        // Verificação se o código inserido não é nulo
        if (codigoFuncionario == null || codigoFuncionario.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro! Insira o seu código.");
            return codigoFuncionario;
        }

        // Pesquisando se existe o funcionário do respectivo código digitado
        List<Funcionario> funcionarioList = funcionarioDAO.readIDFunc(Integer.parseInt(codigoFuncionario));

        if (funcionarioList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "código de funcionário inválido. Tente novamente.");
            return codigoFuncionario;
        }
        return codigoFuncionario;
    }

    // Método para Aparecer os dados de todas as tabelas envolvidas no Empréstimo no JTable
    private void readEmprestimo() {
        DefaultTableModel modelos = (DefaultTableModel) TabelaEmprestimo.getModel();
        modelos.setNumRows(0);
        
        controleProdDAO.getAllEmprestimos().forEach((emprestimo) -> {
            modelos.addRow(new Object[]{
                emprestimo.getIdControle(),
                emprestimo.getNomeCliente(),
                emprestimo.getNomeProduto(),
                emprestimo.getTipoProduto(),
                emprestimo.getQuantidadeProduto(),
                emprestimo.getStatusProduto(),
                emprestimo.getDataDevolucao(),
                emprestimo.getDataEmprestimo()
            });
        });
    }

    // Método para Aparecer os dados da tabela produto no JTable(Tela Empréstimo/Devolução)
    private void readTabelaProduto() {
        DefaultTableModel modelo1 = (DefaultTableModel) TabelaProduto2.getModel();
        modelo1.setNumRows(0);

        prodDAO.read().forEach((pe) -> {
            modelo1.addRow(new Object[]{
                pe.getIdProd(),
                pe.getNomeProd(),
                pe.getTipoProd(),
                pe.getOrigemProd(),
                pe.getQuantidade(),
                pe.getDataEntradaProd(),
                pe.getStatusProduto()
            });
        });

    }

    // Método para Aparecer os dados da tabela produto no JTable(Tela Cadastro Produto)
    private void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) TabelaProduto.getModel();
        modelo.setNumRows(0);

        prodDAO.read().forEach((pe) -> {
            modelo.addRow(new Object[]{
                pe.getIdProd(),
                pe.getNomeProd(),
                pe.getTipoProd(),
                pe.getOrigemProd(),
                pe.getQuantidade(),
                pe.getDataEntradaProd(),
                pe.getStatusProduto()
            });
        });

    }

    // Método para Aparecer os dados da tabela Cliente no JTable
    private void readJTableCli() {
        DefaultTableModel model = (DefaultTableModel) TabelaCliente.getModel();
        model.setNumRows(0);

        try {
            clienteDAO.read().forEach((c) -> {
                model.addRow(new Object[]{
                    c.getIdCli(),
                    c.getNomeCli(),
                });
            });
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Método para pesquisar na Tabela produto pelo Nome do Produto(Tela Empréstimo/Devolução)
    public void readJTableObterPeloNome(String Nomeprod) {
        DefaultTableModel modelo = (DefaultTableModel) TabelaProduto2.getModel();
        modelo.setNumRows(0);

        prodDAO.obterPeloNome(Nomeprod).forEach((pe) -> {
            modelo.addRow(new Object[]{
                pe.getIdProd(),
                pe.getNomeProd(),
                pe.getTipoProd(),
                pe.getOrigemProd(),
                pe.getQuantidade(),
                pe.getDataEntradaProd(),
                pe.getStatusProduto()
            });
        });

    }

    // Método para pesquisar na Tabela Cliente pelo Nome do Cliente
    public void readJTableObterPeloNomeCliente(String NomeCli) {
        DefaultTableModel modelo = (DefaultTableModel) TabelaCliente.getModel();
        modelo.setNumRows(0);

        try {
            clienteDAO.obterPeloNome(NomeCli).forEach((cliente) -> {
                modelo.addRow(new Object[]{
                    cliente.getIdCli(),
                    cliente.getNomeCli()
                });
            });
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Método para pesquisar na Tabela produto pelo Nome do Produto(Tela Cadastro Produto)
    public void readJTableObterPeloNomeCADASTRO(String Nomeprod) {
        DefaultTableModel modelo = (DefaultTableModel) TabelaProduto.getModel();
        modelo.setNumRows(0);

        prodDAO.obterPeloNome(Nomeprod).forEach((pe) -> {
            modelo.addRow(new Object[]{
                pe.getIdProd(),
                pe.getNomeProd(),
                pe.getTipoProd(),
                pe.getOrigemProd(),
                pe.getQuantidade(),
                pe.getDataEntradaProd(),
                pe.getStatusProduto()
            });
        });
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
        PainelPrincipal = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        productPanel = new javax.swing.JPanel();
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
        btnAtualizarProd = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        botaoiniciaratualizacao = new javax.swing.JButton();
        botaoiniciarcadastramento = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        TabelaCliente = new javax.swing.JTable();
        txtIDInvisivelCLI = new javax.swing.JTextField();
        botaocadastrarcliente = new javax.swing.JButton();
        botaoconsultarcliente = new javax.swing.JButton();
        botaorealizaremprestimo = new javax.swing.JButton();
        botaorealizardevolucao = new javax.swing.JButton();
        LabelTabelaCliente = new javax.swing.JLabel();
        LabelTabelaEmprestimo = new javax.swing.JLabel();
        LabelTabelaProduto = new javax.swing.JLabel();
        btnRealizarCadCli = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        ProductItem = new javax.swing.JMenu();
        loanItem = new javax.swing.JMenu();
        reportItem = new javax.swing.JMenu();
        loanReport = new javax.swing.JMenu();
        clientReport = new javax.swing.JMenu();
        aboutItem = new javax.swing.JMenu();
        exitItem = new javax.swing.JMenu();

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

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Controle/logoceduphh.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel19.setText("ALMOXARIFADO");

        javax.swing.GroupLayout PainelPrincipalLayout = new javax.swing.GroupLayout(PainelPrincipal);
        PainelPrincipal.setLayout(PainelPrincipalLayout);
        PainelPrincipalLayout.setHorizontalGroup(
            PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPrincipalLayout.createSequentialGroup()
                .addGroup(PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelPrincipalLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel18))
                    .addGroup(PainelPrincipalLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel19)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        PainelPrincipalLayout.setVerticalGroup(
            PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPrincipalLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel19)
                .addGap(76, 76, 76)
                .addComponent(jLabel18)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main");
        setMinimumSize(new java.awt.Dimension(540, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(540, 650));

        productPanel.setBackground(new java.awt.Color(217, 217, 243));
        productPanel.setMaximumSize(new java.awt.Dimension(540, 650));
        productPanel.setMinimumSize(new java.awt.Dimension(540, 650));
        productPanel.setPreferredSize(new java.awt.Dimension(540, 650));

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TabelaProdutoMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(TabelaProduto);

        jLabel2.setText("ID");

        txtID.setEditable(false);
        txtID.setToolTipText("Este campo não está diponível para edição devido que ele será incrementado automáticamente pelo sistema.");
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

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
        BotaoInserir.setToolTipText("Após preencher todos os campos com seus respectivos dados clique neste botão para efetuar o cadastramento.");
        BotaoInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoInserirActionPerformed(evt);
            }
        });

        jLabel15.setText("Quantidade");

        btnAtualizarProd.setText("Atualizar");
        btnAtualizarProd.setToolTipText("\nSelecione o produto desejado na tabela por meio de um clique, após isso reescreva no campo de texto da quantidade a quantidade que desejas adicionar.");
        btnAtualizarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarProdActionPerformed(evt);
            }
        });

        btnConsulta.setText("Consulta");
        btnConsulta.setToolTipText("Realiza a consulta na tabela a partir da informação contida no campo de texto referente ao nome do produto");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        botaoiniciaratualizacao.setText("Iniciar processo de atualização");
        botaoiniciaratualizacao.setToolTipText("Iníciará o processo de atualização da quantidade de algum produto existente já na tabela.");
        botaoiniciaratualizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoiniciaratualizacaoActionPerformed(evt);
            }
        });

        botaoiniciarcadastramento.setText("Iniciar processo de cadastramento");
        botaoiniciarcadastramento.setToolTipText("Iníciará o processo de cadastramento do produto na tabela.");
        botaoiniciarcadastramento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoiniciarcadastramentoActionPerformed(evt);
            }
        });

        jButton3.setText("Limpar");
        jButton3.setToolTipText("Com este botão será limpado todos os campos de textos e removerá a seleção na tabela.");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Tabela dos produtos");

        javax.swing.GroupLayout productPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, productPanelLayout.createSequentialGroup()
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAtualizarProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotaoInserir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(botaoiniciarcadastramento))
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(botaoiniciaratualizacao)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(29, 29, 29))
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(108, 108, 108)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))))
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel17)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)))
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAtualizarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoiniciaratualizacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoiniciarcadastramento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );

        PainelEmprestimo.setBackground(new java.awt.Color(217, 217, 243));
        PainelEmprestimo.setBorder(new javax.swing.border.MatteBorder(null));
        PainelEmprestimo.setMaximumSize(new java.awt.Dimension(540, 650));
        PainelEmprestimo.setMinimumSize(new java.awt.Dimension(540, 650));
        PainelEmprestimo.setPreferredSize(new java.awt.Dimension(540, 650));

        jLabel8.setText("ID");

        txtIDEmprestimo.setEditable(false);

        jLabel9.setText("Nome do Cliente");

        jLabel10.setText("Produto");

        jLabel11.setText("Tipo do produto");

        txtCliente.setEditable(false);

        txtProduto.setEditable(false);

        jLabel12.setText("Data de Empréstimo");

        jLabel13.setText("Data de Devolução");

        botaoemprestar.setText("Emprestar");
        botaoemprestar.setToolTipText("Realiza a ação de emprestimo. Selecione o cliente desejado na sua tabela e o produto desejado na sua tabela, insira nos campos de texto as datas de emprestimo e devolução e a quantidade.");
        botaoemprestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoemprestarActionPerformed(evt);
            }
        });

        botaodevolver.setText("Devolver");
        botaodevolver.setToolTipText("Realiza a devolução. Selecione um empréstimo na sua tabela, o produto existente na tabela produto.");
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
        TabelaEmprestimo.setToolTipText("TABELA DOS EMPRÉSTIMOS");
        TabelaEmprestimo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabelaEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaEmprestimoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TabelaEmprestimo);

        cbTipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descartável", "Reutilizável", " " }));

        txtIDInvisivel.setEditable(false);
        txtIDInvisivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDInvisivelActionPerformed(evt);
            }
        });

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
        txtQuantidadeDeEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtQuantidadeDeEmprestimoMouseClicked(evt);
            }
        });

        TabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelaCliente.setMaximumSize(new java.awt.Dimension(150, 64));
        TabelaCliente.setMinimumSize(new java.awt.Dimension(150, 64));
        TabelaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaClienteMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TabelaCliente);

        txtIDInvisivelCLI.setEditable(false);

        botaocadastrarcliente.setText("Cadastrar Cliente");
        botaocadastrarcliente.setToolTipText("Pressione este botão para cadastrar o cliente que deseja realizar um empréstimo, caso ainda não há o cliente na tabela.");
        botaocadastrarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaocadastrarclienteActionPerformed(evt);
            }
        });

        botaoconsultarcliente.setText("Consultar Cliente");
        botaoconsultarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoconsultarclienteActionPerformed(evt);
            }
        });

        botaorealizaremprestimo.setText("Realizar Empréstimo");
        botaorealizaremprestimo.setToolTipText("Inícia o processo de empréstimo.");
        botaorealizaremprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaorealizaremprestimoActionPerformed(evt);
            }
        });

        botaorealizardevolucao.setText("Realizar Devolução");
        botaorealizardevolucao.setToolTipText("Inícia o processo de devolução");
        botaorealizardevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaorealizardevolucaoActionPerformed(evt);
            }
        });

        LabelTabelaCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelTabelaCliente.setText("Tabela Cliente");

        LabelTabelaEmprestimo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelTabelaEmprestimo.setText("Tabela Empréstimo");

        LabelTabelaProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelTabelaProduto.setText("Tabela Produto");

        btnRealizarCadCli.setText("Iniciar Cadastro do Cliente");
        btnRealizarCadCli.setToolTipText("Inicialização do processo de cadastramento de cliente.");
        btnRealizarCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarCadCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelEmprestimoLayout = new javax.swing.GroupLayout(PainelEmprestimo);
        PainelEmprestimo.setLayout(PainelEmprestimoLayout);
        PainelEmprestimoLayout.setHorizontalGroup(
            PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(LabelTabelaProduto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(LabelTabelaEmprestimo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelEmprestimoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                        .addComponent(botaocadastrarcliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botaoconsultarcliente))
                                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(LabelTabelaCliente)))
                                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtIDInvisivel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtQuantidadeDeEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelEmprestimoLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel12)
                                                        .addComponent(jLabel13))
                                                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                                        .addGap(85, 85, 85)
                                                        .addComponent(jLabel14))
                                                    .addComponent(jLabel11))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbTipo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtDataDevolucao)
                                                    .addComponent(txtDataEmprestimo))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                        .addComponent(txtIDInvisivelCLI, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(botaoemprestar)
                                            .addComponent(botaorealizaremprestimo)
                                            .addComponent(botaorealizardevolucao)
                                            .addComponent(botaodevolver)
                                            .addComponent(btnRealizarCadCli))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelEmprestimoLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtIDEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        PainelEmprestimoLayout.setVerticalGroup(
            PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelEmprestimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtIDEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(txtIDInvisivel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtIDInvisivelCLI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(txtDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaocadastrarcliente)
                            .addComponent(botaoconsultarcliente))
                        .addGap(10, 10, 10)
                        .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaorealizaremprestimo)
                            .addComponent(LabelTabelaCliente)))
                    .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtQuantidadeDeEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelEmprestimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelEmprestimoLayout.createSequentialGroup()
                        .addComponent(botaoemprestar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaorealizardevolucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaodevolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRealizarCadCli))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LabelTabelaEmprestimo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelTabelaProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );

        ProductItem.setText("Product");
        ProductItem.setName(""); // NOI18N
        ProductItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductItemMouseClicked(evt);
            }
        });
        jMenuBar1.add(ProductItem);

        loanItem.setText("Loan");
        loanItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loanItemMouseClicked(evt);
            }
        });
        jMenuBar1.add(loanItem);

        reportItem.setText("Report");

        loanReport.setText("Loan");
        loanReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loanReportMouseClicked(evt);
            }
        });
        reportItem.add(loanReport);

        clientReport.setText("Client");
        clientReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientReportMouseClicked(evt);
            }
        });
        reportItem.add(clientReport);

        jMenuBar1.add(reportItem);

        aboutItem.setText("About");
        aboutItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutItemMouseClicked(evt);
            }
        });
        aboutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutItemActionPerformed(evt);
            }
        });
        jMenuBar1.add(aboutItem);

        exitItem.setText("Exit");
        exitItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitItemMouseClicked(evt);
            }
        });
        jMenuBar1.add(exitItem);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(productPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        setSize(new java.awt.Dimension(1102, 689));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void aboutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutItemActionPerformed
    }//GEN-LAST:event_aboutItemActionPerformed

    private void exitItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitItemMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitItemMouseClicked

    private void aboutItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutItemMouseClicked
        new Sobre().setVisible(true);
        dispose();
    }//GEN-LAST:event_aboutItemMouseClicked

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
    }//GEN-LAST:event_cbTipoActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
    }//GEN-LAST:event_txtNomeActionPerformed
    
// Quando clicar no menu de cadastro de produto 
    private void ProductItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductItemMouseClicked
        productPanel.setVisible(true);
        PainelEmprestimo.setVisible(false);
        jLabel2.setVisible(false);
        txtID.setVisible(false);
        btnAtualizarProd.setEnabled(false);
        BotaoInserir.setEnabled(false);
        PainelPrincipal.setVisible(false);

        botaoemprestar.setEnabled(false);
        botaodevolver.setEnabled(false);

        jScrollPane4.setEnabled(true);
        TabelaCliente.setVisible(true);

        jScrollPane3.setEnabled(false);
        TabelaEmprestimo.setEnabled(false);
        TabelaEmprestimo.setVisible(false);
        TabelaProduto2.setVisible(false);

        txtID.setEnabled(false);
        txtNome.setEnabled(false);
        cbTipo.setEnabled(false);
        cbOrigem.setEnabled(false);
        txtDataEntrada.setEnabled(false);
        txtStatus.setEnabled(false);
        txtQuantidade.setEnabled(false);
        limpar();
    }//GEN-LAST:event_ProductItemMouseClicked

    // Quando clicar no menu de Empréstimo/Devolução
    private void loanItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loanItemMouseClicked
        productPanel.setVisible(false);
        PainelEmprestimo.setVisible(true);
        txtIDInvisivel.setVisible(false);
        btnAtualizarProd.setEnabled(false);
        BotaoInserir.setEnabled(false);
        botaoconsultarcliente.setEnabled(false);
        PainelPrincipal.setVisible(false);

        botaoemprestar.setEnabled(false);
        botaodevolver.setEnabled(false);

        jScrollPane4.setEnabled(false);
        jScrollPane4.setVisible(true);
        TabelaCliente.setVisible(false);

        jScrollPane3.setEnabled(false);
        jScrollPane3.setVisible(true);
        TabelaEmprestimo.setEnabled(false);
        TabelaEmprestimo.setVisible(false);
        TabelaProduto2.setVisible(false);

        txtIDEmprestimo.setEnabled(false);
        txtCliente.setEnabled(false);
        txtProduto.setEnabled(false);
        cbTipo1.setEnabled(false);
        txtDataEmprestimo.setEnabled(false);
        txtDataDevolucao.setEnabled(false);
        txtQuantidadeDeEmprestimo.setEnabled(false);
        limpar();
    }//GEN-LAST:event_loanItemMouseClicked

    private void BotaoInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoInserirActionPerformed
        produtoEstado.setOrigemProd((String) cbOrigem.getSelectedItem());
        produtoEstado.setTipoProd(String.valueOf(cbTipo.getSelectedItem()));
        produtoEstado.setStatusProduto(txtStatus.getText());

        // Validação para se os campos estão sendo preechidos ou não(vale para todos os campos!!)
        if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo nome inválido!");
            return;
        } 

        if ((txtDataEntrada.getText() == null || txtDataEntrada.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Você deve escrever no formato de uma data!");
            return;
        } 

        produtoEstado.setNomeProd(txtNome.getText());
        produtoEstado.setDataEntradaProd(ConnectionFactory.formatDate(txtDataEntrada.getText()));

        if (produtoEstado.getDataEntradaProd() == null) {
            JOptionPane.showMessageDialog(null, "A Data deve condizer com a realidade!!");
            return;
        } 
        
        if ((txtQuantidade.getText() == null || txtQuantidade.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Insira algum valor para o campo de texto Quantidade!");
            return;
        } 

        int quantidade = Integer.parseInt(txtQuantidade.getText());

        if (quantidade <= 0) {
            JOptionPane.showMessageDialog(null, "Quantidade inválida, deve ser maior ou igual a 1!");
            return;
        }
            
        produtoEstado.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

        prodDAO.inserir(produtoEstado);
        
        readJTable();
        readEmprestimo();
        limpar();
    }//GEN-LAST:event_BotaoInserirActionPerformed

    // Quando a tabela produto ser clicada
    private void TabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaProdutoMouseClicked
        if (TabelaProduto.getSelectedRow() != -1) {
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

        } else {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um produto!");
        }       
    }//GEN-LAST:event_TabelaProdutoMouseClicked

    private void botaoemprestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoemprestarActionPerformed
        String codigoFuncionario;
        int quantidadeProduto;
        int quantidadeEmprestimo;

        try {
            codigoFuncionario = verifyUser();

            if (TabelaProduto2.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela produto.");
                return;
            }

            String statusProduct = prodDAO.getProdStatusById(Integer.parseInt(TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 0).toString()));

            if (statusProduct.trim().equals("Indisponível") || statusProduct.isEmpty()) {
                JOptionPane.showMessageDialog(null, "O produto se encontra indisponível. Tente mais tarde.");
                return;
            }

            if (TabelaCliente.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela cliente");
                return;
            }

            controle.setDataEmprestimo(ConnectionFactory.formatDate(txtDataEmprestimo.getText()));
            controle.setDataDevolucao(ConnectionFactory.formatDate(txtDataDevolucao.getText()));
            
            if (controle.getDataEmprestimo() == null) {
                JOptionPane.showMessageDialog(null, "A Data de empréstimo deve condizer com a realidade!!");
                return;
            } 
            
            if (controle.getDataDevolucao()== null) {
                JOptionPane.showMessageDialog(null, "A Data de devolução deve condizer com a realidade!!");
                return;
            } 

            int quantidadedeemprestimo = Integer.parseInt(txtQuantidadeDeEmprestimo.getText());

            if (quantidadedeemprestimo <= 0) {
                JOptionPane.showMessageDialog(null, "Quantidade inválida, deve ser maior ou igual a 1!");
                return;
            }
            
            //Chamando os métodos
            produtoEstado.setIdProd(Integer.parseInt(txtIDInvisivel.getText())); //Setando o ID do produto conforme está no TextField do Id invisível.
            produtoEstado.setTipoProd(cbTipo1.getSelectedItem().toString()); //Setando o Tipo do produto
            
            funcionario.setIdFunc(Integer.parseInt(codigoFuncionario));
            
            cliente.setNomeCli(txtCliente.getText());
            cliente.setIdCli(txtIDInvisivelCLI.getText());
            
            controle.setCliente(cliente);
            controle.setFuncionario(funcionario);

            //Deve realizar o cálculo de subtração com as colunas de quantidade do controle-produto com a quantidade do produto.
            quantidadeProduto = Integer.parseInt(TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 4).toString());
            quantidadeEmprestimo = Integer.parseInt(txtQuantidadeDeEmprestimo.getText());

            if (quantidadeProduto < quantidadeEmprestimo) {
                JOptionPane.showMessageDialog(
                    null,
                    "Não há quantidade suficiente no estoque para o empréstimo. Retire uma quantidade menor."
                );

                return;
            }

            controleDAO.inserir(controle);

            int idControl = controleDAO.getFirstId();

            if (idControl > -1) {
                txtID.setText(Integer.toString(idControl));
            }

            controleProduto.setQtdProd(Integer.parseInt(txtQuantidadeDeEmprestimo.getText()));
            controle.setIdControle(Integer.parseInt(txtID.getText()));
            produtoEstado.setIdProd(Integer.parseInt(TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 0).toString()));

            controleProduto.setControle(controle);
            controleProduto.setProduto(produtoEstado);
            controleProdDAO.inserir(controleProduto);


            controleProduto.setQtdProd(quantidadedeemprestimo);
            int idProduct = Integer.parseInt(txtIDInvisivel.getText());

            // Atualizando o valor da quantidade na tabela produto
            prodDAO.updateProductAmountById(quantidadedeemprestimo, idProduct);

            if ((quantidadeProduto - quantidadeEmprestimo) == 0) {
                produtoEstado.setStatusProduto("Indisponível");
                prodDAO.AlterarStatus(produtoEstado);
                readEmprestimo();
            }

            JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");

            readJTable();
            readTabelaProduto(); // Método de Colocar na tabela os dados do mysql
            readEmprestimo();// Método de Colocar na tabela os dados do mysql
            limpar(); //Limpar os campos de texto

            botaocadastrarcliente.setEnabled(true);
            botaoconsultarcliente.setEnabled(true);
            TabelaCliente.setEnabled(false);
            botaoemprestar.setEnabled(false);
            TabelaEmprestimo.setEnabled(false);
            TabelaProduto2.setEnabled(false);
            txtDataDevolucao.setEnabled(false);
            txtDataEmprestimo.setEnabled(false);
            txtQuantidade.setEnabled(false);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tente Novamente.");
        }
    }//GEN-LAST:event_botaoemprestarActionPerformed

    // Quando a tabela Empréstimo ser clicada
    private void TabelaEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaEmprestimoMouseClicked
        if (TabelaEmprestimo.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um produto!");
            return;
        }
        
        String v0, v1, v2, v3, v4, v6, v7;
        v0 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 0).toString();
        v1 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 1).toString();
        v2 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 2).toString();
        v3 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 3).toString();
        v4 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 4).toString();
        v6 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 6).toString();
        v7 = TabelaEmprestimo.getValueAt(TabelaEmprestimo.getSelectedRow(), 7).toString();

        txtIDEmprestimo.setText(v0);
        txtCliente.setText(v1);
        txtProduto.setText(v2);
        txtQuantidadeDeEmprestimo.setText(v4);
        txtDataEmprestimo.setText(v6);
        txtDataDevolucao.setText(v7);

        readJTableObterPeloNome(txtProduto.getText());

        if (v3.equals("Descartável")) {
            cbTipo1.setSelectedIndex(0);
        } else {
            cbTipo1.setSelectedIndex(1);
        }
    }//GEN-LAST:event_TabelaEmprestimoMouseClicked

    private void botaodevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaodevolverActionPerformed
        try {
            verifyUser();
            
            if (TabelaEmprestimo.getSelectedRow() != -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela empréstimo.");
                return;
            }
            
            if ((TabelaProduto2.getSelectedRow() != -1)) {                
                JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela produto.");
                return;
            }

            int quantidadeemprestimo = Integer.parseInt(txtQuantidadeDeEmprestimo.getText());

            int quantidadeProduto = (Integer.parseInt(txtIDInvisivel.getText()));

            if (quantidadeemprestimo < 0) {                
                JOptionPane.showMessageDialog(null, "Você deve inserir um valor maior que zero!");
                return;
            }

            prodDAO.updateProductAmountById(quantidadeemprestimo, quantidadeProduto);
            
            produtoEstado.setStatusProduto("");
            produtoEstado.setStatusProduto("Disponível");

            prodDAO.AlterarStatusParaDisponível(produtoEstado);
            readTabelaProduto();
            limpar();

            txtCliente.setEnabled(true);
            txtProduto.setEnabled(true);
            cbTipo1.setEnabled(true);
            txtDataEmprestimo.setEnabled(true);
            txtDataDevolucao.setEnabled(true);
            txtQuantidadeDeEmprestimo.setEnabled(true);
            TabelaEmprestimo.setEnabled(false);
            TabelaProduto2.setEnabled(false);
            TabelaCliente.setVisible(true);

            JOptionPane.showMessageDialog(null, "Produto Devolvido com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaodevolverActionPerformed

    // Quando a tabela Produto ser clicada(Tela Empréstimo/Devolução)
    private void TabelaProduto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaProduto2MouseClicked
        if (TabelaProduto2.getSelectedRow() < 0) { 
            JOptionPane.showMessageDialog(null, "Você deve selecionar um produto!");
            return;
        }
        
        String v0, v1, v2, v3;
        v0 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 0).toString();
        v1 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 1).toString();
        v2 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 2).toString();
        v3 = TabelaProduto2.getValueAt(TabelaProduto2.getSelectedRow(), 4).toString();

        txtIDInvisivel.setText(v0);
        txtProduto.setText(v1);

        if (v2.equals("Descartável")) {
            cbTipo1.setSelectedIndex(0);
        } else {
            cbTipo1.setSelectedIndex(1);
        }
        if (v3.equals("Estado")) {
            cbOrigem.setSelectedIndex(0);
        } else {
            cbOrigem.setSelectedIndex(1);
        }
    }//GEN-LAST:event_TabelaProduto2MouseClicked

    private void botaocadastrarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaocadastrarclienteActionPerformed
        TabelaCliente.setVisible(true);
        TabelaCliente.setEnabled(false);
        
        String nomedocliente = txtCliente.getText();

        if ((nomedocliente.trim().equals("") || nomedocliente == null)) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Insira algum nome.");
        } else {
            cliente.setNomeCli(nomedocliente);
            clienteDAO.inserir(cliente);
            readJTableCli();
            readTabelaProduto();
            limpar();
        }
    }//GEN-LAST:event_botaocadastrarclienteActionPerformed

    private void TabelaProdutoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaProdutoMouseEntered
    }//GEN-LAST:event_TabelaProdutoMouseEntered

    private void btnAtualizarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarProdActionPerformed
        if (TabelaProduto.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um produto!");
            return;
        }
            
        produtoEstado.setIdProd(Integer.parseInt(txtID.getText()));
        produtoEstado.setNomeProd(txtNome.getText());
        produtoEstado.setTipoProd((String) cbTipo.getSelectedItem());
        produtoEstado.setOrigemProd((String) cbOrigem.getSelectedItem());
        produtoEstado.setDataEntradaProd(txtDataEntrada.getText());
        produtoEstado.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        produtoEstado.setStatusProduto("Disponível");
        
        if (produtoEstado.getQuantidade() < 1) {
            produtoEstado.setStatusProduto("Indisponível");
        }

        prodDAO.alterar(produtoEstado);
        readJTable();
    }//GEN-LAST:event_btnAtualizarProdActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        String nome = JOptionPane.showInputDialog("Nome desejado.");
        
        if (nome == null) return;
        
        if (nome.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro, insira alguma informação no campo de texto do nome do produto.");
            return;
        }
        readJTableObterPeloNomeCADASTRO(nome);
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void botaoconsultarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoconsultarclienteActionPerformed
        String nomeCliente = JOptionPane.showInputDialog("Nome do cliente para consulta: ");
        if (nomeCliente.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar! Insira algum nome.");
            return;
        }
        readJTableObterPeloNomeCliente(nomeCliente);
    }//GEN-LAST:event_botaoconsultarclienteActionPerformed

    private void botaoiniciaratualizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoiniciaratualizacaoActionPerformed
        btnAtualizarProd.setEnabled(true);
        BotaoInserir.setEnabled(false);
        txtQuantidade.setEnabled(true);
        txtID.setEnabled(false);
        txtNome.setEnabled(false);
        cbTipo.setEnabled(false);
        cbOrigem.setEnabled(false);
        txtDataEntrada.setEnabled(false);
        txtStatus.setEnabled(false);

        limpar();
    }//GEN-LAST:event_botaoiniciaratualizacaoActionPerformed

    private void botaoiniciarcadastramentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoiniciarcadastramentoActionPerformed
        BotaoInserir.setEnabled(true);
        btnAtualizarProd.setEnabled(false);
        txtID.setEnabled(true);
        txtNome.setEnabled(true);
        cbTipo.setEnabled(true);
        cbOrigem.setEnabled(true);
        txtDataEntrada.setEnabled(true);
        txtStatus.setEnabled(true);
        txtQuantidade.setEnabled(true);

        limpar();
    }//GEN-LAST:event_botaoiniciarcadastramentoActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
    }//GEN-LAST:event_txtIDActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limpar();
        productPanel.setVisible(true);
        PainelEmprestimo.setVisible(false);
        jLabel2.setVisible(false);
        txtID.setVisible(false);
        btnAtualizarProd.setEnabled(false);
        BotaoInserir.setEnabled(false);

        botaoemprestar.setEnabled(false);
        botaodevolver.setEnabled(false);

        jScrollPane4.setEnabled(true);
        TabelaCliente.setVisible(true);

        jScrollPane3.setEnabled(false);
        TabelaEmprestimo.setEnabled(false);
        TabelaEmprestimo.setVisible(false);
        TabelaProduto2.setVisible(false);

        txtID.setEnabled(false);
        txtNome.setEnabled(false);
        cbTipo.setEnabled(false);
        cbOrigem.setEnabled(false);
        txtDataEntrada.setEnabled(false);
        txtStatus.setEnabled(false);
        txtQuantidade.setEnabled(false);
        readJTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botaorealizaremprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaorealizaremprestimoActionPerformed
        TabelaProduto2.setVisible(true);
        botaoemprestar.setEnabled(true);
        TabelaCliente.setVisible(true);
        TabelaCliente.setEnabled(true);
        jScrollPane4.setVisible(true);
        jScrollPane3.setVisible(false);
        LabelTabelaEmprestimo.setVisible(false);
        LabelTabelaCliente.setVisible(true);
        botaocadastrarcliente.setEnabled(true);
        botaoconsultarcliente.setEnabled(true);
        botaodevolver.setEnabled(false);
        txtDataEmprestimo.setEnabled(true);
        txtDataDevolucao.setEnabled(true);
        txtCliente.setEnabled(false);
        txtQuantidadeDeEmprestimo.setEnabled(true);
        TabelaProduto2.setEnabled(true);
        
        JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela cliente e um produto na tabela produto.");
        readTabelaProduto();
        limpar();
    }//GEN-LAST:event_botaorealizaremprestimoActionPerformed

    // Quando a tabela Cliente for clicada
    private void TabelaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaClienteMouseClicked
        if (TabelaCliente.getSelectedRow() != -1) {     
            JOptionPane.showMessageDialog(null, "Você deve selecionar um cliente!");
            return;
        }
        
        String v0, v1;
        v0 = TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 0).toString();
        v1 = TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 1).toString();

        txtIDInvisivelCLI.setText(v0);
        txtCliente.setText(v1);

        JOptionPane.showMessageDialog(jMenu1, "Preencha o campo da data de empréstimo, data de devolução e quantidade caso esteja efetuando um empréstimo.");
    }//GEN-LAST:event_TabelaClienteMouseClicked

    private void loanReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loanReportMouseClicked
        Relatorio r = new Relatorio();
        r.setVisible(true);
    }//GEN-LAST:event_loanReportMouseClicked

    private void clientReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientReportMouseClicked
        int opcao = JOptionPane.showConfirmDialog(null, "Você deseja realizar uma consulta na tabela de clientes?", " Sim ou Não?", JOptionPane.YES_NO_OPTION);
        if (opcao == 0) {
            ConsultaCliente cc = new ConsultaCliente();
            cc.setVisible(true);
        }
    }//GEN-LAST:event_clientReportMouseClicked

    private void botaorealizardevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaorealizardevolucaoActionPerformed
        TabelaProduto2.setVisible(true);
        TabelaProduto2.setEnabled(true);
        botaoemprestar.setEnabled(false);
        botaodevolver.setEnabled(true);
        jScrollPane3.setVisible(true);
        jScrollPane3.setEnabled(true);
        TabelaEmprestimo.setVisible(true);
        TabelaEmprestimo.setEnabled(true);
        jScrollPane4.setVisible(false);
        LabelTabelaEmprestimo.setVisible(true);
        LabelTabelaCliente.setVisible(false);
        botaocadastrarcliente.setEnabled(false);
        botaoconsultarcliente.setEnabled(false);
        txtIDEmprestimo.setEnabled(false);
        txtCliente.setEnabled(false);
        txtProduto.setEnabled(false);
        cbTipo1.setEnabled(false);
        txtDataEmprestimo.setEnabled(false);
        txtDataDevolucao.setEnabled(false);
        txtQuantidadeDeEmprestimo.setEnabled(false);
        
        readEmprestimo();
        readJTableCli();
        readTabelaProduto();
        readJTable();
        limpar();
    }//GEN-LAST:event_botaorealizardevolucaoActionPerformed

    private void txtIDInvisivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDInvisivelActionPerformed
    }//GEN-LAST:event_txtIDInvisivelActionPerformed

    private void txtQuantidadeDeEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQuantidadeDeEmprestimoMouseClicked
    }//GEN-LAST:event_txtQuantidadeDeEmprestimoMouseClicked

    private void btnRealizarCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCadCliActionPerformed
        TabelaCliente.setVisible(true);
        TabelaCliente.setEnabled(true);
        jScrollPane4.setVisible(true);
        LabelTabelaCliente.setVisible(true);
        txtDataDevolucao.setEnabled(false);
        txtDataEmprestimo.setEnabled(false);
        txtQuantidadeDeEmprestimo.setEnabled(false);
        botaodevolver.setEnabled(false);
        botaoemprestar.setEnabled(false);
        botaocadastrarcliente.setEnabled(true);
        botaoconsultarcliente.setEnabled(true);
        txtCliente.setEnabled(true);
        txtCliente.setEditable(true);
        
        JOptionPane.showMessageDialog(null, "Insira o cliente desejado no campo de texto habilitado!");

        readJTableCli();
        limpar();
    }//GEN-LAST:event_btnRealizarCadCliActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoInserir;
    private javax.swing.JLabel LabelTabelaCliente;
    private javax.swing.JLabel LabelTabelaEmprestimo;
    private javax.swing.JLabel LabelTabelaProduto;
    private javax.swing.JPanel PainelEmprestimo;
    private javax.swing.JPanel PainelPrincipal;
    private javax.swing.JMenu ProductItem;
    private javax.swing.JTable TabelaCliente;
    private javax.swing.JTable TabelaEmprestimo;
    private javax.swing.JTable TabelaProduto;
    private javax.swing.JTable TabelaProduto2;
    private javax.swing.JMenu aboutItem;
    private javax.swing.JButton botaocadastrarcliente;
    private javax.swing.JButton botaoconsultarcliente;
    private javax.swing.JButton botaodevolver;
    private javax.swing.JButton botaoemprestar;
    private javax.swing.JButton botaoiniciaratualizacao;
    private javax.swing.JButton botaoiniciarcadastramento;
    private javax.swing.JButton botaorealizardevolucao;
    private javax.swing.JButton botaorealizaremprestimo;
    private javax.swing.JButton btnAtualizarProd;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnRealizarCadCli;
    private javax.swing.JComboBox<String> cbOrigem;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<String> cbTipo1;
    private javax.swing.JMenu clientReport;
    private javax.swing.JMenu exitItem;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu loanItem;
    private javax.swing.JMenu loanReport;
    private javax.swing.JPanel productPanel;
    private javax.swing.JMenu reportItem;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDataDevolucao;
    private javax.swing.JTextField txtDataEmprestimo;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDEmprestimo;
    private javax.swing.JTextField txtIDInvisivel;
    private javax.swing.JTextField txtIDInvisivelCLI;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtQuantidadeDeEmprestimo;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}
