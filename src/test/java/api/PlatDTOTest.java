package api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlatDTOTest {

    @Test
    void getName_ReturnsCorrectName() {
        PlatDTO platDTO = new PlatDTO();
        platDTO.setName("Pizza Margherita");
        assertEquals("Pizza Margherita", platDTO.getName(), "Le nom du plat doit être correctement retourné.");
    }

    @Test
    void getDescription_ReturnsCorrectDescription() {
        PlatDTO platDTO = new PlatDTO();
        platDTO.setDescription("Une pizza classique avec de la mozzarella et du basilic.");
        assertEquals("Une pizza classique avec de la mozzarella et du basilic.", platDTO.getDescription(), "La description du plat doit être correctement retournée.");
    }

    @Test
    void getPrice_ReturnsCorrectPrice() {
        PlatDTO platDTO = new PlatDTO();
        platDTO.setPrice(10.5);
        assertEquals(10.5, platDTO.getPrice(), 0.001, "Le prix du plat doit être correctement retourné.");
    }

    @Test
    void setAndGet_AllFields() {
        PlatDTO platDTO = new PlatDTO();
        platDTO.setName("Lasagnes aux épinards");
        platDTO.setDescription("Lasagnes fraîches aux épinards et à la ricotta.");
        platDTO.setPrice(15.0);

        assertAll("PlatDTO",
                () -> assertEquals("Lasagnes aux épinards", platDTO.getName(), "Le nom doit être correct."),
                () -> assertEquals("Lasagnes fraîches aux épinards et à la ricotta.", platDTO.getDescription(), "La description doit être correcte."),
                () -> assertEquals(15.0, platDTO.getPrice(), 0.001, "Le prix doit être correct.")
        );
    }
}
