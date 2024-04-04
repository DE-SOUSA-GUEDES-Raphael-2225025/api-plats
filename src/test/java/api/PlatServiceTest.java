package api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

public class PlatServiceTest {

    @Mock
    private PlatRepositoryInterface platRepo;

    @InjectMocks
    private PlatService platService;

    private final Jsonb jsonb = JsonbBuilder.create();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlatJSON_ReturnsValidJSON() {
        // Configuration du mock
        when(platRepo.getAllPlats()).thenReturn((ArrayList<Plat>) Arrays.asList(
                new Plat("1", "Plat1", "Description1", 10.0),
                new Plat("2", "Plat2", "Description2", 20.0)
        ));

        // Exécution de la méthode testée
        String result = platService.getAllPlatJSON();

        // Vérification
        assertEquals(jsonb.toJson(platRepo.getAllPlats()), result);
        verify(platRepo, times(1)).getAllPlats();
    }

    @Test
    void getPlatJSON_ReturnsValidJSONForExistingPlat() {
        // Configuration du mock
        Plat expectedPlat = new Plat("1", "Plat1", "Description1", 10.0);
        when(platRepo.getPlat("1")).thenReturn(expectedPlat);

        // Exécution de la méthode testée
        String result = platService.getPlatJSON("1");

        // Vérification
        assertEquals(jsonb.toJson(expectedPlat), result);
        verify(platRepo, times(1)).getPlat("1");
    }

    @Test
    void getPlatJSON_ReturnsNullForNonExistingPlat() {
        // Configuration du mock
        when(platRepo.getPlat("nonExistingId")).thenReturn(null);

        // Exécution de la méthode testée
        String result = platService.getPlatJSON("nonExistingId");

        // Vérification
        assertNull(result);
        verify(platRepo, times(1)).getPlat("nonExistingId");
    }

    // Vous pouvez ajouter d'autres tests pour updatePlat, createPlat, et getPlatPrice en suivant un schéma similaire.

}
