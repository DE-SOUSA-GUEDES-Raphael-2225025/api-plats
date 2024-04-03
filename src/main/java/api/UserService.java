package api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Service pour la gestion des utilisateurs, fournissant des fonctionnalités pour interagir avec les informations utilisateur.
 * Ce service permet de récupérer les données utilisateur sous forme de chaîne JSON,
 * en utilisant un repository pour accéder aux données stockées.
 */
public class UserService {

    /**
     * Référence au repository utilisateur utilisé pour accéder aux données utilisateur.
     */
    private UserRepositoryInterface userRepo;

    /**
     * Construit une nouvelle instance de UserService avec un repository spécifié.
     *
     * @param userRepo Le repository à utiliser pour les opérations sur les données utilisateur.
     */
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Récupère tous les utilisateurs enregistrés et les retourne sous forme de chaîne JSON.
     * Les mots de passe des utilisateurs sont effacés pour des raisons de sécurité avant la conversion en JSON.
     *
     * @return Une représentation JSON de tous les utilisateurs. En cas d'erreur, retourne {@code null}.
     */
    public String getAllUsersJSON() {
        ArrayList<User> allUsers = userRepo.getAllUsers();
        // Effacer les mots de passe des utilisateurs pour la sécurité
        allUsers.forEach(user -> user.setPassword(""));

        // Conversion de la liste des utilisateurs en JSON
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion en JSON : " + e.getMessage());
            return null;
        }
    }

    /**
     * Récupère les informations d'un utilisateur spécifique par son login et les retourne sous forme de chaîne JSON.
     * Le mot de passe de l'utilisateur est effacé pour des raisons de sécurité avant la conversion en JSON.
     *
     * @param login L'identifiant de connexion de l'utilisateur à récupérer.
     * @return Une représentation JSON de l'utilisateur spécifié, ou "{}" si aucun utilisateur n'est trouvé ou en cas d'erreur.
     */
    public String getUserJSON(String login) {
        User myUser = userRepo.getUser(login);
        if (myUser != null) {
            myUser.setPassword(""); // Effacer le mot de passe pour la sécurité

            // Conversion de l'utilisateur en JSON
            try (Jsonb jsonb = JsonbBuilder.create()) {
                return jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println("Erreur lors de la conversion en JSON : " + e.getMessage());
            }
        }
        return "{}";
    }
}
