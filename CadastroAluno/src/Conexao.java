import java.sql.Connection; // Representa uma conexão aberta entre o nosso programa e o banco
import java.sql.DriverManager; // Vai pedir o JDBC uma conexão
import java.sql.SQLException; // Como o banco pode estar desligado, senha pode estar
// errada ou a tabela pode não existir, pode gerar erros específicos.
// O Javinha representa vários deles através do SQLException.

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_escola";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public  static Connection conectar()
            throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

}
