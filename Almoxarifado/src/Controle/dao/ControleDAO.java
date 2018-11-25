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
import javax.swing.JOptionPane;
import model.bean.Controle;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.DateStringConverter;
import model.bean.Cliente;
import model.bean.Funcionario;
import model.bean.ProdEstado;

/**
 *
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira
 * Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso INFEM302 CEDUPHH
 */
public class ControleDAO implements OverDAO<Controle> {

    @Override
    public int inserir(Controle objeto) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ControleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement stmt = null;

        try {
            Funcionario f = new Funcionario();
      
            Cliente cli = new Cliente();
            Controle c = new Controle();
           /* int fk; String fks;
            fk = cli.getIdCli();
            fks = String.valueOf(fk);
            c.setfkclient(fks);
            
            int fkp; String fksp;
            fkp = f.getIdFunc();
            fksp = String.valueOf(fkp);
            c.setfkfunc(fksp);*/
           c.setfkclient(String.valueOf(cli.getIdCli()));
            
            stmt = con.prepareStatement("INSERT INTO controle (DATA_DE_ENTRADA,DATA_DE_SAIDA,Cliente_ID_CLIENTE,Funcionario_ID_FUNCIONARIO) VALUES (?,?,?,?);");
            stmt.setString(1, objeto.getDataEntrada());
            stmt.setString(2, objeto.getDataSaida());
            stmt.setString(3, objeto.getfkclient());
            stmt.setString(4, objeto.getfkfunc());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public int alterar(Controle objeto) {
        ProdEstado pe = new ProdEstado();
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ControleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Controle SET  DATA_DE_ENTRADA = ?, "
                    + "DATA_DE_SAIDA = ? WHERE ID_CONTROLE = ?;");
            stmt.setString(1, objeto.getDataEntrada());
            stmt.setString(2, objeto.getDataSaida());
            stmt.setInt(3, objeto.getIdControle());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {

            try {
                con.close();
            } catch (Exception ex) {

                ex.printStackTrace();

            }
        }

        return 0;
    }

    @Override
    public void excluir(Controle objeto) {
        Controle c = new Controle();

        Connection con = null;

        PreparedStatement stmt = null;

        try {
            ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ControleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            stmt = con.prepareStatement("DELETE FROM Controle WHERE ID_CONTROLE = ?");
            stmt.setInt(1, c.getIdControle());
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {

            try {
                con.close();
            } catch (Exception ex) {

                ex.printStackTrace();

            }
        }
    }

}
