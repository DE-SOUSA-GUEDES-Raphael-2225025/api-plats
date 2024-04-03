package api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Application;

/**
 * Configuration principale de l'application JAX-RS pour la gestion des plats via une API REST.
 * Cette classe étend {@link jakarta.ws.rs.core.Application} et configure le chemin de base de l'API
 * ainsi que la gestion de la connexion à la base de données pour l'accès aux données des plats.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class PlatApplication extends Application {

    /**
     * Crée et fournit une instance de {@link PlatRepositoryInterface} pour la connexion à la base de données.
     * Cette méthode est marquée avec {@link jakarta.enterprise.inject.Produces} pour indiquer qu'elle produit
     * une ressource injectable dans l'application, spécifiquement une connexion à une base de données MariaDB
     * pour accéder aux données des plats.
     *
     * @return Une instance de {@link PlatRepositoryInterface} connectée à la base de données des plats.
     */
    @Produces
    private PlatRepositoryInterface openDbConnection() {
        PlatRepositoryMariadb db = null;
        try {
            db = new PlatRepositoryMariadb("jdbc:mariadb://mysql-apiplats.alwaysdata.net/apiplats_db", "apiplats", "apiplats2345@");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Ferme la connexion à la base de données lorsqu'elle n'est plus nécessaire.
     * Utilise l'annotation {@link jakarta.enterprise.inject.Disposes} pour indiquer que cette méthode
     * doit être appelée pour disposer la ressource produite par la méthode {@code openDbConnection()}.
     *
     * @param platRepo L'instance de {@link PlatRepositoryInterface} à fermer.
     */
    private void closeDbConnection(@Disposes PlatRepositoryInterface platRepo) {
        platRepo.close();
    }
}
