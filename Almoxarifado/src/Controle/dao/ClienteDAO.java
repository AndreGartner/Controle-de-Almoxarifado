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

/**
 *
 * @author Andre
 */
public class ClienteDAO implements OverDAO<Cliente>{
    Cliente cliente = new Cliente();        
    private PreparedStatement stmt;
    private ResultSet rs;

    /**
     * Cria um objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int inserir(Cliente objeto) 
    {
        try {
            ConnectionFactory.getConnection();

            stmt = con.prepareStatement("INSERT INTO cliente (ID_CLIENTE, NOME_CLIENTE) VALUES (?,?);");
            
            stmt.setString(1, objeto.getIdCli());
            stmt.setString(2, objeto.getNomeCli());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
            System.err.println(ex.getMessage());
        }
        
       return 0;         
    }

    /**
     * Atualiza objeto
     * 
     * @param objeto
     * @return 
     */
    @Override
    public int alterar(Cliente objeto)
    {
        //TODO: Criar método alterar()
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Excluir objeto
     * 
     * @param objeto 
     */
    @Override
    public void excluir(Cliente objeto) 
    {
        //TODO: Criar método excluir()
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Listar Clientes pelo nome
     * 
     * @param NomeCli
     * @return mixed
     * @throws SQLException 
     */
    public List<Cliente> obterPeloNome(String NomeCli) throws SQLException 
    {
        List<Cliente> clientesList = new ArrayList<>();

        try {
            con = ConnectionFactory.getConnection();

            stmt = con.prepareStatement("SELECT * FROM cliente WHERE NOME_CLIENTE LIKE ?");
            stmt.setString(1, "%" + NomeCli + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                cliente.setIdCli(rs.getString("ID_CLIENTE"));
                cliente.setNomeCli(rs.getString("NOME_CLIENTE"));
                
                clientesList.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
            throw new SQLException("Não foi possivel listar os clientes!", ex);
        }

        return clientesList;
    }
        
    /**
     * Listar clientes
     * 
     * @return
     * @throws SQLException 
     */
    public List<Cliente> read() throws SQLException 
    {
        List<Cliente> clientesList = new ArrayList<>();

        try {
            ConnectionFactory.getConnection();
            
            stmt = con.prepareStatement("SELECT * FROM cliente;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cliente.setIdCli(rs.getString("ID_CLIENTE"));
                cliente.setNomeCli(rs.getString("NOME_CLIENTE"));
                clientesList.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
            throw new SQLException(ex);
        }

        return clientesList;
    }

    /**
     * Buscar por nome
     * 
     * @param NomeCli
     * @return
     * @throws SQLException 
     */
    public String readIDCli(String NomeCli) throws SQLException
    {
        try {
            ConnectionFactory.getConnection();
            
            stmt = con.prepareStatement("SELECT ID_CLIENTE FROM cliente WHERE NOME_CLIENTE LIKE ?");
            stmt.setString(1, NomeCli);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                cliente.setIdCli(rs.getString("ID_CLIENTE"));
                JOptionPane.showMessageDialog(null, cliente.getIdCli());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível capturar o valor do ID!");
            throw new SQLException(ex);
        }
        
        return cliente.getIdCli();
    }
}
