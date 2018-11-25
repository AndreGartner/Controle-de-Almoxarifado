/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

/**
 *
 * @author Andre
 */
public class ClienteDAO implements OverDAO<Cliente>{
    
    Cliente cli = new Cliente();

    @Override
    public int inserir(Cliente objeto) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement stmt = null;
        
        
        try {
            stmt = con.prepareStatement("INSERT INTO cliente (ID_CLIENTE, NOME_CLIENTE) VALUES (?,?);");
            
            stmt.setInt(1, objeto.getIdCli());
            stmt.setString(2, objeto.getNomeCli());

            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                    
                    
         } catch (SQLException ex) {
                        
            JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
            
     
   
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return 0;         
    }

    @Override
    public int alterar(Cliente objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Cliente objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    
}
