import java.sql.Connection;
import java.sql.PreparedStatement; //Representa comando SQL para preparado para receber valores
import java.sql.SQLException;
public class AlunoDAO {
    public void inserir(
            String nome,
            int idade,
            String curso,
            String turno,
            boolean possui_computador
    ) throws SQLException{
        String sql =
                "INSERT INTO alunos" +
                "(nome, idade, curso, turno, possui_computador)" +
                "VALUES (?, ?, ?, ?, ?)";

        Connection conexao = Conexao.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql);
        comando.setString(1, nome);
        comando.setInt(2, idade);
        comando.setString(3, curso);
        comando.setString(4, turno);
        comando.setBoolean(5, possui_computador);
        comando.executeUpdate(); // Serve para comandos que alteram dados
                                // INSERT, UPDATE, DELETE
    }
}
