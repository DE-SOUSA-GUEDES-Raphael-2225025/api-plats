package api;
import java.util.ArrayList;

public interface UserRepositoryInterface {

    /**
     * Méthode pour obtenir un utilisateur par son login.
     *
     * @param login Le login de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant au login, ou null si aucun utilisateur n'est trouvé.
     */
    User getUser(String login);

    /**
     * Méthode pour obtenir tous les utilisateurs disponibles.
     *
     * @return Une liste de tous les utilisateurs.
     */
    ArrayList<User> getAllUsers();


}
