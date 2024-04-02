package api;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource API REST pour la gestion des plats.
 */
@Path("/plats")
@ApplicationScoped
public class PlatResource {


    private PlatService service;

    public PlatResource() {
    }

    @Inject
    public PlatResource(PlatRepositoryInterface platRepo) {
        this.service = new PlatService(platRepo);
    }

    public PlatResource(PlatService service) {
        this.service = service;
    }

    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatJSON();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getPlat(@PathParam("id") String id) {
        String result = service.getPlatJSON(id);
        if (result == null) throw new NotFoundException("Plat non trouvé pour l'ID : " + id);
        return result;
    }

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
