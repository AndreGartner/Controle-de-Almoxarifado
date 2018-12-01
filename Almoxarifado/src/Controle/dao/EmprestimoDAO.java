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
import model.bean.Controle;
import model.bean.ProdEstado;

/**
 *
 * @author Usuario
 */
public class EmprestimoDAO {

    public int alterar(ProdEstado objeto) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement stmt = null;

        try {
            ProdEstado pe = new ProdEstado();
            pe.setStatusProduto("Emprestado");
            stmt = con.prepareStatement("UPDATE Produto SET"
                    + "STATUS_PRODUTO =?"
                    + "WHERE NOME_PRODUTO = ?;");
            stmt.setString(1, objeto.getStatusProduto());
            stmt.setString(2, objeto.getNomeProd());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return 0;

    }
    
    public List<ProdEstado> read(){
         
        
        Connection con = null;
        try {
            
            con = ConnectionFactory.getConnection();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<ProdEstado> prode = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT ID_PRODUTO, NOME_PRODUTO, TIPO_PRODUTO, STATUS_PRODUTO FROM produto;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdEstado pe = new ProdEstado();

                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setStatusProduto(rs.getString("STATUS_PRODUTO"));
                prode.add(pe);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        }finally{
            ConnectionFactory.Disconnect();
            
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return prode;
        
    }
    public List<Cliente> readCli(){
         
        
        Connection con = null;
        try {
            
            con = ConnectionFactory.getConnection();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Cliente> cli = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT ID_CLIENTE, NOME_CLIENTE FROM Cliente;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cl = new Cliente();

                cl.setIdCli(rs.getInt("ID_CLIENTE"));
                cl.setNomeCli(rs.getString("NOME_CLIENTE"));
                cli.add(cl);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        }finally{
            ConnectionFactory.Disconnect();
            
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cli;
        
    }
    public List<Controle> readCon(){
         
        
        Connection con = null;
        try {
            
            con = ConnectionFactory.getConnection();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Controle> cnt = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT DATA_DE_DEVOLUCAO, DATA_DE_EMPRESTIMO FROM Controle;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Controle cont = new Controle();

                cont.setDataDevolucao(rs.getString("DATA_DE_DEVOLUCAO"));
                cont.setDataEmprestimo(rs.getString("DATA_DE_EMPRESTIMO"));
                cnt.add(cont);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        }finally{
            ConnectionFactory.Disconnect();
            
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cnt;
        
    }
    
    
    
}
