package api;
import java.util.ArrayList;


import java.util.*;

/**
 * Interface d'accès aux données des utilisateurs
 */
public interface UserRepositoryInterface {

    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les utilisateurs
     */
    public void close();

    /**
     * Méthode retournant l'utilisateur dont l'identifiant est passée en paramètre
     * @param id identifiant de l'utilisateur recherché
     * @return un objet User représentant l'utilisateur recherché
     */
    public User getUser( String id );

    /**
     * Méthode retournant la liste des utilisateurs
     * @return une liste d'objets utilisateurs
     */
    public ArrayList<User> getAllUsers();

}