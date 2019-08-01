/**
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira
 * Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso INFEM302 CEDUPHH
 */
package Controle.dao;

import connection.ConnectionFactory;
import static connection.ConnectionFactory.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.ProdEstado;

public class ProdDAO implements OverDAO<ProdEstado> {
    final ProdEstado pe = new ProdEstado();
    private PreparedStatement stmt;
    private ResultSet rs;

    /**
     * Cria um objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int inserir(ProdEstado objeto) 
    {
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement(
                "INSERT INTO produto (ID_PRODUTO, NOME_PRODUTO, TIPO_PRODUTO, "
                + "ORIGEM_PRODUTO, DATA_ENTRADA_PROD,STATUS_PRODUTO,QUANTIDADE_PRODUTO_ENTRADO) VALUES (?,?,?,?,?,?,?) ;"
            );

            stmt.setInt(1, objeto.getIdProd());
            stmt.setString(2, objeto.getNomeProd());
            stmt.setString(3, objeto.getTipoProd());
            stmt.setString(4, objeto.getOrigemProd());
            stmt.setString(5, objeto.getDataEntradaProd());
            stmt.setString(6, objeto.getStatusProduto());
            stmt.setInt(7,objeto.getQuantidade());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        }
        
        return objeto.getIdProd();
    }

    /**
     * Atualiza Objeto
     * 
     * @param objeto
     * @return
     */
    @Override
    public int alterar(ProdEstado objeto)
    {
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement(
                "UPDATE Produto SET NOME_PRODUTO = ?, TIPO_PRODUTO = ?, ORIGEM_PRODUTO = ?,"
                + "DATA_ENTRADA_PROD = ?,STATUS_PRODUTO = ?,QUANTIDADE_PRODUTO_ENTRADO=? "
                + "WHERE ID_PRODUTO = ?;"
            );

            stmt.setInt(7, objeto.getIdProd());
            stmt.setString(1, objeto.getNomeProd());
            stmt.setString(2, objeto.getTipoProd());
            stmt.setString(3, objeto.getOrigemProd());
            stmt.setString(5, objeto.getStatusProduto());
            stmt.setString(4, objeto.getDataEntradaProd());
            stmt.setInt(6,objeto.getQuantidade());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
            System.err.println(ex);
            return 0;
        }

        return objeto.getIdProd();
    }

    /**
     * Deleta um objeto
     * 
     * @param objeto 
     */
    @Override
    public void excluir(ProdEstado objeto) 
    {
        try {
            ConnectionFactory.getConnection();
            
            stmt = con.prepareStatement("DELETE FROM produto WHERE ID_PRODUTO = ?");
            stmt.setInt(1, objeto.getIdProd());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);
        }
    }

    /**
     * Lista todos produtos
     * 
     * @return 
     */
    public List<ProdEstado> read() 
    {
        List<ProdEstado> prode = new ArrayList<>();

        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("SELECT * FROM produto;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));
                pe.setStatusProduto(rs.getString("STATUS_PRODUTO"));
                pe.setQuantidade(rs.getInt("QUANTIDADE_PRODUTO_ENTRADO"));
                prode.add(pe);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        }

        return prode;
    }

    /**
     * Buscar produtos pelo nome
     * 
     * @param Nomeprod
     * @return 
     */
    public List<ProdEstado> obterPeloNome(String Nomeprod) 
    {
        List<ProdEstado> prode = new ArrayList<>();

        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("SELECT * FROM produto WHERE NOME_PRODUTO LIKE ?");
            stmt.setString(1, "%" + Nomeprod + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setStatusProduto(rs.getString("STATUS_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));
                pe.setQuantidade(rs.getInt("QUANTIDADE_PRODUTO_ENTRADO"));
                prode.add(pe);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        }

        return prode;
    }

    /**
     * Buscar produtos pelo tipo
     * 
     * @param Tipoprod
     * @return 
     */
    public List<ProdEstado> obterPeloTipo(String Tipoprod) 
    {
        List<ProdEstado> prode = new ArrayList<>();

        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("SELECT * FROM produto WHERE TIPO_PRODUTO LIKE ?");
            stmt.setString(1, "%" + Tipoprod + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));
                pe.setQuantidade(rs.getInt("QUANTIDADE_PRODUTO_ENTRADO"));
                prode.add(pe);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        }

        return prode;
    }

    /**
     * Buscar produtos pela origem
     * 
     * @param Origemprod
     * @return 
     */
    public List<ProdEstado> obterPeloOrigem(String Origemprod) 
    {
        List<ProdEstado> prode = new ArrayList<>();
        
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("SELECT * FROM produto WHERE ORIGEM_PRODUTO LIKE ?");
            stmt.setString(1, "%" + Origemprod + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));
                pe.setQuantidade(rs.getInt("QUANTIDADE_PRODUTO_ENTRADO"));
                prode.add(pe);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        }

        return prode;
    }

    /**
     * Buscar produtos pela data de entrada
     * 
     * @param Dataentradaprod
     * @return 
     */
    public List<ProdEstado> obterPeloDataEntrada(String Dataentradaprod) 
    {
        List<ProdEstado> prode = new ArrayList<>();

        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("SELECT * FROM produto WHERE DATA_ENTRADA_PROD LIKE ?");
            stmt.setString(1, "%" + Dataentradaprod + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));
                pe.setQuantidade(rs.getInt("QUANTIDADE_PRODUTO_ENTRADO"));
                prode.add(pe);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        }

        return prode;
    }
    
    /**
     * Pega status do produto pelo id
     * 
     * @param idProduct
     * @return
     * @throws SQLException 
     */
    public String getProdStatusById(int idProduct) throws SQLException
    {
        String productStatus = "";
       
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("SELECT STATUS_PRODUTO FROM produto WHERE ID_PRODUTO = ? ");
            stmt.setInt(1, idProduct);
            rs = stmt.executeQuery();

            while (rs.next()) {
                productStatus = rs.getString("STATUS_PRODUTO");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
            throw new SQLException(ex);
        }
        
        return productStatus;
    }
    
    /**
     * Atualiza quantidade do produto
     * 
     * @param newAmount 
     */
    public void updateProductAmount(int newAmount)
    {
        try {
            ConnectionFactory.getConnection();
            
            stmt = con.prepareStatement(
                "UPDATE produto INNER JOIN controle_produto ON "
                + "produto.ID_PRODUTO = controle_produto.Produto_ID_PRODUTO SET "
                + "QUANTIDADE_PRODUTO_ENTRADO = (QUANTIDADE_PRODUTO_ENTRADO - QUANTIDADE_DE_PRODUTO) "
                + "WHERE QUANTIDADE_DE_PRODUTO = ?;"
            );
            stmt.setInt(1, newAmount);
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                                                        
    }
    
    /**
     * Atualiza quantidade de produto buscando pelo id
     * 
     * @param newAmount
     * @param id 
     */
    public void updateProductAmountById(int newAmount, int id)
    {
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement(
                "UPDATE produto INNER JOIN controle_produto ON "
                + "produto.ID_PRODUTO = controle_produto.Produto_ID_PRODUTO SET"
                + "QUANTIDADE_PRODUTO_ENTRADO = (QUANTIDADE_PRODUTO_ENTRADO - ?)"
                + "WHERE ID_PRODUTO = ?;"
            );
            stmt.setInt(1, newAmount);
            stmt.setInt(2, id);
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Altera status do produto
     * 
     * @param objeto
     * @return 
     */
    public int AlterarStatus(ProdEstado objeto)
    {
        try {
            ConnectionFactory.getConnection();
            objeto.setStatusProduto("Indisponível");
            
            stmt = con.prepareStatement("UPDATE produto SET STATUS_PRODUTO=? WHERE ID_PRODUTO=?");
            stmt.setString(1,objeto.getStatusProduto());
            stmt.setInt(2,objeto.getIdProd());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }         
        
        return 0;
    }
    
    /**
     * Altera status para Disponivel
     * 
     * @param objeto
     * @return 
     */
    public int AlterarStatusParaDisponível(ProdEstado objeto)
    {
        try {
            ConnectionFactory.getConnection();
            objeto.setStatusProduto("Disponível");
            
            stmt = con.prepareStatement("UPDATE produto SET STATUS_PRODUTO=? WHERE ID_PRODUTO=?");
            stmt.setString(1, objeto.getStatusProduto());
            stmt.setInt(2,objeto.getIdProd());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
}
