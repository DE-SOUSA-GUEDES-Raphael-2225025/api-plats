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
    public void close() {

    }

    @Override
    public User getUser(String login) {
        // Implémentez la requête SQL pour récupérer un utilisateur
        return null; // exemple simplifié
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT id, login, password, name FROM users"; // Assurez-vous que ces colonnes existent dans votre table

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User(resultSet.getString("surname"),resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // En production, envisagez de logger cette exception plutôt que d'imprimer sa stack trace.
        }

        return users;
    }

}
