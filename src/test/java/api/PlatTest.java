package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlatTest {

    private Plat plat;

    @BeforeEach
    void setUp() {
        plat = new Plat("1", "Pizza Margherita", "Une pizza traditionnelle italienne.", 8.5);
    }

    @Test
    void testGetId() {
        assertEquals("1", plat.getId(), "L'ID du plat doit correspondre à celui fourni dans le constructeur.");
    }

    @Test
    void testGetName() {
        assertEquals("Pizza Margherita", plat.getName(), "Le nom du plat doit correspondre à celui fourni dans le constructeur.");
    }

    @Test
    void testGetDescription() {
        assertEquals("Une pizza traditionnelle italienne.", plat.getDescription(), "La description du plat doit correspondre à celle fournie dans le constructeur.");
    }

    @Test
    void testGetPrice() {
        assertEquals(8.5, plat.getPrice(), 0.001, "Le prix du plat doit correspondre à celui fourni dans le constructeur.");
    }
}
