//Denomina de qual pacote está classe pertence
package connection;

//ferramentas para conexão importadas
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.bean.Controle;
import model.bean.ControleProd;
import model.bean.Funcionario;
import model.bean.ProdEstado;

/**
 *
 * @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso
 * INFEM302
 * CEDUPHH
 */


public class ConnectionFactory {
    
    
    
    
    
    public static Connection con = null;
    
    //Método para conexão com o banco de dados
    public static Connection getConnection() throws SQLException {

		try {
			// Pedir para a classe carregar
			// O driver do mysql
			Class.forName("com.mysql.jdbc.Driver");
			// Caminho da base de dados
			String base = "jdbc:mysql://localhost:3306/db_almoxarifado?useSSL=false";
			String usuario = "root";
			String senha = "123";

			con = DriverManager.getConnection(base, usuario, senha);
			System.out.println("Conectado!!");

		} catch (ClassNotFoundException e) {
			System.out.println("ERRO ao carregar o driver");

			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("ERRO na base de dados");
			e.printStackTrace();
		}

		return con;
	}
    
    
    public static void Disconnect(){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            
        }
    }
        
 public static boolean Login(){
   
        try {
            Funcionario f = new Funcionario();
            ConnectionFactory.getConnection();
             PreparedStatement stmt = null;
                 stmt = con.prepareStatement("Select * from Funcionario WHERE NOME_FUNCIONARIO = ? , SENHA_FUNCIONARIO = ?");
                 stmt.setString(1, f.getNomeFunc());
                 stmt.setString(2, f.getSenhaFunc());
                 stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
     return true;
    }
 
 
 public static String formatDate(String campo){
     String temp, temp2, temp3 = null, temp4, temp5, a, b;
        ArrayList<String> letras = new ArrayList<String>();
        String numeros = "0123456789";
        int cont = 0;
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
            } else if (hm < 1 || hm > 12) {
                 
                return null;
            } else if (ha < 1979 || ha > 2100) {
                 
                return null;
            }else{
            temp = campo.substring(0, 2) + "/";
            temp2 = campo.substring(2, 4) + "/";
            temp3 = temp + temp2 + campo.substring(4, campo.length());
            return temp3;
            }   
        }
        return null;
        
    
    
}
}

