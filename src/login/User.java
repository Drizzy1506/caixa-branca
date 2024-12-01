package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    public String nome = "";

    public Connection conectarBD() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/test";
            conn = DriverManager.getConnection(url, "lopes", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean verificarUsuario(String login, String senha) {
        boolean result = false;
        String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";
        try (Connection conn = conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, senha);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = true;
                    nome = rs.getString("nome");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
