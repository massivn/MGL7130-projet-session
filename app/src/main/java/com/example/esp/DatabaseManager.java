package com.example.esp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import android.util.Log;
public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/espor";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                Log.d("Database","Connexion à la base de données réussie !");
            } else {
                Log.d("Database","Échec de la connexion à la base de données.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
