package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // Initialisation avec des valeurs de test.
        user = new User("jdoe", "John", "s3cr3t", "Doe");
    }

    @Test
    void testGetters() {
        assertEquals("jdoe", user.getLogin(), "Le login doit être 'jdoe'.");
        assertEquals("John", user.getName(), "Le nom doit être 'John'.");
        assertEquals("s3cr3t", user.getPassword(), "Le mot de passe doit être 's3cr3t'.");
        assertEquals("Doe", user.getSurname(), "Le prénom doit être 'Doe'.");
    }

    @Test
    void testSetters() {
        // Modification des valeurs via les setters
        user.setLogin("janedoe");
        user.setName("Jane");
        user.setPassword("newpassword");
        user.setSurname("Doe");

        // Vérification que les getters retournent les nouvelles valeurs
        assertEquals("janedoe", user.getLogin(), "Le login doit être mis à jour à 'janedoe'.");
        assertEquals("Jane", user.getName(), "Le nom doit être mis à jour à 'Jane'.");
        assertEquals("newpassword", user.getPassword(), "Le mot de passe doit être mis à jour à 'newpassword'.");
        assertEquals("Doe", user.getSurname(), "Le prénom doit être mis à jour à 'Doe'.");
    }
}
