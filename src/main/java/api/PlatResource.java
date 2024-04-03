package api;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource REST pour la gestion des plats au sein d'une application JAX-RS.
 * Cette classe fournit des endpoints pour réaliser les opérations CRUD sur les plats :
 * création, lecture, mise à jour, et obtention du prix d'un plat spécifique.
 */
@Path("/plats")
@ApplicationScoped
public class PlatResource {

    /**
     * Le service utilisé pour effectuer les opérations logiques sur les plats.
     */
    private PlatService service;

    /**
     * Constructeur par défaut requis pour l'initialisation JAX-RS.
     */
    public PlatResource() {
    }

    /**
     * Constructeur avec injection de dépendance pour initialiser le service avec un repository spécifique.
     *
     * @param platRepo Le repository à utiliser pour accéder aux données des plats.
     */
    @Inject
    public PlatResource(PlatRepositoryInterface platRepo) {
        this.service = new PlatService(platRepo);
    }

    /**
     * Constructeur pour initialiser le service directement avec une instance de {@link PlatService}.
     *
     * @param service L'instance de {@link PlatService} à utiliser.
     */
    public PlatResource(PlatService service) {
        this.service = service;
    }

    /**
     * Obtient tous les plats disponibles.
     *
     * @return Une chaîne JSON représentant tous les plats.
     */
    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatJSON();
    }

    /**
     * Obtient les informations d'un plat spécifique par son identifiant.
     *
     * @param id L'identifiant du plat recherché.
     * @return Une chaîne JSON représentant le plat.
     * @throws NotFoundException Si aucun plat avec l'identifiant fourni n'est trouvé.
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getPlat(@PathParam("id") String id) {
        String result = service.getPlatJSON(id);
        if (result == null) throw new NotFoundException("Plat non trouvé pour l'ID : " + id);
        return result;
    }

    /**
     * Met à jour un plat existant.
     *
     * @param id L'identifiant du plat à mettre à jour.
     * @param plat Les nouvelles informations du plat.
     * @return Une réponse indiquant le succès de la mise à jour.
     * @throws NotFoundException Si le plat à mettre à jour n'est pas trouvé.
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updatePlat(@PathParam("id") String id, Plat plat) {
        boolean updated = service.updatePlat(id, plat);
        if (!updated) {
            throw new NotFoundException("Plat non trouvé pour mise à jour avec l'ID : " + id);
        } else {
            return Response.ok("Plat mis à jour avec succès").build();
        }
    }

    /**
     * Crée un nouveau plat.
     *
     * @param platDTO Les données du nouveau plat à créer.
     * @return Une réponse indiquant le succès ou l'échec de la création.
     */
    @PUT
    @Path("/create")
    @Consumes("application/json")
    public Response createPlat(PlatDTO platDTO) {
        boolean created = service.createPlat(platDTO.getName(), platDTO.getDescription(), platDTO.getPrice());
        if (!created) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de créer le plat").build();
        } else {
            return Response.ok("Plat créé avec succès").build();
        }
    }

    /**
     * Obtient le prix d'un plat spécifique par son identifiant.
     *
     * @param id L'identifiant du plat.
     * @return Une réponse JSON contenant le prix du plat.
     * @throws NotFoundException Si aucun plat avec l'identifiant fourni n'est trouvé.
     */
    @GET
    @Path("price/{id}/")
    @Produces("application/json")
    public Response getPlatPrice(@PathParam("id") String id) {
        Double price = service.getPlatPrice(id);
        if (price == null) {
            throw new NotFoundException("Plat non trouvé pour l'ID : " + id);
        }
        return Response.ok("{\"price\": " + price + "}").build();
    }
}
