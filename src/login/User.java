package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A classe User é responsável pela interação com o banco de dados, incluindo a verificação de usuários
 * com login e senha. Ela contém métodos para conectar ao banco e verificar a existência de um usuário.
 */
public class User {
    /**
     * Estabelece uma conexão com o banco de dados MySQL usando o DriverManager.
     * A URL de conexão é configurada com o banco de dados, usuário e senha.
     *
     * @return Connection Retorna o objeto Connection que representa a conexão com o banco de dados.
     * @throws SQLException Se houver um erro ao estabelecer a conexão, uma exceção será lançada.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jbdc:mysql://127.0.0.1/test?user=lopes&password=123";
        } catch (Exception e) { }

        return conn;
    }

    /**
     * Variável que armazena o nome do usuário, caso encontrado no banco de dados.
     */
    public String nome = "";
    /**
     * Variável que armazena o status da verificação do usuário.
     * 'true' se o usuário foi encontrado, 'false' caso contrário.
     */
    public boolean result = false;

    /**
     * Verifica se o usuário existe no banco de dados com o login e senha fornecidos.
     * Realiza uma consulta no banco de dados para encontrar o nome do usuário.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return boolean Retorna 'true' se o usuário for encontrado, 'false' caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();
        //INSTRUÇÃO SQL
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) { }
        return result;
    }
} // fim da class