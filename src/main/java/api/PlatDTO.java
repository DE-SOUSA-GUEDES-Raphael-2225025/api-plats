package api;

import java.util.List;

public class PlatDTO {

    private String name;
    private String description;
    private Double price;

    /**
     * Constructeur par défaut
     */
    public PlatDTO() {}

    /**
     * Méthode permettant d'obtenir le nom du plat.
     * @return String nom du plat.
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant de définir le nom du plat.
     * @param name String nom du plat à définir.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode permettant d'obtenir la description du plat.
     * @return String description du plat.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Méthode permettant de définir la description du plat.
     * @param description String description du plat à définir.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode permettant d'obtenir le prix du plat.
     * @return Double prix du plat.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Méthode permettant de définir le prix du plat.
     * @param price Double prix du plat à définir.
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}
