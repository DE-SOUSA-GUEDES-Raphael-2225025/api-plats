package api;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@Path("/users")
@ApplicationScoped
public class UserResource {

    private UserService userService;


    public UserResource() {
    }

    // Injecter UserService via le constructeur

    @Inject
    public UserResource(UserRepositoryInterface userRepo) {
        this.userService = new UserService(userRepo);
    }

    public UserResource(UserService userService) {
        this.userService = userService;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userService.getAllUsersJSON();
    }

    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("login") String login) {
        return userService.getUserJSON(login);
    }
}
