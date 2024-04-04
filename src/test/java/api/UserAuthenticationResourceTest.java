package api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

class UserAuthenticationResourceTest {

    @Mock
    private UserRepositoryInterface userRepo;

    @Mock
    private ContainerRequestContext requestContext;

    @InjectMocks
    private UserAuthenticationResource authResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authResource = new UserAuthenticationResource(userRepo);
    }

    @Test
    void authenticate_ReturnsUnauthorized_WhenNoAuthHeader() throws UnsupportedEncodingException {
        when(requestContext.getHeaderString("Authorization")).thenReturn(null);

        Response response = authResource.authenticate(requestContext);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus(), "Doit retourner un statut non autorisé.");
    }

    @Test
    void authenticate_ReturnsUserJson_WhenCredentialsAreValid() throws UnsupportedEncodingException {
        String validCredentials = "Basic " + Base64.getEncoder().encodeToString("validUser:validPass".getBytes("UTF-8"));
        when(requestContext.getHeaderString("Authorization")).thenReturn(validCredentials);
        when(userRepo.getUser("validUser")).thenReturn(new User("validUser", "User Name", "validPass", "Surname"));

        Response response = authResource.authenticate(requestContext);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Doit retourner un statut OK.");
        assertNotNull(response.getEntity(), "Doit retourner les informations de l'utilisateur en JSON.");
    }

    @Test
    void authenticate_ReturnsUnauthorized_WhenCredentialsAreInvalid() throws UnsupportedEncodingException {
        String invalidCredentials = "Basic " + Base64.getEncoder().encodeToString("user:wrongPass".getBytes("UTF-8"));
        when(requestContext.getHeaderString("Authorization")).thenReturn(invalidCredentials);
        when(userRepo.getUser("user")).thenReturn(new User("user", "User Name", "correctPass", "Surname"));

        Response response = authResource.authenticate(requestContext);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus(), "Doit retourner un statut non autorisé pour des identifiants invalides.");
    }
}
