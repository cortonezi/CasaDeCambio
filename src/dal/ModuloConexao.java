
package dal;
import java.sql.*;

/**
 *
 * @author rafae
 */
public class ModuloConexao {
     //metodo responsavel pela conexao com o banco
    
    public static Connection conector() {
        java.sql.Connection conexao = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/casadecambio";
        String user = "root";
        String password = "123456";
        
        //estabelecendo a conexao com o banco        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            
            //a linha abaixo serve para mostrar o erro de conexao
            System.out.println(e);
            return null;
        }
        
    }
    
}
