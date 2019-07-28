/**
 *
 * @author rosan
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
import model.bean.Funcionario;

public class FuncionarioDAO implements OverDAO<Funcionario> {
    final Funcionario funcionario = new Funcionario();
    private PreparedStatement stmt;
    private ResultSet rs;

    /**
     * Cria um objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int inserir(Funcionario objeto) 
    {
        try {
            ConnectionFactory.getConnection();
            
            stmt = con.prepareStatement("INSERT INTO Funcionario(NOME_FUNCIONARIO,SENHA_FUNCIONARIO) VALUES(?,?)");
            stmt.setString(1, objeto.getNomeFunc());
            stmt.setString(2, objeto.getSenhaFunc());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar! " + ex);
        }
                
        return 0;
    }

    /**
     * Atualiza um objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int alterar(Funcionario objeto) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Exclui um objeto
     * 
     * @param objeto 
     */
    @Override
    public void excluir(Funcionario objeto) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Busca pelo id
     * 
     * @param IdFunc
     * @return
     * @throws SQLException 
     */
    public List<Funcionario> readIDFunc(int IdFunc) throws SQLException 
    {
        List<Funcionario> funcionarioList = new ArrayList<>();
        
        try {
            ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE ID_FUNCIONARIO LIKE ?");
            stmt.setInt(1, IdFunc);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                funcionario.setIdFunc(rs.getInt("ID_FUNCIONARIO"));
                funcionarioList.add(funcionario);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível capturar o valor do ID!");
            throw new SQLException("Erro ao Buscar Funcionário pelo ID! " + ex);
        }
        
        return funcionarioList;
    }

    
}
