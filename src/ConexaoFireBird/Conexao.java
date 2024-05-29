package ConexaoFireBird;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private Connection conexao;

    public Connection conectar() {
        try {
            try {
                Class.forName("org.firebirdsql.jdbc.FBDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }

            conexao = DriverManager.getConnection(
               "jdbc:firebirdsql:192.168.88.250:D:/dados/Salao/Onildo/dados.GDB",
               "sysdba",
               "masterkey");

            return conexao;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
