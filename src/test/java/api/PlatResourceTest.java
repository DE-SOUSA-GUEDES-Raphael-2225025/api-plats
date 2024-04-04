package api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PlatResourceTest {

    @Mock
    private PlatService platService;

    @InjectMocks
    private PlatResource platResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlats_ReturnsAllPlatsAsJson() {
        String expectedJson = "[{\"id\":\"1\",\"name\":\"Plat1\"}]";
        when(platService.getAllPlatJSON()).thenReturn(expectedJson);

        String result = platResource.getAllPlats();

        assertEquals(expectedJson, result);
        verify(platService, times(1)).getAllPlatJSON();
    }

    @Test
    void getPlat_ReturnsPlatJson_WhenPlatExists() {
        String platId = "1";
        String expectedJson = "{\"id\":\"1\",\"name\":\"Plat1\"}";
        when(platService.getPlatJSON(platId)).thenReturn(expectedJson);

        String result = platResource.getPlat(platId);

        assertEquals(expectedJson, result);
        verify(platService, times(1)).getPlatJSON(platId);
    }

    @Test
    void getPlat_ReturnsEmptyJson_WhenPlatDoesNotExist() {
        String platId = "nonexistent";
        when(platService.getPlatJSON(platId)).thenReturn("{}");

        String result = platResource.getPlat(platId);

        assertEquals("{}", result);
        verify(platService, times(1)).getPlatJSON(platId);
    }
}
