/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.dao;

import connection.ConnectionFactory;
import static connection.ConnectionFactory.con;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.Funcionario;

/**
 *
 * @author rosan
 */
public class FuncionarioDAO implements OverDAO<Funcionario> {

    @Override
    public int inserir(Funcionario objeto) {
        try {
            ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
             PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into Funcionario(NOME_FUNCIONARIO,SENHA_FUNCIONARIO) values(?,?)");
            stmt.setString(1, objeto.getNomeFunc());
            stmt.setString(2, objeto.getSenhaFunc());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar"+ ex);
            
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                
        return 0;
    }

    @Override
    public int alterar(Funcionario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Funcionario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<Funcionario> readIDFunc(int IdFunc){
         
       
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario f = new Funcionario();
        List<Funcionario> funce = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("SELECT ID_FUNCIONARIO FROM funcionario WHERE ID_FUNCIONARIO LIKE ?");
            stmt.setInt(1, IdFunc);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                f.setIdFunc(rs.getInt("ID_FUNCIONARIO"));
                funce.add(f);
                
                JOptionPane.showMessageDialog(null, f.getIdFunc());
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível capturar o valor do ID!");
        }
        
        return funce;
       
    }

    
}
