/**
 * @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso
 * INFEM302
 * CEDUPHH
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Funcionario;

public class ConnectionFactory {
    public static Connection con = null;
    
    /**
     * Método para conexão com o banco de dados
     * 
     * @return Connection
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException 
    {
        try {
            // Pedir para a classe carregar o driver do mysql
            Class.forName("com.mysql.jdbc.Driver");
            // Caminho da base de dados
            String base    = "jdbc:mysql://localhost:3306/db_almoxarifado?useSSL=false";
            String senha   = "";
            String usuario = "root";

            con = DriverManager.getConnection(base, usuario, senha);
            System.out.println("Conectado!!");

        } catch (ClassNotFoundException e) {
            System.out.println("ERRO ao carregar o driver");

        } catch (SQLException e) {
            System.out.println("ERRO na base de dados");
        }

        return con;
    }
    
    /**
     * Desconectar Banco de Dadods
     *  
     */
    public static void Disconnect(){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
        
    /**
     * Logar usuário
     * 
     * @param objeto
     * @return boolean 
     * @throws java.sql.SQLException 
     */
    public static boolean Login(Funcionario objeto) throws SQLException{
        ConnectionFactory.getConnection();
            
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Funcionario WHERE NOME_FUNCIONARIO LIKE ? AND SENHA_FUNCIONARIO LIKE ?");
        stmt.setString(1, objeto.getNomeFunc());
        stmt.setString(2, objeto.getSenhaFunc());
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }
 
 
     public static String formatDate(String campo){
        String temp, temp2, temp3, temp4, a, b;
        String numeros = "0123456789";
        int cont = 0;
        
        ArrayList<String> letras;
        letras = new ArrayList<>();
        
        for (int i = 0; i < campo.length(); i++) {
            a = campo.substring(i, i + 1);
            
            for (int j = 0; j < numeros.length(); j++) {
                b = numeros.substring(j, j + 1);
                if (a.equals(b)) {
                    cont++;
                }
            }
            
            if (cont == 0) {
                temp4 = campo.substring(i, i + 1);
                letras.add(temp4);
            }
            cont = 0;
        }
        
        for (String letra : letras) {
            campo = campo.replace(letra, "");
            cont++;
        }
        
        if (campo.length() == 8) {
            int hd = Integer.parseInt(campo.substring(0, 2));
            int hm = Integer.parseInt(campo.substring(2, 4));
            int ha = Integer.parseInt(campo.substring(4, campo.length()));
            
            if (hd < 1 || hd > 31) {
                return null;
            }
            
            if (hm < 1 || hm > 12) {
                return null;
            }
            
            if (ha < 1979 || ha > 2100) {
                return null;
            }
            
            temp  = campo.substring(0, 2) + "/";
            temp2 = campo.substring(2, 4) + "/";
            temp3 = temp + temp2 + campo.substring(4, campo.length());
            
            return temp3;
        }
        
        return null;
    }
}

