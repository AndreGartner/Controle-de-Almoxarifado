/**
 *
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira
 * Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso INFEM302 CEDUPHH
 */
package Controle.dao;

import connection.ConnectionFactory;
import static connection.ConnectionFactory.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Controle;
import model.bean.Cliente;
import model.bean.Funcionario;

public class ControleDAO implements OverDAO<Controle> {
    Cliente cliente               = new Cliente();
    Controle controle             = new Controle();
    ClienteDAO clienteDao         = new ClienteDAO();
    Funcionario funcionario       = new Funcionario();
    FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    private ResultSet rs;
    private PreparedStatement stmt;

    /**
     * Cria um objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int inserir(Controle objeto) 
    {
        try {
            ConnectionFactory.getConnection();

            clienteDao.readIDCli(cliente.getNomeCli());
            funcionarioDao.readIDFunc(funcionario.getIdFunc());

            stmt = con.prepareStatement(
                "INSERT INTO controle (ID_CONTROLE,DATA_DE_DEVOLUCAO,DATA_DE_EMPRESTIMO,Cliente_ID_CLIENTE,Funcionario_ID_FUNCIONARIO) VALUES (?,?,?,?,?);"
            );
            stmt.setInt(1, objeto.getIdControle());
            stmt.setString(2, objeto.getDataDevolucao());
            stmt.setString(3, objeto.getDataEmprestimo());
            stmt.setString(4, objeto.getCliente().getIdCli());
            stmt.setInt(5, objeto.getFuncionario().getIdFunc());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
            System.err.println(ex.getMessage());
        }
        
        return objeto.getIdControle();
    }

    /**
     * Atualiza objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int alterar(Controle objeto) 
    {
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement(
                "UPDATE Controle SET  DATA_DE_DEVOLUCAO = ?, DATA_DE_EMPRESTIMO = ? WHERE ID_CONTROLE = ?;"
            );
            
            stmt.setString(1, objeto.getDataDevolucao());
            stmt.setString(2, objeto.getDataEmprestimo());
            stmt.setInt(3, objeto.getIdControle());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return objeto.getIdControle();
    }

    /**
     * Exclui objeto
     * 
     * @param objeto 
     */
    @Override
    public void excluir(Controle objeto) 
    {
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("DELETE FROM Controle WHERE ID_CONTROLE = ?");
            stmt.setInt(1, controle.getIdControle());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Retorna id do ultimo empréstimo
     * 
     * @return 
     */
    public int getFirstId()
    {
        int idControl  = 0;
        try {
            ConnectionFactory.getConnection();
            
            stmt = con.prepareStatement("SELECT ID_CONTROLE FROM controle ORDER BY ID_CONTROLE DESC LIMIT 1;");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                idControl = rs.getInt("ID_CONTROLE");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idControl;
    }
}
