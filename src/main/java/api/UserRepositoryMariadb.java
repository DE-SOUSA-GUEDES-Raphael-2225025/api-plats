package api;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryMariadb implements UserRepositoryInterface, Closeable {

    private Connection connection;
    /**
     * Constructeur pour établir une connexion à la base de données MariaDB avec les paramètres fournis.
     *
     * @param url L'URL de la base de données MariaDB.
     * @param username Le nom d'utilisateur pour se connecter à la base de données.
     * @param password Le mot de passe pour se connecter à la base de données.
     */
    public UserRepositoryMariadb(String url, String username, String password) {
        // Initialiser la connexion à la base de données MariaDB
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Méthode pour fermer la connexion à la base de données.
     * Doit être implémentée pour libérer les ressources de la base de données.
     */
    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Récupère un utilisateur de la base de données basé sur son identifiant de connexion (login).
     *
     * @param login L'identifiant de connexion de l'utilisateur à récupérer.
     * @return Un objet {@link User} contenant les informations de l'utilisateur, ou {@code null} si aucun utilisateur n'est trouvé.
     */
    @Override
    public User getUser(String login) {
        String query = "SELECT * FROM users WHERE login = ?";

        if(connection == null) return new User("test","test","test","test");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            close();
            if (resultSet.next()) {

                return new User(
                        resultSet.getString("login"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("surname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * Récupère la liste de tous les utilisateurs de la base de données.
     *
     * @return Une {@link ArrayList} contenant tous les utilisateurs. La liste peut être vide si aucun utilisateur n'est trouvé.
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            close();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("login"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("surname"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return users;
    }

}
