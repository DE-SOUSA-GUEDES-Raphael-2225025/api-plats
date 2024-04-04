package api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

class UserServiceTest {

    @Mock
    private UserRepositoryInterface userRepo;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsersJSON_ReturnsValidJSONWithPasswordsCleared() {
        // Configuration du mock pour retourner une liste d'utilisateurs
        when(userRepo.getAllUsers()).thenReturn(new ArrayList<>(Arrays.asList(
                new User("user1", "User One", "password1", "One"),
                new User("user2", "User Two", "password2", "Two")
        )));

        // Appel de la méthode à tester
        String jsonResult = userService.getAllUsersJSON();

        // Vérification que le résultat JSON ne contient pas de mots de passe
        assertFalse(jsonResult.contains("password1"));
        assertFalse(jsonResult.contains("password2"));
        assertTrue(jsonResult.contains("user1"));
        assertTrue(jsonResult.contains("user2"));

        // Vérification que le repository a été appelé comme attendu
        verify(userRepo, times(1)).getAllUsers();
    }

    @Test
    void getUserJSON_ReturnsValidJSONWithPasswordCleared() {
        // Configuration du mock pour retourner un utilisateur spécifique
        when(userRepo.getUser("user1")).thenReturn(new User("user1", "User One", "secret", "One"));

        // Appel de la méthode à tester
        String jsonResult = userService.getUserJSON("user1");

        // Vérification que le résultat JSON ne contient pas le mot de passe
        assertFalse(jsonResult.contains("secret"));
        assertTrue(jsonResult.contains("user1"));
        assertTrue(jsonResult.contains("User One"));

        // Vérification que le repository a été appelé comme attendu
        verify(userRepo, times(1)).getUser("user1");
    }
}
