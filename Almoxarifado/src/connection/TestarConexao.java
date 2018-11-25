/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rosan
 */
public class TestarConexao {
    
     public static void main (String[] a){
         Connection con;
         try {
             con = ConnectionFactory.getConnection();
         } catch (SQLException ex) {
             Logger.getLogger(TestarConexao.class.getName()).log(Level.SEVERE, null, ex);
         }
         
     }
    
}
