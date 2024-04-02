package api;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'accèder aux livres stockés dans une base de données Mariadb
 */
public class PlatRepositoryMariadb implements PlatRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;


    public PlatRepositoryMariadb(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }


    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public Plat getPlat(String id) {
        final String query = "SELECT * FROM Plats WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Plat(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Plat> getAllPlats() {
        List<Plat> plats = new ArrayList<>();
        final String query = "SELECT * FROM Plats";
        try (Statement st = dbConnection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                plats.add(new Plat(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (ArrayList<Plat>) plats;
    }


    @Override
    public boolean updatePlat(String id, String name, String description, Double price) {
        final String query = "UPDATE Plats SET name = ?, description = ?, price = ? WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setString(4, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean createPlat(String name, String description, Double price) {
        final String query = "INSERT INTO Plats (name, description, price) VALUES (?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deletePlat(String id) {
        final String query = "DELETE FROM Plats WHERE id = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
