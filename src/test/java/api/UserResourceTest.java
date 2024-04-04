package api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserResourceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ReturnsAllUsersAsJson() {
        String expectedJson = "[{\"login\":\"user1\",\"name\":\"User One\"}]";
        when(userService.getAllUsersJSON()).thenReturn(expectedJson);

        String result = userResource.getAllUsers();

        assertEquals(expectedJson, result);
        verify(userService, times(1)).getAllUsersJSON();
    }

    @Test
    void getUser_ReturnsUserJson_WhenUserExists() {
        String login = "user1";
        String expectedJson = "{\"login\":\"user1\",\"name\":\"User One\"}";
        when(userService.getUserJSON(login)).thenReturn(expectedJson);

        String result = userResource.getUser(login);

        assertEquals(expectedJson, result);
        verify(userService, times(1)).getUserJSON(login);
    }

    @Test
    void getUser_ReturnsEmptyJson_WhenUserDoesNotExist() {
        String login = "nonexistent";
        when(userService.getUserJSON(login)).thenReturn("{}");

        String result = userResource.getUser(login);

        assertEquals("{}", result);
        verify(userService, times(1)).getUserJSON(login);
    }
}
