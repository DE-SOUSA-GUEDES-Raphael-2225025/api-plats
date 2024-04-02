package api;
import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryMariadb implements UserRepositoryInterface {

    private Connection connection;

    public UserRepositoryMariadb(String url, String username, String password) {
        // Initialiser la connexion à la base de données MariaDB
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String login) {
        // Implémentez la requête SQL pour récupérer un utilisateur
        return null; // exemple simplifié
    }

    @Override
    public ArrayList<User> getAllUsers() {
        // Implémentez la requête SQL pour récupérer tous les utilisateurs
        return null; // exemple simplifié
    }

}
