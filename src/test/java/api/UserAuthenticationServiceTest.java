package api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserAuthenticationServiceTest {

    @Mock
    private UserRepositoryInterface userRepo;

    private UserAuthenticationService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new UserAuthenticationService(userRepo);
    }

    @Test
    void isValidUser_ReturnsTrue_WhenCredentialsAreCorrect() {
        // Configuration du mock pour retourner un utilisateur spécifique
        when(userRepo.getUser("validUser")).thenReturn(new User("validUser", "User Name", "correctPassword", "Surname"));

        assertTrue(authService.isValidUser("validUser", "correctPassword"),
                "L'authentification doit réussir avec des identifiants valides.");
    }

    @Test
    void isValidUser_ReturnsFalse_WhenPasswordIsIncorrect() {
        // Configuration du mock pour simuler un utilisateur avec un mot de passe incorrect
        when(userRepo.getUser("validUser")).thenReturn(new User("validUser", "User Name", "correctPassword", "Surname"));

        assertFalse(authService.isValidUser("validUser", "incorrectPassword"),
                "L'authentification doit échouer avec un mot de passe incorrect.");
    }

    @Test
    void isValidUser_ReturnsFalse_WhenUserDoesNotExist() {
        // Configuration du mock pour retourner null, simulant un utilisateur inconnu
        when(userRepo.getUser("unknownUser")).thenReturn(null);

        assertFalse(authService.isValidUser("unknownUser", "anyPassword"),
                "L'authentification doit échouer lorsque l'utilisateur n'existe pas.");
    }

    @Test
    void getUserJson_ReturnsUserJson_WhenUserExists() {
        User testUser = new User("userJson", "User Json", "userPass", "Json");
        String expectedJson = "{\"login\":\"userJson\",\"name\":\"User Json\",\"password\":\"userPass\",\"surname\":\"Json\"}";
        when(userRepo.getUser("userJson")).thenReturn(testUser);

        String resultJson = authService.getUserJson("userJson");

        assertEquals(expectedJson, resultJson, "Doit retourner le JSON de l'utilisateur spécifié.");
    }

    @Test
    void getUserJson_ReturnsNull_WhenUserDoesNotExist() {
        when(userRepo.getUser("nonExistingUser")).thenReturn(null);

        String resultJson = authService.getUserJson("nonExistingUser");

        assertNull(resultJson, "Doit retourner null lorsque l'utilisateur n'existe pas.");
    }
}
