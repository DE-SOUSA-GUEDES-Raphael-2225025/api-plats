package api;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Service pour gérer les plats et fournir leurs informations au format JSON.
 * Ce service sert de couche intermédiaire entre les ressources REST et la couche d'accès aux données,
 * permettant ainsi une meilleure abstraction et facilitant la maintenance.
 */
public class PlatService {

    /**
     * Référence vers le repository des plats pour accéder et manipuler les données des plats.
     */
    private PlatRepositoryInterface platRepo;

    /**
     * Constructeur avec injection de dépendance pour initialiser le repository des plats.
     * @param platRepo Le repository des plats injecté.
     */
    @Inject
    public PlatService(PlatRepositoryInterface platRepo) {
        this.platRepo = platRepo;
    }

    /**
     * Récupère tous les plats disponibles et les sérialise au format JSON.
     * @return Une chaîne de caractères JSON représentant la liste des plats.
     */
    public String getAllPlatJSON() {
        ArrayList<Plat> allPlat = platRepo.getAllPlats();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allPlat);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Récupère les informations d'un plat spécifique par son identifiant et les sérialise au format JSON.
     * @param id L'identifiant du plat à rechercher.
     * @return Une chaîne de caractères JSON représentant le plat, ou null si le plat n'est pas trouvé.
     */
    public String getPlatJSON(String id) {
        Plat myPlat = platRepo.getPlat(id);
        if (myPlat != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                return jsonb.toJson(myPlat);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Met à jour les informations d'un plat spécifique.
     * @param id L'identifiant du plat à mettre à jour.
     * @param plat Les nouvelles données du plat.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean updatePlat(String id, Plat plat) {
        return platRepo.updatePlat(id, plat.getName(), plat.getDescription(), plat.getPrice());
    }

    public boolean createPlat(String name, String description, Double price){
        return platRepo.createPlat(name, description, price);
    }

    public Double getPlatPrice(String id) {
        Plat plat = platRepo.findPlatById(id);
        if (plat != null) {
            return plat.getPrice();
        }
        return null;
    }

}
