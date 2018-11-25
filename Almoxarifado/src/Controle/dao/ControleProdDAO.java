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
import model.bean.Controle;
import model.bean.ControleProd;
import model.bean.ProdEstado;


/**
 *
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso
 * INFEM302
 * CEDUPHH
 */
public class ControleProdDAO implements OverDAO<ControleProd> {

    @Override
    public int inserir(ControleProd objeto) {
        
        ProdEstado pe = new ProdEstado();
        Controle c = new Controle();
                
                
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ControleProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement stmt = null;
        
        
        try {
          
            stmt = con.prepareStatement("INSERT INTO controle_produto (Controçe_ID_CONTROLE, Quantidade_produto) VALUES (?,?);");
             


            stmt.setInt(1, objeto.getfkIdControle());
            stmt.setInt(2, objeto.getQtdProd()); 
            
            stmt.executeUpdate();
           
            
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
        }finally {
            ConnectionFactory.Disconnect();
        }
        return 0;
        
      
    }

    @Override
    public int alterar(ControleProd objeto) {
        
       Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement stmt = null;
        
        
        try {
            stmt = con.prepareStatement("UPDATE Produto SET (Quantidade_produto = ? WHERE Produto_ID_PRODUTO = ?;");
            
            stmt.setInt(1, objeto.getQtdProd());
             stmt.setInt(2, objeto.getfkIdProd());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                    
                    
         } catch (SQLException ex) {
                        
            JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
            
     
   
        }finally {
             try {
                 con.close();
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
        }
        
        return 0;
    }

    @Override
    public void excluir(ControleProd objeto) {
       ControleProd cp = new ControleProd();
        
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement stmt = null;
        
        
        try {
            stmt = con.prepareStatement("Delete from Controle_Produto WHERE Produto_ID_PRODUTO = ?");
            
            stmt.setInt(1, cp.getfkIdProd());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                    
                    
         } catch (SQLException ex) {
                        
            JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
            
     
   
        }finally {
             try {
                 con.close();
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
        }
    }

     
}
