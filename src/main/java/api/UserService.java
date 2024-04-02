package api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class UserService {

    private UserRepositoryInterface userRepo;

    // Supposant qu'il y a un constructeur ou une méthode d'initialisation pour userRepo...

    public String getAllUsersJSON() {
        ArrayList<User> allUsers = userRepo.getAllUsers();
        allUsers.forEach(user -> {
            user.setPassword("");
        });

        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String getUserJSON(String login) {
        User myUser = userRepo.getUser(login);
        if (myUser != null) {
            myUser.setPassword("");
            try (Jsonb jsonb = JsonbBuilder.create()) {
                return jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return "{}";
    }

}
