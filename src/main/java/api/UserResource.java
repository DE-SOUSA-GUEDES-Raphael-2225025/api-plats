package api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {

    private UserService userService;

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
