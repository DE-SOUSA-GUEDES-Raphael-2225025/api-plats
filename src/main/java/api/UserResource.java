package api;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


/**
 * Classe de ressource REST pour les opérations liées aux utilisateurs dans l'application.
 * Fournit des endpoints pour récupérer les informations des utilisateurs soit individuellement par login, soit tous les utilisateurs enregistrés.
 */
@Path("/users")
@ApplicationScoped
public class UserResource {

    /**
     * Service gérant la logique métier liée aux utilisateurs.
     */
    private UserService userService;

    /**
     * Constructeur par défaut requis pour l'initialisation par le framework JAX-RS.
     */
    public UserResource() {
    }

    /**
     * Constructeur avec injection de dépendance pour initialiser UserService via un repository.
     *
     * @param userRepo Le repository d'utilisateur à injecter dans le service.
     */
    @Inject
    public UserResource(UserRepositoryInterface userRepo) {
        this.userService = new UserService(userRepo);
    }

    /**
     * Constructeur permettant d'initialiser directement avec une instance de UserService.
     *
     * @param userService L'instance de UserService à utiliser.
     */
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * Récupère tous les utilisateurs enregistrés et les retourne sous forme de chaîne JSON.
     *
     * @return Une représentation JSON de tous les utilisateurs.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userService.getAllUsersJSON();
    }

    /**
     * Récupère les informations d'un utilisateur spécifique par son identifiant de connexion (login) et les retourne sous forme de chaîne JSON.
     *
     * @param login L'identifiant de connexion de l'utilisateur à récupérer.
     * @return Une représentation JSON de l'utilisateur spécifié. Si l'utilisateur n'est pas trouvé, retourne une structure JSON vide.
     */
    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("login") String login) {
        return userService.getUserJSON(login);
    }
}
