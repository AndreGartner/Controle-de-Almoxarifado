/**
 *
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso
 * INFEM302
 * CEDUPHH
 */
package Controle.dao;

import connection.ConnectionFactory;
import static connection.ConnectionFactory.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Controle;
import model.bean.ControleProd;
import model.bean.Emprestimo;
import model.bean.ProdEstado;

public class ControleProdDAO implements OverDAO<ControleProd> {
    Controle controle         = new Controle();
    ProdEstado prodEstado     = new ProdEstado();
    Emprestimo emprestimo     = new Emprestimo();
    ControleProd controleProd = new ControleProd();

    private PreparedStatement stmt;
    private ResultSet rs;

    /**
     * Cria um objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int inserir(ControleProd objeto) {
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement(
                "INSERT INTO controle_produto (Controle_ID_CONTROLE,Produto_ID_PRODUTO, QUANTIDADE_DE_PRODUTO) VALUES (?,?,?);"
            );
            stmt.setInt(1, objeto.getControle().getIdControle());
            stmt.setInt(2, objeto.getProduto().getIdProd());
            stmt.setInt(3, objeto.getQtdProd()); 
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
        }
        
        return objeto.getControle().getIdControle();
    }

    /**
     * Alterar Objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int alterar(ControleProd objeto) 
    {
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("UPDATE Produto SET (Quantidade_produto = ? WHERE Produto_ID_PRODUTO = ?;");
            stmt.setInt(1, objeto.getQtdProd());
            stmt.setInt(2, objeto.getProduto().getIdProd());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
        }
        
        return objeto.getControle().getIdControle();
    }

    /**
     * Excluir Objeto
     * 
     * @param objeto 
     */
    @Override
    public void excluir(ControleProd objeto) 
    {
        try {
            ConnectionFactory.getConnection();
  
            stmt = con.prepareStatement("Delete from Controle_Produto WHERE Produto_ID_PRODUTO = ?");
            stmt.setInt(1, controleProd.getProduto().getIdProd());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
        }
    }

    /**
     * Busca todos empréstimos
     * 
     * @return
     */
    public List<Emprestimo> getAllEmprestimos() 
    {
        List<Emprestimo> emprestimoList = new ArrayList<>();
        
        try {
            ConnectionFactory.getConnection();
            
            stmt = con.prepareStatement(
                "SELECT C.ID_CONTROLE, CLI.NOME_CLIENTE, P.NOME_PRODUTO, P.TIPO_PRODUTO ,CP.QUANTIDADE_DE_PRODUTO, P.STATUS_PRODUTO, C.DATA_DE_DEVOLUCAO, C.DATA_DE_EMPRESTIMO "
                + "FROM controle AS C INNER JOIN cliente AS CLI ON CLI.ID_CLIENTE = C.Cliente_ID_CLIENTE INNER JOIN controle_produto AS CP ON C.ID_CONTROLE = CP.Controle_ID_CONTROLE "
                + "INNER JOIN PRODUTO AS P ON CP.Produto_ID_PRODUTO = P.ID_PRODUTO ORDER BY C.ID_CONTROLE DESC "
            );

            rs = stmt.executeQuery();

            while (rs.next()) {
                emprestimo.setIdControle(rs.getInt("ID_CONTROLE"));
                emprestimo.setNomeCliente(rs.getString("NOME_CLIENTE"));
                emprestimo.setNomeProduto(rs.getString("NOME_PRODUTO"));
                emprestimo.setTipoProduto(rs.getString("TIPO_PRODUTO"));
                emprestimo.setQuantidadeProduto(rs.getInt("QUANTIDADE_DE_PRODUTO"));
                emprestimo.setStatusProduto(rs.getString("STATUS_PRODUTO"));
                emprestimo.setDataDevolucao(rs.getString("DATA_DE_DEVOLUCAO"));
                emprestimo.setDataEmprestimo(rs.getString("DATA_DE_EMPRESTIMO"));
                emprestimoList.add(emprestimo);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        return emprestimoList;
    }
}
