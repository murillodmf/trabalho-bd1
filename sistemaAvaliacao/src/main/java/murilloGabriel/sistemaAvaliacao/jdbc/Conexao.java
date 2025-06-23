package murilloGabriel.sistemaAvaliacao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/seu_banco";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao carregar driver PostgreSQL: " + ex.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
} 
    
