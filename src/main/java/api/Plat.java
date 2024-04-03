package api;

/**
 * Représente un plat dans un système de gestion de menu de restaurant.
 * Cette classe fournit des informations de base sur un plat, telles que son identifiant,
 * son nom, sa description et son prix.
 */
public class Plat {

    /**
     * Identifiant unique du plat.
     */
    private String id;

    /**
     * Nom du plat.
     */
    private String name;

    /**
     * Description du plat.
     */
    private String description;

    /**
     * Prix du plat.
     */
    private Double price;

    /**
     * Constructeur pour créer une nouvelle instance de Plat.
     *
     * @param id L'identifiant unique du plat.
     * @param name Le nom du plat.
     * @param description Une brève description du plat.
     * @param price Le prix du plat.
     */
    public Plat(String id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Retourne l'identifiant du plat.
     *
     * @return L'identifiant du plat.
     */
    public String getId() {
        return id;
    }

    /**
     * Retourne le nom du plat.
     *
     * @return Le nom du plat.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la description du plat.
     *
     * @return La description du plat.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le prix du plat.
     *
     * @return Le prix du plat.
     */
    public Double getPrice() {
        return price;
    }
}

