/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.bean.Controle;
import model.bean.ProdEstado;

/**
 *
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira
 * Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso INFEM302 CEDUPHH
 *
 *
 *
 *
 *
 *
 */
public class ProdDAO implements OverDAO<ProdEstado> {

    ProdEstado pe = new ProdEstado();

    @Override
    public int inserir(ProdEstado objeto) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO produto (ID_PRODUTO, NOME_PRODUTO, TIPO_PRODUTO, ORIGEM_PRODUTO, DATA_ENTRADA_PROD,STATUS_PRODUTO) VALUES (?,?,?,?,?,?) ;");

            stmt.setInt(1, objeto.getIdProd());
            stmt.setString(2, objeto.getNomeProd());
            stmt.setString(3, objeto.getTipoProd());
            stmt.setString(4, objeto.getOrigemProd());
            stmt.setString(5, objeto.getDataEntradaProd());
            stmt.setString(6, objeto.getStatusProduto());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    @Override
    public int alterar(ProdEstado objeto) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Produto SET NOME_PRODUTO = ?, "
                    + "TIPO_PRODUTO = ?, ORIGEM_PRODUTO = ?, DATA_ENTRADA_PROD = ?,STATUS_PRODUTO = ?"
                    + "WHERE ID_PRODUTO = ?;");

            stmt.setInt(6, objeto.getIdProd());
            stmt.setString(1, objeto.getNomeProd());
            stmt.setString(2, objeto.getTipoProd());
            stmt.setString(3, objeto.getOrigemProd());
            stmt.setString(5, objeto.getStatusProduto());
            stmt.setString(4, objeto.getDataEntradaProd());

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

    @Override
    public void excluir(ProdEstado objeto) {
        ProdEstado pe = new ProdEstado();

        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Delete from produto WHERE ID_PRODUTO = ?");

            stmt.setInt(1, objeto.getIdProd());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Excluir!" + ex);

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<ProdEstado> read() {

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

            stmt = con.prepareStatement("SELECT * FROM produto;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdEstado pe = new ProdEstado();

                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));
                pe.setStatusProduto(rs.getString("STATUS_PRODUTO"));
                prode.add(pe);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        } finally {
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

    public List<ProdEstado> obterPeloNome(String Nomeprod) {
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

            stmt = con.prepareStatement("SELECT * FROM produto WHERE NOME_PRODUTO LIKE ?");
            stmt.setString(1, "%" + Nomeprod + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdEstado pe = new ProdEstado();

                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));

                prode.add(pe);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        } finally {
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

    public List<ProdEstado> obterPeloTipo(String Tipoprod) {
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

            stmt = con.prepareStatement("SELECT * FROM produto WHERE TIPO_PRODUTO LIKE ?");
            stmt.setString(1, "%" + Tipoprod + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdEstado pe = new ProdEstado();

                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));

                prode.add(pe);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        } finally {
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

    public List<ProdEstado> obterPeloOrigem(String Origemprod) {
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

            stmt = con.prepareStatement("SELECT * FROM produto WHERE ORIGEM_PRODUTO LIKE ?");
            stmt.setString(1, "%" + Origemprod + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdEstado pe = new ProdEstado();

                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));

                prode.add(pe);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        } finally {
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

    public List<ProdEstado> obterPeloDataEntrada(String Dataentradaprod) {
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

            stmt = con.prepareStatement("SELECT * FROM produto WHERE DATA_ENTRADA_PROD LIKE ?");
            stmt.setString(1, "%" + Dataentradaprod + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdEstado pe = new ProdEstado();

                pe.setIdProd(rs.getInt("ID_PRODUTO"));
                pe.setNomeProd(rs.getString("NOME_PRODUTO"));
                pe.setTipoProd(rs.getString("TIPO_PRODUTO"));
                pe.setOrigemProd(rs.getString("ORIGEM_PRODUTO"));
                pe.setDataEntradaProd(rs.getString("DATA_ENTRADA_PROD"));

                prode.add(pe);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro com a conexão da tabela!!");
        } finally {
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
    public int AlterarStatus(ProdEstado objeto){
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("UPDATE produto SET STATUS_PRODUTO=? WHERE ID_PRODUTO=?");
            stmt.setString(1,objeto.getStatusProduto() );
            stmt.setInt(2,objeto.getIdProd());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdDAO.class.getName()).log(Level.SEVERE, null, ex);
        }          return 0;
}
}
