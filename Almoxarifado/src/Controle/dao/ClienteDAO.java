/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.dao;

import connection.ConnectionFactory;
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
import model.bean.ProdEstado;

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
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
public List<Cliente> read() {

        Connection con = null;
        try {

            con = ConnectionFactory.getConnection();

        } catch (SQLException ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT * FROM cliente;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setIdCli(rs.getInt("ID_CLIENTE"));
                cliente.setNomeCli(rs.getString("NOME_CLIENTE"));
                clientes.add(cliente);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        } finally {
            ConnectionFactory.Disconnect();

            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return clientes;

    }


    
}
