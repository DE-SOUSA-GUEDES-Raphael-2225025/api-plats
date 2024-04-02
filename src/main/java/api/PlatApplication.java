package api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class PlatApplication extends Application {

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

    private void closeDbConnection(@Disposes PlatRepositoryInterface platRepo) {
        platRepo.close();
    }
}
