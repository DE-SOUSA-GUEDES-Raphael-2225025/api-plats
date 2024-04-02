package api;


import java.util.*;

/**
 * Interface d'accès aux données des livres
 */
public interface PlatRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les livres
     */
     void close();

    /**
     * Méthode retournant le livre dont la référence est passée en paramètre
     * @param id identifiant du livre recherché
     * @return un objet Book représentant le livre recherché
     */

   Plat getPlat(String id)
    ;


    /**
     * Méthode retournant la liste des livres
     * @return une liste d'objets livres
     */
    ArrayList<Plat> getAllPlats();


    boolean updatePlat(String id, String name, String description, Double price);

    boolean createPlat(String name, String description, Double price);

    boolean deletePlat(String id);
}
